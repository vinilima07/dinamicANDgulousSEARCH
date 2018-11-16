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
public class Caixeiro {

    /** 
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    private int N;		    /* numero de vertices */
    private int[]ciclo;	    /* ciclo atual */
    private int[]melhor_ciclo;  /* melhor ciclo encontrado */
    private int[]explorado;     /* vetor para armazenar se o vertice foi explorado pelo DFS*/
    private int[][]g;	    /* matriz de distancias */
    private static int nivel = 0;	    /* profundidade alcancada pelo DFS*/
    private static int min = 10000;	    /* usado para selecionar o menor ciclo */

    public Caixeiro(int[][] g, int[]ciclo, int[] explorado, int[] melhor_ciclo, int N) {
        this.g = g;
        this.ciclo = ciclo;
        this.explorado = explorado;
        this.melhor_ciclo = melhor_ciclo;
        this.N = N;
        for(int i=0; i < N; i++)
            explorado[i]=0;
    }
    
    public void verifica(int origem){
        dfs(origem, origem);
        /* Resultado */
        for(int i=0; i<N ;i++)
            System.out.println(":"+melhor_ciclo[i]);
    }
    
    public int medeciclo(int[] t)
    {
        int i;
        int l=0;

        for(i=0;i<N-1;i++)
            l=l+g[t[i]][t[i+1]];
        l=l+g[t[N-1]][t[0]];

        return l;
    }	    

    /* Esta funcao implementa o algoritmo DFS modificado para
     * poder percorrer todos os caminhos possiveis. Apos o retorno, o vertice 
     * eh desmarcado como explorado, permitindo que ele seja explorado novamente */
    public void dfs(int v, int nivel)
    {
        if(nivel == N)return;
        int i,j,dist;
        explorado[v]=1;
        ciclo[nivel]=v;
        if(nivel==N-1){
            /* completou um ciclo */
            dist=medeciclo(ciclo);
            if(dist<min){
                min=dist;
                for(j=0;j<N;j++) 
                    melhor_ciclo[j]=ciclo[j];
            }
        }
        for(i=0;i<N;i++){
            if(explorado[i] != 0){
                dfs(i,nivel+1);
                explorado[i]=0;
            }
        }
    }
}
