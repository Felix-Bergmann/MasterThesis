package randomCoalition;

import java.math.BigInteger;
import java.util.Scanner;

public class StirlingNumber2 {
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.print("n = ");
        int n = scan.nextInt();
        System.out.print("k = ");
        int k = scan.nextInt();
        System.out.println("StirlingNumber2 : "+S(n,k));
    }
    
    public static BigInteger S(int n, int k){        
        BigInteger[][] dp = new BigInteger[n+1][k+1];
     
        // Base cases
        for (int i = 0; i <= n; i++){
            dp[i][0] = BigInteger.ZERO;
        }
        for (int i = 0; i <= k; i++){
            dp[0][k] = BigInteger.ZERO;
        }
     
        // Fill rest of the entries in dp[][]
        // in bottom up manner
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= k; j++){
                if (i == j || j == 1){
                    dp[i][j] = BigInteger.ONE;
                }else if (j > i){
                    dp[i][j] = BigInteger.ZERO;
                }else{
                    dp[i][j] = (dp[i - 1][j].multiply(BigInteger.valueOf(j))).add(dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n][k];
           
    }
}
