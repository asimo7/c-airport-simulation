/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BNL
 */


public class Gate{
    private int free = 0;
    private int counter = 0;    
    private int temp = 0;  
    private static int gateNumber = 1;
    private Semaphore bridge = new Semaphore(3);
    LinkedList<Integer> listFree = new LinkedList<Integer>();

//    public void temp(int temp){
//        free =this.temp;
//    }
    
    public Gate(int free){
        this.free = free;
    }
    public int process(Airplane airplane, String name){
        
        try {
            bridge.acquire();
            
            counter = counter + 1;
            
            int currentGateNumber = gateNumber;
            gateNumber = (gateNumber % 3) + 1;
            if(counter > 3){
                temp = listFree.poll();
                currentGateNumber = temp;
            }
            
            System.out.printf("%s has arrived at GATE %d\n", name, currentGateNumber);
            System.out.printf("Passengers are starting to disembark from PLANE %s at GATE %d\n", name, currentGateNumber);
            PassengerGenerator pg = new PassengerGenerator(name,currentGateNumber);
            Cleaner clean = new Cleaner(name);
            Supply sup = new Supply(name);
            Fuel fuel = new Fuel(name);
            fuel.start();
            pg.disembark();
            pg.join();
            sup.start();
            clean.start();
            clean.join();
            pg.embark(listFree);
            pg.join();
            bridge.release();
          
        } catch (InterruptedException ex) {
            Logger.getLogger(Gate.class.getName()).log(Level.SEVERE, null, ex);
        }

        return(free);
    }
    
}
