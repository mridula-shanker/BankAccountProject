
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE; 
	private final double TRANSACTION_FEE; 
	private final double FREE_TRANS; 
	private int numTransactions;
	
	CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
	{
		super(n, b);
		OVER_DRAFT_FEE = odf;
		FREE_TRANS= freeTrans;
		TRANSACTION_FEE= tf;
	}

	CheckingAccount(String n, double odf, double tf, int freeTrans)
	{
		super(n);
		OVER_DRAFT_FEE = odf;
		FREE_TRANS= freeTrans;
		TRANSACTION_FEE=tf;
		
	}

	@Override
	public void deposit(double amt)
	{
		if (amt < 0)
		{
			throw new IllegalArgumentException();
		}
		numTransactions++;
		if (numTransactions>FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		super.deposit(amt);
		
	}
	
	@Override
	public void withdraw (double amt)
	{
		if (this.getBalance()<0 || amt<0)
		{
			throw new IllegalArgumentException();
		}
		
	
		super.withdraw(amt);
		
	}
	
	@Override
	public void transfer(BankAccount other, double amt)
	{
		if(this.getName().equals(other.getName()))
		{
			super.transfer(other, amt);
		}
		else
		{
			throw new IllegalArgumentException();
		}
		
		numTransactions++;
		if(this.getBalance()>TRANSACTION_FEE)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		else
		{
			throw(new IllegalArgumentException());
		}
	}
	
	
	@Override
	public void endOfMonthUpdate() 
	{
		numTransactions=0;
		
	}
	
	
	
}
