package main.Java;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File file = new File("formulario.txt");
        boolean fileCreated = file.createNewFile();

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        int numMenu;
        System.out.println("1. Cadastrar um novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6. sair");
        numMenu = sc.nextInt();

        switch (numMenu){
            case 1:
                System.out.println("Cadastre um pet: ");
                try (FileReader fr = new FileReader(file);
                     BufferedReader br = new BufferedReader(fr)){
                    String line;
                    while((line = br.readLine()) != null){
                        System.out.println(line);
                        try(BufferedWriter writer = new BufferedWriter(new FileWriter("arquivo.txt", true))){
                            writer.write(sc.nextLine());
                        }catch (IOException e){
                            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Alterando os dados do pet: ");
                break;
            case 6:
                System.out.println("Saindo...");
                break;
            default:
                while (numMenu > 6 || numMenu < 6){
                    System.out.println("1. Cadastrar um novo pet");
                    System.out.println("2. Alterar os dados do pet cadastrado");
                    System.out.println("3. Deletar um pet cadastrado");
                    System.out.println("4. Listar todos os pets cadastrados");
                    System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
                    System.out.println("6. sair");
                    numMenu = sc.nextInt();
                }
                break;
        }

        sc.close();
    }
}
