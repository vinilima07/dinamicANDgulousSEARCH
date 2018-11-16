/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parte1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Positivo
 */
public class Main_Brute {
    public static void main(String[] args)throws FileNotFoundException, Exception{
        File f = new File("C:\\Users\\Positivo\\Documents\\NetBeansProjects\\AEDS2_TPFINAL_Teorica\\src\\Parte1\\adjacencias.txt");
        Scanner s = new Scanner(f);
        int numCidades = Integer.parseInt(s.nextLine());
        int[][] matrizAdjacencia = new int[numCidades][numCidades];
        String adjacencias = "";
        int linha = 0;
        int coluna = 0;

        while(s.hasNext()) {
            adjacencias = s.nextLine();
            Scanner dist = new Scanner(adjacencias).useDelimiter(" ");
            while (dist.hasNext()){
                matrizAdjacencia[linha][coluna] = dist.nextInt();
                coluna++;
            }
            coluna = 0;
            linha++;
        }

        for(linha = 0; linha < numCidades; linha++){
            for(coluna = 0; coluna < numCidades; coluna++)
                System.out.print(matrizAdjacencia[linha][coluna]+" ");
            System.out.println("\n");
        }
        Caixeiro cx = new Caixeiro(matrizAdjacencia, new int[numCidades], 
                        new int[numCidades], new int[numCidades], numCidades);
        cx.verifica(0);
    }
}