package randomCoalition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Partition_StirlingNumber2 {
    private static double[][] Stirling_Table;
    private static double[] Bell_Number;
        
    public static ArrayList<ArrayList<Integer>> randomPartition(int n){
        create_table(n);
        
        //select a random k with probabilities according to the Stirling numbers of second kind
        Random rand = new Random();
        int k = -1;
        double r = rand.nextDouble()*Bell_Number[n];
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum+=Stirling_Table[n][i];
            if(r < sum){
                k = i;
                break;
            }
        }
        return recursivePartition(n,k);
    }
    
    
    
    /*
    In a bottom-up order, choose a random path to (n=1,k=1) and build the partition accordingly
    "With probability {n−1,k−1}/{n,k} put n in its own set, and make the rest a partition of 
    n−1 elements into k−1 sets chosen uniformly at random. 
    Otherwise generate a uniform random partition of n−1 elements into k sets, and insert n 
    into a set uniformly chosen from those sets."
    (https://mathoverflow.net/questions/141999/how-to-efficiently-sample-uniformly-from-the-set-of-p-partitions-of-an-n-set)
    */
    private static ArrayList<ArrayList<Integer>> recursivePartition(int n, int k){
        if(n == 1){
            return new ArrayList<>(Arrays.asList((new ArrayList<>(Arrays.asList(0)))));
        }
        Random rand = new Random();
        long r = (long)(rand.nextDouble()*Stirling_Table[n][k]);
        if(r < Stirling_Table[n-1][k-1]){
            ArrayList<ArrayList<Integer>> tmp = recursivePartition(n-1, k-1);
            tmp.add(new ArrayList<>(Arrays.asList(n-1)));
            return tmp;
        }else{
            ArrayList<ArrayList<Integer>> tmp = recursivePartition(n-1, k);
            int ra = rand.nextInt(k);
            tmp.get(ra).add(n-1);
            return tmp;
        }
    }
    
    
    
    /*
    function create_table fills out the table with the Stirling Numbers of the second kind
    with a dynamic programming approach
    additionally, for each n it computes the sums of these values over all possible k's
    */
    private static void create_table(int n){
        Stirling_Table = new double[n+1][n+1];
        Bell_Number = new double[n+1];
        
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= i; j++){
                if (i == j || j == 1){
                    Stirling_Table[i][j] = 1.0;
                    Bell_Number[i]++;
                }else{
                    Stirling_Table[i][j] = Stirling_Table[i-1][j] * j + Stirling_Table[i-1][j-1];
                    Bell_Number[i]+= Stirling_Table[i][j];
                }
            }
        }
    }
    
    // random partition with uniformly random k between from and to (to exclusive) 
    public static ArrayList<ArrayList<Integer>> randomPartition_from_to(int n, int from, int to){
        create_table(n);
        
        Random rand = new Random();
        int k = from + (int)Math.floor(rand.nextDouble()*(to - from));
        
        return recursivePartition(n,k);
    }
    
}
