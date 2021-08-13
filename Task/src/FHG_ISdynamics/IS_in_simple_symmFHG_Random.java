package FHG_ISdynamics;

import java.util.ArrayList;
import java.util.Random;

public class IS_in_simple_symmFHG_Random {
    public static double[] curr_utility;
    public static int[] inCoalition;
    public static ArrayList<Integer>[] coalitions;
    public static ArrayList<Integer> not_checked;   
    public static int[][] utilities;
    public static int[] deviate;
    
    
    public static int[] find_IS(ArrayList<Integer>[] coal, int[][] util) {
        int n = util.length;
        int m = coal.length;
        utilities = util;
        coalitions = coal;
        curr_utility = new double[n];
        inCoalition  = new int[n];
        not_checked = new ArrayList<>();
        deviate = new int[n+1];
                        
        for (int i = 0; i < m; i++) {
            for (int j : coal[i]) {
                inCoalition[j] = i;
                curr_utility[j]=compute_utility(j, i);
            }
        }
        
        for (int i = 0; i < n; i++) {
            not_checked.add(i);
        }
        
        Random rand = new Random();
        
        while(!not_checked.isEmpty()){
            int r = rand.nextInt(not_checked.size());
            int i = not_checked.get(r);
            
            ArrayList<Integer> not_checked_coal = new ArrayList<>();
            for (int c = 0; c < m; c++) {
                not_checked_coal.add(c);
            }
            
            
            while(!not_checked_coal.isEmpty()) {
                r = rand.nextInt(not_checked_coal.size());
                int c = not_checked_coal.get(r);
                
                if(coalitions[c].isEmpty() || coalitions[c].contains(i)){
                    not_checked_coal.remove(Integer.valueOf(c));
                    continue;
                }
                if(allow_deviation(i,c)){
                    int leaving_coal = inCoalition[i];
                    coalitions[inCoalition[i]].remove(Integer.valueOf(i));
                    inCoalition[i] = c;
                    coalitions[c].add(i);
                    
                    not_checked = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        not_checked.add(j);
                    }
                    
                    recompute_utilities(leaving_coal);
                    recompute_utilities(c);
                    deviate[n]++;
                    deviate[i]++;
                    break;
                }
                not_checked_coal.remove(Integer.valueOf(c));
            }
            not_checked.remove(Integer.valueOf(i));
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
}
