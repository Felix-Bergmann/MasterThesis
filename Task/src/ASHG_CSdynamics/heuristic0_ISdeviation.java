package ASHG_CSdynamics;

import java.util.ArrayList;

public class heuristic0_ISdeviation {
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
        
        for (int i : notElim) {
            for (int c = 0; c < m; c++) {
                if(coalitions[c].isEmpty() || coalitions[c].contains(i)){
                    continue;
                }
                if(is_IS_deviation(i,c)){      
                    ArrayList<Integer> tmp = new ArrayList<>(coalitions[c].size()+1);
                    for (Integer integ : coalitions[c]) {
                        tmp.add(integ);
                    }
                    tmp.add(i);
                    return tmp;
                }
            }
        }
        return new ArrayList<>();
    }
    
    
    private static boolean is_IS_deviation(int agent, int coalition){
        double u = compute_utility(agent,coalition);
        if(u<=curr_utility[agent]){
            return false;
        }
        for (int i : coalitions[coalition]){
            /* agent can join if current utility of every i increases
               -> sum < sum + u_i(agent)
               -> 0 < u_i(agent)
               -> can join if utility of i for 'agent' is positive
            */
            if(utilities[i][agent] <= 0){
                return false;
            }
        }       
        return true;
    }
    
    
    private static double compute_utility(int agent, int coalition){
        double sum = 0.0;
        for (int j : coalitions[coalition]){
            sum += utilities[agent][j];
        }
        return sum;
    }
    
}
