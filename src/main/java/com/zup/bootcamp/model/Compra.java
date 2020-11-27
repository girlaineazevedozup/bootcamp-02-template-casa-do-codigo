package com.zup.bootcamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

@Entity
public class Compra {

    @Deprecated
    public Compra(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @NotBlank
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

    @NotNull
    @ManyToOne
    private Pais pais;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    @ManyToOne
    private Estado estado;

    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @Embedded
    @JsonProperty("cupom_aplicado")
    private CupomAplicado cupomAplicado;

    public Compra(@Email @NotBlank String email, @NotBlank String nome,
                  @NotBlank String sobrenome, @NotBlank String documento,
                  @NotBlank String endereco, @NotBlank String complemento,
                  @NotNull Pais pais, @NotBlank String telefone, @NotBlank String cep,
                  Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    public void setEstado(Estado estado) {
        Assert.notNull(pais, "Estado não pode ser associado a um país nulo");
        Assert.isTrue(estado.pertencePais(pais), "Este estado não pertence ao País associado a compra.");
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", pais=" + pais +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", estado=" + estado +
                ", pedido=" + pedido +
                ", cupom_aplicado=" + cupomAplicado +
                '}';
    }

    public void aplicaCupom(Cupom cupom) {
        if(cupom.isValido() && cupomAplicado == null)
            this.cupomAplicado = new CupomAplicado(cupom);
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupom inválido.");
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public Pais getPais() {
        return pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Estado getEstado() {
        return estado;
    }

    public boolean temEstado() {
        return Optional.ofNullable(estado).isPresent();
    }

    public boolean temCupomAplicado() {
        return Optional.ofNullable(cupomAplicado).isPresent();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }
}
