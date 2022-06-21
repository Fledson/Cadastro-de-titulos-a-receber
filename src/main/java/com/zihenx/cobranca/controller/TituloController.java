package com.zihenx.cobranca.controller;

import com.zihenx.cobranca.model.StatusTitulo;
import com.zihenx.cobranca.model.Titulo;
import com.zihenx.cobranca.repository.filter.TituloFilter;
import com.zihenx.cobranca.service.CadastroTituloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

/**
 * Classe controladora dos titulos
 * 
 * @Controller => controller no spring deve ter a anotação controller
 */

@Controller
@RequestMapping("/titulos")
public class TituloController {

    // Anotação @Autowired fará como se ele se auto instanciace como objeto
    @Autowired
    private CadastroTituloService cadastroTituloService;

    private final String CADASTRO_VIEW = "CadastroTitulo";

    /**
     * Função que retorna minha view (Cadastro de titulos)
     * 
     * @GetMapping => serve para fazer o mapeamento da rota
     * @return Retorna a view
     */
    @GetMapping("/novo")
    public ModelAndView novo(Titulo titulo) {
        // Instanciando uma ModelAndView de cadastro, passando minha view de cadastro
        ModelAndView view = new ModelAndView(CADASTRO_VIEW);
        // Adicionando dentro um novo titulo
        view.addObject(new Titulo());
        // retornando o modelo de visualização
        return view;
    }

    @GetMapping("{codigo}")
    public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
        // Instanciando a view
        ModelAndView view = new ModelAndView(CADASTRO_VIEW);
        // adicionando a view o meu titulo encontrado
        view.addObject(titulo);
        // redirecionando a pagina para a pagina de cadastro de novo titulo com meu
        // titulo
        return view;
    }

    @GetMapping
    public ModelAndView pesquisaTitulo(@ModelAttribute("filtro") TituloFilter filtro) {

        List<Titulo> listaTitulos = cadastroTituloService.filtrar(filtro);

        // instanciando um novo modelo de view (passando minha view como parametro)
        ModelAndView view = new ModelAndView("PesquisaTitulo");

        // adicionando um objeto para ser renderizado no front
        view.addObject("titulos", listaTitulos);

        // retornando a view
        return view;

    }

    @PostMapping
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes atributes) {
        return cadastroTituloService.salvar(titulo, errors, atributes);
    }

    @DeleteMapping("{codigo}")
    public String excluir(@PathVariable Long codigo, RedirectAttributes atributes) {
        return cadastroTituloService.excluirTitulo(codigo, atributes);
    }

    @PutMapping("/{codigo}/receber")
    public @ResponseBody String receber(@PathVariable("codigo") Long codigo) {
        return cadastroTituloService.atualizarStatusTitulo(codigo);
    }

    // criando um atributo para todas os modelAndView
    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }

}
