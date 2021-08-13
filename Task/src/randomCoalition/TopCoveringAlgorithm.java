package randomCoalition;

import java.util.ArrayList;
import java.util.Arrays;

/*
Implementation of the Top Covering Algorithm for B-hedonic games with strict preferences
*/
public class TopCoveringAlgorithm {

    public static void main(String[] args) {
        int N = 4;
        /*
        Ordered and strict preferences over agents represented by an array over array of Integers(Agents).
        */
        int[][] preferences = { {1,2,3,0},
                                {2,3,0,1},
                                {0,1,3,2},
                                {2,1,0,3} };      
        
        
        ArrayList<ArrayList<Integer>> result = TopCovering(N,preferences);
        System.out.print("{ ");
        for (int i = 0; i < result.size(); i++){
            System.out.print("{");
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.print(result.get(i).get(j)+",");
            }
            System.out.print("}, ");
        }
        System.out.println("}");
    }
    
    
    /*
    TopCovering algorthm as a function 
    input:  n: number of agents, pref: agents' preferences
    output: core stable partition of agents
    
    R := Set of agents not yet included in a Connected Component(CC)
    pi := set of Connected Components
    */
    public static ArrayList<ArrayList<Integer>> TopCovering(int n, int[][] pref){
        ArrayList<Integer> R = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            R.add(i);
        }
        ArrayList<ArrayList<Integer>> pi = new ArrayList<>();
        
        
        for (int k = 1; k <= n; k++) {
            ArrayList<Integer> CC = new ArrayList<>();
            int size = Integer.MAX_VALUE;
            //find the smallest Connected Component(CC) of agents that are not yet covered and add it to pi
            for (int i = 0; i < R.size(); i++) {
                ArrayList<Integer> temp = GetConnectedComponent(R, i, pref);
                if(temp.size() < size){
                    CC = temp;
                    size = temp.size();
                }
            }
            pi.add(CC);
            
            for (Integer elem : CC) {
                R.remove(elem);
            }
            if(R.isEmpty()){
                return pi;
            }
        }
        return pi;
    }

    
    /*
    Get Connected Component(CC) by transitively adding the ChoiceSets of agents in CC to the CC
    input:  agents: not yet covered agents(R) , agent_index: index of the agent in R we are seaching the CC for, pref: agents' preferences
    output: Connected Component of remaining agents for an agent
    */
    public static ArrayList<Integer> GetConnectedComponent(ArrayList<Integer> agents, int agent_index, int[][] pref){
        ArrayList<Integer> CC = new ArrayList<>(Arrays.asList(agents.get(agent_index))); 
        boolean[] already_added = new boolean[agents.size()];
        int i = 0;
        already_added[agent_index] = true;
        
        while(i < CC.size()){
            int agent = CC.get(i);
            //Add the choice set of agent to CC
            int cs = pref[agent][0];
            if (agents.contains(cs) && !already_added[agents.indexOf(cs)]) {
                CC.add(cs);
                already_added[agents.indexOf(cs)] = true;
            }
            i++;
        }
        
        return CC;
    }
    
}

