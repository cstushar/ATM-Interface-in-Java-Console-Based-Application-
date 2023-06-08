package in.ineuron.controller;

import in.ineuron.service.Atm;

public class Main {

	public static void main(String[] args) throws Exception {

		// authenticating the credentials given by the user
		String auth = Atm.authenticate();

		// if authentication successful , show the menu
		if (auth.equalsIgnoreCase("success")) {
			Boolean quit = false;
			while (!quit) {
				Integer choice = Atm.showMenu();
				switch (choice) {
				case 1:
					Atm.withdraw();
					break;
				case 2:
					Atm.deposit();
					break;
				case 3:
					Atm.transfer();
					break;
				case 4:
					Atm.showTransactionHistory();
					break;
				case 5:
					quit = true;
					System.out.println("Thank you for using this ATM. Goodbye!");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			}
		}
		// if authentication failed , exit the system
		else {
			System.out.println("Invalid credentials, Please try again");
			System.exit(0);
		}

	}
}