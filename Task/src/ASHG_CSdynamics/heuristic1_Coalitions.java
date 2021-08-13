package ASHG_CSdynamics;

import java.util.ArrayList;

public class heuristic1_Coalitions {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    /*
    An IS-deviation where the utilities of all agents in the destination coalition increase equals a
    blocking coalition consisting of the deviating agent and the destination coalition
    */
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util) {
        int n = util.length;
        int m = coal.length;
        int o = notElim.size();
        utilities = util;
        coalitions = coal;
        eliminated = notElim;
        curr_utility = curr_util;
        
        for (int c = 0; c < m; c++) {
            for (int k = 0; k < c; k++) {
                if(c==k || coalitions[c].isEmpty() || coalitions[k].isEmpty()){
                    continue;
                }
                if(can_Merge(c,k)){      
                    ArrayList<Integer> tmp = new ArrayList<>(coalitions[c].size()+coalitions[k].size());
                    for (Integer integ : coalitions[c]) {
                        tmp.add(integ);
                    }
                    for (Integer integ : coalitions[k]) {
                        tmp.add(integ);
                    }
                    return tmp;
                }
            }
        }
        return new ArrayList<>();
    }
    
    //not actually an IS-deviation because utilities of agents in coalition need to increase for a blocking coalition
    private static boolean can_Merge (int coalition1, int coalition2){
        int n = coalitions[coalition1].size();
        int m = coalitions[coalition2].size();
        
        for (int i : coalitions[coalition1]){
            int sum = (int)(curr_utility[i]);
            for (int j : coalitions[coalition2]) {
                if(utilities[i][j]>0){
                    sum+=utilities[i][j];
                }
            }
            if(curr_utility[i] >= sum){
                return false;
            }
        } 
        for (int j : coalitions[coalition2]){
            int sum = (int)(curr_utility[j]);
            for (int i : coalitions[coalition1]) {
                if(utilities[j][i]>0){
                    sum+=utilities[j][i];
                }
            }
            if(curr_utility[j] >= sum){
                return false;
            }
        } 
        return true;
    }
}
