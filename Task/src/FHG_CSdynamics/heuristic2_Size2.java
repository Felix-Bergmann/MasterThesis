package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristic2_Size2 {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util, boolean simple) {
        int o = notElim.size();
        if(o>=2){
            utilities = util;
            coalitions = coal;
            curr_utility = curr_util;
            if(simple){
                ArrayList<Integer> notElimi = new ArrayList<>();
                for (int i = 0; i < o; i++) {
                    int k = notElim.get(i);
                    if(curr_utility[k] < 0.5){
                        notElimi.add(k);
                    }
                }

                for (int i = 0; i < notElimi.size(); i++) {
                    int k = notElimi.get(i);
                    for (int j = 0; j < i; j++) {
                        int l = notElimi.get(j);
                        if(utilities[k][l] / 2.0 > curr_utility[k] && utilities[l][k] / 2.0 > curr_utility[l]){
                            return new ArrayList<>(Arrays.asList(k,l));
                        }
                    }
                }
                
                
            } else {
                for (int i = 0; i < o; i++) {
                    int k = notElim.get(i);
                    for (int j = 0; j < i; j++) {
                        int l = notElim.get(j);
                        if(utilities[k][l] / 2.0 > curr_utility[k] && utilities[l][k] / 2.0 > curr_utility[l]){
                            return new ArrayList<>(Arrays.asList(k,l));
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
