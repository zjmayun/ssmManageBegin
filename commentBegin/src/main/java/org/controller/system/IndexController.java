package org.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@RequestMapping
    public String index() {
    	return "/system/index";
    }	
}
