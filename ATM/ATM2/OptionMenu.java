package ATM2;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OptionMenu {
    Scanner input = new Scanner(System.in);
	DecimalFormat format = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer,Account> data = new HashMap<Integer,Account>();
    public void getLogin() throws IOException{
        boolean loop = false;
        int customerNumber = 0;
        int pinNumber = 0;
        while(!loop){
            try {
                System.out.println("\n What is your customer number : ");
                customerNumber = input.nextInt();
                System.out.println("\n What is your pin number : ");
                pinNumber = input.nextInt();
                Iterator it = data.entrySet().iterator();
                while(it.hasNext()){
                 Map.Entry pair = (Map.Entry) it.next();
                 Account acc = (Account) pair.getValue();
                 if(data.containsKey(customerNumber) && pinNumber == acc.getPinNumber()){
                    getAccountType(acc);
                    loop = true;
                    break;
                 }
                }
            } catch (InputMismatchException e)  {
				System.out.println("\nInvalid Character(s). Only Numbers.");
            }
        }
    }
    
    public void getAccountType(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSelect the account you want to access: ");
				System.out.println(" Type 1 - checking Account");
				System.out.println(" Type 2 - Savings Account");
				System.out.println(" Type 3 - Exit");
				System.out.print("\nChoice: ");

				int selection = input.nextInt();

				switch (selection) {
				case 1:
					getChecking(acc);
					break;
				case 2:
					getSaving(acc);
					break;
				case 3:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getChecking(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCheckings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("\nChoice: ");

				int selection = input.nextInt();

				switch (selection) {
				case 1:
					System.out.println("\nCheckings Account Balance: " + format.format(acc.getCheckingBalance()));
					break;
				case 2:
					acc.getCheckingWidthrawInput();
					break;
				case 3:
					acc.getCheckingDepositInput();
					break;

				case 4:
					acc.getTransferInput("checking");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
		getAccountType(acc);
	}

	public void getSaving(Account acc) {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nSavings Account: ");
				System.out.println(" Type 1 - View Balance");
				System.out.println(" Type 2 - Withdraw Funds");
				System.out.println(" Type 3 - Deposit Funds");
				System.out.println(" Type 4 - Transfer Funds");
				System.out.println(" Type 5 - Exit");
				System.out.print("Choice: ");
				int selection = input.nextInt();
				switch (selection) {
				case 1:
					System.out.println("\nSavings Account Balance: " + format.format(acc.getSavingBalance()));
					break;
				case 2:
					acc.getSavingWidthrawInput();
					break;
				case 3:
					acc.getSavingDepositInput();
					break;
				case 4:
					acc.getTransferInput("Saving");
					break;
				case 5:
					end = true;
					break;
				default:
					System.out.println("\nInvalid Choice.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\n Invalid input");
                input.next();
}
        }
		getAccountType(acc);
	}
	public void createAccount() throws IOException {
		int cst_no = 0;
		boolean end = false;
	
		while (!end) {
			try {
				System.out.println("\nEnter your customer number (or enter 0 to exit): ");
				cst_no = input.nextInt();
	
				if (cst_no == 0) {
					end = true;
				} else if (data.containsKey(cst_no)) {
					System.out.println("\nThis customer number is already registered.");
				} else {
					System.out.println("\nEnter PIN to be registered: ");
					int pin = input.nextInt();
					data.put(cst_no, new Account(cst_no, pin));
					System.out.println("\nYour new account has been successfully registered!");
					end = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	
		if (cst_no != 0) {
			System.out.println("\nRedirecting to login.............");
			getLogin();
		}
	}
	
public void mainMenu() throws IOException{
	boolean loop = false;
	while(!loop){
		try {
			System.out.println("\n Type 1 - Login");
			System.out.println(" Type 2 - Create Account");
			System.out.print("\nChoice: ");
			int choice = input.nextInt();
			switch(choice){				
				case 1:
				getLogin();
				loop = true;
				break;
			case 2:
				createAccount();
				loop = true;
				break;
			default:
				System.out.println("\nInvalid Choice.");
			}
		} catch (InputMismatchException e) {
			System.out.println("\nInvalid Choice.");
			input.next();
		}
	}
	System.out.println("\nThank You for using this ATM.\n");
	input.close();
	System.exit(0);

}
}