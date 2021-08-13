package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Random;
import randomCoalition.Partition_StirlingNumber2;

public class Test_Random_CS {
    public static int Tests = 100;
    public static int Tests_coalitions = 100;
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> notEliminated;
    public static boolean simple = true;
    
    
    
    public static void main(String[] args) {
        
        for (int N = 1; N <= 20; N++) {
            utilities = new int[N][N];        

            int cs = 0;
            //System.out.println("------------------------------- n="+N);
            for (int I = 0; I < Tests; I++) {
                Random rand = new Random();

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(i!=j){
                            utilities[i][j] = rand.nextInt(2);
                            utilities[j][i] = utilities[i][j];
                        }
                    }
                }
                
                //printUtilities();
                for (int T = 0; T < Tests_coalitions; T++) {
                    ArrayList<ArrayList<Integer>> tmp = Partition_StirlingNumber2.randomPartition(N);
                    coalitions = new ArrayList[tmp.size()];

                    for (int i = 0; i < tmp.size(); i++) {
                        coalitions[i] = new ArrayList<>();
                        for (int j = 0; j < tmp.get(i).size(); j++){
                            coalitions[i].add(tmp.get(i).get(j));
                        }
                    }
                    curr_utility = new double[N];
                    notEliminated = new ArrayList<>();
                    //printCoalitions();
                    
                    
                    Preprocessing.perform(coalitions, utilities, notEliminated, curr_utility, simple);
                    
                    
                    ArrayList<Integer> blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    
                    
                    
                    if(blocking_coal.isEmpty()){
                        cs++;
                    }
                    
                    
                }
            }
            
            
            System.out.println("  "+N+" & "+(1.0*cs / (Tests * Tests_coalitions))+ " \\\\");
            
        }
    }    
    
    
    public static void printCoalitions(){
        for (int j = 0; j < coalitions.length; j++) {
            System.out.print(coalitions[j]+"  ");
        }
        System.out.println("");
    }
    
    public static void printUtilities(){
        System.out.println("{");
        for (int i = 0; i < utilities.length; i++) {
            System.out.print("{");
            for (int j = 0; j < utilities[i].length; j++) {
                System.out.print(" "+utilities[i][j]+",");
            }
            System.out.println("}");
        }
        System.out.println("};");
    }
    
    public static void combineArrays (int[] first, int[] second){
        for (int i = 0; i < second.length-1; i++) {
            if(second[i]>0){
                first[0]++;
            }
        }
        first[1]+=second[second.length-1];
    }
}
