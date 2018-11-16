package Parte1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteForceTSP {

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
            System.out.println("0 :=: Time: "+(endTime-startTime)+"ms"+" Distancia: "+shortestDistance);

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
     * Resolve o problema
     * 
     * @param	distanceMatrix	armazena todas as distancias das adjacencias
     * */
    private static void solveTSP(int[][] distanceMatrix) {

            int totalCities = distanceMatrix.length;
            ArrayList<Integer> cities = new ArrayList<Integer>();
            for(int i=0;i<totalCities;i++){
                    cities.add(i);
            }
            int startCity = cities.get(0);
            int currentDistance = 0;
            bruteForceSearch(distanceMatrix, cities, startCity, currentDistance);
    }
    /*
     * Metodo recursivo que por meio meio da forca bruta gera todas as possiveis permutacoes da jornada
     * 
     * @param	distanceMatrix	armazena a matriz de adjancencias.
     * @param	cities possiveis caminhos da jornada
     * @param	startCity cidade inicial
     * @param	currentDistance	distancia percorrida na jornada.
     * */

    private static void bruteForceSearch(int[][] distanceMatrix, ArrayList<Integer> cities, int startCity, int currentDistance) {

            if(startCity < cities.size()-1){
                    for(int i=startCity; i < cities.size(); i++){
                            int tempCity = cities.get(i);
                            cities.set(i, cities.get(startCity));
                            cities.set(startCity, tempCity);
                            currentDistance = computeDistance(cities,distanceMatrix);
                            bruteForceSearch(distanceMatrix, cities, startCity+1, currentDistance);
                            tempCity = cities.get(i);
                            cities.set(i, cities.get(startCity));
                            cities.set(startCity, tempCity);
                    }
            }
            else{
                    if(shortestDistance > currentDistance){
                            shortestDistance = currentDistance;
                            shortestPath = new ArrayList<Integer>(cities);
                    }
                    if(longestDistance < currentDistance){
                            longestDistance = currentDistance;
                            //longestPath = new ArrayList<Integer>(cities);
                    }
            }
    }

    /*
     * Verifica a distancia total da jornada passada
     * 
     * @param	cities	Possivel caminho da jornada.
     * @param	distanceMatrix	armazena a matriz de adjacencia.
     * 
     * */
    private static int computeDistance(ArrayList<Integer> cities, int[][] distanceMatrix) {
            int distance = 0;
            for(int i=0;i<cities.size()-1;i++){
                    distance = distance + distanceMatrix[cities.get(i)][cities.get(i+1)];
            }
            //adiciona a distancia para voltar a cidade inicial
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

    public static void main(String[] args) throws IOException {
//            Scanner s = new Scanner(System.in);
//            System.out.println("Insira o numero de cidades");
//             s.nextInt()

        for(int i = 2; i < 14 ; i+=2){
            generateAdj(i); //as matrizes gerada sao diferentes
            startTSP("src\\Parte1\\adjacencias"+i+".txt"); 
            System.out.println("\n");
        }
    }
}