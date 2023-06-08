package in.ineuron.dto;

import java.io.Serializable;

//It is a dto layer which help to transfer the user data between classes
public class AccountHolder implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer accountId = null;
	private String accHolderName = null;
	private Integer card = null;
	private Integer pin = null;
	private Double balance = null;

	// some getters and setter method
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public Integer getCard() {
		return card;
	}

	public void setCard(Integer card) {
		this.card = card;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
