package br.com.desafio.Service;

import br.com.desafio.enums.SexoPet;
import br.com.desafio.enums.TipoPet;
import br.com.desafio.model.Pet;
import static util.InputHelper.*;

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


    public List<String> obterPerguntasFormulario(){
        List<String> perguntas = new ArrayList<>();
        File file = new File(PATH_FORMULARIO);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    perguntas.add(linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler formulário: " + e.getMessage());
        }
        return perguntas;
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

    public void buscarEApresentarPets(String tipo, String criterio, String valor){
        File path = new File(PATH_CADASTROS);
        File[] arquivos = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0){
            System.out.println("Nenhum pet cadastrado até agora.");
            return;
        }

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
                if (dados.contains(tipo.toLowerCase()) && dados.contains(valor.toLowerCase())){
                    count ++;
                    System.out.println("\n" + count + conteudo.toString());
                    encontrou = true;
                }
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo: " + arq.getName());
            }
        }
        if (!encontrou) System.out.println("\nNenhum pet encontrado com esses critérios.");
    }

    public void alterarPet(String tipo, String valor){
        File path = new File(PATH_CADASTROS);
        File[] arquivos = path.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        List<File> resultadosEncontrados = new ArrayList<>();

        if (arquivos == null || arquivos.length == 0){
            System.out.println("Não há pets cadastrados!");
        }

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

        System.out.println("Escolha o número do pet que deseja alterar: ");
        int escolha = sc.nextInt();
        File arquivoParaAlterar = resultadosEncontrados.get(escolha - 1);

        Pet petParaEditar = new Pet();

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoParaAlterar))) {
            List<String> linhas = new ArrayList<>();
            String ln;
            while ((ln = reader.readLine()) != null) {
                linhas.add(ln);
            }

            String tipoStr = linhas.get(1).split("-")[1].trim();
            String sexoStr = linhas.get(2).split("-")[1].trim();

            if (tipoStr.equalsIgnoreCase("Cachorro")) petParaEditar.setTipoPet(TipoPet.Cachorro);
            else petParaEditar.setTipoPet(TipoPet.Gato);

            if (sexoStr.equalsIgnoreCase("Femea")) petParaEditar.setSexo(SexoPet.Femea);
            else petParaEditar.setSexo(SexoPet.Macho);

        } catch (IOException e) {
            System.out.println("Erro ao carregar dados originais.");
        }

        System.out.println("O que deseja alterar? ");
        System.out.println("1. Nome | 2. Endereço | 3. Idade | 4. Peso | 5. Raça | 6. Todos");
        int opc = sc.nextInt();
        sc.nextLine();

        if (opc >= 1 && opc <= 6){
            switch (opc){
                case 1:
                    System.out.println("Novo nome: ");
                    valor = sc.nextLine();
                    petParaEditar.setName(valor);
                    break;
                case 2:
                    System.out.println("Novo Endereço: ");
                    petParaEditar.setEndereco(lerEndereco());
                    break;
                case 3:
                    System.out.println("Nova idade: ");
                    valor = sc.nextLine();
                    petParaEditar.setIdade(lerIdade(valor));
                    break;
                case 4:
                    System.out.println("Novo peso: ");
                    valor = sc.nextLine();
                    petParaEditar.setPeso(validarPeso(valor));
                    break;
                case 5:
                    System.out.println("Nova raça: ");
                    valor = sc.nextLine();
                    petParaEditar.setRaca(valor);
                case 6:
                    System.out.println("Novo nome: ");
                    valor = sc.nextLine();
                    petParaEditar.setName(valor);

                    System.out.println("Novo Endereço: ");
                    petParaEditar.setEndereco(lerEndereco());

                    System.out.println("Nova idade: ");
                    valor = sc.nextLine();
                    petParaEditar.setIdade(lerIdade(valor));

                    System.out.println("Novo peso: ");
                    valor = sc.nextLine();
                    petParaEditar.setPeso(validarPeso(valor));

                    System.out.println("Nova raça: ");
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
    }

}
