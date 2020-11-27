package com.zup.bootcamp.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zup.bootcamp.infrastructure.CupomRepository;
import com.zup.bootcamp.model.*;
import com.zup.bootcamp.validation.annotation.ExistsValue;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

public class CompraRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    @ExistsValue(domainClass = Pais.class, fieldName = "id")
    @JsonProperty("id_pais")
    private Long idPais;

    @ExistsValue(domainClass = Estado.class, fieldName = "id")
    @JsonProperty("id_estado")
    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @Valid
    @NotNull
    private PedidoRequest pedido;

    @JsonProperty("codigo_cupom")
    @ExistsValue(domainClass = Cupom.class, fieldName = "codigo")
    private String codigoCupom;

    public CompraRequest(@Email @NotBlank String email, @NotBlank String nome,
                         @NotBlank String sobrenome, @NotBlank String documento,
                         @NotBlank String endereco, @NotBlank String complemento,
                         @NotBlank String cidade, @NotNull Long idPais,
                         @NotBlank String telefone, @NotBlank String cep,
                         @Valid @NotNull PedidoRequest pedido) {
        super();
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "CompraRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", pedido=" + pedido +
                '}';
    }

    public boolean documentoValido() {
        Assert.hasLength(documento, "Documento vazio.");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) ||
                cnpjValidator.isValid(documento, null);
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public PedidoRequest getPedido() {
        return pedido;
    }

    public Compra toModel(EntityManager manager, CupomRepository cupomRepository){
        @NotNull Pais pais = manager.find(Pais.class, idPais);

        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, pais,
                telefone, cep, funcaoCriacaoPedido);

        if(temEstado())
            compra.setEstado(manager.find(Estado.class, idEstado));

        if(temCupomDesconto())
            compra.aplicaCupom(cupomRepository.findByCodigo(codigoCupom));

        return compra;
    }

    public boolean temEstado() {
        return Optional.ofNullable(idEstado).isPresent();
    }

    public boolean temCupomDesconto() {
        return Optional.ofNullable(codigoCupom).isPresent();
    }

    public Optional<String> getCodigoCupom(){
        return Optional.ofNullable(codigoCupom);
    }
}
