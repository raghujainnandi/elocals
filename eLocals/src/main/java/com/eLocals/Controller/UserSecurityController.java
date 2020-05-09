package com.eLocals.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eLocals.Entity.UserAccount;
import com.eLocals.Repository.AccountRepository;



@Controller

public class UserSecurityController {
	
	private static final  Logger LOGGER=LoggerFactory.getLogger(UserSecurityController.class);

	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Autowired
	AccountRepository accountRepository;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home()
	{
		return "index";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		LOGGER.info("Inside signUp()");
		UserAccount useraccount = new UserAccount();
		model.addAttribute("userAccount", useraccount);
		return "register";
	}

	@PostMapping("savesignup")
	public String saveSignUp(Model model, UserAccount user) {
		LOGGER.info("Inside saveSignUp()");
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		accountRepository.save(user);
		return "login";
	}

	@GetMapping("/login")
	public String DisplayLoginPage() {
		LOGGER.info("DisplayLoginPage()");
		return "login";
	}

	@PostMapping(value = "/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {

		LOGGER.info(" Inside validate()");
		UserAccount useraccount = accountRepository.findByEmail(email);
		String db_pass = useraccount.getPassword();
		if (bCryptEncoder.matches(password, db_pass)) {
			return "redirect:/welcome";
		} else {
			model.addAttribute("msg", "Invalid Credentials ..Please Enter Valid Credentials");
		}
		return "redirect:/login";

	}
	
	@GetMapping(value="/welcome")
	public String welcome(Model model)
	{
		return "welcome";
	}
}
