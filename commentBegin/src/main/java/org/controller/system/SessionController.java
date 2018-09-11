package org.controller.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.constant.SessionKeyConst;
import org.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/session")
public class SessionController {
    
	@Autowired
	private HttpSession session;
	
	@SuppressWarnings(value="unchecked")
	@RequestMapping(value="/menus",method=RequestMethod.GET)
	@ResponseBody
	public List<MenuDto> getMenus(){
		List<MenuDto> list=(List<MenuDto>)session.getAttribute(SessionKeyConst.MENU_IFNO);
		return list;
	}
	
	@RequestMapping(value="/sigOut",method=RequestMethod.DELETE)
	public String sigOut() {
		session.invalidate();
		return "/system/login";
	}
}
