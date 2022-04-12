package com.GroupG.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.GroupG.beans.Login;
import com.GroupG.beans.User;

public class LoginDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public int save(Login login) {
		String sql = "insert into login(username,password) values('" + login.getUsername() + "','" + login.getPassword() + "')";
		return template.update(sql);
	}

	public int saveuser(User login) {
		String sql = "insert into user(username,password,email,fullname,address) values('" + login.getUsername() + "','" + login.getPassword() +"','" + login.getEmail() +"','" + login.getFullname() +"','" + login.getAddress() + "')";
		return template.update(sql);
	}

	public boolean loginchecking(User user) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root123");
		String sql = "select * from user where username = '" + user.getUsername() + "' and password = '" + user.getPassword()
				+ "'";

		PreparedStatement ps = connection.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		boolean status = rs.next();
		return status;
	}
	


	public List<Login> getUsers() {
		return template.query("select * from login", new RowMapper<Login>() {
			public Login mapRow(ResultSet rs, int row) throws SQLException {
				Login e = new Login();
				e.setUsername(rs.getString(2));
				e.setPassword(rs.getString(3));

				return e;
			}
		});
	}
	
	public List<Login> getLoginDetails(Login login) {
		String sql="select * from account where username = '"+login.getUsername()+"'";
		List<Login> accList= template.query(sql,new BeanPropertyRowMapper<Login>(Login.class));
		return accList;
			}
	
}