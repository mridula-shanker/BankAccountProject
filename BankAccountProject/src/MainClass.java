import java.util.ArrayList;
import java.util.Scanner;

public class MainClass 
{

	public static void main(String[] args) 
	{
		ArrayList <BankAccount> accounts = new ArrayList <BankAccount>();
		
		Scanner in = new Scanner(System.in);
		final int OVER_DRAFT_FEE = 15;
		final double RATE = 0.0025;
		final double TRANSACTION_FEE = 1.5;
		final int MIN_BAL = 300;
		final int MIN_BAL_FEE = 10;
		final int FREE_TRANSACTIONS = 10;
		
		boolean ans = true;
		while (ans)
		{
			System.out.print("Would you like to add an account (account), make a transaction (transaction), or terminate the program? (terminate)");
			String input = in.nextLine();
			if(input.equals("account"))
			{
				System.out.println("Would you like to make a Checking Account(checking) or Savings Account(saving)?");
				String type = in.nextLine();
				if (type.equals("checking")) 
				{
					System.out.println("Please enter your name");
					String name = in.nextLine();
					System.out.println("Please enter your name");
					String name = in.nextLine();
					CheckingAccount(String n, double b, double odf, double tf, int freeTrans)
				}
				
			}
			else if(input.equals("transaction"))
			{
				
			}
			else if(input.equals("terminate"))
			{
				
			}
			else
			{
				System.out.print("Please enter a valid response.");
			}
				
			
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
