/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport;

import java.util.Random;

/**
 *
 * @author BNL
 */
public class Cleaner extends Thread {

	private final String planeName;
        private final Random random;
	
    public Cleaner(String planeName) {
            this.planeName = planeName;
            
            this.random = new Random();
            
        }
    public void run() {

    	try {
		for (int i=1; i<5; i++) {
			switch (i) {
			  case 1:
			    System.out.printf("PLANE %s: Cleaners are mopping the plane floor\n",planeName);
			    break;
			  case 2:
			    System.out.printf("PLANE %s: Cleaners are wiping down plane seats\n",planeName);
			    break;
			  case 3:
                                System.out.printf("PLANE %s: Cleaners are picking up the  Garbage\n",planeName);	
                                break;
			  case 4:
        			System.out.printf("PLANE %s: Cleaners are spraying disinfectants\n",planeName);
				break;
			    }
			Thread.sleep(random.nextInt(1000));
		}
	} catch(InterruptedException e) {
		System.out.println("Thread interrupted.");
	}
	System.out.printf("~~ PLANE %s: Cleaners have finished cleaning ~~\n",planeName);
    }


	
}
