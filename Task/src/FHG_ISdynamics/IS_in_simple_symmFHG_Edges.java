package FHG_ISdynamics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IS_in_simple_symmFHG_Edges {
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
        
        
        List<Pair> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if(utilities[i][j]>0){
                    count++;
                }
            }
            edges.add(new Pair(i,count));
        }
        
        //Ascending p1.y - p2.y
        //Descending p2.y - p1.y
        Collections.sort(edges, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return (int)Math.signum(p2.y-p1.y);
            }
        });
        
        
        int i = 0;
        while(i < n){
            int agent = edges.get(i).x;
            for (int c = 0; c < m; c++) {
                if(coalitions[c].isEmpty() || coalitions[c].contains(agent)){
                    continue;
                }
                if(allow_deviation(agent,c)){
                    int leaving_coal = inCoalition[agent];
                    coalitions[inCoalition[agent]].remove(Integer.valueOf(agent));
                    inCoalition[agent] = c;
                    coalitions[c].add(agent);
                    recompute_utilities(leaving_coal);
                    recompute_utilities(c);
                    deviate[n]++;
                    deviate[agent]++;
                    i = -1;
                    break;
                }
            }
            i++;
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
    
    private static class Pair{
        public int x;
        public int y;
        
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
