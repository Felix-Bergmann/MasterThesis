package FHG_CSdynamics;

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
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util, boolean simple) {
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
    
    //not actually an IS-deviation because utilities of agents in coalition need to increase for a blocking coalition
    private static boolean is_IS_deviation(int agent, int coalition){
        double u = compute_utility(agent,coalition);
        if(u<=curr_utility[agent]){
            return false;
        }
        for (int i : coalitions[coalition]){
            /* agent can join if current utility of every i increases (not like in IS deviation, where utilities of agents in coalition can stay the same)
               -> sum/n < (sum + u_i(agent)) / (n+1)
               -> sum * (n+1) / n - sum < u_i(agent)
               -> sum / n < u_i(agent)
               -> can join if current utility of i is smaller then utility of i for 'agent'
            */
            if(curr_utility[i] >= utilities[i][agent]){
                return false;
            }
        }       
        return true;
    }
    
    
    private static double compute_utility(int agent, int coalition){
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
    
    /*
    public static void remove_cliques(ArrayList<Integer>[] coal, int[][] util){
        for (int i = 0; i < coal.length; i++) {
            boolean is_clique = true;
            for (Integer j : coal[i]) {
                for (Integer k : coal[i]) {
                    if(k.equals(j)){
                        continue;
                    }
                    if(util[j][k] <= 0){
                        is_clique = false;
                        break;
                    }
                }
                if(!is_clique){
                    break;
                }
            }
            
            ArrayList<Integer>[] tmp = new ArrayList[coal.length-1];
            int counter = 0;
            for (int j = 0; j < coal.length; j++) {
                if(j!=i){
                    tmp[counter++] = coal[j];
                }
            }
            coal = tmp;
        }
    }*/
}
