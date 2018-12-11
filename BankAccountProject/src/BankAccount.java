
public class abstract BankAccount 
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
	
	public double deposit(double amt)
	{
		balance += amt;
	}
	
	public double withdraw (double amt)
	{
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
	
	public abstract endOfMonthUpdate();
	
	public void transfer(BankAccount other, double amt)
	{
		this.withdraw(amt);
		other.deposit(amt);
	}
	
	public String toString()
	{
		return acctNum + "\t" + name + "\t$" + balance ;
	}
}