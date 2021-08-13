package FHG_ISdynamics;


import java.util.ArrayList;

public class IS_in_simple_symmFHG_overall_Utility {
    public static double[] curr_utility;
    public static int[] inCoalition;
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static int[] deviate;
    
    
    public static int[] find_IS(ArrayList<Integer>[] coal, int[][] util) {
        int n = util.length;
        int m = coal.length;
        utilities = util;
        coalitions = coal;
        curr_utility = new double[n];
        inCoalition  = new int[n];
        deviate = new int[n+1];
                       
        
        for (int i = 0; i < m; i++) {
            for (int j : coal[i]) {
                inCoalition[j] = i;
                curr_utility[j]=compute_utility(j, i);
            }
        }
        
        boolean deviation_possible = true;
        while(deviation_possible){
            deviation_possible = false;
            int deviating_agent = -1;
            int dest_coalition = -1;
            double util_increase = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                for (int c = 0; c < m; c++) {
                    if(coalitions[c].isEmpty() || coalitions[c].contains(i)){
                        continue;
                    }
                    if(allow_deviation(i,c)){
                        deviation_possible = true;
                        double u = compute_utility(i,c) - curr_utility[i];
                        double v = compute_difference_with(c,i);
                        double w = compute_difference_without(inCoalition[i], i);
                        if(u + v - w > util_increase){
                            deviating_agent = i;
                            dest_coalition = c;
                            util_increase = u + v - w;
                        }
                    }
                }
            }
            if(deviation_possible){
                int leaving_coal = inCoalition[deviating_agent];
                coalitions[inCoalition[deviating_agent]].remove(Integer.valueOf(deviating_agent));
                inCoalition[deviating_agent] = dest_coalition;
                coalitions[dest_coalition].add(deviating_agent);
                recompute_utilities(leaving_coal);
                recompute_utilities(dest_coalition);
                deviate[n]++;
                deviate[deviating_agent]++;
            }
        }
        return deviate;
    }
    
    public static boolean allow_deviation(int agent, int coalition){
        double u = compute_utility(agent,coalition);
        if(u<=curr_utility[agent]){
            return false;
        }
        for (int i : coalitions[coalition]){
            /* agent can join if current utility of any i won't fall
               -> sum/n <= (sum+u_i(agent))/(n+1)
               -> sum*(n+1)/n - sum <= u_i(agent)
               -> sum / n <= u_i(agent)
               -> can join if current utility of i is smaller/equal then utility of 'agent' for i
            */
            if(curr_utility[i] > utilities[i][agent]){
                return false;
            }
        }       
        return true;
    }
    
    public static double compute_utility(int agent, int coalition){
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
    
    public static void recompute_utilities(int coalition){
        for(int j : coalitions[coalition]){
            curr_utility[j] = compute_utility(j,coalition);
        }
    }
    
    public static double compute_difference_without(int coalition, int agent){
        double diff = 0.0;
        for (int j : coalitions[coalition]){
            if(j != agent){
                double tmp_util = curr_utility[j] * coalitions[coalition].size();
                tmp_util -= utilities[j][agent];
                tmp_util /= (coalitions[coalition].size()-1);
                diff += curr_utility[j] - tmp_util;
            }
        }
        return diff;
    }
    
    public static double compute_difference_with(int coalition, int agent){
        double diff = 0.0;
        for (int j : coalitions[coalition]){
            if(j != agent){
                double tmp_util = curr_utility[j] * coalitions[coalition].size();
                tmp_util += utilities[j][agent];
                tmp_util /= (coalitions[coalition].size()+1);
                diff += tmp_util - curr_utility[j];
            }
        }
        return diff;
    }
    
}
