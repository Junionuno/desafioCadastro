package br.com.desafio.model;

import br.com.desafio.enums.SexoPet;
import br.com.desafio.enums.TipoPet;

public class Pet {
    private String name;
    private TipoPet tipoPet;
    private SexoPet sexo;
    private Endereco endereco;
    private double idade;
    private double peso;
    private String raca;

    @Override
    public String toString() {
        return  " - " + name + '\n' +
                " - " + tipoPet + '\n' +
                " - " + sexo + '\n' +
                " - " + endereco + '\n' +
                " - " + idade + " anos"  + '\n' +
                " - " + peso + "kg" + '\n'+
                " - " + raca ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TipoPet getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(TipoPet tipoPet) {
        this.tipoPet = tipoPet;
    }

    public SexoPet getSexo() {
        return sexo;
    }

    public void setSexo(SexoPet sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
