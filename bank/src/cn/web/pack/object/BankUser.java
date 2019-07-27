package cn.web.pack.object;

public class BankUser {

	private int account_id;

	private String account_password;

	private String account_name;

	private double account_balance;

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getAccount_password() {
		return account_password;
	}

	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	public BankUser(int account_id, String account_password, String account_name, double account_balance) {
		super();
		this.account_id = account_id;
		this.account_password = account_password;
		this.account_name = account_name;
		this.account_balance = account_balance;
	}

	public void println() {

		System.out.println("account_id:" + this.account_id + "\taccount_password:" + this.account_password
				+ "\taccount_name:" + this.account_name + "\taccount_balance:" + this.account_balance);

	}
}
