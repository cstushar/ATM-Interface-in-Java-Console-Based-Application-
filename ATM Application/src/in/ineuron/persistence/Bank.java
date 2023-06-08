package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.dto.AccountHolder;
import util.JdbcUtil;

public class Bank {
	public static Connection connection = null;
	public static ResultSet resultSet = null;
	public static AccountHolder acc = null;

	// getting the connection in static way , scope is global
	static {
		try {
			connection = JdbcUtil.get_connection();
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Integer authenticate(Integer cardNo, Integer userPin) {
		String pstmtSelectQuery = "Select accountId from users where card = ? && pin = ?";
		if (connection != null) {
			try {
				PreparedStatement pstmtSelect = connection.prepareStatement(pstmtSelectQuery);

				if (pstmtSelect != null) {
					pstmtSelect.setInt(1, cardNo);
				}
				if (pstmtSelect != null) {
					pstmtSelect.setInt(2, userPin);
				}
				if (pstmtSelect != null) {
					resultSet = pstmtSelect.executeQuery();
				}
				if (resultSet != null) {
					if (resultSet.next())
						return resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String withdraw(AccountHolder acc, Double amount) {
		if (amount != null && acc.getBalance() >= amount) {
			String pstmtUpdateQuery = "update users set balance = ? where accountid=?";
			Double totalAmount = acc.getBalance() - amount;
			if (connection != null) {
				try {
					PreparedStatement pstmtUpdate = connection.prepareStatement(pstmtUpdateQuery);

					if (pstmtUpdate != null) {
						pstmtUpdate.setDouble(1, totalAmount);
						pstmtUpdate.setInt(2, acc.getAccountId());
						pstmtUpdate.executeUpdate();
						updateAccountClass(acc);
						return "Success";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return "Failure";
		}
		return "Failure";
	}

	private static void updateAccountClass(AccountHolder acc) {
		String pstmtSelectQuery = "Select balance from users where accountId = ?";
		if (connection != null) {
			try {
				PreparedStatement pstmtSelect = connection.prepareStatement(pstmtSelectQuery);
				if (pstmtSelect != null) {
					pstmtSelect.setInt(1, acc.getAccountId());
					resultSet = pstmtSelect.executeQuery();
				}
				if (resultSet != null) {
					if (resultSet.next()) {
						// update balance to the Account object
						acc.setBalance(resultSet.getDouble(1));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void deposit(AccountHolder acc, Double amount) {
		String pstmtUpdateQuery = "update users set balance = ? where accountid=?";
		Double totalAmount = acc.getBalance() + amount;
		if (connection != null) {
			try {
				PreparedStatement pstmtUpdate = connection.prepareStatement(pstmtUpdateQuery);
				if (pstmtUpdate != null) {
					pstmtUpdate.setDouble(1, totalAmount);
					pstmtUpdate.setInt(2, acc.getAccountId());
					pstmtUpdate.executeUpdate();
					updateAccountClass(acc);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static String transfer(AccountHolder acc, AccountHolder recAcc, Double amount) throws SQLException {

		if (acc.getBalance() >= amount) {
			withdraw(acc, amount);
			deposit(recAcc, amount);

			return "success";
		} else {
			return "You don't have sufficient balance, Transaction Failed";
		}
	}

	public static AccountHolder findAccount(Integer accId) {
		AccountHolder newacc;
		String pstmtSelectQuery = "Select accountId,accHolderName,card,pin,balance from users where accountId = ?";
		if (connection != null) {
			newacc = new AccountHolder();
			try {
				PreparedStatement pstmtSelect = connection.prepareStatement(pstmtSelectQuery);

				if (pstmtSelect != null) {
					pstmtSelect.setInt(1, accId);
					resultSet = pstmtSelect.executeQuery();
				}
				if (resultSet != null) {
					if (resultSet.next()) {
						// copy resultSet data to Account object
						newacc.setAccountId(resultSet.getInt(1));
						newacc.setAccHolderName(resultSet.getString(2));
						newacc.setCard(resultSet.getInt(3));
						newacc.setPin(resultSet.getInt(4));
						newacc.setBalance(resultSet.getDouble(5));
						return newacc;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void dataEntry(String transferType, Double amount, Integer accId, Integer recAccId)
			throws SQLException {

		String pstmtInsertQuery = "insert into transactions (id,amount,transactionType,transferAccount) values (?,?,?,?);";

		if (connection != null) {
			PreparedStatement pstmtInsert = connection.prepareStatement(pstmtInsertQuery);

			switch (transferType) {

			case "Withdraw": {
				if (pstmtInsert != null) {
					pstmtInsert.setInt(1, accId);
					pstmtInsert.setDouble(2, amount);
					pstmtInsert.setString(3, "Withdrawl");
					pstmtInsert.setInt(4, 0);
					pstmtInsert.executeUpdate();
				}
				break;
			}
			case "Deposit": {
				if (pstmtInsert != null) {
					pstmtInsert.setInt(1, accId);
					pstmtInsert.setDouble(2, amount);
					pstmtInsert.setString(3, "Deposit");
					pstmtInsert.setInt(4, 0);
					pstmtInsert.executeUpdate();
				}
				break;
			}

			case "Transfer": {
				if (pstmtInsert != null) {
					pstmtInsert.setInt(1, accId);
					pstmtInsert.setDouble(2, amount);
					pstmtInsert.setString(3, "Transfer");
					pstmtInsert.setInt(4, recAccId);
					pstmtInsert.executeUpdate();
				}
			}
				break;
			}
		}
	}

	public static Boolean showTransactionHistory(AccountHolder acc2) {

		String pstmtSelectQuery = "SELECT transid, amount, transactionType, transferAccount FROM transactions "
				+ "WHERE id = ? ORDER BY transid DESC LIMIT 5";
		if (connection != null) {
			try {
				PreparedStatement pstmtSelect = connection.prepareStatement(pstmtSelectQuery);

				if (pstmtSelect != null) {
					pstmtSelect.setInt(1, acc2.getAccountId());
					resultSet = pstmtSelect.executeQuery();
				}
				if (resultSet != null) {
					System.out.println("Trans.ID Amount\t\tTransaction_Type");
					// copy resultSet data to Account object
					int count = 0;
					while (resultSet.next() && count < 5) {
						System.out.print("  " + resultSet.getInt(1) + "\t");
						System.out.print(resultSet.getDouble(2) + "\t");
						System.out.print("\t" + resultSet.getString(3) + "\t");
						if (resultSet.getInt(4) != 0) {
							System.out.print("To Account : " + resultSet.getInt(4));
						}
						System.out.println();
						count++;
					}
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
