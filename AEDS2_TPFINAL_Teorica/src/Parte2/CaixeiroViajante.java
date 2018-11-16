/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parte2;

import java.io.IOException;

/**
 * @author Bruno
 * @author Ramon
 */
public class CaixeiroViajante {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        testaHeuristica();
    }
    
    public static void testaHeuristica() throws IOException {
        int[][] caminho;
        LerArquivoTSP read;
        Grafo grafo;
        Heuristica h;
        
        for (int i = 0; i < 3; i++) {
            String fileName;
            if (i == 0) {
                fileName = "si535";                
            }
            else if (i == 1) {
                fileName = "pa561";
            }
            else {
                fileName = "si1032";
            }
            read = new LerArquivoTSP(fileName);
            grafo = read.getGrafo();
            h = new Heuristica(grafo);
            caminho = h.encontraCaminho();
            System.out.println("Arquivo: " + fileName + ".tsp\nDistancia encontrada pela heuristica: " + h.getPesoTotal());
        }
        
    }

}
