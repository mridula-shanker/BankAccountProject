
	import java.util.ArrayList;

	public class BankAccountTest 
	{

		public static void main(String[] args) 
		{
			ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
			accounts.add(new CheckingAccount("Mr. Ellis", 100, 30, 1, 10));
			accounts.add(new CheckingAccount("Mrs. Ellis",30,1,10));
			accounts.add(new SavingsAccount("Mr. Ellis", 500, .025, 200,5));
			accounts.add(new SavingsAccount("Mrs. Ellis", .025, 200,5));
		
			boolean correct = true;
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();
			System.out.println("11111111111111111");
			accounts.get(3).deposit(600);
			try 
			{
				accounts.get(3).deposit(-10);
				System.out.println("savings deposit negative");
				return;
				

			}
			catch(IllegalArgumentException e)
			{
				
			}

			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("22222222222222222222222222222222");
			try 
			{

				accounts.get(0).deposit(-10);
				System.out.print("checking deposit negative");
				return;
			}
			catch(IllegalArgumentException e)
			{
				
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNN");
		
			
			try 
			{
				accounts.get(3).withdraw(-10);
				System.out.println("savings withdraw negative");
				return;

			}
			catch(IllegalArgumentException e)
			{
				
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnn");
		
			try 
			{

				accounts.get(0).withdraw(-10);
				System.out.print("checking withdraw negative");
				return;
			}
			catch(IllegalArgumentException e)
			{
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("nNnNnNnNnNNNNNN");
	
			try
			{
				accounts.get(3).transfer(accounts.get(0), 50);
				System.out.println("savings transfer names don't match");
				return;
			}
			catch(IllegalArgumentException e)
			{
				
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("transfer 50 from acct 3 to 0");

			try
			{
				accounts.get(0).transfer(accounts.get(3), 50);
				System.out.println("checking transfer names don't match");
				return;
			}
			catch(IllegalArgumentException e)
			{
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("transfer 50 from acct 0 to 3");

			accounts.get(0).withdraw(120);
			
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("--------------------------");

			if(accounts.get(0).getBalance() != -50)
			{
				System.out.println("line 82 overdraft fee");
				return;
			}

			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("-it became negative 50--------------");

			try
			{
				accounts.get(3).withdraw(601);
				System.out.println("overdraft savings line 89");
				return;
			}
			catch(IllegalArgumentException e)
			{
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("-not sufficent balance------------------");
		
			accounts.get(3).withdraw(600);
			if(accounts.get(3).getBalance()!= -5)
			{
				System.out.println("withdraw line 96 min balance fee");
				return;
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("--------------------------");
			
			accounts.get(3).deposit(100);
			if(accounts.get(3).getBalance() != 95)
			{
				System.out.println("deposit line 102 maybe minbal");
				return;
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("--------------------------");
		
			accounts.get(2).transfer(accounts.get(0),200);

			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("--------------------------");
			
			for(int i = 0; i<8; i++)
			{
				if(i%2 == 0)
				{
					accounts.get(0).deposit(10);
					accounts.get(0).toString();
					System.out.println("22222");
				}
				else
				{
					accounts.get(0).withdraw(10);
					accounts.get(0).toString();
					System.out.println("11111");
				}
			}

			System.out.println("for loop");
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("--------------------------");

			if(accounts.get(0).getBalance() != 150)
			{
				System.out.println("transactions in loop on 111");
				return;
			}
			
			accounts.get(0).withdraw(50);
			if(accounts.get(0).getBalance() != 99)
			{
				System.out.println("transaction fee line 119");
				return;
			}
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("-99999---------------");			
			
			try
			{
				accounts.get(0).transfer(accounts.get(2), 100);
				System.out.println("transfer overdraft line 132");
				return;
			}
			catch(Exception e)
			{
			}
			
			accounts.get(0).withdraw(99);
	
			accounts.get(0).toString();
			accounts.get(2).toString();
			accounts.get(1).toString();
			accounts.get(3).toString();		
			System.out.println("after withdraw 99");
			if(accounts.get(0).getBalance() != -31)
			{
				System.out.println("transaction fee overdraft");
				return;
			}
			System.out.println(accounts.get(0));
			System.out.println("you made it!");

					
		}

	}
