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
		String accType="";
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
				System.out.println("Successfully added a Checking account with $ " + bal + " balance " + name);
			}
			else if (accType.equals("C") && bal > 0)
			{
				accounts.add(new CheckingAccount(name, bal, OVER_DRAFT_FEE, TRANSACTION_FEE,FREE_TRANSACTIONS));
				System.out.println("Successfully added a Checking account with $ "+ bal +  " balance " + name);		
			}
			else if (accType.equals("S") && bal == 0)
			{
				accounts.add(new SavingsAccount(name, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added a Savings account with $ " + bal +  " balance " + name);
			}
			else if (accType.equals("S") && bal > 0)
			{
				accounts.add(new SavingsAccount(name, bal, RATE, MIN_BAL,MIN_BAL_FEE));
				System.out.println("Successfully added a Savings account with $ " + bal +  " balance " + name);
			}
		}
	}	
	
	public static double getValidAmt()
	{
		Scanner in = new Scanner(System.in);
		double amt = -1;
		while (amt <= -1)
		{
			System.out.println("Please enter the valid amount");
			if (in.hasNextDouble())
			{
				amt = in.nextDouble();
				return amt;
			}
			else
			{
				in.next();
				amt = -1;
			}	
		}
		return amt;
	}
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
			if (input.equals("num"))
			{	
				int tempAccNo = -1;
				while (tempAccNo <= -1)
				{
					System.out.println("Please enter Account number");
					if (inn.hasNextInt())
					{
						tempAccNo = inn.nextInt();
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
								String confInput = confIn.nextLine();
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
						System.out.println("Invalid number");
						inn.next();
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
		            name = nameIn.nextLine();
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
		        		if(accIn.hasNextInt())
		        		{
		        			boolean matchfoundflag = false;
		        			isNumber=true;
		        			int checkNum = accIn.nextInt();
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
		        			accIn.next();
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
		int accNo;
		boolean action = true;
		do
		{
			System.out.println("Please enter Transcaction Type:  Withdraw (W); Deposit (D); Transfer (T) or Back to menu (B)?");
			String input = in.nextLine();
			switch(input){
			case ("D"):
			{
				System.out.println("Deposit in progress....");
				accNo = getValidAccount();
				if (accNo != -1)
				{
					double amt = getValidAmt();
					String accName = accounts.get(accNo).getName();
					accounts.get(accNo).deposit(amt);
					System.out.println("Your money has been successfully deposited");
					System.out.println(accNo + " -- " + accounts.get(accNo).getBalance() + " --" );
					System.out.println(accounts.get(accNo).toString());	
				}
				else
				{
					break;
				}
			}
			break;
			case("W"):
				System.out.println("Withdraw in progress.....");
				accNo = getValidAccount();
				if (accNo != -1)
				{
					double amt = getValidAmt();
					accounts.get(accNo).withdraw(amt);	
					System.out.println(accNo + " -- " + accounts.get(accNo).getBalance() + " --" );
					System.out.println(accounts.get(accNo).toString());
				}
				else
				{
					break;
				}
				break;
						
			case ("T"):
				System.out.println("Transfer in progress.....");
				
				accNo = getValidAccount();
				System.out.println("Enter the Destination account number.....");
				int toAccNo = getValidAccount();
				if (accNo != -1 && toAccNo != -1)
				{
					double amt = getValidAmt();
					System.out.println(accNo + " -- " + accounts.get(accNo).getBalance() + " --" + toAccNo);
					accounts.get(accNo).transfer(accounts.get(toAccNo), amt);
				}
				else
				{
					break;
				}
				break;
				
			case("back"):
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
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		boolean ans = false;
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
				ans=true;
			}
			else
			{
				System.out.print("Please enter a valid response.");
			}
		
		}while(!ans);
		
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
