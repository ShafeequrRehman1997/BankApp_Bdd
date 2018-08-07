package createAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.InvalidCustomer;
import org.cap.model.Account;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdef {
	Customer customer;
	private double openingBalance;
	private IAccountService accountService;
	private int accountNo;
	@Mock
	private IAccountDao accountDao;
	private double amount_withdraw;
	private double amount_deposit;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		customer=new Customer();
		openingBalance=1000;
		//accountDao=new AccountDaoImpl();
		accountService=new AccountServiceImpl(accountDao);
	}
	@Given("^customer details$")
	public void customer_details() throws Throwable {
	customer.setFirstName("shafeeq");
	customer.setLastName("mohd");
	Address address=new Address();
	address.setDoorNo(12);
	address.setCity("Hyderabad");
	customer.setAddress(address);
	}

	@When("^Valid Customer$")
	public void valid_Customer() throws Throwable {
	assertNotNull(customer);
	}

	@When("^Valid Opening Balance$")
	public void valid_Opening_Balance() throws Throwable {
	  assertTrue(openingBalance>=500);
	}

	@Then("^create new Account$")
	public void create_new_Account() throws Throwable {
		
		//Proxy/Fake declaration
		Account account=new Account();
	  //Account account=accountService.createAccount(customer, openingBalance);
		account.setAccountNo(1);
		account.setOpeningBalance(1000);
		account.setCustomer(customer);
		
		Mockito.when(accountDao.addAccount(account)).thenReturn(true);
		
		Account account2= accountService.createAccount(customer, openingBalance);
		Mockito.verify(accountDao).addAccount(account2);
		
	  assertNotNull(account2);
	  assertEquals(openingBalance, account.getOpeningBalance(),0.0);
	  assertEquals(1, account.getAccountNo());
	}
	@Given("^Customer details$")
	public void customer_details1() throws Throwable {
	   customer=null;
	}

	@When("^Invalid Customer$")
	public void invalid_Customer() throws Throwable {
	    assertNull(customer);
	}

	@Then("^throw 'Invalid Customer' error message$")
	public void throw_Invalid_Customer_error_message() throws Throwable {
	  try {
		  accountService.createAccount(customer, 3000);
	  }
	  catch(InvalidCustomer e) {
		  
	  }
	}

	@Given("^Customer details and Opening Balance$")
	public void customer_details_and_Opening_Balance() throws Throwable {
	    openingBalance=1000;
	}

	@When("^Invalid Opening Balance$")
	public void invalid_Opening_Balance() throws Throwable {
	  assertTrue(openingBalance<100000);
	}

	@Then("^throw 'Invalid Balance' error message$")
	public void throw_Invalid_Balance_error_message() throws Throwable {
	  try {
		  accountService.createAccount(customer, 3000);
	  }
	  catch(Exception ex) {
		  
	  }
	}
	
	@Given("^Account number$")
	public void account_number() throws Throwable {
	    accountNo=1001;
	}

	@When("^valid account number$")
	public void valid_account_number() throws Throwable {
	 assertTrue(accountNo>0);
	}

	@Then("^find account details$")
	public void find_account_details() throws Throwable {
		//actual business logic
		Account account=accountService.findAccountById(accountNo);//
	}
	
	
	@Given("^Account number (\\d+) and amount (\\d+)$")
	public void account_number_and_amount(int accNo, int amount) throws Throwable {
	  this.accountNo=accNo;
	  this.amount_withdraw=amount;
	  this.amount_deposit=amount;
	}

	@When("^Find account and do withdrawal$")
	public void find_account_and_do_withdrawal() throws Throwable {
	   //mokito
		Account account=new Account();
		account.setAccountNo(1001);
		account.setCustomer(customer);
		account.setOpeningBalance(10000);
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account);
		Mockito.when(accountDao.updateBalance(accountNo, 9000)).thenReturn(account);
		Account updatedaccount=accountService.withdraw(accountNo,amount_withdraw);
		Mockito.verify(accountDao).findAccountById(1001);
		Mockito.verify(accountDao).updateBalance(accountNo, 9000);
		assertEquals(9000, updatedaccount.getOpeningBalance(),0.0);
	}

	@Then("^update the account$")
	public void update_the_account() throws Throwable {
		Account account=new Account();
		account.setAccountNo(1001);
		account.setCustomer(customer);
		
		//Mockito.when(accountDao.updateBalance(accountNo, 9000)).thenReturn(account);
		Account updated_account=accountService.updateBalance(accountNo, 9000);
		assertEquals(9000, updated_account.getOpeningBalance(),0.0);
	}
	
	@When("^Find account and deposit$")
	public void find_account_and_deposit() throws Throwable {
		Account account=new Account();
		account.setAccountNo(1001);
		account.setCustomer(customer);
		account.setOpeningBalance(10000); 
		Mockito.when(accountDao.findAccountById(1001)).thenReturn(account);
		
		
		
		Mockito.when(accountDao.updateBalance(accountNo, 11000)).thenReturn(account);
		
		Account updatedaccount=accountService.deposit(accountNo,amount_deposit);
		
		Mockito.verify(accountDao).findAccountById(1001);
		
	/*	Mockito.verify(accountDao).updateBalance(accountNo, 11000);*/
		
		
		assertEquals(11000, updatedaccount.getOpeningBalance(),0.0);
	}

	@Then("^update the account balance$")
	public void update_the_account_balance() throws Throwable {
	  
	}

	
}
