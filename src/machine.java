import java.util.LinkedHashMap;
import java.util.Scanner;

public class machine {

	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		int i, j, x, temp_value, limit, index;
		final int FIVE = 5;
		final int TEN = 10;
		LinkedHashMap<Integer, Integer> amounts = new LinkedHashMap<>(); // will be dispensed
		LinkedHashMap<Integer, Integer> machine = new LinkedHashMap<>(); // machine money
		int dispensing_amount, total_amount = 0;
		int[] bills = {100, 50, 20, 5, 1};

		// create maps
		for(i = 0; i < FIVE; i++){
			//amounts.put(bills[i], 0);
			machine.put(bills[i], 0);
		}

		// save user inputs to amounts array
		System.out.print("Enter amounts: \n" );
        for (Integer key : machine.keySet()) {
        	machine.merge(key,0,(k,v)->sc.nextInt());
        }

        for (Integer key : machine.keySet()) {
        	System.out.println(key + " = " + machine.get(key));
        	total_amount = total_amount + (key * machine.get(key));
        }

        System.out.println("\nTotal amount: " + total_amount);

        while(true){
        	if(total_amount <= 0){
	        	System.out.println("\nNo more bills or coins left! \nGoodbye!");
	        	System.exit(0);
        	}

	        System.out.println("\nDispensing Amount: ");
	        dispensing_amount = sc.nextInt();

	        if(dispensing_amount == 0){
	        	System.out.println("Goodbye!");
	        	System.exit(0);
	        }else{
	       // check if machine has enough money
	        if(total_amount < dispensing_amount){
	        	System.out.println("Not enough money in the machine");
	        }else{
	            String str_amount = String.valueOf(dispensing_amount);	// gets length of dispensing amount
	            String[] digits = str_amount.split("");

	            int length = digits.length;
	            int multiplier = 1;
	            int[] values = new int[length];

	            // 125 --> 100, 20, 5
	            for(i = length-1; i >= 0; i--){
	            	values[i] = Integer.parseInt(digits[i]) * multiplier;
	            	multiplier = multiplier * TEN;
	            }

	            int goal = 0;
	            for(i = 0; i < length; i++){
	            	temp_value = 0;
	            	//System.out.println(values[i]);
	                for (Integer key : machine.keySet()) {
	            		for(j = 0; j < machine.get(key); j++){
	            			index = j + 1;
	            			temp_value = key * index;

	            			if(temp_value == values[i]){
	            				if(goal != dispensing_amount){
	            					amounts.put(key, index);
	                				goal = goal + temp_value;

	                				machine.put(key, machine.get(key)-index);
	            				}
	            				temp_value = 0;

	            				if(i != length-1) i = i + 1;
	            			}
	            		}

	                }

	            }
	            for (Integer key : amounts.keySet()) {
	            	if(amounts.get(key) != 0){
	            		System.out.print("Amount: "+ amounts.get(key) + " P" + key + " ");
	            	}

	            }
	            System.out.println("");
	            total_amount = total_amount - dispensing_amount;

	            System.out.println("");
	            System.out.println(total_amount);
	            for (Integer key : machine.keySet()) {
	            	System.out.println(key + " = " + machine.get(key));
	            }

	            amounts.clear();
	        }

        }
        }
	}

}
