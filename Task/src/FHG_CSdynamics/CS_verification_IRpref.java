package FHG_CSdynamics;

import java.util.ArrayList;
import java.util.Collections;

public class CS_verification_IRpref {
    public static ArrayList<ArrayList<Integer>>[] preferences;
    
    public static boolean verify_CS(ArrayList<ArrayList<Integer>> coal, ArrayList<ArrayList<Integer>>[] prefs) {
        int n = prefs.length;
        int m = coal.size();
        preferences = prefs;
        int[] inCoalition = new int[n];
        
        for (int i = 0; i < m; i++) {
            Collections.sort(coal.get(i));
            for (int j : coal.get(i)) {
                inCoalition[j] = i;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (ArrayList<Integer> list : preferences[i]) {
                Collections.sort(list);
                boolean block_exists = true;
                for (int j : list) {
                    if (prefers(j, coal.get(inCoalition[j]), list)){
                        block_exists = false;
                        break;
                    }
                }
                if(block_exists){
                    System.out.println(list);
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean prefers(int agent, ArrayList<Integer> list1, ArrayList<Integer> list2){
        for (ArrayList<Integer> list : preferences[agent]) {
            if(list.equals(list1)){
                return true;
            }else if(list.equals(list2)){
                return false;
            }
        }
        return false;
    }
}
