
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;
import java.util.ArrayList;
/* YOU NEED TO ADD YOUR CODE TO THIS CLASS, REMOVING ALL DUMMY CODE
 *
 * DO NOT CHANGE THE NAME OR SIGNATURE OF ANY OF THE EXISTING METHODS
 * (Signature includes parameter types, return types and being static)
 *
 * You can add private methods to this class if it makes your code cleaner,
 * but this class MUST work with the UNMODIFIED Tester.java class.
 *
 * This is the ONLY class that you can submit for your assignment.
 *
 * MH 2020
 */

public class Queens
{
    static Random r = new Random();
    private static int boardSize = 10;
    
    // creates a valid genotype with random values
    public static Integer [] randomGenotype()
    {
        Integer [] genotype = new Integer [boardSize];
        //Set data structure use so as to avoid duplicates
        Set<Integer> c = new HashSet<>();
        int i = 0;
        while(i<boardSize)
        {
            int v = r.nextInt(boardSize) + 1;
            
            while(c.contains(v)) // checks if the set contanins the value already and loop until unique one found.
            {
                v = r.nextInt(boardSize) + 1;
            }
            c.add(v);
            genotype[i] = v;
            i = i+1;
        }
                
        
        return genotype;
    }
    
    // swaps 2 genes in the genotype
    // the swap happens with probability p, so if p = 0.8
    // then 8 out of 10 times this method is called, a swap happens
    public static Integer[] mutate(Integer[] genotype, double p)
    {
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        while(x == y) 
                {
                    y = r.nextInt(10);
                
                }
        double v = r.nextDouble();
        if(v <= p)
        {
                Integer m = genotype[x];
                genotype[x] = genotype[y];
                genotype[y] = m;
                                    }
        
        return genotype;
    }
    
    // creates 2 child genotypes using the 'cut-and-crossfill' method
    public static Integer[][] crossover(Integer[] parent0, Integer[] parent1)
    {
        //Set data structure use so as to avoid duplicates
        Set<Integer> n0 = new HashSet<>();
        Integer [][] children = new Integer [2][boardSize];
        Set<Integer> n1 = new HashSet<>();
        int x = 0;
        int s = (boardSize/2);
        
        while(x < boardSize/2)
        {
            children[0][x] = parent0[x];
            n0.add(parent0[x]);
            children[1][x] = parent1[x];
            n1.add(parent1[x]);
            x = x + 1;
        }
        
        while (n0.size() != 10) 
        {
     
            if (!(n0.contains(parent1[s])))
            {
                children[0][n0.size()] = parent1[s];
                n0.add(parent1[s]);
            }
            s = (s + 1) % (boardSize);
        }
        while (n1.size() != 10)
        {
            if (!(n1.contains(parent0[s])))
            {
                children[1][n1.size()] = parent0[s];
                n1.add(parent0[s]);
            }
            s = (s+1)%boardSize;
        }
              return children;
    }
    
    // calculates the fitness of an individual
    public static int measureFitness(Integer [] genotype)
    {
        int r0,r1,c0,c1;
        double fitness = 0.5 * boardSize * (boardSize - 1);
        int fit = (int) fitness;
        for (int j = 0; j< genotype.length; j++) 
        {
            for (int k = j + 1; k < genotype.length; k++) 
            {
                if (j != k) 
                {
                    c0 = genotype[j];
                    c1 = genotype[k]; 
                    r0 = j;
                    r1 = k;
                    int a =  Math.abs(r0-r1);
                    int b = Math.abs(c0-c1);
                     
            if (a == b)
            fit = fit- 1;
                    
              }
            }
        }
       
      return fit;
    }
}
