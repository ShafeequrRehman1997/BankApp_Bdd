package org.cap.service;

import javax.security.auth.login.AccountLockedException;

import org.cap.dao.AccountDaoImpl;
import org.cap.dao.IAccountDao;
import org.cap.exception.AccountNotFound;
import org.cap.exception.InsufficientBalance;
import org.cap.exception.InvalidBalance;
import org.cap.exception.InvalidCustomer;
import org.cap.model.Account;
import org.cap.model.Customer;
import org.cap.util.AccountUtil;

public class AccountServiceImpl implements IAccountService {
private IAccountDao accountDao=new AccountDaoImpl();
public AccountServiceImpl(IAccountDao accountDao2) {
	accountDao=accountDao2;
}
public AccountServiceImpl() {
	// TODO Auto-generated constructor stub
}
	@Override
	public Account createAccount(Customer customer, double amount) throws InvalidCustomer, InvalidBalance {
		if(customer!=null) {
			if(amount>=500) {
				Account account=new Account();
				account.setCustomer(customer);
				account.setOpeningBalance(amount);
				account.setAccountNo(AccountUtil.generateAccountNo());
				
				//
				boolean flag=accountDao.addAccount(account);
				if(flag)
					return account;
				else
				return null;
			}
			else {
				throw new InvalidBalance("Sorry! invalid balance");
		}
		}
		else {
			throw new InvalidCustomer("Sorry! customer refers null");
		}
		//return null;
	}
	@Override
	public Account findAccountById(int accountNo) {
		return accountDao.findAccountById(accountNo);
	}
	@Override
	public Account withdraw(int accountNo, double amount_withdraw) throws AccountNotFound, InsufficientBalance{
		Account account=accountDao.findAccountById(accountNo);
		if(account==null)
			throw new AccountNotFound("sorry!no such account found");
		if(amount_withdraw>account.getOpeningBalance())
			throw new InsufficientBalance("Insufficient Balance Exception");
		account.setOpeningBalance(account.getOpeningBalance() - amount_withdraw);
		
		updateBalance(accountNo, account.getOpeningBalance());
		return account;
	}
	@Override
	public Account updateBalance(int accountNo, double amount) {
		// TODO Auto-generated method stub
		return accountDao.updateBalance(accountNo, amount);
	}

	@Override
	public Account deposit(int accountNo, double amount_deposit) throws AccountNotFound {
		Account account=accountDao.findAccountById(accountNo);
		if(account==null)
			throw new AccountNotFound("sorry!no such account found");
		else
		account.setOpeningBalance(account.getOpeningBalance() + amount_deposit);
		
		updateBalance(accountNo, account.getOpeningBalance());
		return account;
	}
	
}

