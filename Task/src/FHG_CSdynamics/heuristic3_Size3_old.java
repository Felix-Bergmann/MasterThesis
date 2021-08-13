package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristic3_Size3_old {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util) {
        int o = notElim.size();
        if(o>=3){
            utilities = util;
            coalitions = coal;
            eliminated = notElim;
            curr_utility = curr_util;


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
        return new ArrayList<>();
    }
}
