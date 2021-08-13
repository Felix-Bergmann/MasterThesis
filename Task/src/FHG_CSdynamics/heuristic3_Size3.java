package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristic3_Size3 {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util, boolean simple) {
        int o = notElim.size();
        if(o>=3){
            utilities = util;
            coalitions = coal;
            curr_utility = curr_util;
            if(simple){
                ArrayList<Integer> notElimi = new ArrayList<>();
                for (int i = 0; i < o; i++) {
                    int k = notElim.get(i);
                    if(curr_utility[k] < (2.0/3.0)){
                        notElimi.add(k);
                    }
                }


                for (int i = 0; i < notElimi.size(); i++) {
                    for (int j = 0; j < i; j++) {
                        for (int k = 0; k < j; k++) {
                            int l = notElimi.get(i);
                            int m = notElimi.get(j);
                            int n = notElimi.get(k);
                            if((utilities[l][m]+utilities[l][n]) / 3.0 > curr_utility[l] && 
                               (utilities[m][l]+utilities[m][n]) / 3.0  > curr_utility[m] && 
                               (utilities[n][l]+utilities[n][m]) / 3.0 > curr_utility[n]){
                                return new ArrayList<>(Arrays.asList(l,m,n));
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < o; i++) {
                    for (int j = 0; j < i; j++) {
                        for (int k = 0; k < j; k++) {
                            int l = notElim.get(i);
                            int m = notElim.get(j);
                            int n = notElim.get(k);
                            if((utilities[l][m]+utilities[l][n]) / 3.0 > curr_utility[l] && 
                               (utilities[m][l]+utilities[m][n]) / 3.0  > curr_utility[m] && 
                               (utilities[n][l]+utilities[n][m]) / 3.0 > curr_utility[n]){
                                return new ArrayList<>(Arrays.asList(l,m,n));
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
