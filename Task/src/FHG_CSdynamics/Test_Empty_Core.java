package FHG_CSdynamics;

import java.util.ArrayList;
import randomCoalition.Partition_StirlingNumber2;

public class Test_Empty_Core {
    public static int Tests = 1000;
    public static int N;
    public static ArrayList<Integer>[] coalitions;
    public static double[] curr_utility;
    public static ArrayList<Integer> notEliminated;
    public static boolean simple = true;
    public static int[] coalition_size_appear;
    
    
    /*
    //Counterexample for simple, symmetric FHG with empty core [Aziz et al. 2017]
    public static int[][] utilities = {{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,1,1 ,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,1,1 ,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,1,1 ,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0 ,1,1,1,1,0,0,0,0,0,0 ,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,1,0,1,0,0,0,0,0,0,0,0,0 ,1,1,1,1,0,0,0,0,0,0 ,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,1,1,0,0,0,0,0,0,0,0,0,0 ,1,1,1,1,0,0,0,0,0,0 ,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,0,1,1,0,0,0,0,0,0 ,0,0,1,1,1,1,0,0,0,0 ,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,1,0,1,0,0,0,0,0,0 ,0,0,1,1,1,1,0,0,0,0 ,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0 ,0,0,1,1,1,1,0,0,0,0 ,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,0,1,1,0,0,0 ,0,0,0,0,1,1,1,1,0,0 ,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0 ,0,0,0,0,1,1,1,1,0,0 ,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,1,0,0,0,0 ,0,0,0,0,1,1,1,1,0,0 ,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1 ,0,0,0,0,0,0,1,1,1,1 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1 ,0,0,0,0,0,0,1,1,1,1 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,1,1,0 ,0,0,0,0,0,0,1,1,1,1 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                       
                                       {1,1,1,1,1,1,0,0,0,0,0,0,0,0,0 ,0,1,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                       {1,1,1,1,1,1,0,0,0,0,0,0,0,0,0 ,1,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                                       {0,0,0,1,1,1,1,1,1,0,0,0,0,0,0 ,0,0,0,1,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                       {0,0,0,1,1,1,1,1,1,0,0,0,0,0,0 ,0,0,1,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
                                       {0,0,0,0,0,0,1,1,1,1,1,1,0,0,0 ,0,0,0,0,0,1,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                       {0,0,0,0,0,0,1,1,1,1,1,1,0,0,0 ,0,0,0,0,1,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                                       {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 ,0,0,0,0,0,0,0,1,0,0 ,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,1,1,1,1,1 ,0,0,0,0,0,0,1,0,0,0 ,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {1,1,1,0,0,0,0,0,0,0,0,0,1,1,1 ,0,0,0,0,0,0,0,0,0,1 ,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                                       {1,1,1,0,0,0,0,0,0,0,0,0,1,1,1 ,0,0,0,0,0,0,0,0,1,0 ,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0},
                                       
                                       {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,0,0 ,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,0,0 ,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {1,1,1,0,0,0,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,0,0 ,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,1,1 ,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,1,1 ,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,1,1,1,0,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,1,1 ,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,1,1,1,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,1,1,1,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,1,1,1,0,0,0,0,0,0 ,1,1,0,0,0,0,0,0,0,0 ,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0 ,0,0,1,1,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0 ,0,0,1,1,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,1,1,1,0,0,0 ,0,0,1,1,0,0,0,0,0,0 ,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1 ,0,0,0,0,1,1,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1 ,0,0,0,0,1,1,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1},
                                       {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1 ,0,0,0,0,1,1,0,0,0,0 ,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
                                       };
    
    */
    
    /*
    //Counterexample where Clique of size 4 is not found, because every agent is in a clique of 3
    public static int[][] utilities = {{0,1,1,1,0,0,1,0,0,1,0,0},
                                       {1,0,1,0,0,0,0,0,0,0,0,0},
                                       {1,1,0,0,0,0,0,0,0,0,0,0},
                                       {1,0,0,0,1,1,1,0,0,1,0,0},
                                       {0,0,0,1,0,1,0,0,0,0,0,0},
                                       {0,0,0,1,1,0,0,0,0,0,0,0},
                                       {1,0,0,1,0,0,0,1,1,1,0,0},
                                       {0,0,0,0,0,0,1,0,1,0,0,0},
                                       {0,0,0,0,0,0,1,1,0,0,0,0},
                                       {1,0,0,1,0,0,1,0,0,0,1,1},
                                       {0,0,0,0,0,0,0,0,0,1,0,1},
                                       {0,0,0,0,0,0,0,0,0,1,1,0}};
    */
    
    
    //Counterexample for ASHG with empty core [Aziz, Brandt and Seedig 2013]
    public static int[][] utilities = {{  0,  6,  4,-33,  4,  5},
                                       {  6,  0,  5,-33,-33,-33},
                                       {  4,  5,  0,  6,  4,-33},
                                       {-33,-33,  6,  0,  5,-33},
                                       {  4,-33,  4,  5,  0,  6},
                                       {  5,-33,-33,-33,  6,  0}};
    
    
    public static void main(String[] args) {
        N = utilities.length;
        coalition_size_appear = new int[N+1];
        int cs = 0;
        int block = 0;
        for (int I = 0; I < Tests; I++) {
            //ArrayList<ArrayList<Integer>> tmp = Partition_StirlingNumber2.randomPartition_from_to(N, 30, 41);
            
            ArrayList<ArrayList<Integer>> tmp = Partition_StirlingNumber2.randomPartition(N);
            coalitions = new ArrayList[tmp.size()];

            for (int i = 0; i < tmp.size(); i++) {
                coalitions[i] = new ArrayList<>();
                for (int j = 0; j < tmp.get(i).size(); j++){
                    coalitions[i].add(tmp.get(i).get(j));
                }
            }
            coalition_size_appear[tmp.size()]++;
            
            
            curr_utility = new double[N];
            notEliminated = new ArrayList<>();

            Preprocessing.perform(coalitions, utilities, notEliminated, curr_utility, simple);
            
            ArrayList<Integer> blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
            if(blocking_coal.isEmpty()){
                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                if(blocking_coal.isEmpty()){
                    blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    }
                }
            }
            if(blocking_coal.isEmpty()){
                cs++;
                //printCoalitions();
                //System.out.println("  is core stable");
            }else{
                block++;
                //printCoalitions();
                //System.out.println("  Blocked by "+blocking_coal);
            }

        }
        
        System.out.println(block+" / "+cs+" out of "+Tests+" Tests");
        System.out.println("Appearances of coalition sizes (N="+N+"): ");
        for (int i = 1; i <= N; i++) {
            if(coalition_size_appear[i]!=0){
                System.out.println(i+": "+coalition_size_appear[i]);
            }
        }
    }
    
    
    
    public static void printCoalitions(){
        for (int j = 0; j < coalitions.length; j++) {
            System.out.print(coalitions[j]+"  ");
        }
        System.out.println("");
    }
    
    public static void printUtilities(){
        System.out.println("{");
        for (int i = 0; i < utilities.length; i++) {
            System.out.print("{");
            for (int j = 0; j < utilities[i].length; j++) {
                System.out.print(" "+utilities[i][j]+",");
            }
            System.out.println("}");
        }
        System.out.println("};");
    }
}
