package in.ineuron.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import in.ineuron.dto.AccountHolder;
import in.ineuron.persistence.Bank;

//This layer gives the service to the controller layer 
public class Atm {

	// creating a Dto object static and scope is global
	public static AccountHolder acc = null;

	// doing user authentication
	public static String authenticate() throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the INEURON BANK\n");
		System.out.println("Please authenticate yourself");
		System.out.print("Enter Card No: ");
		String cardNo = br.readLine();
		System.out.print("Enter user 4 Digit PIN: ");
		String userPin = br.readLine();

		Integer authAcc = Bank.authenticate(Integer.parseInt(cardNo), Integer.parseInt(userPin));

		if (authAcc != null) {
			acc = Bank.findAccount(authAcc);
			System.out.println("Your Account Balance : " + acc.getBalance());
			return "success";
		} else {
			return "failed";
		}
	}

	public static Integer showMenu() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("\nPlease select an option:");
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Transfer");
		System.out.println("4. Show transaction history");
		System.out.println("5. Quit");
		System.out.print("Enter your choice: ");

		return Integer.parseInt(br.readLine());
	}

	public static void withdraw() throws IOException, NumberFormatException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter amount you want to withdraw: ");
		String amount = br.readLine();
		String status = Bank.withdraw(acc, Double.parseDouble(amount));
		Bank.dataEntry("Withdraw", Double.parseDouble(amount), acc.getAccountId(), null);

		if (status.equalsIgnoreCase("success")) {
			System.out.println("Withdrawal successful. Your new balance is " + acc.getBalance());
		} else {
			System.out.println("Withdrawal failed. Please check your balance and try again.");
		}
	}

	public static void deposit() throws IOException, NumberFormatException, SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter amount you want to deposit: ");
		String amount = br.readLine();
		Bank.deposit(acc, Double.parseDouble(amount));
		Bank.dataEntry("Deposit", Double.parseDouble(amount), acc.getAccountId(), null);
		System.out.println("Deposit successful. Your new balance is " + acc.getBalance());
	}

	public static void transfer() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nEnter recipient's Account Number : ");
		String recAccId = br.readLine();
		AccountHolder obj = Bank.findAccount(Integer.parseInt(recAccId));
		if (obj != null) {

			System.out.println("Enter amount to Transfer : ");
			String amount = br.readLine();
			String status = null;
			try {
				status = Bank.transfer(acc, obj, Double.parseDouble(amount));
				Bank.dataEntry("Transfer", Double.parseDouble(amount), acc.getAccountId(), obj.getAccountId());
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			if (status.equalsIgnoreCase("success")) {
				System.out.println("Transfer successful. Your new balance is " + acc.getBalance());
			} else {
				System.out.println(status);
			}
		} else {
			System.out.println("Account Does not Exist, Please try again & enter a valid account number.");
		}

	}

	public static void showTransactionHistory() {
		System.out.println("\nLast 5 Transaction history:");
		Boolean status = Bank.showTransactionHistory(acc);
		if (status != true) {
			System.out.println("Some Technical issue occured , Please try again later");
		} else {

		}
	}

}
