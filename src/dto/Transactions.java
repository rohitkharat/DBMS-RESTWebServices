package dto;

public class Transactions {
	
	int transaction_id;
	int from_account;
	String from_bank;
	int to_account;
	String to_bank;
	float amount;
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getFrom_account() {
		return from_account;
	}
	public void setFrom_account(int from_account) {
		this.from_account = from_account;
	}
	public String getFrom_bank() {
		return from_bank;
	}
	public void setFrom_bank(String from_bank) {
		this.from_bank = from_bank;
	}
	public int getTo_account() {
		return to_account;
	}
	public void setTo_account(int to_account) {
		this.to_account = to_account;
	}
	public String getTo_bank() {
		return to_bank;
	}
	public void setTo_bank(String to_bank) {
		this.to_bank = to_bank;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
}
