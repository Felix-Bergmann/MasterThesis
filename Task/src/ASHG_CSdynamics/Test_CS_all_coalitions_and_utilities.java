package ASHG_CSdynamics;

import java.util.ArrayList;

public class Test_CS_all_coalitions_and_utilities {
    public static int N;   
    public static double heu0 = 0, heu0_excl = 0;
    public static double heu1 = 0, heu1_excl = 0;
    public static double heu2 = 0, heu2_excl = 0;
    public static double heu3 = 0, heu3_excl = 0;
    public static double heu4 = 0, heu4_excl = 0;
    public static double heu5 = 0, heu5_excl = 0;
    public static double heu6 = 0, heu6_excl = 0;
    public static double empty = 0;
    public static double overall = 0;
    public static boolean simple = true;
    
    public static void main(String[] args) {
        for (int i = 1; i < 7; i++) {
            N = i;
            int[][] utilities = new int[N][N];        
            System.out.println("------------------------\n "+N);
            //long start_time = System.currentTimeMillis();
            rec_Util(0, 0, utilities);
            //long end_time = System.currentTimeMillis();
            //System.out.println((end_time - start_time)+" ms");
            System.out.println(" ("+overall+"): heu0 finds "+heu0+"(excl: "+heu0_excl+") | heu1 finds "+heu1+"(excl: "+heu1_excl+") | heu2 finds "+heu2+"(excl: "+heu2_excl+") | heu3 finds "+heu3+"(excl: "+heu3_excl+") | \n          heu4 finds "+heu4+"(excl: "+heu4_excl+") | heu5 finds "+heu5+"(excl: "+heu5_excl+") | heu6 finds "+heu6+"(excl: "+heu6_excl+")  / "+empty);
            heu0 = 0; heu0_excl = 0;
            heu1 = 0; heu1_excl = 0;
            heu2 = 0; heu2_excl = 0;
            heu3 = 0; heu3_excl = 0;
            heu4 = 0; heu4_excl = 0;
            heu5 = 0; heu5_excl = 0;
            heu6 = 0; heu6_excl = 0;
            empty = 0;
            overall = 0;
            }
    }    
    
    
    
    //recursive function to get all possible utility-matrices
    public static void rec_Util(int i, int j, int[][] utilities){
        if(i==N){
            ArrayList<Integer>[] coalitions = new ArrayList[N];
            for (int k = 0; k < N; k++) {
                coalitions[k] = new ArrayList<>();
            }
            //printUtilities(utilities);
            add_Element_n(0, utilities, coalitions);
            return;
        }
        if(j==i){
            rec_Util(i+1, 0, utilities);
            return;
        }
        rec_Util(i, j+1, utilities);
        utilities[i][j] = 1;
        utilities[j][i] = 1;
        rec_Util(i, j+1, utilities);
        utilities[i][j] = 0;
        utilities[j][i] = 0;
        
    }
    
    
    
    //recursive function to get all possible starting coalition and check for blocking coalition if size equals N
    public static void add_Element_n(int n, int[][] utilities, ArrayList<Integer>[] coalitions){
        if(n==N){
            blocking_Coalition_exists(utilities, coalitions);
            return;
        }
        
        for (int i = 0; i < coalitions.length; i++) {
            if(coalitions[i].isEmpty()){
                coalitions[i].add(n);
                add_Element_n(n+1, utilities, coalitions);
                coalitions[i].remove(Integer.valueOf(n));
                break;
            }else{
                coalitions[i].add(n);
                add_Element_n(n+1, utilities, coalitions);
                coalitions[i].remove(Integer.valueOf(n));
            }
        }
    }
    
    
    
    public static void blocking_Coalition_exists(int[][] utilities, ArrayList<Integer>[] coalitions){
        ArrayList<Integer> notEliminated = new ArrayList<>();
        double[] curr_utility = new double[N];
        overall++;
        Preprocessing.perform(coalitions, utilities, notEliminated, curr_utility);
        
        ArrayList<Integer> blocking_coal0 = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
        ArrayList<Integer> blocking_coal1 = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
        ArrayList<Integer> blocking_coal2 = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
        ArrayList<Integer> blocking_coal3 = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
        
        ArrayList<Integer> blocking_coal4 = heuristic_Size4.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
        ArrayList<Integer> blocking_coal5 = heuristic_Size5.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
        ArrayList<Integer> blocking_coal6 = heuristic_Size6.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                
        if(!blocking_coal0.isEmpty()){
            heu0++;
            if(blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty()){
                heu0_excl++;
            }
        }
        if(!blocking_coal1.isEmpty()){
            heu1++;
            if(blocking_coal0.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty()){
                heu1_excl++;
            }
        }
        if(!blocking_coal2.isEmpty()){
            heu2++;
            if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal3.isEmpty()){
                heu2_excl++;
            }
        }
        if(!blocking_coal3.isEmpty()){
            heu3++;
            if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal2.isEmpty()){
                heu3_excl++;
            }
        }
        
        /*
        if(!blocking_coal4.isEmpty()){
            heu4++;
            if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty() && blocking_coal5.isEmpty() && blocking_coal6.isEmpty()){
                heu4_excl++;
            }
        }
        if(!blocking_coal5.isEmpty()){
            heu5++;
            if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty() && blocking_coal4.isEmpty() && blocking_coal6.isEmpty()){
                heu5_excl++;
            }
        }
        if(!blocking_coal6.isEmpty()){
            heu6++;
            if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty() && blocking_coal4.isEmpty() && blocking_coal5.isEmpty()){
                heu6_excl++;
            }
        }
        */
        if(blocking_coal0.isEmpty() && blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty()){
            empty++;
        }
        /*
        if(blocking_coal1.isEmpty() && blocking_coal2.isEmpty() && blocking_coal3.isEmpty() && blocking_coal4.isEmpty() && blocking_coal5.isEmpty() && blocking_coal6.isEmpty()){
            empty++;
        }
        */
    }
    
    
    
    public static void printCoalitions(ArrayList<Integer>[] coalitions){
        for (int i = 0; i < coalitions.length; i++) {
            System.out.print(coalitions[i] + "  ");
        }
        System.out.println(" ");
    }
    
    public static void printUtilities(int[][] utilities){
        for (int i = 0; i < utilities.length; i++) {
            for (int j = 0; j < utilities[i].length; j++) {
                System.out.print(" "+utilities[i][j]+",");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
