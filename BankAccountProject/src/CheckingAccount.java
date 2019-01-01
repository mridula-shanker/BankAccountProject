
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE; 
	private final double TRANSACTION_FEE; 
	private final double FREE_TRANS; 
	private int numTransactions;
	
	/**
	 * Constructor creates an instance with initial balance 
	 * @param account name, initial balance, overdraft and transaction fee, and number of free transactions
	 */
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		FREE_TRANS= freeTrans;
		TRANSACTION_FEE= tf;
	}

	/**
	 * Constructor creates an instance with initial balance as zero
	 * @param account name, overdraft and transaction fee, and number of free transactions
	 */
	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		FREE_TRANS= freeTrans;
		TRANSACTION_FEE=tf;
		
	}

	/**
	 * Overriding deposit method to track number of transaction and transaction fee. 
	 * @param amount
	 */
	@Override
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		numTransactions++;
		double newAmt = amt;
		if (numTransactions > FREE_TRANS)
		{
			newAmt = amt - TRANSACTION_FEE;
		}
		super.deposit(newAmt);
		
	}
	
	/**
	 * Overriding withdraw method to track number of transaction and transaction fee. 
	 * @param amount
	 */
	@Override
	public void withdraw (double amt)
	{
		if (this.getBalance()<0 || amt<0)//amount and balance should be positive
		{
			throw new IllegalArgumentException();
		}
		
		double newAmt = amt;
		
		numTransactions++;
		if(numTransactions > FREE_TRANS)
			newAmt = newAmt + TRANSACTION_FEE;

		if(this.getBalance() - newAmt < 0)
			newAmt = newAmt + OVER_DRAFT_FEE;

		super.withdraw(newAmt);
	
	}
	
	/**
	 * Overriding transfer method to validate user name and to track number of transaction and transaction fee. 
	 * @param other account and amount
	 */
	@Override
	public void transfer(BankAccount other, double amt)
	{
		if(this.getBalance()<amt ||  amt<0)
		{
			throw new IllegalArgumentException();
		}
			
		if(this.getName().equals(other.getName()))
		{
			numTransactions++;
			double newAmt = amt;
			if(numTransactions > FREE_TRANS)
				newAmt = newAmt + TRANSACTION_FEE;
			if(this.getBalance()>=newAmt)
			{
				super.transfer(other, amt);
			}
			else
			{
				numTransactions--;
				throw new IllegalArgumentException();
			}
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
	}
	
	/**
	 * Overriding abstract method to reset the number of transaction to zero
	 */
	@Override
	public  void endOfMonthUpdate() 
	{
		numTransactions=0;
		
	}
	
	
	
}
