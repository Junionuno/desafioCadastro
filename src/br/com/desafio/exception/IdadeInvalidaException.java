package br.com.desafio.exception;

public class IdadeInvalidaException extends  Exception{
    public IdadeInvalidaException(){
        super("Idade inválida!");
    }
    public IdadeInvalidaException(String message) {
        super(message);
    }
}
