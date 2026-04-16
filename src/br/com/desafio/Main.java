package br.com.desafio;
import br.com.desafio.Service.PetService;
import br.com.desafio.model.Pet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.InputHelper.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("src/data/formulario.txt");
        File path = new File("src/data/petsCadastrados");

        int numMenu = 0;
        while (numMenu !=5) {
            System.out.println("\n1. Cadastrar um novo pet");
            System.out.println("2. Listar pets por algum critério");
            System.out.println("3. Alterar os dados do pet cadastrado");
            System.out.println("4. Deletar um pet cadastrado");
            System.out.println("5. Sair\n");
            numMenu = sc.nextInt();

            List<Pet> pets = new ArrayList<>();
            PetService service = new PetService();

            switch (numMenu) {
                case 1:
                    System.out.println("\n---Cadastro de Pets---");
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String question;
                        int count = 1;
                        Pet pet = new Pet();

                        while ((question = br.readLine()) != null) {

                            if (question.trim().isEmpty()) continue;
                            System.out.println(question);
                            String answer = sc.nextLine();

                            switch (count) {
                                case 1:
                                    pet.setName(lerTextoValido());
                                    break;
                                case 2:
                                    pet.setTipoPet(lerTipoPet(answer));
                                    break;
                                case 3:
                                    pet.setSexo(lerSexoPet(answer));
                                    break;
                                case 4:
                                    pet.setEndereco(lerEndereco());
                                    break;
                                case 5:
                                    pet.setIdade(lerIdade(answer));
                                    break;
                                case 6:
                                    pet.setPeso(validarPeso(answer));
                                    break;
                                case 7:
                                    pet.setRaca(validarRaca(answer));
                            }
                            count++;
                        }
                        service.salvarPet(pet);
                    }catch (IOException e){
                        System.out.println("Erro ao ler o formulário: " + e.getMessage());
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("\n---Busca de Pets---");
                    System.out.println("Tipo cachorro (Cachorro/Gato): ");
                    String tipo = sc.nextLine();
                    while (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")){
                        System.out.println("\nErro! escolha entre cachorro ou gato!");
                        tipo = sc.nextLine();
                    }

                    System.out.println("\nDigite o valor que procura (Nome, raça ou idade): ");
                    String valorBusca = sc.nextLine();

                    service.buscarEApresentarPets(tipo, "", valorBusca);

                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("\n---Alteração de Cadastro---");
                    System.out.println("Tipo cachorro (Cachorro/Gato): ");
                    String tipoAltera = sc.nextLine();
                    while (!tipoAltera.equalsIgnoreCase("cachorro") && !tipoAltera.equalsIgnoreCase("gato")){
                        System.out.println("\nErro! escolha entre cachorro ou gato!");
                        tipoAltera = sc.nextLine();
                    }

                    System.out.println("\nDigite o valor que procura (Nome, raça ou idade): ");
                    String valorBuscaAltera = sc.nextLine();

                    service.alterarPet(tipoAltera, valorBuscaAltera);
                case 5:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    while (numMenu > 5 || numMenu < 5) {
                        System.out.println("\n1. Cadastrar um novo pet");
                        System.out.println("2. Listar pets por algum critério");
                        System.out.println("3. Alterar os dados do pet cadastrado");
                        System.out.println("4. Deletar um pet cadastrado");
                        System.out.println("5. Sair\n");
                        numMenu = sc.nextInt();
                    }
            }
        }

        sc.close();
    }
}
