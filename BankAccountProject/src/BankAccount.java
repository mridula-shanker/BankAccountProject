
public abstract class BankAccount 
{
	public static int nextAccNum;
	private String name;
	private int acctNum;
	private double balance;
	
	
	
	public BankAccount(String n)
	{
		name = n;
		acctNum = nextAccNum++;
		balance=0;
	}
	
	public BankAccount(String n, double b)
	{
		name=n;
		acctNum=nextAccNum++;
		balance=b;
	}
	
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		balance += amt;
	}
	
	public void withdraw(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		balance -= amt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public abstract void endOfMonthUpdate();
	
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	public int getAccountNumber()
	{
		return acctNum;
	}
	
	public String toString()
	{
		System.out.println(acctNum + "\t" + name + "\t$" + balance) ;
		return acctNum + "\t" + name + "\t$" + balance;
	}
}
