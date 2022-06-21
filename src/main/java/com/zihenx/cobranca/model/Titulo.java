package com.zihenx.cobranca.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Modelo de Titulo
 * 
 * @Entity => declara meu model como uma entidade JPA
 */
@Entity
public class Titulo {
    /**
     * @ID => define a chave primaria
     * @GeneratedValue define como esse id será tratado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotBlank(message = "Descrição não pode ser vazia")
    @Size(max = 100, message = "A descrição não pode ser maior que 100 caracteres")
    private String descricao;

    /**
     * Tratando para trabalhar apenas com data, sem horas
     * DateTimeFormat(pattern = "dd/MM/yyyy") para formatar data
     */
    @NotNull(message = "Data não pode ser vazia")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;

    /**
     * formatando o numero
     */
    @NotNull(message = "Valor não pode ser vazio")
    @DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
    @DecimalMax(value = "9999999999.99", message = "Valor não pode ser maior que 9.999.999.999,99")
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusTitulo status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusTitulo getStatus() {
        return status;
    }

    public void setStatus(StatusTitulo status) {
        this.status = status;
    }

    /**
     * Verifica se meu status é igual a pendente
     * 
     * @return retorna um boolean (true para pendente)
     */
    public boolean isPendente() {
        return StatusTitulo.PENDENTE.equals(this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Titulo))
            return false;
        Titulo titulo = (Titulo) o;
        return getCodigo().equals(titulo.getCodigo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodigo());
    }

}
