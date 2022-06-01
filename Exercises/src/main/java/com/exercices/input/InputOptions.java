package com.exercices.input;

import com.exercices.controller.ProdutoController;

import java.util.Scanner;

public class InputOptions {

    private Scanner scanner = new Scanner(System.in);
    private ProdutoController produtoController = new ProdutoController();


    public void menuInput(){
        while (true) {
            menuDisplay();
            int id = 0;
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    produtoController.createProduto();
                    break;
                case "2":
                    id = idInput();
                    produtoController.updateProduto(id);
                    break;
                case "3":
                    id = idInput();
                    produtoController.deleteProduto(id);
                    break;
                case "4":
                    System.out.println("Informe os termos para busca:");
                    String keyword = scanner.nextLine();
                    produtoController.searchProduto(keyword);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Escolha apenas um valor válido.");
            }
        }
    }
    void menuDisplay(){
        System.out.println("_____________________________");
        System.out.println("======== XPTO System ========");
        System.out.println("Digite a opção desejada:");
        System.out.println("1 - Inserir nova tarefa");
        System.out.println("2 - Atualizar uma oferta");
        System.out.println("3 - Deletar uma oferta");
        System.out.println("4 - Buscar termo");
        System.out.println("0 - Sair");
        System.out.println("_____________________________");
    }

    int idInput(){
        int id = 0;
        boolean hold = true;
        while (hold){
            System.out.println("Informe o número de ID do produto: ");
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                hold = false;
            }catch (Exception e){System.out.println("Informe apenas valores numéricos inteiros, p.ex. 1, 2, 3.");}
        }
        return id;
    }


    public void preMenuInput() {
        ProdutoController produtoController = new ProdutoController();
        produtoController.createDatabase();
        menuInput();
    }
}
