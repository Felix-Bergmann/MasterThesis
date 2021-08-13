package ASHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristicTMP_Size1 {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util, boolean simple) {
        int o = notElim.size();
        utilities = util;
        coalitions = coal;
        eliminated = notElim;
        curr_utility = curr_util;

        if(!simple){
            for (int i = 0; i < o; i++) {
                int k = notElim.get(i);
                if(curr_utility[k] < 0){
                    return new ArrayList<>(Arrays.asList(k));
                }
            }
        }
        return new ArrayList<>();
    }
}
