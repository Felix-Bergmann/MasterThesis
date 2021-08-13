package randomCoalition;

import java.util.ArrayList;
import java.util.Arrays;

public class Partition_hopefully_CS {
    
    //greedy approach to build a partion consisting of cliques of size 3 and one coalition containing the other agents
    public static ArrayList<ArrayList<Integer>> generateTrios(int n, int[][] utilities){
        ArrayList<ArrayList<Integer>> partition = new ArrayList<>();
        boolean[] discovered = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if(!discovered[i]){
                for (int j = 0; j < i; j++) {
                    if(!discovered[j]){
                        for (int k = 0; k < j; k++) {
                            if(!discovered[k] && utilities[i][j]>0 &&
                               utilities[i][k]>0 && utilities[j][k]>0){
                                discovered[k] = true;
                                discovered[j] = true;
                                discovered[i] = true;
                                partition.add(new ArrayList<>(Arrays.asList(k,j,i)));
                            }
                        }
                    }
                }
            }
        }
        
        ArrayList<Integer> last = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(!discovered[i]){
                last.add(i);
            }
        }
        partition.add(last);
        
        return partition;
    }
}
