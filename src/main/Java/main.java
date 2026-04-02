package main.Java;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class main {
    public static void main(String[] args) throws IOException, IdadeInvalidaException {
        Scanner sc = new Scanner(System.in);
        File file = new File("formulario.txt");
        boolean fileCreated = file.createNewFile();

        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            System.out.println();
        }catch (IOException e){
            e.printStackTrace();
        }

        int numMenu;
        System.out.println("1. Cadastrar um novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6. sair\n");
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
                    int count = 1;
                    while((question = br.readLine()) != null){
                        if(question.trim().isEmpty()) continue;
                        System.out.println(question);
                        String answer = sc.nextLine();
                        switch (count){
                            case 1:
                                String regex = ("^[a-zA-ZÀ-ÿ ]+$");
                                String tentativa = answer;
                                while(!tentativa.matches(regex)){
                                    System.out.println("Erro! Digite novamente: ");
                                    tentativa = sc.nextLine();
                                }
                                pet.setName(tentativa);
                                break;
                            case 2:
                            if (answer.equalsIgnoreCase("cachorro")){
                                pet.setTipoPet(TipoPet.Cachorro);
                            } else if (answer.equalsIgnoreCase("gato")) {
                                pet.setTipoPet(TipoPet.Gato);
                            } break;
                            case 3:
                            if (answer.equalsIgnoreCase("femea")){
                                pet.setSexo(SexoPet.Femea);
                            } else if (answer.equalsIgnoreCase("macho")) {
                                pet.setSexo(SexoPet.Macho);
                            } break;
                            case 4:
                                System.out.println("Numero: (opcional)");
                                String numero = sc.nextLine();
                                if (!numero.isEmpty()){
                                    Endereco.numero = numero;
                                }else {
                                    Endereco.numero = "Número não informado";
                                }
                                System.out.println("Cidade: ");
                                String cidade = sc.nextLine();
                                System.out.println("Rua: ");
                                String rua = sc.nextLine();
                                Endereco endereco = new Endereco(cidade, rua);
                                pet.setEndereco(endereco);
                                break;
                            case 5:
                                String tentativaIdade = answer;
                                while (true){
                                    if (!tentativaIdade.matches("(\\d+([.,]\\d+)?)")){
                                        System.out.println("Erro: Digite apenas números!");
                                        tentativaIdade = sc.nextLine();
                                        continue;
                                    }

                                    try {
                                        double idade = Double.parseDouble(tentativaIdade.replace(",", "."));
                                        if (idade > 20){
                                            throw new IdadeInvalidaException();
                                        }
                                        pet.setIdade(idade);
                                        break;
                                    }catch (IdadeInvalidaException e){
                                        System.out.print(e.getMessage());
                                        System.out.println(" Tente novamente: ");
                                        tentativaIdade = sc.nextLine();
                                    }
                                }
                                break;
                            case 6:
                                String tentativaPeso = answer;
                                while (true){
                                    if (!tentativaPeso.matches("(\\d+([.,]\\d+)?)")){
                                        System.out.println("Erro: Digite apenas números!");
                                        tentativaPeso = sc.nextLine();
                                        continue;
                                    }
                                    try {
                                        double peso = Double.parseDouble(tentativaPeso.replace("," , "."));
                                        if (peso > 60 || peso < 0.5){
                                            throw new PesoInvalidoException();
                                        }
                                        pet.setPeso(peso);
                                        break;
                                    } catch (PesoInvalidoException e) {
                                        System.out.print(e.getMessage());
                                        System.out.println(" Tente novamente: ");
                                        tentativaPeso = sc.nextLine();
                                    }
                                } break;
                            case 7:
                                String tentativaRaca = answer;
                                while(!tentativaRaca.matches("^[a-zA-ZÀ-ÿ ]+$")){
                                    System.out.println("Erro! Digite novamente: ");
                                    tentativaRaca = sc.nextLine();
                                }
                                pet.setRaca(tentativaRaca);
                        }
                        count ++;
                    }
                    pets.add(pet);
                    dadosParaSalvar.append(pet).append("\n");

                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm");
                    String dateFormated = localDateTime.format(dtf);

                    String caminhoCompleto = "G:/Desafio Cadastro/src/main/Java/petsCadastrados/"+ dateFormated + "-" + pet.getName().toUpperCase() + ".txt";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto))){
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
