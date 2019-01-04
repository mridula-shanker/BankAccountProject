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

	
	/*
	 * to fetch the valid transaction amount which allows only the positive values (double)
	 * @param display string (String type)
	 * @return valid transaction amount (double)
	 */	
	public static double getValidAmt(String dispStr)
	{
		Scanner in = new Scanner(System.in);
		double amt = -1;
		while (amt <= -1)  
		{
			System.out.println(dispStr);
			if (in.hasNextDouble())// Allows only the double value
			{
				amt = in.nextDouble();
				if (amt >= 0) // Allows only the positive integer
				{
					return amt;
				}
				else
				{
					amt = -1;
				}
			}
			else
			{
				in.next();
				amt = -1;
			}	
		}
		return amt;
	}

	/*
	 * To fetch the account type for the given account number
	 * @param Account number (int)
	 * @return Account Type (Saving or Checking)- String
	 */
	public static String getAccType(int accNo)
	{
		BankAccount tempVar = accounts.get(accNo);
		String accType="";
		if (tempVar instanceof SavingsAccount) // Fetch the account type
		{
			accType = "Saving";
		}
		else
		{
			accType = "Checking";
		}
		return accType;
	}
	
	/*
	 * to get a valid account number from the user. either by account number or by name
	 * @return valid account number (integer); 
	 * in case of return back to previous menu without a valid account number it will return a -1
	 */
	public static int getValidAccount()
	{
		Scanner in = new Scanner(System.in);
		Scanner inn = new Scanner(System.in);
		Scanner confIn = new Scanner(System.in);
		Scanner nameIn = new Scanner(System.in);
		Scanner accIn = new Scanner(System.in);		
		
		boolean action = true;
		int accNo=-1;
		do
		{
			System.out.println("Would you like to enter account number (num); or name (name); or go back (back)");
			String input = in.nextLine();
			if (input.equalsIgnoreCase("num"))   // By Account number
			{	
				int tempAccNo = -1;
				while (tempAccNo <= -1)
				{
					System.out.println("Please enter a Valid Account number: ");
					if (inn.hasNextInt())
					{
						tempAccNo = inn.nextInt();
						int arraySize = accounts.size() - 1;
						if (tempAccNo > arraySize || tempAccNo < 0)  // Validate the account number by array size
						{
							System.out.println("Invalid Account number - out of index");
							action = false;
							break;
						}
						else
						{
							String accName = accounts.get(tempAccNo).getName(); // fetch the account name for the given account number
							String accType = getAccType(tempAccNo);
							double tempbal = accounts.get(tempAccNo).getBalance();
							boolean conf = true;
							do
							{
								System.out.println (accName + "\t" +  accType + "\t" + tempbal + " - Please Verify Name (Y/N)");
								String confInput = confIn.nextLine();
								if(confInput.equalsIgnoreCase("Y"))  // Re-confirming the account name
								{
									conf = true;
									return tempAccNo;
								}
								else if(confInput.equalsIgnoreCase("N"))
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
						System.out.println("Invalid number");
						inn.next();
						tempAccNo = -1;
					}	
				}
			}
			else if (input.equalsIgnoreCase("name")) // Search Account by name
			{
				String regex = "^[a-zA-Z ]+$";
				String name;
		        do 
		        {
		            System.out.println("Please enter the name: ");
		            name = nameIn.nextLine();
		            if(!name.matches(regex))
		            	System.out.println("Please Enter A Valid String (Only alphabets)");
		        } while(!name.matches(regex)); // Allows only the alphabet
		        
		        ArrayList<Integer> accNumList = new ArrayList <Integer>();
		        int match = 0;
	        	System.out.println("Account number and balance that matches the given name: ");
		        for(int i=0; i<accounts.size(); i++)
		        {
		        	String tempName = accounts.get(i).getName();
		        	if(tempName.equals(name))   // Validate the account name
		        	{
		        		int a = accounts.get(i).getAccountNumber();
		        		double b = accounts.get(i).getBalance();
		        		String accType= getAccType(i);
		        		System.out.println(a + "\t" + b + "\t" + accType); // Displays the account information
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
			        	System.out.println("Please enter and reconfirm the account number from the list above");
		        		if(accIn.hasNextInt())
		        		{
		        			boolean matchfoundflag = false;
		        			isNumber=true;
		        			int checkNum = accIn.nextInt();
		        			for(int j=0; j<accNumList.size(); j++) 
		        			{
		        				if(checkNum==accNumList.get(j)) // To check the give acc number in the account number list
		        				{
		        					matchfoundflag = true;
		        					return checkNum;
		        				}
		        			}
		        			if (matchfoundflag == false)
		        			{
		        				System.out.println("Invalid Account Number (Not found in the listed values)... Please try again");
		        				isNumber = true;
		        				break;
		        			}
		        		}
		        		else
		        		{
		        			System.out.println("Enter a valid number");
		        			accIn.next();
		        			isNumber=false;
		        		}
		        	}while(!isNumber);

		        }
			}
			else if (input.equalsIgnoreCase("back"))
			{
				System.out.println("Exiting the account number input option");
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
	
	/*
	 * to add the new account Saving (save); Checking (Check)
	 */
	public static void addAccount()
	{
		Scanner in = new Scanner(System.in);
		double bal=0;
		String accType="";
		boolean saveCheckOption = true;
		boolean ans = true;
		do
		{
			System.out.println("Would you like to add a Savings (save) or Checking (check) Account or go back to Main menu (back)?");
			String input = in.nextLine();
			if (input.equalsIgnoreCase("save"))
			{
				System.out.println("save");
				accType="S";
				ans = true;
			}
			else if (input.equalsIgnoreCase("check"))
			{
				System.out.println("check");
				accType="C";
				ans = true;
			}			
			else if (input.equalsIgnoreCase("back"))
			{
				System.out.println("Exiting the Add Account Option and back to Main Menu");
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
	            System.out.println("Please enter your name:");
	            name = in.nextLine();
	            if(!name.matches(regex))
	            	System.out.println("Please Enter A Valid String (Only alphabets)");
	        } while(!name.matches(regex));  // Allows only the alphabet for the name
	        
	        bal = getValidAmt("Please enter intial balance (zero to any postive number)");
			
			try
			{
			if (accType.equals("C") && bal==0) // Checking account with zero balance
			{
				accounts.add(new CheckingAccount(name,OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
				System.out.println("Successfully added a Checking account for " + name + " with $" + bal + " balance");
			}
			else if (accType.equals("C") && bal > 0) // Checking account with initial balance
			{
				accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
				System.out.println("Successfully added a Checking account for " + name + " with $" + bal + " balance");	
			}
			else if (accType.equals("S") && bal == 0)  // Saving account with zero balance
			{
				accounts.add(new SavingsAccount(name, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added a Savings account for " + name + " with $" + bal + " balance");
			}
			else if (accType.equals("S") && bal > 0)  // Saving account with initial balance
			{
				accounts.add(new SavingsAccount(name, bal, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added a Savings account for " + name + " with $" + bal + " balance");
			}
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Transacation not Authorised. Account Creation has been cancelled");			
			}

		}
	}	
	
	/**
	 * To carry out the transaction like Deposit(D), Withdraw(W), and transfer(T)
	 */
	public static void transactions()
	{
		Scanner in = new Scanner(System.in);
		int accNo;
		boolean action = true;
		do
		{
			System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T); Get Acct Info (I) or Back to Main menu (B)?");
			String input = in.nextLine();
			switch(input)
			{
			case ("D"):  // To deposit to the account
				try
				{
				System.out.println("Deposit in progress...");
				accNo = getValidAccount();
				if (accNo != -1)
				{
					double amt = getValidAmt("Please enter the valid amount");
					String accName = accounts.get(accNo).getName();
					accounts.get(accNo).deposit(amt);
					System.out.println("Deposit transaction has been successfully Completed");
					System.out.println(accounts.get(accNo).toString() + "\t" + getAccType(accNo));
				}
				else
				{
					break;
				}
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Transacation not Authorised. Deposit has been cancelled");			
				}
			break;
			
			case("W"):
				try
				{
				System.out.println("Withdraw in progress...");
				accNo = getValidAccount();
				if (accNo != -1)
				{
					double amt = getValidAmt("Please enter the valid amount");
					accounts.get(accNo).withdraw(amt);	
					System.out.println("Withdraw transaction has been successfully Completed");
					System.out.println(accounts.get(accNo).toString() + "\t" + getAccType(accNo));
				}
				else
				{
					break;
				}
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Transacation not Authorised. Withdraw has been cancelled");			
				}
				break;

			case ("T"):
				try
				{
				System.out.println("Transfer in progress.....");
				
				accNo = getValidAccount();
				System.out.println("Enter the Destination account number.....");
				int toAccNo = getValidAccount();
				if (accNo != -1 && toAccNo != -1)
				{
					double amt = getValidAmt("Please enter the valid amount");
					accounts.get(accNo).transfer(accounts.get(toAccNo), amt);
					System.out.println("Transfer transaction has been successfully Completed");
					System.out.println(accounts.get(accNo).toString() + "\t" + getAccType(accNo));
					System.out.println(accounts.get(toAccNo).toString() + "\t" + getAccType(toAccNo));
				}
				else
				{
					break;
				}
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Transacation not Authorised. Transaction has been cancelled");			
			}

			break;

			case ("I"):
				try
				{	
				accNo = getValidAccount();
				if (accNo != -1)
				{
					System.out.println(accounts.get(accNo).toString() + "\t" + getAccType(accNo));
				}
				else
				{
					break;
				}	
				}
				catch(IllegalArgumentException e)
				{
					System.out.println("Transacation not Authorised. Please Check with Administrator");			
				}
				break;
				
			case("B"):
				System.out.println("Exiting the Transaction option");
				action = true;
				break;			
				
			default:
				System.out.println("Enter Valid Transcaction....");
				action = false;
				break;
			}
		}
		while(!action);
	
	}
	
	/**
	 * Main Function to add an Account, to make a transaction, to get account info and to exit
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		boolean ans = false;
		do
		{
			System.out.println(" Would you like to add an account (account), make a transaction (transaction), or terminate the program? (terminate)");
			String input = in.nextLine();
			if(input.equalsIgnoreCase("account"))
			{
				System.out.println("create account");
				addAccount();
			}
			else if(input.equalsIgnoreCase("transaction"))
			{
				System.out.println("transaction");
				transactions();
			}
			else if(input.equalsIgnoreCase("terminate"))
			{
				System.out.println("terminate");
				ans=true;
			}
			else
			{
				System.out.print("Please enter a valid response.");
			}
		
		}while(!ans);
	}

}
