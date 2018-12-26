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
		double bal=0;
		String accType="A";
		boolean saveCheckOption = true;
		boolean ans = true;
		do
		{
			System.out.println("Would you like to add a Savings (save) or Checking (check) Account or Back to menu (B)?");
			String input = in.nextLine();
			if (input.equals("save"))
			{
				System.out.println("save");
				accType="S";
				ans = true;
			}
			else if (input.equals("check"))
			{
				System.out.println("check");
				accType="C";
				ans = true;
			}			
			else if (input.equals("B"))
			{
				System.out.println("Exiting the Add Account Option");
				saveCheckOption = false;
				ans = true;
			}				
			else
			{
				System.out.println("Please Enter a valid response");
				ans = false;
			}
		}while(!ans);
		
		if (saveCheckOption == true)
		{
		
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
					bal = -1;
				}	
			}
			
			if (accType.equals("C") && bal==0)
			{
				accounts.add(new CheckingAccount(name,OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
				System.out.println("Successfully added the Checking account with $ " + 0.00 + " balance" + name);
			}
			else if (accType.equals("C") && bal > 0)
			{
				accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
				System.out.println("Successfully added the Checking account with $ " + bal +  " balance" + name);		
			}
			else if (accType.equals("S") && bal == 0)
			{
				accounts.add(new SavingsAccount(name, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added the Savings account with $ " + bal +  " balance" + name);
			}
			else if (accType.equals("S") && bal > 0)
			{
				accounts.add(new SavingsAccount(name, bal, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added the Savings account with $ " + bal +  " balance" + name);
			}
		}
	}	
	
	
	public static int getValidAccount()
	{
		Scanner in = new Scanner(System.in);
		boolean action = true;
		int accNo=0;
		do
		{
			System.out.println("Want to Enter Account Number (num); or Name (name); or go back (back)");
			String input = in.nextLine();
			if (input.equals("num"))
			{	
				int tempAccNo = -1;
				while (tempAccNo <= -1)
				{
					System.out.println("Please enter Account number");
					if (in.hasNextInt())
					{
						tempAccNo = in.nextInt();
						int arraySize = accounts.size() - 1;
						if (tempAccNo > arraySize)
						{
							System.out.println("Invalid Account number");
							action = false;
							break;
						}
						else
						{
							String accName = accounts.get(tempAccNo).getName();
							boolean conf = true;
							do
							{
								System.out.println(accName + " - Please Verify Name (Y/N)");
								String confInput = in.nextLine();
								if(confInput.equals("Y"))
								{
									conf = true;
									return tempAccNo;
								}
								else if(confInput.equals("N"))
								{
									conf = true;
									break;
								}
								else
								{
									System.out.println("Enter a valid option (Y/N");
									conf = false;
								}
							}while(!conf);
							
						}
					}
					else
					{
						in.next();
						tempAccNo = -1;
					}	
				}
			}
			else if (input.equals("name"))
			{
				String regex = "^[a-zA-Z ]+$";
				String name;
		        do 
		        {
		            System.out.print("Please enter your name:");
		            name = in.nextLine();
		            if(!name.matches(regex))
		            	System.out.println("Please Enter A Valid String (Only alphabets)");
		        } while(!name.matches(regex));
		        
		        ArrayList<Integer> accNumList = new ArrayList <Integer>();
		        int match = 0;
	        	System.out.println("Account number and balance Matches the given name: ");
		        for(int i=0; i<accounts.size(); i++)
		        {
		        	String tempName = accounts.get(i).getName();
		        	if(tempName.equals(name))
		        	{
		        		int a = accounts.get(i).getAccountNumber();
		        		double b = accounts.get(i).getBalance();
		        		BankAccount tempVar = accounts.get(i);
		        		String accType="";
		        		if (tempVar instanceof SavingsAccount)
		        		{
		        			accType = "Saving";
		        		}
		        		else
		        		{
		        			accType = "Checking";
		        		}
		        		System.out.println(a + "----" + b + "----" + accType);
		        		match=1;
		        		accNumList.add(i);
		        	}
		        }
		        
		        if(match==0)
		        {
		        	System.out.println("No match found!");
			        	action = false;
			        	break;
		        }
		        else
		        {
		        	boolean isNumber;
		        	do
		        	{
			        	System.out.println("Please reconfirm your account number from the list above");
		        		if(in.hasNextInt())
		        		{
		        			boolean matchfoundflag = false;
		        			isNumber=true;
		        			int checkNum = in.nextInt();
		        			for(int j=0; j<accNumList.size(); j++)
		        			{
		        				if(checkNum==accNumList.get(j))
		        				{
		        					matchfoundflag = true;
		        					return checkNum;
		        				}
		        			}
		        			if (matchfoundflag == false)
		        			{
		        				System.out.println("Invalid Account Number. Please try again");
		        				isNumber = true;
		        				break;
		        			}
		        		}
		        		else
		        		{
		        			System.out.println("Enter a valid number");
		        			in.next();
		        			isNumber=false;
		        		}
		        	}while(!isNumber);

		        }
			}
			else if (input.equals("back"))
			{
				System.out.println("Exiting the Get Account Information option");
				action = true;
			}
			else
			{
				System.out.println("Enter Valid Transcaction (num or name or back)");
				action = false;
			}
		}while (!action);

		return accNo;
	}
	
	public static void transactions()
	{
		Scanner in = new Scanner(System.in);
		boolean action = true;
		do
		{
			System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
			String input = in.nextLine();
			if (input.equals("D"))
			{
				System.out.println("Deposit");
				int accNo = getValidAccount();
			}
			else if (input.equals("W"))
			{
				System.out.println("Withdraw");
				
			}			
			else if (input.equals("T"))
			{
				System.out.println("Transfer");
			}
			else if (input.equals("back"))
			{
				System.out.println("Exiting the Transaction option");
				action = true;
			}
			else
			{
				System.out.println("Enter Valid Transcaction");
				action = false;
			}
		}while(!action);
	
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		boolean ans = true;
		do
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
		
		}while(ans);
		
			//words.matches("[^a-zA-Z0-9]+$"
		
			/**
			 * accNum
			 * local variable = null
			 * for ( accNum == localVariable) 
			 *		
			 *		check variable
			 *
			 * local variable = acct num
			 * if localVariable == null
			 * 		prompt user to enter correct number
			 */
		

	}

}
