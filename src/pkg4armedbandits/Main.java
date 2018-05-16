/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pkg4armedbandits;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dogan.cakir
 */
public class Main {
    static double Qtable[]={0.00,0.00,0.00,0.00};//epsilon greedy=>0.0 optimalinit=>5.0
    static List<Bag> bags=new ArrayList<Bag>();
    static double beta;
    static double alfa;
    static double noise=0.0;
    static int policy;
    static int totalReward=0;
    static int counterbag[]={0,0,0,0};
    static double epsilon;
    static double ak[]={0.0,0.0,0.0,0.0};
    static void updatePreferences(double reward,int ind,int k )
    {
        double b;
        totalReward+=reward;
        Qtable[ind]=Qtable[ind]+(1.0/(k+1))*(reward-Qtable[ind]);
        counterbag[ind]++;
        double max=0;
        if(policy==3)
        {
            for(int i=0;i<4;i++)
            {
                if(Qtable[i]>max)
                {
                    max=Qtable[i];
                    ind=i;
                }
            }
            bags.get(ind).preferenceValues.add(bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1)+(beta*(1.0-bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1))));
            
            for(int i=0;i<4;i++)
            {
                if(i!=ind)
                {
                    bags.get(ind).preferenceValues.add(bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1)+(beta*(0.0-bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1))));            
                }
            }
        }
        else if(bags.get(ind).rewards.size()<=1)
        {
            bags.get(ind).preferenceValues.add(bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1)+(beta*reward));
            bags.get(ind).avgReward=reward;
        }
        else
        {
            bags.get(ind).preferenceValues.add(bags.get(ind).preferenceValues.get(bags.get(ind).preferenceValues.size()-1)+(beta*bags.get(ind).rewards.get(bags.get(ind).rewards.size()-1)-bags.get(ind).avgReward));
            bags.get(ind).avgReward=bags.get(ind).avgReward+alfa*(reward-bags.get(ind).avgReward);
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        beta=0.01;
        alfa=0.0001;
        boolean cont=true;
        epsilon=0.35;
        bags.add(new Bag(30, 10, 10));
        bags.add(new Bag(30, 30, 30));
        bags.add(new Bag(200, 10, 10));
        bags.add(new Bag(300,20, 20));
        List<Integer> selectedBags=new ArrayList<Integer>();
        List<Integer> notselectedBags=new ArrayList<Integer>();
        for(int i=0;i<bags.size();i++)
        {
            bags.get(i).preferenceValues.add(1.0/bags.size());
        }
        System.out.println("Please enter the policy");
        Scanner in= new Scanner(System.in);
        policy=in.nextInt();
        
        if(policy==0||policy==3)
            for(int w=0;w<5000000&&cont;w++)
            {
                double prob=0;
                for(int i=0;i<bags.size();i++)
                {
                    if(bags.get(i).preferenceValues.get(bags.get(i).preferenceValues.size()-1)>prob)
                        prob=bags.get(i).preferenceValues.get(bags.get(i).preferenceValues.size()-1);
                }
                for(int i=0;i<bags.size();i++)
                {
                    if(bags.get(i).preferenceValues.get(bags.get(i).preferenceValues.size()-1)==prob)
                        selectedBags.add(i);
                    else
                        notselectedBags.add(i);
                }
                int  n;
                Random rand = new Random();
                if(epsilon>rand.nextDouble()&&notselectedBags.size()>0)
                {
                    n = rand.nextInt(notselectedBags.size());
                    rand = new Random();
                    n=notselectedBags.get(n);
                    
                }
                else if(selectedBags.size()>1)
                {
                    
                    
                    n = rand.nextInt(selectedBags.size());
                    rand = new Random();
                    n=selectedBags.get(n);
                }
                else
                {
                    n=selectedBags.get(0);
                }
                
                if(bags.get(n).balls.get(rand.nextInt(bags.get(n).balls.size()-1)).color=="red")
                {
                    updatePreferences(1-noise,n,w+1);
                    ak[n]+=(1.0/counterbag[n]);
                }
                else
                {
                    updatePreferences(noise, n,w+1);
                    ak[n]+=(1.0/counterbag[n]);
                }
                if (ak[n]>=100000&&ak[n]*ak[n]<100000) cont=false;
                if(epsilon>0.001)epsilon-=0.00001;
            }
        else if(policy==1||policy==2)
        {
            if(policy==2)
                for(int i=0;i<4;i++)
                    Qtable[i]=5.0;
                        
            for(int w=0;w<5000000;w++)
            {
                
                double prob=0;
                for(int i=0;i<bags.size();i++)
                {
                    if(Qtable[i]>prob)
                    prob=Qtable[i];
                }
                for(int i=0;i<bags.size();i++)
                {
                    if(Qtable[i]==prob)
                        selectedBags.add(i);
                    else
                        notselectedBags.add(i);
                }
                int  n;
                Random rand = new Random();
                if(epsilon>rand.nextDouble()&&notselectedBags.size()>0)
                {
                    n = rand.nextInt(notselectedBags.size());
                    rand = new Random();
                    n=notselectedBags.get(n);
                    
                }
                else if(selectedBags.size()>1)
                {
                    
                    
                    n = rand.nextInt(selectedBags.size());
                    rand = new Random();
                    n=selectedBags.get(n);
                }
                else
                {
                    n=selectedBags.get(0);
                }
                
                if(bags.get(n).balls.get(rand.nextInt(bags.get(n).balls.size()-1)).color=="red")
                {
                    updatePreferences(1.0-noise,n,w+1);
                    ak[n]+=(1.0/counterbag[n]);
                }
                else
                {
                    updatePreferences(noise, n,w+1);
                    ak[n]+=(1.0/counterbag[n]);
                }
                if (ak[n]>=100000&&ak[n]*ak[n]<100000) cont=false;
                if(epsilon>0.001)epsilon-=0.00001;
                
            }
        }
        int steps=counterbag[0]+counterbag[1]+counterbag[2]+counterbag[3];
        System.out.println("Stepsize until convergence:"+steps);
        System.out.println("Total expected reward:"+totalReward);
        
    }
    
}
