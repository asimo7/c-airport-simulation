/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

/**
 *
 * @author BNL
 */
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fuel extends Thread {
        private static Lock lock = new ReentrantLock();
	private final String planeName;
        private final Random random;
    public Fuel(String planeName) {
            this.planeName = planeName;
            this.random = new Random();
        }
    public void run() {
        lock.lock();
        System.out.printf("Truck: Refuelling truck is coming to refuel PLANE %s\n", planeName);
    	try {
                
		for (int i=1; i<6; i++) {
                    System.out.println("~~ PLANE " +planeName+": Refueling Process "+ i*20+" Precent ~~\n");
                    Thread.sleep(random.nextInt(1000));
                }
        }
	 catch(InterruptedException e) {
		System.out.println("Thread interrupted.");
	}
	finally{
            System.out.printf("~~ PLANE %s: Fuel Resupplied ~~\n",planeName);
            lock.unlock();
        }
    }


	
}