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

public class Supply extends Thread {

	private final String planeName;
        private final Random random;
	
    public Supply(String planeName) {
            this.planeName = planeName;
            
            this.random = new Random();
            
        }
    public void run() {

    	try {
		for (int i=1; i<4; i++) {
			switch (i) {
			  case 1:
			    System.out.printf("PLANE %s: Restocking the Snacks\n",planeName);
			    break;
			  case 2:
			    System.out.printf("PLANE %s: Restocking the Beverages\n",planeName);
			    break;
			  case 3:
                                System.out.printf("PLANE %s: Restocking other Supplies\n",planeName);	
                                break;
                        }
                    Thread.sleep(random.nextInt(1000));
                   
		}   
	} catch(InterruptedException e) {
		System.out.println("Thread interrupted.");
	}
	System.out.printf("~~ PLANE %s: Resupplied ~~\n",planeName);
    }


	
}
