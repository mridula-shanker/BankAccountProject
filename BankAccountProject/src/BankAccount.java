/**
 * Mridula Shanker
 * 9th Grade-Period 6
 * Bank Account Project
 *
 */
public abstract class BankAccount 
{
	public static int nextAccNum;
	private String name;
	private int acctNum;
	private double balance;
	
	
	/**
	 * Constructor creates an instance with name, account number, and initial balance of 0
	 * @param account name (String)
	 */
	public BankAccount(String n)
	{
		name = n;
		acctNum = nextAccNum;
		balance=0;
		nextAccNum++;
	}
	
	/**
	 * Constructor creates an instance with name, account number, and initial balance given in parameter
	 * @param account name (String) and initial balance (double)
	 */
	public BankAccount(String n, double b)
	{
		name=n;
		acctNum=nextAccNum++;
		balance=b;
	}
	
	/**
	 * adds given amount and updates balance
	 * @param amt to deposit
	 */
	public void deposit(double amt)
	{
		if (amt < 0)//allows only positive numbers
		{
			throw new IllegalArgumentException();
		}
		balance += amt;
	}
	
	/**
	 * subtract given amt and updates balance
	 * @param amt to withdraw
	 */
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		balance -= amt;
	}
	
	/**
	 * 
	 * @return account name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @return account balance
	 */
	public double getBalance()
	{
		return balance;
	}
	
	/**
	 * Abstract method for updating end of month transaction
	 */
	public abstract void endOfMonthUpdate();
	
	/**
	 * transfers amount from one account to another
	 * @param other account (type BankAccount) and amount to transfer
	 */
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt); // withdraw amount from this object
		other.deposit(amt); // deposit amount to other object
	}
	
	/**
	 * 
	 * @return account number
	 */
	public int getAccountNumber()
	{
		return acctNum;
	}
	
	/**
	 * 
	 * @return account number, name, and balance as a concatenated String
	 */
	public String toString()
	{
		//System.out.println(acctNum + "\t" + name + "\t$" + balance) ;
		return acctNum + "\t" + name + "\t$" + balance;
	}
}
