package org.cap.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import org.cap.model.Account;

import java.sql.PreparedStatement;


public class AccountDaoImpl implements IAccountDao {

	@Override
	public boolean addAccount(Account account) {
		String sql="insert into account values(?,?,?)";
		try {
			PreparedStatement pst=(PreparedStatement) getMySQLConnection().prepareStatement(sql);
			pst.setInt(1, account.getAccountNo());
			pst.setDouble(2, account.getOpeningBalance());
			pst.setString(3, account.getCustomer().getFirstName());
			
			int count=pst.executeUpdate();
			
			if(count>0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private Connection getMySQLConnection() {
		Connection connection=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd", "root", "India123");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			return connection;
}

	@Override
	public Account findAccountById(int accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateBalance(int accountNo, double amount) {
		// TODO Auto-generated method stub
		return null;
	}
}
