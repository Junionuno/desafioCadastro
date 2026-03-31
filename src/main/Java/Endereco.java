package main.Java;

public class Endereco {
    public static String numero;
    private String cidade;
    private String rua;

    public Endereco(String cidade, String rua) {
        this.cidade = cidade;
        this.rua = rua;
    }

    @Override
    public String toString() {
        return  "Rua " + rua + ", " + numero + ", " + cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) { Endereco.numero = numero;}

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
