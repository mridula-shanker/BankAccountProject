import java.util.ArrayList;
import java.util.Scanner;



public class MainClass 
{
	
	static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	static final int OVER_DRAFT_FEE = 15;
	static final double RATE = 0.0025;
	static final double TRANSACTION_FEE = 1.5;
	static final int MIN_BAL = 300;
	static final int MIN_BAL_FEE = 10;
	static final int FREE_TRANSACTIONS = 10;

	public static void addAccount()
	{
		Scanner in = new Scanner(System.in);
		boolean ans;
		double bal=0;
		do
		{

			System.out.println("Would you like to add a Savings (save) or Checking (check) Account or Back to menu (B)?");
			String input = in.nextLine();
			if (input.equals("save"))
			{
				ans=false;
				System.out.println("save");
				//System.out.println("Please enter name\n");
				
				String regex = "^[a-zA-Z ]+$";
				String name;
		        do {
		            System.out.print("Please enter your name:");
		            name = in.nextLine();
		            if(!name.matches(regex))
		            	System.out.println("Please Enter A Valid String (Only alphabets)");
		        } while(!name.matches(regex));
		        
				
				bal = -1;
				while (bal <= -1)
				{
					System.out.println("Please enter intial balance (zero to any postive number)");
					if (in.hasNextDouble())
					{
						bal = in.nextDouble();
					}
					else
					{
						in.next();
					}	
				}

				if (bal==0)
				{
					accounts.add(new CheckingAccount(name,OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
					System.out.println("Successfully added the Saving accont with $ " + 0.00 + " balance" + name);
					int temp = accounts.size()-1;
					System.out.println(accounts.get(temp).getAccountNumber());
				}
				else
				{
					accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
					System.out.println("Successfully added the Saving accont with $ " + bal +  " balance" + name);
					int temp = accounts.size()-1;
					System.out.println(accounts.get(temp).getAccountNumber());
			
				}
			}
			else if (input.equals("check"))
			{
				ans=false;
				System.out.println("check");
				
				String regex = "^[a-zA-Z ]+$";
				String name;
		        do {
		            System.out.print("Please enter your name:");
		            name = in.nextLine();
		            if(!name.matches(regex))
		            	System.out.println("Please Enter A Valid String (Only alphabets)");
		        } while(!name.matches(regex));
		
				bal = -1;
				while (bal <= -1)
				{
					System.out.println("Please enter intial balance (zero to any postive number)");
					if (in.hasNextDouble())
					{
						bal = in.nextDouble();
					}
					else
					{
						in.next();
					}
					
				}
					
				if (bal==0)
				{
					accounts.add(new CheckingAccount(name,OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
					System.out.println("Successfully added the Checking accont with $ " + 0.00 + " balance" + name);
				}
				else
				{
					accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
					System.out.println("Successfully added the Checking accont with $ " + bal + " balance" + name);
				}
			}
			else if (input.equals("back"))
			{
				System.out.println("Quiting from account options");
				ans=true;
			}
			else 
				System.out.println("Please reenter a valid response");
				ans=false;
		}
		while(!ans);
	}
	
	public static int getValidAccount()
	{
		Scanner in = new Scanner(System.in);
		boolean accStat = false;
		int accNo;
		while (!accStat)
		{
			System.out.println("Please enter a Valid Account Number (integer))");
			if (in.hasNextInt())
			{
				accNo = in.nextInt();
				int arrSize = accounts.size();
				if (accNo > arrSize-1)
				{
					System.out.println("Not a valid Acc number");
				}
			}
			else
			{
				in.next();
				accStat = false;
			}
			
		}

		return validAcct;
	}
	public static void transactions()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
		String input = in.nextLine();
		while(!input.equals("back"))
		{
			if (input.equals("D"))
			{
				System.out.println("Deposit");
				System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
				input = in.nextLine();
			}
			else if (input.equals("W"))
			{
				System.out.println("Withdraw");
				System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
				input = in.nextLine();
			}			
			else if (input.equals("T"))
			{
				System.out.println("Transfer");
				System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
				input = in.nextLine();
			}
			else if (input.equals("back"))
			{
				break;
			}
			else{
				System.out.println("Enter Valid Transcaction");
				System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
				input = in.nextLine();
			}
		}
	
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		boolean ans = true;
		while (ans)
		{
			System.out.print("Would you like to add an account (account), make a transaction (transaction), or terminate the program? (terminate)");
			String input = in.nextLine();
			if(input.equals("a"))
			{
				System.out.println("create account");
				addAccount();
			}
			else if(input.equals("t"))
			{
				System.out.println("transaction");
				transactions();
			}
			else if(input.equals("terminate"))
			{
				System.out.println("terminate");
				ans=false;
			}
			else
			{
				System.out.print("Please enter a valid response.");

			}
				
			//words.matches("[^a-zA-Z0-9 ]"
			
			/**
			 * accNum
			 * local variable = null
			 * for ( accNum == localVariable) 
			 *		
			 *		check variable
			 *
			 * local variable = acct num
			 * if localVariable == null
			 * 		promt user to enter correct number
			 */
		}

	}

}
