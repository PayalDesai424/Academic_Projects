package com.GroupG.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.GroupG.beans.Accounts;
import com.GroupG.beans.Login;
import com.GroupG.beans.User;
import com.GroupG.dao.AccountsDao;
import com.GroupG.dao.LoginDao;

/* Author Group G
 * LoginController class
 * */
@Controller
public class LoginController {
	@Autowired
	LoginDao dao;
	
	@Autowired
	AccountsDao Adao;
	
	@RequestMapping("/register")
	public String adduserform(Model m) {
		m.addAttribute("command", new Login());
		return "register";
	}

	@RequestMapping("/login")
	public String loginuserform(Model m) {
		m.addAttribute("command", new Login());
		return "login";
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String adduser(@Valid @ModelAttribute("register") Login log, User login, Errors errors, Model m) {
		//System.out.println(login.getUsername().equals(null) || login.getPassword().equals(null));
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			dao.saveuser(login);
			List<Accounts> list = Adao.getAccDetails(log);
			if(!list.isEmpty())
			{
			m.addAttribute("accountno", list.get(0).getAccountno());
			m.addAttribute("accountbalance", list.get(0).getAccountbalance());
			//System.out.println("cnt "+login.getLoginid()+"  "+list.get(0).getAccount_no()+"  "+list.get(0).getBalance());
			m.addAttribute("uname", login.getUsername());
				return "redirect:/home";
			}
			else
			{
				m.addAttribute("uname", login.getUsername());
				return "redirect:/userhome";
			}
			}
			
			
}
 	@RequestMapping(value = "/LoginValidation", method = RequestMethod.POST)
	public String LoginValidation(@Valid @ModelAttribute("login") Login login, User user,Accounts account,Errors errors, Model m) throws SQLException {
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		}
		else if (login.getUsername().equals("admin") && login.getPassword().equals("admin123")) {
			return "redirect:/adminpage";
		} 
		else if(dao.loginchecking(user))
		{
			dao.save(login);
			List<Accounts> list = Adao.getAccDetails(login);
			if(!list.isEmpty())
			{
			m.addAttribute("accountno", list.get(0).getAccountno());
			m.addAttribute("accountbalance", list.get(0).getAccountbalance());
			//System.out.println("cnt "+login.getLoginid()+"  "+list.get(0).getAccount_no()+"  "+list.get(0).getBalance());
			m.addAttribute("uname", login.getUsername());
				return "redirect:/home";
			}
			else
			{
				m.addAttribute("uname", login.getUsername());
				return "redirect:/userhome";
			}
			}
			
		else
		{		
			return "redirect:/ErrorForm";
		}
			
}
 
	@RequestMapping("/home")
	public String home(Model m) {
		m.addAttribute("command", new Login());
		return "home";
	}
	
	@RequestMapping("/userhome")
	public String userhome(Model m) {
		m.addAttribute("command", new Login());
		return "userhome";
	}


	@RequestMapping("/ErrorForm")
	public String ErrorForm(Model m) {
		m.addAttribute("command", new Login());
		return "ErrorForm";
	}

	
	@RequestMapping("/adminpage")
	public String admin(Model m) {
		m.addAttribute("command", new Login());
		return "adminpage";
	}
	
	@RequestMapping("/viewuser")
	public String getAllUser(Model m)
	{
		List<Accounts> list=Adao.getAllUser();
		m.addAttribute("list",list);
		return "viewuser";
	}


	
	
	
}
