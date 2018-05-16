/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pkg4armedbandits.Bag;

/**
 *
 * @author dogan.cakir
 */
public class BagTest {
    
    public BagTest() {
    }
    Bag bag;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void test_generate_bag(){
        bag = new Bag(5,10,15);
        assertEquals(30, bag.balls.size());
    }
    @Test
    public void test_generate_bag_ball_colors_red(){
        bag = new Bag(10,20,30);
        int red=0;
        for(int i=0;i<bag.balls.size();i++)
        {
            if(bag.balls.get(i).color=="red")red++;
        }
        assertEquals(10, red);
    }
        @Test
    public void test_generate_bag_ball_colors_blue(){
        bag = new Bag(10,20,30);
        int blue=0;
        for(int i=0;i<bag.balls.size();i++)
        {
            if(bag.balls.get(i).color=="blue")blue++;
        }
        assertEquals(20, blue);
    }
        @Test
    public void test_generate_bag_ball_colors_yellow(){
        bag = new Bag(10,20,30);
        int yellow=0;
        for(int i=0;i<bag.balls.size();i++)
        {
            if(bag.balls.get(i).color=="yellow")yellow++;
        }
        assertEquals(30, yellow);
    }
 
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
