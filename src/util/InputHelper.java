package util;

import br.com.desafio.enums.SexoPet;
import br.com.desafio.enums.TipoPet;
import br.com.desafio.exception.IdadeInvalidaException;
import br.com.desafio.exception.PesoInvalidoException;
import br.com.desafio.model.Endereco;

import java.util.Scanner;

public class InputHelper {
    private static Scanner sc = new Scanner(System.in);

    public static String lerTextoValido(){
        String mensagem = sc.nextLine();
        while (!mensagem.matches("[a-zA-Z\\s]+[a-zA-Z\\s]+")){
            System.out.println("Erro! Formato inválido. Digite novamente: ");
            mensagem = sc.nextLine();
        }
        return mensagem;
    }

    public static double lerDouble(String mensagem){
        while (true){
            System.out.println(mensagem);
            String entrada = sc.nextLine().replace(",", ".");
            if (entrada.matches("(\\d+([.,]\\d+)?)")){
                return Double.parseDouble(entrada);
            }
            System.out.println("Erro: Digite apenas números! ");
        }
    }

    public static TipoPet lerTipoPet(String valor) {
        while (!valor.equalsIgnoreCase("cachorro") && !valor.equalsIgnoreCase("gato")){
                System.out.println("Erro! Escolha entre cachorro ou gato.");
                valor = sc.nextLine();
        }
        if (valor.equalsIgnoreCase("cachorro")){
            return TipoPet.Cachorro;
        }else if (valor.equalsIgnoreCase("gato")){
            return TipoPet.Gato;
        }
        return null;
    }

    public static SexoPet lerSexoPet(String valor){
        if (valor.equalsIgnoreCase("macho")){
            return SexoPet.Macho;
        }else if(valor.equalsIgnoreCase("femea")){
            return SexoPet.Femea;
        }else{
            System.out.println("Erro! Escolha entre macho ou femea.");
        }
        return null;
    }

    public static Endereco lerEndereco(){
        System.out.println("Número: (opcional)");
        String numero = sc.nextLine();
        if (!numero.isEmpty()){
            Endereco.numero = numero;
        }else {
            Endereco.numero = "Número não informado";
        }
        System.out.println("Cidade: ");
        String cidade = sc.nextLine();
        if (cidade.isEmpty()){
            System.out.println("Digite o nome da cidade: ");
            cidade = sc.nextLine();
        }

        System.out.println("Rua: ");
        String rua = sc.nextLine();

        return new Endereco(cidade, rua);
    }

    public static double lerIdade(String valor){
        while(true){
            if(!valor.matches("(\\d+([.,]\\d+)?)")){
                System.out.println("Erro: Digite apenas números.");
                valor = sc.nextLine();
                continue;
            }

            try {
                double idade = Double.parseDouble(valor.replace(",", "."));
                    if (idade > 20){
                        throw new IdadeInvalidaException();
                    }
                    return idade;
            }catch (IdadeInvalidaException e) {
                System.out.println(e.getMessage() + " Tente novamente.");
                valor = sc.nextLine();
            }
        }
    }

    public static double validarPeso(String valor){
        while(true){
            if (!valor.matches("(\\d+([.,]\\d+)?)")){
                System.out.println("Erro: Digite apenas números.");
                valor = sc.nextLine();
                continue;
            }

            try {
                double peso = Double.parseDouble(valor);
                if (peso > 60 || peso < 0.5){
                    throw new PesoInvalidoException();
                }
                return peso;
            }catch (PesoInvalidoException e){
                System.out.println(e.getMessage() + " Tente novamente.");
                valor = sc.nextLine();
            }
        }
    }

    public static String validarRaca(String valor){
        while(!valor.matches("^[a-zA-ZÀ-ÿ ]+$")){
            System.out.println("Erro! Digite novamente: ");
            valor = sc.nextLine();
        }
        return valor;
    }

}
