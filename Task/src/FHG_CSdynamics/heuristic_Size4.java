package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristic_Size4 {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util, boolean simple) {
        int o = notElim.size();
        if(o>=4){
            utilities = util;
            coalitions = coal;
            eliminated = notElim;
            curr_utility = curr_util;


            for (int i = 0; i < o; i++) {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < j; k++) {
                        for (int l = 0; l < k; l++) {
                            int u = notElim.get(i);
                            int v = notElim.get(j);
                            int w = notElim.get(k);
                            int x = notElim.get(l);
                            if((utilities[u][v]+utilities[u][w]+utilities[u][x]) / 4.0 > curr_utility[u] && 
                               (utilities[v][u]+utilities[v][w]+utilities[v][x]) / 4.0 > curr_utility[v] && 
                               (utilities[w][u]+utilities[w][v]+utilities[w][x]) / 4.0 > curr_utility[w] && 
                               (utilities[x][u]+utilities[x][v]+utilities[x][w]) / 4.0 > curr_utility[x]){
                                return new ArrayList<>(Arrays.asList(u,v,w,x));
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
