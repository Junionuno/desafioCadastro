package main.Java;

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
        return  "1 - " + name + '\n' +
                "2 - " + tipoPet + '\n' +
                "3 - " + sexo + '\n' +
                "4 - " + endereco + '\n' +
                "5 - " + idade + " anos"  + '\n' +
                "6 - " + peso + "kg" + '\n'+
                "7 - " + raca ;
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
