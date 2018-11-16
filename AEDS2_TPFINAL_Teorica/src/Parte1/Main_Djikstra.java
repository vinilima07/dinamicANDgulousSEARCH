package Parte1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Positivo
 */
public class Main_Djikstra {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
//   public static void main(String[] args)
//   throws FileNotFoundException, Exception
//   {
//        File f = new File("C:\\Users\\Positivo\\Documents\\NetBeansProjects\\AEDS2_TPFINAL_Teorica\\src\\Parte1\\adjacencias.txt");
//
//        Scanner s = new Scanner(f);
//
//        int numCidades = Integer.parseInt(s.nextLine());
//
//        Grafo grafo = new Grafo(numCidades);
//        
//        String adjacencias = "";
//        
//        int linha = 0;
//        int coluna = 0;
//        
//        while(s.hasNext()) {
//            adjacencias = s.nextLine();
//            Scanner dist = new Scanner(adjacencias).useDelimiter(" ");
//            while (dist.hasNext()){
//                int peso = dist.nextInt();
//                Grafo.Aresta a = new Grafo.Aresta(linha, coluna, peso);
//                grafo.insereAresta (a.v1 (), a.v2 (), a.peso ());
//                grafo.insereAresta (a.v2 (), a.v1 (), a.peso ());
//                coluna++;
//            }
//            coluna = 0;
//            linha++;
//        }
//        
//        Dijkstra dj = new Dijkstra(grafo);
//        dj.obterArvoreCMC (1);
//        dj.imprimeCaminho (1, 2);
//   }
    
}
