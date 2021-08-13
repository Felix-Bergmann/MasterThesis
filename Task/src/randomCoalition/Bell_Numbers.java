package randomCoalition;

import java.math.BigInteger;

public class Bell_Numbers {
    /*public final double[] numbers = {1, 2, 5, 15, 52, 203, 877, 4140, 21147, 115975, 
                                       678570, 4213597, 27644437, 190899322, 1382958545, 10480142147, 82864869804, 682076806159, 5832742205057, 51724158235372};
    */
    
    public static void main(String[] args) {
        System.out.println(" 0: 1 * 2^0 = 1\n 1: 1 * 2^1 = 2");
        BigInteger[] lastline = {BigInteger.ONE};
        BigInteger[] newline;
        for (int i = 2; i < 51; i++) {
            newline = new BigInteger[i];
            newline[0] = lastline[lastline.length-1];
            for (int j = 1; j < i; j++) {
                newline[j] = newline[j-1].add(lastline[j-1]);
            }
            if(i<10){
                System.out.print(" ");
            }
            System.out.println(i+": "+newline[i-1]+" * 2^"+i+" = "+(newline[i-1].multiply( BigInteger.valueOf((long)Math.pow(2, (i*(i-1)/2.0))) )));
            lastline = newline;
        }
        System.out.println(Math.pow(2.0, 222));
    }
}
