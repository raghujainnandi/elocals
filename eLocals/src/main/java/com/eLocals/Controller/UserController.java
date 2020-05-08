package com.eLocals.Controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eLocals.Entity.User;
import com.eLocals.Repository.UserRepository;

@Controller

public class UserController
{
	private static final  Logger LOGGER=LoggerFactory.getLogger(UserController.class);
	
	@Autowired 
	UserRepository userrepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home()
	{
		return "index";
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.GET)
	public String displaySignUpPage()
	{
		LOGGER.info("Inside DisplaySignUpMethod");
		return "register";
	}

	@RequestMapping(value="/savesignup",method=RequestMethod.POST)
	public String SaveRegisteredUser(@ModelAttribute ("user") User user)
	{
		LOGGER.info("Inside SaveRegisteredUserMethod");
		userrepository.save(user);
		return "login" ;
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String displayLoginPage()
	{
		LOGGER.info("Inside SaveRegisteredUserMethod");
		return "login";
	}
	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(@RequestParam("email") String email,@RequestParam("password")String password,Model model)
	{
		LOGGER.info("Inside validateMethod ..The Email is : "+email);
		User user = userrepository.findByEmail(email);
		if(user.getPassword().equals(password))
		{
			return "welcome";
		}
		else
		{
			model.addAttribute("msg","Invalid Email or Password....Please Enter the Valid Email and Password");
		}
		return "login";
		
	}
	

}
