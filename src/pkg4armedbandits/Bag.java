/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4armedbandits;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dogan.cakir
 */
public class Bag {
    public List<Ball> balls;
    public List<Double> preferenceValues;
    public List<Double> rewards;
    public double avgReward=0;
    public Bag(int red,int blue,int yellow)
    {
        balls = new ArrayList<Ball>();
        rewards=new ArrayList<Double>();
        preferenceValues=new ArrayList<Double>();
        for(int i=0;i<red;i++)
        {
            balls.add(new Ball("red"));            
        }
        for(int i=0;i<blue;i++)
        {
            balls.add(new Ball("blue"));            
        }
        for(int i=0;i<yellow;i++)
        {
            balls.add(new Ball("yellow"));            
        }
    }    
}
