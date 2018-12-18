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
			System.out.print("Would you like to add an account, make a transaction, or terminate the program?");
			String input = in.nextLine();
			if(input.equals("Bank Account"))
			{
				
			}
			else if(input.equals("Transaction"))
			{
				
			}
			else if(input.equals("Terminate"))
			{
				
			}
			else
				
		}

	}

}
