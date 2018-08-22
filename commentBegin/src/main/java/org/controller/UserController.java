package org.controller;

import org.bean.User;
import org.constant.PageCodeEnum;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class UserController {
	@Autowired
    private UserService userService; 
	
	@RequestMapping(value="/validate",method=RequestMethod.POST)
	public String validate(User user,Model model) {
		if(!userService.validate(user)) {
			model.addAttribute("object", user);
			return "/system/index";
		}
		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
		return "/system/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(User user,Model model) {
		return "/system/login";
	}
	
	
	
	
}
