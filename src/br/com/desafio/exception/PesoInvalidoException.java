package br.com.desafio.exception;

public class PesoInvalidoException extends Exception {
    public PesoInvalidoException(){
        super("Peso inválido!");
    }
    public PesoInvalidoException(String message) {
        super(message);
    }
}
