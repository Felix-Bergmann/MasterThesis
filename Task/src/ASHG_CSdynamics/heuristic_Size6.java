package ASHG_CSdynamics;

import java.util.ArrayList;
import java.util.Arrays;

public class heuristic_Size6 {
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> eliminated;
    
    
    public static ArrayList<Integer> find_BlockCoal(ArrayList<Integer>[] coal, int[][] util, ArrayList<Integer> notElim, double[] curr_util) {
        int o = notElim.size();
        if(o>=6){
            utilities = util;
            coalitions = coal;
            eliminated = notElim;
            curr_utility = curr_util;


            for (int i = 0; i < o; i++) {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < j; k++) {
                        for (int l = 0; l < k; l++) {
                            for (int m = 0; m < l; m++) {
                                for (int n = 0; n < m; n++) {
                                    int u = notElim.get(i);
                                    int v = notElim.get(j);
                                    int w = notElim.get(k);
                                    int x = notElim.get(l);
                                    int y = notElim.get(m);
                                    int z = notElim.get(n);
                                    if((utilities[u][v]+utilities[u][w]+utilities[u][x]+utilities[u][y]+utilities[u][z]) > curr_utility[u] && 
                                       (utilities[v][u]+utilities[v][w]+utilities[v][x]+utilities[v][y]+utilities[v][z]) > curr_utility[v] && 
                                       (utilities[w][u]+utilities[w][v]+utilities[w][x]+utilities[w][y]+utilities[w][z]) > curr_utility[w] && 
                                       (utilities[x][u]+utilities[x][v]+utilities[x][w]+utilities[x][y]+utilities[x][z]) > curr_utility[x] &&
                                       (utilities[y][u]+utilities[y][v]+utilities[y][w]+utilities[y][x]+utilities[y][z]) > curr_utility[y] &&
                                       (utilities[z][u]+utilities[z][v]+utilities[z][w]+utilities[z][x]+utilities[z][y]) > curr_utility[z]){
                                        return new ArrayList<>(Arrays.asList(u,v,w,x,y,z));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return new ArrayList<>();
    }
}
