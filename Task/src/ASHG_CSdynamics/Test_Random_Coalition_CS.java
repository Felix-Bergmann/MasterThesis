package ASHG_CSdynamics;

import java.util.ArrayList;
import java.util.Random;
import randomCoalition.Partition_StirlingNumber2;

public class Test_Random_Coalition_CS {
    public static int Tests = 1000;
    public static int Tests_coalitions = 1000;
    public static ArrayList<Integer>[] coalitions;
    public static int[][] utilities;
    public static double[] curr_utility;
    public static ArrayList<Integer> notEliminated;
    public static boolean simple = true;
    
    public static long[] times;
    
    
    public static void main(String[] args) {
        for (int N = 50; N <= 50; N++) {
            
            times = new long[24];
            long start_time = System.currentTimeMillis();
            long end_time = System.currentTimeMillis();
            ArrayList<Integer> blocking_coal = new ArrayList<>();
            
            int cs = 0;
            utilities = new int[N][N];        

            //long start_time = System.currentTimeMillis();
            for (int I = 0; I < Tests; I++) {
                Random rand = new Random();

                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(i!=j){
                            utilities[i][j] = rand.nextInt(2);
                            utilities[j][i] = utilities[i][j];
                        }
                    }
                }
                
                for (int T = 0; T < Tests_coalitions; T++) {
                    ArrayList<ArrayList<Integer>> tmp = Partition_StirlingNumber2.randomPartition(N);
                    coalitions = new ArrayList[tmp.size()];

                    for (int i = 0; i < tmp.size(); i++) {
                        coalitions[i] = new ArrayList<>();
                        for (int j = 0; j < tmp.get(i).size(); j++){
                            coalitions[i].add(tmp.get(i).get(j));
                        }
                    }
                    curr_utility = new double[N];
                    notEliminated = new ArrayList<>();
                    
                    
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    

                    Preprocessing.perform(coalitions, utilities, notEliminated, curr_utility);
                    
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[12] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[13] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[14] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[15] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[16] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[17] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[18] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[19] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[20] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[21] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[22] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[23] += (end_time - start_time);
                    
                    
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[0] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[1] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[2] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal =  heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[3] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[4] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[5] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[6] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[7] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[8] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[9] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[10] += (end_time - start_time);
                    
                    start_time = System.currentTimeMillis();
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                        if(blocking_coal.isEmpty()){
                            blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                            if(blocking_coal.isEmpty()){
                                blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                            }
                        }
                    }
                    end_time = System.currentTimeMillis();
                    times[11] += (end_time - start_time);
                    
                    
                    
                    if(blocking_coal.isEmpty()){
                        //cs++;
                        //System.out.println("This partition does not admit a blocking coalition!");
                    }else{
                        //printCoalitions();
                        //System.out.println("blocked by "+blocking_coal);
                    }

                }
            }
            for (int i = 0; i < 24; i++) {
                System.out.println("  "+i+" & "+times[i]+" \\\\");
            }
            //System.out.println("  "+N+" & "+(100.0*cs / (Tests * Tests_coalitions))+ " \\\\");
        }
    }    
    
    
    public static void printCoalitions(){
        for (int j = 0; j < coalitions.length; j++) {
            System.out.print(coalitions[j]+"  ");
        }
        System.out.println("");
    }
    
    public static void printUtilities(){
        for (int i = 0; i < utilities.length; i++) {
            for (int j = 0; j < utilities[i].length; j++) {
                System.out.print(" "+utilities[i][j]+",");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public static void combineArrays (int[] first, int[] second){
        for (int i = 0; i < second.length-1; i++) {
            if(second[i]>0){
                first[0]++;
            }
        }
        first[1]+=second[second.length-1];
    }
}
