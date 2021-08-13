package ASHG_CSdynamics;

import java.util.ArrayList;

public class Test_Empty_Core {
    public static int N;
    public static ArrayList<Integer>[] coalitions;
    
    public static boolean simple = false;
    
    public static int[][] utilities = {{  0,  6,  4,-33,  4,  5},
                                       {  6,  0,  5,-33,-33,-33},
                                       {  4,  5,  0,  6,  4,-33},
                                       {-33,-33,  6,  0,  5,-33},
                                       {  4,-33,  4,  5,  0,  6},
                                       {  5,-33,-33,-33,  6,  0}};
    
    public static void main(String[] args) {
        N = utilities.length;
        ArrayList<Integer>[] coalitions = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            coalitions[i] = new ArrayList<>();
        }
        //printUtilities();
        
        add_Element_n(0, coalitions);
    }    
    
    //recursive function to get a random starting coalition and check for blocking coalition if size equals N
    public static void add_Element_n(int n, ArrayList<Integer>[] coalitions){
        if(n==N){
            blocking_Coalition_exists(coalitions);
            return;
        }
        
        for (int i = 0; i < coalitions.length; i++) {
            if(coalitions[i].isEmpty()){
                coalitions[i].add(n);
                add_Element_n(n+1, coalitions);
                coalitions[i].remove(Integer.valueOf(n));
                break;
            }else{
                coalitions[i].add(n);
                add_Element_n(n+1, coalitions);
                coalitions[i].remove(Integer.valueOf(n));
            }
        }
    }
    
    
    
    public static boolean blocking_Coalition_exists(ArrayList<Integer>[] coalitions){
        double[] curr_utility = new double[N];
        ArrayList<Integer> notEliminated = new ArrayList<>();
        Preprocessing.perform(coalitions, utilities, notEliminated, curr_utility);
        
        ArrayList<Integer> blocking_coal = heuristic2_Size2.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
        if(blocking_coal.isEmpty()){
            blocking_coal = heuristic1_Coalitions.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
            if(blocking_coal.isEmpty()){
                blocking_coal = heuristic3_Size3.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                if(blocking_coal.isEmpty()){
                    blocking_coal = heuristic0_ISdeviation.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility);
                    if(blocking_coal.isEmpty()){
                        blocking_coal = heuristicTMP_Size1.find_BlockCoal(coalitions, utilities, notEliminated, curr_utility, simple);
                    }
                }
            }
        }
                    
                    
        //printCoalitions(coalitions);
        if(blocking_coal.isEmpty()){
            System.out.println(" -> no blocking coalition found");
        }else{
           System.out.println(" blocked by "+blocking_coal+"\n");
        }
        //System.out.println("");
        
        
        return !blocking_coal.isEmpty();
    }
    
    
    
    public static void printCoalitions(ArrayList<Integer>[] coalitions){
        for (int i = 0; i < coalitions.length; i++) {
            System.out.print(coalitions[i] + "  ");
        }
        System.out.println(" ");
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
}

