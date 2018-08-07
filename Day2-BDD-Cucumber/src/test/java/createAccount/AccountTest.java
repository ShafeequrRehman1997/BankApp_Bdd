package createAccount;

import static org.junit.Assert.*;

import org.cap.exception.InvalidBalance;
import org.cap.exception.InvalidCustomer;
import org.cap.model.Address;
import org.cap.model.Customer;
import org.cap.service.AccountServiceImpl;
import org.cap.service.IAccountService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AccountTest {
private Customer customer;
private double openingBalance;
private IAccountService accountService;

@Before
public void setUp() {
	customer=new Customer();

	customer.setFirstName("shafeeq");
	customer.setLastName("mohd");
	Address address=new Address();
	address.setDoorNo(12);
	address.setCity("Hyderabad");
	customer.setAddress(address);
	accountService=new AccountServiceImpl();
}
@Rule
public ExpectedException exception=ExpectedException.none();

@Test
public void test_customer_with_null() throws InvalidCustomer, InvalidBalance {
	customer=null;
	exception.expect(InvalidCustomer.class);
	exception.expectMessage("Sorry");
	accountService.createAccount(customer, 1000);
	
}

@Test
public void test_invalid_balance() throws InvalidCustomer, InvalidBalance {
	double amount=100;
	exception.expect(InvalidBalance.class);
	exception.expectMessage("Sorry");
	accountService.createAccount(customer, amount);
	
}

}
