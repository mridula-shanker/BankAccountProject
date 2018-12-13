
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;

	
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
		
	}
	
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n, 0);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
	}

	@Override
	public void withdraw(double amt)
	{
		if (amt<=0 || )
		{
			super.withdraw(amt);
		}
	}
	
	
	@Override
	public void endOfMonthUpdate() 
	{
		
	}
}

