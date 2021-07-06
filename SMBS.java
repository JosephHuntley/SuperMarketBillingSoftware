package com.joe.billingSoftware;

import java.text.DecimalFormat;
import java.util.Scanner;

public class SMBS {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("$#0.00");
		Double subtotal = 0.00;
		boolean quit = false;
		Double stateTax = .06;
		Scanner sc = new Scanner(System.in);

		Order order = new Order();
		while(quit != true) {
			sc = new Scanner(System.in);
			System.out.println("\n\nSubtotal: " + df.format(subtotal));
			System.out.print("Please type in the unique identification number: ");
			String input = sc.nextLine().toLowerCase();

			try {
				if(input.equals("total")) {
					quit = true;

					//*Total the order and print receipt
					order.printRecipt();
					Double total = subtotal + (subtotal * stateTax);
					System.out.println("Total:\t" + df.format(total));
				}

				else if(input.equals("void")) {
					//*TODO Add the ability to void items off receipt and subtotal
				}

				else if(input.equals("abort")) {
					System.out.print("Are you sure you want to cancel this "
							+ "order? Y/N: ");
					input = sc.nextLine().toLowerCase();

					if(input.equals("y")) {
						quit = true;
						//*TODO re-add inventory to database if order canceled
						System.out.println("Order canceled sucessfully");
					}
				}

				else {
					int uniqueID = Integer.parseInt(input);
					System.out.print("What is the quanity: ");
					int qty = sc.nextInt();
					order.findItem(uniqueID, qty);
					subtotal += order.getSubtotal();
				}
			}
			catch(Exception e){
				System.out.println("Invalid option, please try again.");
			}

		}
		sc.close();


	}		
}
