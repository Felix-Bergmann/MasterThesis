package ASHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class Preprocessing {   
    
    public static void perform(ArrayList<Integer>[] coalitions, int[][] utilities, ArrayList<Integer> notEliminated, double[] curr_utility) {
        int n = utilities.length;
        
        for (int i = 0; i < coalitions.length; i++) {
            for (int j : coalitions[i]) {
                curr_utility[j]=compute_utility(j, i, coalitions, utilities);
            }
        }
        
        //check and eliminate agents if they are already in their best possible coalition
        for (int i = 0; i < n; i++) {            
            double best = 0.0;
            for (int j = 0; j < n; j++) {
                if(utilities[i][j] > 0){
                    best += utilities[i][j];
                }
            }
            if(curr_utility[i] < best){
                notEliminated.add(i);
            }else{
                //System.out.println(" eliminated "+i);
            }
        }
    }
    
    
     private static double compute_utility(int agent, int coalition, ArrayList<Integer>[] coalitions, int[][] utilities){
        double sum = 0.0;
        for (int j : coalitions[coalition]){
            sum += utilities[agent][j];
        }
        return sum;
    }
}
