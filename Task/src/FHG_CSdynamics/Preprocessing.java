package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Preprocessing {   
    
    public static void perform(ArrayList<Integer>[] coalitions, int[][] utilities, ArrayList<Integer> notEliminated, double[] curr_utility, boolean simple) {
        int n = utilities.length;
        int m = coalitions.length;
        
        int[] connections = new int[n];
        
        for (int i = 0; i < coalitions.length; i++) {
            for (int j : coalitions[i]) {
                curr_utility[j]=compute_utility(j, i, coalitions, utilities);
            }
        }
        
        //check and eliminate agents if they are already in their best possible coalition
        for (int i = 0; i < n; i++) {
            double best = 0.0;
            if(simple){
                double count_ones = 0.0;
                for (int j = 0; j < n; j++) {
                    if(utilities[i][j] > 0){
                        count_ones++;
                        connections[i]++;
                    }
                    
                }
                best = count_ones / (count_ones+1);
            }else{
                int[] a = (int[])utilities[i].clone();
                Arrays.sort(a);
                
                for (int j = n-1; j >= 0; j--) {
                    if(a[j] > best){
                        best *= n-j;
                        best += a[j];
                        best /= n-j+1;
                    }
                    if(utilities[i][j]>0){
                        connections[i]++;
                    }
                }
            }
            if(curr_utility[i] < best){
                notEliminated.add(i);
            }
            notEliminated.sort(new Comparator<Integer>(){
                @Override
                public int compare(Integer i1, Integer i2){
                    return (connections[i2] - connections[i1]);
                }
            });
        }
    }
    
    
     private static double compute_utility(int agent, int coalition, ArrayList<Integer>[] coalitions, int[][] utilities){
        double sum = 0.0;
        boolean contains_agent = false;
        for (int j : coalitions[coalition]){
            if(agent == j){
                contains_agent = true;
            }
            sum += utilities[agent][j];
        }
        
        if(contains_agent){
            return sum / coalitions[coalition].size();
        }else{
            return sum / (coalitions[coalition].size()+1);
        }
    }
}
