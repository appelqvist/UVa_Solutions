package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Andréas Appelqvist on 2016-10-04.
 * UVa Online Judge Problem:
 * 10608 Friends
 */
public class Main {
    public static void main(String[] args) {
        try {
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br = new BufferedReader(new FileReader("src/sample_input"));
            int testcases = Integer.parseInt(br.readLine());
            for (int i = 0; i < testcases; i++) {
                String[] values = br.readLine().split(" ");
                int townCitizens = Integer.parseInt(values[0])+1; //Add one to use the id as index in array.
                int nrOfPairs = Integer.parseInt(values[1]);

                if(nrOfPairs == 0){
                    System.out.println(0); //If the is no friends return 0 as size
                }else{
                    DisjointSets djs = new DisjointSets(townCitizens);
                    int a, b;
                    String pairs[];
                    for (int j = 0; j < nrOfPairs; j++) {
                        pairs = br.readLine().split(" ");
                        a = Integer.parseInt(pairs[0]);
                        b = Integer.parseInt(pairs[1]);

                        a = djs.findParentOf(a);
                        b = djs.findParentOf(b);
                        djs.union(a, b);
                    }
                    System.out.println(djs.getBiggestSetSize());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DisjointSets {
    private int[] nodeParents;
    HashMap<Integer,Integer> size = new HashMap<>();

    public DisjointSets(int nbrOfNodes) {
        //Initialize the nodes
        nodeParents = new int[nbrOfNodes];
        for (int j = 1; j < nodeParents.length; j++) {
            nodeParents[j] = -1;
            size.put(j,1);
        }
    }

    public void union(int root1, int root2) {
        if (root1 != root2) {
            nodeParents[root2] = root1; //root2 get root1 as new root
            size.replace(root1,size.get(root1)+size.get(root2));
            size.replace(root2,0);
        }
    }

    public int getBiggestSetSize(){
        int max = 0;
        for(int i = 1; i < size.size()+1; i++){
            if(size.get(i) > max){
                max = size.get(i);
            }
        }
        return max;
    }

    public int findParentOf(int index) {
        if (nodeParents[index] < 0) {
            return index; //node is the root
        } else {
            return findParentOf(nodeParents[index]);
        }
    }
}


