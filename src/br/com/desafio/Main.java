package br.com.desafio;
import br.com.desafio.service.PetService;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File("src/data/formulario.txt");
        String barra = "=".repeat(46);

        int numMenu = 0;
        while (numMenu !=5) {
            System.out.println("\n"+barra);
            System.out.println("============ CADASTRO DE PET 3000 ============");
            System.out.println(barra);
            System.out.println("Bem vindo ao sistema de Cadastro de Pet 3000!");
            System.out.println("\n[1] Cadastrar um novo pet");
            System.out.println("[2] Listar pets por algum critério");
            System.out.println("[3] Alterar os dados do pet cadastrado");
            System.out.println("[4] Deletar um pet cadastrado");
            System.out.println("[5] Sair\n");
            System.out.print(">> ");
            numMenu = sc.nextInt();

            PetService service = new PetService();

            switch (numMenu) {
                case 1:
                    service.registrarPet();
                    break;
                case 2:
                    service.buscarEApresentarPets();
                    break;
                case 3:
                    service.alterarPet();
                    break;
                case 4:
                    service.apagarPet();
                    break;
                case 5:
                    System.out.println("\nAté logo...");
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
