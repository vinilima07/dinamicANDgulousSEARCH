package Parte1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GulousTSP {

    /*
     * shortestPath:		o menor caminho para percorrer todas as cidades apenas uma vez cada.
     * shortestDistance:	variavel para armazenar a menor distancia total percorrida.
     * longestDistance:		variavel para armazenar a maior distancia total percorrida.
     *  
     */
    public static int shortestDistance;
    public static int longestDistance;
    public static ArrayList<Integer> shortestPath;

    /*
     *Soluciona o TSP usando forca bruta
     * 
     * @param	pathName: caminho do arquivo.
     * */
    public static void startTSP(String pathName) {
            File file = new File("output.txt");
            try {
            File f = new File(pathName);
            Scanner s = new Scanner(f);
            int numCidades = Integer.parseInt(s.nextLine());
            int[][] adjacency_matrix = new int[numCidades][numCidades];
            String adjacencias = "";
            int linha = 0;
            int coluna = 0;

            while(s.hasNext()) {
                adjacencias = s.nextLine();
                Scanner dist = new Scanner(adjacencias).useDelimiter(" ");
                while (dist.hasNext()){
                    adjacency_matrix[linha][coluna] = dist.nextInt();
                    coluna++;
                }
                coluna = 0;
                linha++;
            }
            int problemNo = 0;
            shortestDistance = Integer.MAX_VALUE;
            longestDistance = Integer.MIN_VALUE;
            shortestPath = null;
            int totalCities = numCidades;
            long startTime = System.currentTimeMillis();
            solveTSP(adjacency_matrix);
            long endTime = System.currentTimeMillis();

            for(int city : shortestPath){
                System.out.print((Integer.toString(city))+"->");
            }
            System.out.println("0 :=: Time: "+(endTime-startTime)+"ms");

            } catch(FileNotFoundException expception) {

                    System.out.println("File given does not exists.");
                    expception.printStackTrace();
                    System.exit(0);
            }
            catch(IOException ioException){
                    System.out.println("Error occured during Input Output operation.");
                    ioException.printStackTrace();
                    System.exit(0);
            }
    }

    /*
     * Resolve o tsp criando a indexacao inicial
     * 
     * */
    private static void solveTSP(int[][] distanceMatrix) {

            int totalCities = distanceMatrix.length;
            ArrayList<Integer> cities = new ArrayList<Integer>();
            for(int i=0;i<totalCities;i++){
                    cities.add(i);
            }
            int startCity = cities.get(0);
            int currentDistance = 0;
            gulousSearch(distanceMatrix, cities);
    }
    /*
     * Metodo guloso gera (10000*numero de cidades) variacoes de 
       caminho e dentro dessa amostra pega a melhor de forma a torna-la
       polinomial
     * 
     * @param	distanceMatrix	armazena a matriz de adjancencias.
     * @param	cities possiveis caminhos da jornada
     * */

    private static void gulousSearch(int[][] distanceMatrix, ArrayList<Integer> cities) {
            int currentDistance;
            for(int i = 0; i < 5000*cities.size() ; i++){
                cities.remove(0);
                Collections.shuffle(cities); //embaralha as cidades
                cities.add(0, 0);
                currentDistance = computeDistance(cities,distanceMatrix);
                if(shortestDistance > currentDistance){
                    shortestDistance = currentDistance;
                    shortestPath = new ArrayList<Integer>(cities);
                }
            }
    }

    /*
     * Computes the distance covered during the journey
     * 
     * @param	cities			possible path of the journey.
     * @param	distanceMatrix	stores distance between two cities.
     * 
     * */
    private static int computeDistance(ArrayList<Integer> cities, int[][] distanceMatrix) {
            int distance = 0;
            for(int i=0;i<cities.size()-1;i++){
                    distance = distance + distanceMatrix[cities.get(i)][cities.get(i+1)];
            }
            distance = distance + distanceMatrix[cities.get(cities.size()-1)][cities.get(0)];
            return distance;
    }

    /*
    * Gera matrizes de adjacencias e as escrevem nos arquivos de teste 
    *
    * */
    public static void generateAdj(int numCity) throws IOException{
        Random r = new Random();
        FileWriter fileWriter = new FileWriter("src\\Parte1\\adjacencias"+numCity+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.printf("%d\n", numCity);
        for(int i=0; i < numCity ; i++){
            for(int j = 0; j < numCity ; j++){
                if(j != i)  printWriter.printf("%d ", 1+r.nextInt(50));
                else  printWriter.print("0 ");
            }
            printWriter.printf("\n");
        }
        fileWriter.close();
    }
    /***
     * SOLUCAO GULOSA PARA O TSP
       BASTA DESCOMENTAR ESSA MAIN E EXECUTA-LA
       ELA RESOLVE CRIANDO UMA AMOSTRA DE VARIACOES DE CAMINHO
       E SELECIANO O MELHOR CAMINHO DENTRO DA AMOSTRA*
     **/
//    public static void main(String[] args) throws IOException {
////            Scanner s = new Scanner(System.in);
////            System.out.println("Insira o numero de cidades");
////             s.nextInt()
//
//        for(int i = 2; i < 16 ; i+=2){
//            generateAdj(i);
//            startTSP("src\\Parte1\\adjacencias"+i+".txt");
//            System.out.println("\n");
//        }
//    }
}