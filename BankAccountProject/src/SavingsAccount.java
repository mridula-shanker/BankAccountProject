
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;

	/**
	 * Constructor creates an instance with initial balance 
	 * @param account name, initial balance, interest rate, minimum balance, and minimum balance fee.
	 */
	public SavingsAccount(String n, double b, double r, double mb, double mbf)
	{
		super(n,b);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
	}
	
	/**
	 * Constructor creates an instance with initial balance as 0
	 * @param account name, interest rate, minimum balance, and minimum balance fee.
	 */
	public SavingsAccount(String n, double r, double mb, double mbf)
	{
		super(n, 0);
		intRate=r;
		MIN_BAL=mb;
		MIN_BAL_FEE=mbf;
	}

	/**
	 * Overriding withdraw method to track minimum balance and minimum balance fee. 
	 * @param amount
	 */
	public void withdraw(double amt)
	{
		if (amt <=0 || this.getBalance() < amt || this.getBalance() < 0 )
		{
			throw new IllegalArgumentException();
		}
		
		super.withdraw(amt);
		if(this.getBalance()< MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}
		/**
		if (this.getBalance() >= amt + MIN_BAL_FEE) // to validate the withdraw amount and minimum balance fee should not exceed the current balance
		{
			super.withdraw(amt);
			if(this.getBalance()< MIN_BAL)
			{
				super.withdraw(MIN_BAL_FEE);
			}
		}
		 */
	}
	

	/**
	 * Overriding transfer method for Saving Account with account name validation
	 * @param other account and amount
	 */
	@Override
	public void transfer(BankAccount other, double amt)
	{
		if(this.getBalance()<amt)
		{
			throw new IllegalArgumentException();
		}

		if(this.getName().equals(other.getName()))
		{
			double newAmt = amt; //+ TRANSACTION_FEE;
			if(this.getBalance()<0 || this.getBalance() < newAmt)
				throw new IllegalArgumentException();
			//super.withdraw(CA_TRANSACTION_FEE);
			super.transfer(other, amt);
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * to update deposit with interest 
	 * @param amount
	 */
	public void addInterest()
	{
		super.deposit(getBalance()* intRate);
	}
	
	/**
	 * Overriding abstract method to add interest
	 */
	@Override
	public void endOfMonthUpdate() 
	{
		addInterest();
	}
}

