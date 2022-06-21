package com.zihenx.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zihenx.cobranca.model.StatusTitulo;
import com.zihenx.cobranca.model.Titulo;
import com.zihenx.cobranca.repository.Titulos;
import com.zihenx.cobranca.repository.filter.TituloFilter;

@Service
public class CadastroTituloService {

  @Autowired
  private Titulos titulos;
  private final String CADASTRO_VIEW = "CadastroTitulo";

  public String salvar(Titulo titulo, Errors errors, RedirectAttributes atributes) {
    if (errors.hasErrors()) {
      // se der erro ele recarrega a pagina
      return CADASTRO_VIEW;
    }

    try {
      // salvando no banco de dados
      titulos.save(titulo);

      // colocando para passar um atributo de redirecionamento
      atributes.addFlashAttribute("mensagem", "Titulo cadastrado com sucesso");

      // fazendo um redirecionamento para limpar os campos
      return "redirect:/titulos/novo";
    } catch (DataIntegrityViolationException error) {
      errors.rejectValue("dataVencimento", null, "Formato de data inválido");
      return CADASTRO_VIEW;
    }
  }

  public List<Titulo> filtrar(TituloFilter filtro) {
    if (filtro.getDescricao() == null) {
      return pesquisarTitulos();
    } else {
      return pesquisarTitulos(filtro);
    }
  }

  public List<Titulo> pesquisarTitulos() {
    return titulos.findAll();
  }

  public List<Titulo> pesquisarTitulos(TituloFilter filtro) {
    return titulos.findByDescricaoContaining(filtro.getDescricao());
  }

  public String excluirTitulo(Long codigo, RedirectAttributes atributes) {
    titulos.deleteById(codigo);

    // colocando para passar um atributo de redirecionamento
    atributes.addFlashAttribute("mensagem", "Titulo excluido com sucesso");

    return "redirect:/titulos";
  }

  public String atualizarStatusTitulo(Long codigo) {
    // Recuperando o titulo
    Titulo titulo = titulos.findById(codigo).get();
    // Atualizando para RECEBIDO
    titulo.setStatus(StatusTitulo.RECEBIDO);
    // Salvando a alteração
    titulos.save(titulo);
    // retornando o valor recebido
    return StatusTitulo.RECEBIDO.getDescricao();
  }

}
