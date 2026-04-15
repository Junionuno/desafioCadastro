package br.com.desafio.Service;

import br.com.desafio.model.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetService {
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
        for (File arq : arquivos){
            try (BufferedReader reader = new BufferedReader(new FileReader(arq))){
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null){
                    conteudo.append(linha).append(" ");
                }

                String dados = conteudo.toString().toLowerCase();
                if (dados.contains(tipo.toLowerCase()) && dados.contains(valor.toLowerCase())){
                    System.out.println("Encontrado: " + conteudo.toString());
                    encontrou = true;
                }
            }catch (IOException e){
                System.out.println("Erro ao ler o arquivo: " + arq.getName());
            }
        }
        if (!encontrou) System.out.println("Nenhum pet encontrado com esses critérios.");
    }

}
