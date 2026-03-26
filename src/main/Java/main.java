package main.Java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        List<Pet> pets = new ArrayList<>();
        switch (numMenu){
            case 1:
                sc.nextLine();
                StringBuilder dadosParaSalvar = new StringBuilder();
                System.out.println("Cadastre um pet: ");
                Pet pet = new Pet();
                try (FileReader fr = new FileReader(file);
                     BufferedReader br = new BufferedReader(fr)){
                    String question;
                    int count = 0;
                    while((question = br.readLine()) != null){
                        if(question.trim().isEmpty()) continue;
                        System.out.println(question);
                        String answer = sc.nextLine();
                        count ++;
                        switch (count){
                            case 1: pet.setName(answer); break;
                            case 2: pet.setTipoPet(answer); break;
                            case 3: pet.setSexo(answer); break;
                            case 4:
                                System.out.println("Numero: ");
                                int numero = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Cidade: ");
                                String cidade = sc.nextLine();
                                System.out.println("Rua: ");
                                String rua = sc.nextLine();
                                Endereco endereco = new Endereco(numero, cidade, rua);
                                pet.setEndereco(endereco);
                                break;
                            case 5: pet.setIdade(Integer.parseInt(answer)); break;
                            case 6: pet.setPeso(Double.parseDouble(answer)); break;
                            case 7: pet.setRaca(answer);
                        }
                    }
                    pets.add(pet);
                    dadosParaSalvar.append(pet).append("\n");

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("pets_cadastrados.txt"))){
                        writer.write(dadosParaSalvar.toString());
                        writer.newLine();
                        System.out.println("Pet cadastrado com sucesso!");
                    }
                }catch (IOException e){
                    System.out.println("Erro no processo: " + e.getMessage());
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
