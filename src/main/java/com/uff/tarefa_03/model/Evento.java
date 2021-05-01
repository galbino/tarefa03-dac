package com.uff.tarefa_03.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="Evento")
public class Evento {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String sigla;
    private String area;
    private String instituicao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", orphanRemoval = true)
    private List<Edicao> edicoes;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }

    public void setEdicoes(List<Edicao> edicao) {
        this.edicoes = edicao;
    }
}
