package FHG_ISdynamics;

import java.util.ArrayList;
import java.util.Random;
import randomCoalition.Partition_StirlingNumber2;

public class Test_IS_counterexample {
    public static int N = 15;
    public static int Tests = 1;
    
    public static int[][] utilities = { {    0,   436, -2251, -2251,   436,      228,   248, -2251, -2251,   223,      228,   228, -2251, -2251,   223},
                                        {  436,     0,   436, -2251, -2251,      223,   228,   248, -2251, -2251,      223,   228,   228, -2251, -2251},
                                        {-2251,   436,     0,   436, -2251,    -2251,   223,   228,   248, -2251,    -2251,   223,   228,   228, -2251},
                                        {-2251, -2251,   436,     0,   436,    -2251, -2251,   223,   228,   248,    -2251, -2251,   223,   228,   228},
                                        {  436, -2251, -2251,   436,     0,      248, -2251, -2251,   223,   228,      228, -2251, -2251,   223,   228},
                                        
                                        {  228,   223, -2251, -2251,   248,        0,   188, -2251, -2251,   188,      228,   171, -2251, -2251,   236},
                                        {  248,   228,   223, -2251, -2251,      188,     0,   188, -2251, -2251,      236,   228,   171, -2251, -2251},
                                        {-2251,   248,   228,   223, -2251,    -2251,   188,     0,   188, -2251,    -2251,   236,   228,   171, -2251},
                                        {-2251, -2251,   248,   228,   223,    -2251, -2251,   188,     0,   188,    -2251, -2251,   236,   228,   171},
                                        {  223, -2251, -2251,   248,   228,      188, -2251, -2251,   188,     0,      171, -2251, -2251,   236,   228},
                                        
                                        {  228,   223, -2251, -2251,   228,      228,   236, -2251, -2251,   171,        0,   171, -2251, -2251,   171},
                                        {  228,   228,   223, -2251, -2251,      171,   228,   236, -2251, -2251,      171,     0,   171, -2251, -2251},
                                        {-2251,   228,   228,   223, -2251,    -2251,   171,   228,   236, -2251,    -2251,   171,     0,   171, -2251},
                                        {-2251, -2251,   228,   228,   223,    -2251, -2251,   171,   228,   236,    -2251, -2251,   171,     0,   171},
                                        {  223, -2251, -2251,   228,   228,      236, -2251, -2251,   171,   228,      171, -2251, -2251,   171,     0},
                                        
        }; /*
    public static int[][] utilities = { {    0,   228,   228,     436,   248,   228,   -2251, -2251, -2251,   -2251, -2251, -2251,     436,   223,   223},
                                        {  228,     0,   228,     223,   188,   171,   -2251, -2251, -2251,   -2251, -2251, -2251,     248,   188,   236},
                                        {  228,   228,     0,     223,   236,   171,   -2251, -2251, -2251,   -2251, -2251, -2251,     228,   171,   171},
                                        
                                        {  436,   223,   223,       0,   228,   228,     436,   248,   228,   -2251, -2251, -2251,   -2251, -2251, -2251},
                                        {  248,   188,   236,     228,     0,   228,     223,   188,   171,   -2251, -2251, -2251,   -2251, -2251, -2251},
                                        {  228,   171,   171,     228,   228,     0,     223,   236,   171,   -2251, -2251, -2251,   -2251, -2251, -2251},
                                        
                                        {-2251, -2251, -2251,     436,   223,   223,       0,   228,   228,     436,   248,   228,   -2251, -2251, -2251},
                                        {-2251, -2251, -2251,     248,   188,   236,     228,     0,   228,     223,   188,   171,   -2251, -2251, -2251},
                                        {-2251, -2251, -2251,     228,   171,   171,     228,   228,     0,     223,   236,   171,   -2251, -2251, -2251},
                                        
                                        {-2251, -2251, -2251,   -2251, -2251, -2251,     436,   223,   223,       0,   228,   228,     436,   248,   228},
                                        {-2251, -2251, -2251,   -2251, -2251, -2251,     248,   188,   236,     228,     0,   228,     223,   188,   171},
                                        {-2251, -2251, -2251,   -2251, -2251, -2251,     228,   171,   171,     228,   228,     0,     223,   236,   171},
                                        
                                        {  436,   248,   228,   -2251, -2251, -2251,   -2251, -2251, -2251,     436,   223,   223,       0,   228,   228},
                                        {  223,   188,   171,   -2251, -2251, -2251,   -2251, -2251, -2251,     248,   188,   236,     228,     0,   228},
                                        {  223,   236,   171,   -2251, -2251, -2251,   -2251, -2251, -2251,     228,   171,   171,     228,   228,     0},
        };*/
    public static ArrayList<Integer>[] coalitions;
    
    
    public static void main(String[] args) {
        for (int I = 0; I < Tests; I++) {
            coalitions = new ArrayList[4];
            coalitions[0] = new ArrayList<>();
            coalitions[1] = new ArrayList<>();
            coalitions[2] = new ArrayList<>();
            coalitions[3] = new ArrayList<>();
            coalitions[0].add(0); coalitions[0].add(5); coalitions[0].add(10); 
            coalitions[1].add(1); coalitions[1].add(6); coalitions[1].add(11); 
            coalitions[2].add(2); coalitions[2].add(7); coalitions[2].add(12); 
            coalitions[3].add(3); coalitions[3].add(8); coalitions[3].add(13); 
            coalitions[0].add(4); coalitions[0].add(9); coalitions[0].add(14); 
                      
            int[] deviations = IS_in_simple_symmFHG.find_IS(coalitions, utilities);
            for (int i = 0; i <= N; i++) {
                System.out.print(deviations[i]+", ");
            }
            System.out.println("");
        }
    }
    
    
    
    
    
    
    public static void printCoalitions(){
        for (int j = 0; j < coalitions.length; j++) {
            System.out.print(coalitions[j]+"  ");
        }
        System.out.println("");
    }
    
    public static void printUtilities(){
        for (int i = 0; i < utilities.length; i++) {
            for (int j = 0; j < utilities[i].length; j++) {
                System.out.print(" "+utilities[i][j]+",");
            }
            System.out.println("");
        }
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