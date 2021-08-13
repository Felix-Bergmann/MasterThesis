package FHG_ISdynamics;

import java.util.ArrayList;
import java.util.Random;
import randomCoalition.Partition_StirlingNumber2;

public class Test_IS {
    public static int N = 50;
    public static int Tests = 10000;
    public static int[][] utilities0;
    public static int[][] utilities1;
    public static int[][] utilities2;
    public static int[][] utilities3;
    public static int[][] utilities4;
    public static ArrayList<Integer>[] coalitions0;
    public static ArrayList<Integer>[] coalitions1;
    public static ArrayList<Integer>[] coalitions2;
    public static ArrayList<Integer>[] coalitions3;
    public static ArrayList<Integer>[] coalitions4;
    
    static int[][] lexio = new int[N+1][2];
    static int[][] rando = new int[N+1][2];
    static int[][] pers = new int[N+1][2];
    static int[][] over = new int[N+1][2];
    static int[][] edge = new int[N+1][2];
    
    
    public static void main(String[] args) {
      for (int n = 1; n <= N; n++) {
        utilities0 = new int[n][n];
        utilities1 = new int[n][n];
        utilities2 = new int[n][n];
        utilities3 = new int[n][n];  
        utilities4 = new int[n][n];  
          
        long time = 0L;
        
        for (int I = 0; I < Tests; I++) {
            Random rand = new Random();
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(i!=j){
                        utilities0[i][j] = rand.nextInt(2);
                        utilities0[j][i] = utilities0[i][j];
                        
                        utilities1[i][j] = rand.nextInt(2);
                        utilities1[j][i] = utilities1[i][j];
                        
                        utilities2[i][j] = rand.nextInt(2);
                        utilities2[j][i] = utilities2[i][j];
                        
                        utilities3[i][j] = rand.nextInt(2);
                        utilities3[j][i] = utilities3[i][j];
                        
                        utilities4[i][j] = rand.nextInt(2);
                        utilities4[j][i] = utilities4[i][j];
                    }
                }
            }
            //ArrayList<ArrayList<Integer>> tmp = Partition_StirlingNumber2.randomPartition(n);
            coalitions0 = new ArrayList[n];
            coalitions1 = new ArrayList[n];
            coalitions2 = new ArrayList[n];
            coalitions3 = new ArrayList[n];
            coalitions4 = new ArrayList[n];
            
            for (int i = 0; i < n; i++) {
                coalitions0[i] = new ArrayList<>(); coalitions0[i].add(i);
                coalitions1[i] = new ArrayList<>(); coalitions1[i].add(i);
                coalitions2[i] = new ArrayList<>(); coalitions2[i].add(i);
                coalitions3[i] = new ArrayList<>(); coalitions3[i].add(i);
                coalitions4[i] = new ArrayList<>(); coalitions4[i].add(i);
            }
            
            //printCoalitions();
            //printUtilities();
            //System.out.println("\n");
            
            
            long start_time = System.currentTimeMillis();
            IS_in_simple_symmFHG_pers_Utility.find_IS(coalitions0, utilities0);
            long end_time = System.currentTimeMillis();
            time += (end_time - start_time);
            
            
            updateSteps(n, lexio, IS_in_simple_symmFHG.find_IS(coalitions0, utilities0));
            updateSteps(n, rando, IS_in_simple_symmFHG_Random.find_IS(coalitions1, utilities1));
            updateSteps(n, pers, IS_in_simple_symmFHG_pers_Utility.find_IS(coalitions2, utilities2));
            updateSteps(n, over, IS_in_simple_symmFHG_overall_Utility.find_IS(coalitions3, utilities3));
            updateSteps(n, edge, IS_in_simple_symmFHG_Edges.find_IS(coalitions4, utilities4));
            
            
        }
        
        System.out.println("    "+n+" & "+time+"  \\\\");
      }
      
      
      System.out.println("Agents that deviate [0]/ Avg Steps [1] for lexiographic: ");
      for (int i = 1; i <= N; i++) {
          System.out.println("  "+i+" & "+((double)lexio[i][1] / ((double)Tests))+"   \\\\ ");
      }
      
      System.out.println("Agents that deviate / Avg Steps for random: ");
      for (int i = 1; i <= N; i++) {
          System.out.println("  "+i+" & "+((double)rando[i][1] / ((double)Tests))+"   \\\\ ");
      }
        
      System.out.println("Agents that deviate / Avg Steps for personal Utility: ");
      for (int i = 1; i <= N; i++) {
          System.out.println("  "+i+" & "+((double)pers[i][1] / ((double)Tests))+"   \\\\ ");
      }
        
      System.out.println("Agents that deviate / Avg Steps for overall Utility: ");
      for (int i = 1; i <= N; i++) {
          System.out.println("  "+i+" & "+((double)over[i][1] / ((double)Tests))+"   \\\\ ");
      }
      
      System.out.println("Agents that deviate / Avg Steps for number of edges: ");
      for (int i = 1; i <= N; i++) {
          System.out.println("  "+i+" & "+((double)edge[i][1] / ((double)Tests))+"   \\\\ ");
      }
      
    }   
    
    
    
    
    
    public static void printCoalitions(){
        for (int j = 0; j < coalitions0.length; j++) {
            System.out.print(coalitions0[j]+"  ");
        }
        System.out.println("");
    }
    
    public static void printUtilities(){
        for (int i = 0; i < utilities0.length; i++) {
            for (int j = 0; j < utilities0[i].length; j++) {
                System.out.print(" "+utilities0[i][j]+",");
            }
            System.out.println("");
        }
    }
    
    public static void updateSteps (int n, int[][] first, int[] second){
        for (int i = 0; i < second.length-1; i++) {
            if(second[i]>0){
                first[n][0]++;
            }
        }
        first[n][1]+=
                second[second.length-1];
    }
}
