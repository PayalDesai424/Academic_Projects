package com.GroupG.controller;

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
import com.GroupG.dao.AccountsDao;


/* Author: Group G
 * */

@Controller
public class AccountsController {
	@Autowired
	AccountsDao Adao;
	
	@RequestMapping("/deposit")
	public String AccountForm(Model m) {
		m.addAttribute("command", new Accounts());
		return "deposit";
	}

	@RequestMapping("/withdrawmoney")
	public String WithdrawMoney(Model m) {
		m.addAttribute("command", new Accounts());
		return "withdrawmoney";
	}
	
	@RequestMapping("/addmoney")
	public String Addmoneyform(Model m) {
		m.addAttribute("command", new Accounts());
		return "addmoney";
	}
	
	@RequestMapping("/bill")
	public String PayBillsform(Model m) {
		m.addAttribute("command", new Accounts());
		return "bill";
	}
	@RequestMapping("/Final")
	public String Final(Model m) {
		m.addAttribute("command", new Accounts());
		return "Final";
	}

	@RequestMapping("/transfermoney")
	public String TransferForm(Model m) {
		m.addAttribute("command", new Accounts());
		return "transfermoney";
	}
	
	@RequestMapping(value = "/addaccount", method = RequestMethod.POST)
	public String adduser(@Valid @ModelAttribute("deposit") Login login,Accounts account, Errors errors, Model m) {
		System.out.println(account.getAccountbalance()<=(0));
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			Adao.saveaccount(account);
			List<Accounts> list = Adao.getAccDetails(login);
			if(!list.isEmpty())
			{
			m.addAttribute("accountno", list.get(0).getAccountno());
			m.addAttribute("accountbalance", list.get(0).getAccountbalance());
			
			m.addAttribute("uname", login.getUsername());
				return "redirect:/home";
			}
			else
			{
				m.addAttribute("uname", login.getUsername());
				return "redirect:/userhome";
			}
		}}
			
	
	
	@RequestMapping(value = "/updateaccount", method = RequestMethod.POST)
	public String AddBalance(@Valid @ModelAttribute("withdrawmoney")Login login, Accounts account, Errors errors, Model m) {
		System.out.println(account.getAccountbalance()<=(0));
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			Adao.updateaccount(account.getAccountno() , account.getWithdrawmoney());
			List<Accounts> list = Adao.getAccDetails(login);
			if(!list.isEmpty())
			{
			m.addAttribute("accountno", list.get(0).getAccountno());
			m.addAttribute("accountbalance", list.get(0).getAccountbalance());
			
			m.addAttribute("uname", login.getUsername());
				return "redirect:/home";
			}
			else
			{
				m.addAttribute("uname", login.getUsername());
				return "redirect:/userhome";
			}
		}}
	
	@RequestMapping(value = "/addmoney", method = RequestMethod.POST)
	public String updateuser(@Valid @ModelAttribute("addmoney")Login login, Accounts account, Errors errors,Model m) {
		System.out.println(account.getAccountbalance()<=(0));
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			Adao.addmoney(account.getAccountno() , account.getAddmoney());
			List<Accounts> list = Adao.getAccDetails(login);
			if(!list.isEmpty())
			{
			m.addAttribute("accountno", list.get(0).getAccountno());
			m.addAttribute("accountbalance", list.get(0).getAccountbalance());
			
			m.addAttribute("uname", login.getUsername());
				return "redirect:/home";
			}
			else
			{
				m.addAttribute("uname", login.getUsername());
				return "redirect:/userhome";
			}
		}}
	
	@RequestMapping(value = "/paybill", method = RequestMethod.POST)
	public String bill(@Valid @ModelAttribute("bill")Login login, Accounts account, Errors errors,Model m) {
		
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			Adao.payingbill(account.getAccountno() , account.getAmmount());
			Adao.savebill(account);
			
				return "redirect:/Final";
			}
		}
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public String transfermonry(@Valid @ModelAttribute("transfermoney") Login login,Accounts account, Errors errors,Model m) {
		
		if (errors.hasErrors()) {
			return "redirect:/ErrorForm";
		} else {
			Adao.transfermoney(account.getSecondaccountno() , account.getAmmount());
			Adao.transfermoney1(account.getAccountno() , account.getAmmount());
				return "redirect:/Final";
			}
		}
		}
	
	

