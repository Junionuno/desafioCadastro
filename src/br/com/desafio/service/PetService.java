package br.com.desafio.service;

import br.com.desafio.enums.SexoPet;
import br.com.desafio.enums.TipoPet;
import br.com.desafio.model.Endereco;
import br.com.desafio.model.Pet;
import static br.com.desafio.util.InputHelper.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetService {
    Scanner sc = new Scanner(System.in);
    private final String PATH_FORMULARIO = "src/data/formulario.txt";
    private final String PATH_CADASTROS = "src/data/petsCadastrados/";

    public void registrarPet(){
        System.out.println("\n===== Cadastro de Pets =====");
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_FORMULARIO))) {
            String question;
            int count = 1;
            Pet pet = new Pet();

            while ((question = br.readLine()) != null) {

                if (question.trim().isEmpty()) continue;
                System.out.println(question);
                System.out.print(">> ");
                String answer = sc.nextLine();

                switch (count) {
                    case 1:
                        pet.setName(lerTextoValido(answer));
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
            salvarPet(pet);
        }catch (IOException e){
            System.out.println("Erro ao ler o formulário: " + e.getMessage());
        }
    }

    public void salvarPet(Pet pet){
        LocalDateTime agora = LocalDateTime.now();
        String dataFormatada = agora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm"));
        String nomeArquivo = dataFormatada + "-" + pet.getName().toUpperCase() + ".txt";

        File arquivoDestino = new File(PATH_CADASTROS + nomeArquivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDestino))){
            writer.write(pet.toString());
            System.out.println("\nPet cadastrado com sucesso!");
        }catch (IOException e){
            System.out.println("\nErro ao salvar o pet: " + e.getMessage());
        }
    }

    public void buscarEApresentarPets(){
        File path = new File(PATH_CADASTROS);
        File[] arquivos = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0){
            System.out.println("Nenhum pet cadastrado até agora.");
            return;
        }

        System.out.println("\n===== Busca de Pets =====");
        System.out.print("Tipo pet (Cachorro/Gato): \n>> ");
        String tipo = sc.nextLine();
        while (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")){
            System.out.print("\nErro! escolha entre cachorro ou gato! \n>>");
            tipo = sc.nextLine();
        }

        System.out.print("\nDigite o valor que procura (Nome, raça ou idade): \n>>");
        String valorBusca = sc.nextLine();

        boolean encontrou = false;
        int count = 0;
        for (File arq : arquivos){
            try (BufferedReader reader = new BufferedReader(new FileReader(arq))){
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null){
                    conteudo.append(linha).append(" ");
                }

                String dados = conteudo.toString().toLowerCase();
                if (dados.contains(tipo.toLowerCase()) && dados.contains(valorBusca.toLowerCase())){
                    count ++;
                    System.out.println(count + conteudo.toString());
                    encontrou = true;
                }
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo: " + arq.getName());
            }
        }
        if (!encontrou) System.out.println("\nNenhum pet encontrado com esses critérios.");
    }

    public void alterarPet(){
        File path = new File(PATH_CADASTROS);
        File[] arquivos = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        List<File> resultadosEncontrados = new ArrayList<>();

        if (arquivos == null || arquivos.length == 0){
            System.out.println("Não há pets cadastrados!");
        }

        System.out.println("\n===== Alteração de Cadastro =====");
        System.out.println("Tipo pet (Cachorro/Gato): ");
        String tipo = sc.nextLine();
        while (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")){
            System.out.println("\nErro! escolha entre cachorro ou gato!");
            tipo = sc.nextLine();
        }

        System.out.print("\nDigite o valor que procura (Nome, raça ou idade): \n>>");
        String valor = sc.nextLine();

        boolean encontrou = false;
        for (File arq : arquivos){
            try (BufferedReader reader = new BufferedReader(new FileReader(arq))){
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null){
                    conteudo.append(linha).append(" ");
                }

                String dados = conteudo.toString().toLowerCase();
                if (dados.contains(tipo.toLowerCase()) && dados.contains(valor.toLowerCase())){
                    resultadosEncontrados.add(arq);
                    System.out.println(resultadosEncontrados.size() + " " + conteudo);
                    encontrou = true;
                }
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo: " + arq.getName());
            }
        }
        if (!encontrou) System.out.println("\nNenhum pet encontrado com esses critérios.");

        System.out.print("\nEscolha o número do pet que deseja alterar: \n>>");
        int escolha = sc.nextInt();
        File arquivoParaAlterar = resultadosEncontrados.get(escolha - 1);

        Pet petParaEditar = new Pet();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParaAlterar))) {
            List<String> linhas = new ArrayList<>();
            String ln;
            while ((ln = reader.readLine()) != null) {
                linhas.add(ln);
            }

            String nomeStr = linhas.get(0).split("-")[1].trim();
            petParaEditar.setName(nomeStr);

            String tipoStr = linhas.get(1).split("-")[1].trim();
            if (tipoStr.equalsIgnoreCase("Cachorro")) petParaEditar.setTipoPet(TipoPet.Cachorro);
            else petParaEditar.setTipoPet(TipoPet.Gato);

            String sexoStr = linhas.get(2).split("-")[1].trim();
            if (sexoStr.equalsIgnoreCase("Femea")) petParaEditar.setSexo(SexoPet.Femea);
            else petParaEditar.setSexo(SexoPet.Macho);

            String ruaStr = linhas.get(3).split(",")[0].substring(6);
            String numeroStr = linhas.get(3).split(",")[1].trim();
            String cidadeStr = linhas.get(3).split(",")[2].trim();
            petParaEditar.setEndereco(new Endereco(cidadeStr, ruaStr));

            String idadeStr = linhas.get(4).substring(2, 6).trim();
            petParaEditar.setIdade(Double.parseDouble(idadeStr));

            String pesoStr = linhas.get(5).substring(2,6).trim();
            petParaEditar.setPeso(Double.parseDouble(pesoStr));

            String racaStr = linhas.get(6).split("-")[1].trim();
            petParaEditar.setRaca(racaStr);
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados originais.");
        }

        System.out.println("\nO que deseja alterar? ");
        System.out.print("[1] Nome | [2] Endereço | [3] Idade | [4] Peso | [5] Raça | [6] Todos \n>>");
        int opc = sc.nextInt();
        sc.nextLine();

        if (opc >= 1 && opc <= 6){
            switch (opc){
                case 1:
                    System.out.print("Novo nome: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setName(valor);
                    break;
                case 2:
                    System.out.print("Novo Endereço: \n>>");
                    petParaEditar.setEndereco(lerEndereco());
                    break;
                case 3:
                    System.out.print("Nova idade: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setIdade(lerIdade(valor));
                    break;
                case 4:
                    System.out.print("Novo peso: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setPeso(validarPeso(valor));
                    break;
                case 5:
                    System.out.print("Nova raça: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setRaca(valor);
                    break;
                case 6:
                    System.out.print("Novo nome: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setName(valor);

                    System.out.print("Novo Endereço: \n>>");
                    petParaEditar.setEndereco(lerEndereco());

                    System.out.print("Nova idade: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setIdade(lerIdade(valor));

                    System.out.print("Novo peso: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setPeso(validarPeso(valor));

                    System.out.print("Nova raça: \n>>");
                    valor = sc.nextLine();
                    petParaEditar.setRaca(valor);
                    break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoParaAlterar, false))) {
            writer.write(petParaEditar.toString());
            writer.newLine();
            System.out.println("Dados do pet atualizados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar alterações: " + e.getMessage());
        }

        String nomeAntigo = arquivoParaAlterar.getName();
        String novoNome = petParaEditar.getName().toUpperCase();

        if (!nomeAntigo.contains(novoNome)){
            String dataOriginal = nomeAntigo.substring(0, 16);
            String novoNomeDir = dataOriginal + "-" + novoNome + ".txt";
            File novoDir = new File(arquivoParaAlterar.getParent(), novoNomeDir);

            if (arquivoParaAlterar.renameTo(novoDir)){
                System.out.println("Renomeado para: " + novoNomeDir);
            }else{
                System.out.println("Erro ao renomear.");
            }
        }
    }

    public void apagarPet(){
        File path = new File(PATH_CADASTROS);
        File[] arquivos = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        List<File> resultadosEncontrados = new ArrayList<>();

        if (arquivos == null || arquivos.length == 0){
            System.out.println("Não há pets cadastrados!");
        }

        System.out.println("\n===== Exlcuir Cadastro =====");
        System.out.print("Tipo pet (Cachorro/Gato): \n>> ");
        String tipo = sc.nextLine();
        while (!tipo.equalsIgnoreCase("cachorro") && !tipo.equalsIgnoreCase("gato")){
            System.out.println("\nErro! escolha entre cachorro ou gato!");
            tipo = sc.nextLine();
        }

        System.out.print("\nDigite o valor que procura (Nome, raça ou idade): \n>> ");
        String valor = sc.nextLine();

        boolean encontrou = false;
        for (File arq : arquivos){
            try (BufferedReader reader = new BufferedReader(new FileReader(arq))){
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null){
                    conteudo.append(linha).append(" ");
                }

                String dados = conteudo.toString().toLowerCase();
                if (dados.contains(tipo.toLowerCase()) && dados.contains(valor.toLowerCase())){
                    resultadosEncontrados.add(arq);
                    System.out.println(resultadosEncontrados.size() + " " + conteudo);
                    encontrou = true;
                }
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo: " + arq.getName());
            }
        }
        if (!encontrou) System.out.println("\nNenhum pet encontrado com esses critérios.");

        System.out.print("\nEscolha o número do pet que deseja deletar: \n>> ");
        int escolha = sc.nextInt();

        System.out.print("Tem certeza que deseja deletar? (S/N)\n>>");
        String escolhaDelete = sc.next();

        File arquivoParaDeletar = resultadosEncontrados.get(escolha - 1);

        if (escolhaDelete.equalsIgnoreCase("s")){
            if (arquivoParaDeletar.delete()){
                System.out.println("Arquivo de " +  arquivoParaDeletar.getName() + " excluído com sucesso!");
            }else{
                System.out.println("Falha ao excluir o arquivo.");
            }
        }else {
            System.out.println("Ação cancelada!");
        }


    }
}
