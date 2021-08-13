package FHG_ISdynamics;


import java.util.ArrayList;

public class IS_in_symmFHG {
    public static double[] curr_utility;
    public static int[] inCoalition;
    public static ArrayList<Integer>[] coalitions;
    public static final double[][] utilities = { {    0,  436,-2251,-2251,  436,   228,  248,-2251,-2251,  223,   228,  228,-2251,-2251,  223},
                                                 {  436,    0,  436,-2251,-2251,   223,  228,  248,-2251,-2251,   223,  228,  228,-2251,-2251},
                                                 {-2251,  436,    0,  436,-2251, -2251,  223,  228,  248,-2251, -2251,  223,  228,  228,-2251},
                                                 {-2251,-2251,  436,    0,  436, -2251,-2251,  223,  228,  248, -2251,-2251,  223,  228,  228},
                                                 {  436,-2251,-2251,  436,    0,   248,-2251,-2251,  223,  228,   228,-2251,-2251,  223,  228},
                                                 
                                                 {  228,  223,-2251,-2251,  248,     0,  188,-2251,-2251,  188,   228,  171,-2251,-2251,  236},
                                                 {  248,  228,  223,-2251,-2251,   188,    0,  188,-2251,-2251,   236,  228,  171,-2251,-2251},
                                                 {-2251,  248,  228,  223,-2251, -2251,  188,    0,  188,-2251, -2251,  236,  228,  171,-2251},
                                                 {-2251,-2251,  248,  228,  223, -2251,-2251,  188,    0,  188, -2251,-2251,  236,  228,  171},
                                                 {  223,-2251,-2251,  248,  228,   188,-2251,-2251,  188,    0,   171,-2251,-2251,  236,  228},
                                                 
                                                 {  228,  223,-2251,-2251,  228,   228,  236,-2251,-2251,  171,     0,  171,-2251,-2251,  171},
                                                 {  228,  228,  223,-2251,-2251,   171,  228,  236,-2251,-2251,   171,    0,  171,-2251,-2251},
                                                 {-2251,  228,  228,  223,-2251, -2251,  171,  228,  236,-2251, -2251,  171,    0,  171,-2251},
                                                 {-2251,-2251,  228,  228,  223, -2251,-2251,  171,  228,  236, -2251,-2251,  171,    0,  171},
                                                 {  223,-2251,-2251,  228,  228,   236,-2251,-2251,  171,  228,   171,-2251,-2251,  171,    0}    };
    /*
    
    public static final double[][] utilities = { { 0, 5, 0, 5,-2, 0},
                                                 { 5, 0,-1, 0, 6, 0},
                                                 { 0,-1, 0, 0,-2, 1},
                                                 { 5, 0, 0, 0, 3, 0},
                                                 {-2, 6,-2, 3, 0, 3},
                                                 { 0, 0, 1, 0, 3, 0} };
    */
    
    
    public static void main(String[] args) {
        int n = utilities.length;
        curr_utility = new double[n];
        inCoalition  = new int[n];
        coalitions = new ArrayList[n];
                
        //create starting coalitions        
        for (int i = 0; i < n/3; i++) {
            coalitions[i] = new ArrayList<>();
            
            coalitions[i].add(i);
            inCoalition[i] = i;
            curr_utility[i] = 0;
            
            
            coalitions[i].add(i+5);
            inCoalition[i+5] = i;
            curr_utility[i+5] = 152;
            
            coalitions[i].add(i+10);
            inCoalition[i+10] = i;
            curr_utility[i+10] = 152;
            
        }
        
        //printCoalitions(n);
        
        
        int i = 0;
        while(i < n){
            for (int coal = 0; coal < n/3; coal++) {
                if(coalitions[coal].isEmpty() || coalitions[coal].contains(i)){
                    continue;
                }
                if(allow_deviation(i,coal)){
                    int leaving_coal = inCoalition[i];
                    coalitions[inCoalition[i]].remove(Integer.valueOf(i));
                    inCoalition[i] = coal;
                    coalitions[coal].add(i);
                    /*
                    for (int j = 0; j < n/3; j++) {
                        System.out.print("  "+coalitions[j]);
                    }
                    System.out.println("");
                    */
                    recompute_utilities(leaving_coal);
                    recompute_utilities(coal);
                    i = -1;
                    if(coalitions[2].isEmpty()){
                        printCoalitions(n);
                    }
                    break;
                }
            }
            i++;
        }
        
        //printCoalitions(n);
    }
    
    public static boolean allow_deviation(int agent, int coalition){
        double u = compute_utility(agent,coalition);
        if(u<=curr_utility[agent]){
            return false;
        }
        for (int i : coalitions[coalition]){
            /* agent can join if current utility of any i won't fall
               -> sum/n <= (sum+u_i(agent))/(n+1)
               -> sum*(n+1)/n - sum <= u_i(agent)
               -> sum / n <= u_i(agent)
               -> can join if current utility of i is smaller/equal then utility of 'agent' for i
            */
            if(curr_utility[i] > utilities[i][agent]){
                return false;
            }
        }       
        return true;
    }
    
    public static double compute_utility(int agent, int coalition){
        double sum = 0.0;
        boolean contains_agent = false;
        for (int j : coalitions[coalition]){
            if(agent == j){
                contains_agent = true;
            }
            sum += utilities[agent][j];
        }
        
        if(contains_agent){
            return sum / coalitions[coalition].size();
        }else{
            return sum / (coalitions[coalition].size()+1);
        }
    }
    
    public static void recompute_utilities(int coalition){
        //System.out.print(" Utilities in coalition "+coalition+": [");
        for(int j : coalitions[coalition]){
            curr_utility[j] = compute_utility(j,coalition);
            //System.out.print("u("+j+")="+curr_utility[j]+", ");
        }
        //System.out.println("]");
    }
    
    public static void printCoalitions(int n){
        for (int j = 0; j < n/3; j++) {
            if(j==1 || j==3 || j==4){
                System.out.print(coalitions[j]+"  ");
            }
        }
        System.out.println("");
    }
    
}
