package com.GroupG.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.GroupG.beans.Accounts;
import com.GroupG.beans.Login;

public class AccountsDao {
	
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public float saveaccount(Accounts account) {
		String sql = "insert into account(accountno,accountbalance,username,accounttype) values('" + account.getAccountno() + "','" + account.getAccountbalance() + "','" + account.getUsername() + "','" + account.getAccounttype() + "')";
		return template.update(sql);	
	}
			
		
	public int updateaccount(int accountno, String amount) {
		int count = 0;
		try {

		Float wdmoney = Float.parseFloat(amount);
		String sql = "update account set accountbalance = (accountbalance- " + wdmoney + ") where accountno= " + accountno + " ";
		count = template.update(sql);

		} catch (Exception e) {
		e.printStackTrace();
		}
		return count;
		}
	
	public int addmoney(int accountno, String amount) {
		int count = 0;
		try {

		Float addmoney = Float.parseFloat(amount);
		String sql = "update account set accountbalance = (accountbalance + " + addmoney + ") where accountno= " + accountno + " ";
		count = template.update(sql);

		} catch (Exception e) {
		e.printStackTrace();
		}
		return count;
	
	}
	
	public float savebill(Accounts account) {
		String sql = "insert into bill(ammount,billingcompany,accountno) values('" + account.getAmmount() + "','" + account.getBillingcompany() + "','" + account.getAccountno() + "')";
		return template.update(sql);
	}
	
	public int payingbill(int accountno, String ammount) {
		int count = 0;
		try {

		Float bill = Float.parseFloat(ammount);
		String sql = "update account set accountbalance = (accountbalance- " + bill + ") where accountno= " + accountno + " ";
		count = template.update(sql);

		} catch (Exception e) {
		e.printStackTrace();
		}
		return count;
		}
	
	public int transfermoney(String secondaccountno, String ammount) {
		int count = 0;
		try {

		Float money = Float.parseFloat(ammount);
		String sql = "update account set accountbalance = (accountbalance - " + money + ") where accountno= " + secondaccountno + " ";
		count = template.update(sql);

		} catch (Exception e) {
		e.printStackTrace();
		}
		return count;
		}
	
	public int transfermoney1(int accountno, String ammount) {
		int count = 0;
		try {

		Float money = Float.parseFloat(ammount);
		String sql = "update account set accountbalance = (accountbalance + " + money + ") where accountno= " + accountno + " ";
		count = template.update(sql);

		} catch (Exception e) {
		e.printStackTrace();
		}
		return count;
		}

	public List<Accounts> getAccDetails(Login login) {
		
		String sql="select * from account where username = '"+login.getUsername()+"'";
		List<Accounts> accList= template.query(sql,new BeanPropertyRowMapper<Accounts>(Accounts.class));
		return accList;
			}
	
	public List<Accounts> getAllUser() {
		
		String sql="select * from account";
		List<Accounts> accList= template.query(sql,new BeanPropertyRowMapper<Accounts>(Accounts.class));
		return accList;
			}
	

	
}
