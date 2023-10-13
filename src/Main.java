import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.GraphRenderer;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void populate(BinaryTree tree, int amount, int index) {
        Random generator = new Random();
        for (int i = 0; i < amount; i++) {
            int rando = generator.nextInt(100);
            tree.put(index, rando);
            index++;
        }
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.setProperty("org.graphstream.ui", "swing");

        while (true) {
            System.out.println("OPÇÕES:");
            System.out.println("0 - Sair");
            System.out.println("1 - Árvore Binária");
            System.out.println("2 - Árvore AVL");
            int treeOption = scanner.nextInt();
            if (treeOption == 0) {
                break;
            } else if (treeOption == 1) {
                BinaryTree binaryTree = new BinaryTree();
                binaryTree.print();
                int index = 0;
                while (true) {
                    System.out.println("OPÇÕES:");
                    System.out.println("0 - Sair");
                    System.out.println("1 - Adicionar");
                    System.out.println("2 - Remover");
                    System.out.println("3 - Buscar");
                    System.out.println("4 - Popular aleatóriamente");
                    int option = scanner.nextInt();
                    if (option == 0) {
                        break;
                    }
                    if (option == 1) {
                        System.out.println("Número a ser adicionado:");
                        int toAdd = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        binaryTree.put(index, toAdd);
                        long endTime = System.nanoTime();
                        long elapsed = endTime - initialTime;
                        System.out.println("Tempo de inserção: " + (elapsed) + "ns");
                        index++;
                        binaryTree.print();
                    } else if (option == 2) {
                        System.out.println("Número a ser removido:");
                        int toRemove = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        if (binaryTree.delete(toRemove)) {
                            long endTime = System.nanoTime();
                            long elapsed = endTime - initialTime;
                            System.out.println("Tempo de remoção: " + (elapsed) + "ns");
                            binaryTree.print();
                        } else {
                            System.out.println("Número não encontrado");
                        }
                    } else if (option == 3) {
                        System.out.println("Número a ser buscado:");
                        int toSearch = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        if (binaryTree.search(toSearch)) {
                            long endTime = System.nanoTime();
                            long elapsed = endTime - initialTime;
                            System.out.println("Tempo de busca: " + (elapsed) + "ns");
                        } else {
                            System.out.println("Número não encontrado");
                        }
                    } else if (option == 4) {
                        System.out.println("Quantidade de números:");
                        int toPopulate = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        populate(binaryTree, toPopulate, index);
                        long endTime = System.nanoTime();
                        long elapsed = endTime - initialTime;
                        System.out.println("Tempo de inserção: " + (elapsed) + "ns");
                        index+=toPopulate;
                        binaryTree.print();
                    } else {
                        System.out.println("Opção inválida");
                    }
                }
            } else if (treeOption == 2) {
                AVLTree avlTree = new AVLTree();
                avlTree.print();
                int index = 0;
                while (true) {
                    System.out.println("OPÇÕES:");
                    System.out.println("0 - Sair");
                    System.out.println("1 - Adicionar");
                    System.out.println("2 - Remover");
                    System.out.println("3 - Buscar");
                    System.out.println("4 - Popular aleatóriamente");
                    int option = scanner.nextInt();
                    if (option == 0) {
                        break;
                    }
                    if (option == 1) {
                        System.out.println("Número a ser adicionado:");
                        int toAdd = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        avlTree.put(index, toAdd);
                        long endTime = System.nanoTime();
                        long elapsed = endTime - initialTime;
                        System.out.println("Tempo de inserção: " + (elapsed) + "ns");
                        index++;
                        avlTree.print();
                    } else if (option == 2) {
                        System.out.println("Número a ser removido:");
                        int toRemove = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        if (avlTree.delete(toRemove)) {
                            long endTime = System.nanoTime();
                            long elapsed = endTime - initialTime;
                            System.out.println("Tempo de remoção: " + (elapsed) + "ns");
                            avlTree.print();
                        } else {
                            System.out.println("Número não encontrado");
                        }
                    } else if (option == 3) {
                        System.out.println("Número a ser buscado:");
                        int toSearch = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        if (avlTree.search(toSearch)) {
                            long endTime = System.nanoTime();
                            long elapsed = endTime - initialTime;
                            System.out.println("Tempo de busca: " + (elapsed) + "ns");
                        } else {
                            System.out.println("Número não encontrado");
                        }
                    } else if (option == 4) {
                        System.out.println("Quantidade de números:");
                        int toPopulate = scanner.nextInt();
                        long initialTime = System.nanoTime();
                        populate(avlTree, toPopulate, index);
                        long endTime = System.nanoTime();
                        long elapsed = endTime - initialTime;
                        System.out.println("Tempo de inserção: " + (elapsed) + "ns");
                        index+=toPopulate;
                        avlTree.print();
                    } else {
                        System.out.println("Opção inválida");
                    }
                }
            } else {
                System.out.println("Opção inválida");
            }
        }
    }
}
