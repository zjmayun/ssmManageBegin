package org.controller.system;

import javax.servlet.http.HttpSession;

import org.bean.User;
import org.constant.PageCodeEnum;
import org.constant.SessionKeyConst;
import org.dto.GroupDto;
import org.dto.UserDto;
import org.service.GroupService;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/login")
public class LoginController {
	@Autowired
    private UserService userService; 
	@Autowired
	private HttpSession session;
	@Autowired
	private GroupService groupService;
	
	@RequestMapping
	public String index() {
		return "/system/login";
	}
	
	@RequestMapping(value="/sessionTimeOut")
	public String sessionTimeOut(Model model) {
		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.SESSION_TIMEOUT);
		return "/system/error";
	}
	
	@RequestMapping(value="/noAuth")
	public String noAuth(Model model) {
		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.NO_AUTH);
		session.invalidate();
		return "/system/error";
	}
	
	@RequestMapping(value="/validate")
	public String validate(UserDto userDto,RedirectAttributes att,Model model) {
		if(userService.validate(userDto)) {
		    session.setAttribute("object", userDto);
			session.setAttribute(SessionKeyConst.USER_INFO, userDto);
			GroupDto groupDto=groupService.selectByGroupId(userDto.getGroupId());
			session.setAttribute(SessionKeyConst.MENU_IFNO, groupDto.getMenuListDto());
			session.setAttribute(SessionKeyConst.ACTION_INFO, groupDto.getActionListDto());
		    return "redirect:/index";
		}
	    att.addFlashAttribute(PageCodeEnum.KEY, PageCodeEnum.LOGIN_FAIL);
		return "redirect:/login";
	}
	
	
	
	
}
