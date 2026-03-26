package main.Java;

public class Pet {
    private String name;
    private String lastName;
    private String tipoPet;
    private String sexo;
    private Endereco endereco;
    private int idade;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTipoPet() {
        return tipoPet;
    }

    public void setTipoPet(String tipoPet) {
        this.tipoPet = tipoPet;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
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
