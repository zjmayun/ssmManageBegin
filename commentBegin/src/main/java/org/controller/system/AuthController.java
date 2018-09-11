package org.controller.system;

import org.constant.DicTypeConst;
import org.constant.SessionKeyConst;
import org.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/auth")
public class AuthController {
    
	@Autowired
	private DicService dicService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String auth(Model model) {
		model.addAttribute("httpMethodList", dicService.selectByType(DicTypeConst.HTTPACTION));
		return "/system/auth";
	}
}
