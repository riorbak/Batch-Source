package com.revature.JDBCBank.old;

/* Bank admins should be able to view and edit all accounts
 *  -This includes:
 * 		-Approving/denying accounts
 * 		-Withdrawing, depositing, transferring from all accounts
 * 		-Canceling accounts
 */
public class Admin extends Employee 
{
	private static final long serialVersionUID = 1L;

	Admin()
	{
		super();
	}
	public Admin(String id, String pw)
	{
		this();
		this.setEmployID(id);
		this.setEmployPass(pw);
	}
	
	//cancel user account
	public void cancelAccount(Customer cust)
	{
		double cash = 0;
		
		Account acc = cust.selectAccount();
		
		cash = acc.closeAccount();
		
		System.out.println("$" + cash + " returned to " + cust.getName() + ".");
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Admin [toString()=" + super.toString() + "]";
	}

	

	
}
