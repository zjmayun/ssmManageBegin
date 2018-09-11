package org.controller.content;

import java.util.List;

import org.bean.Business;
import org.bean.Dic;
import org.constant.DicTypeConst;
import org.constant.PageCodeEnum;
import org.dto.BusinessDto;
import org.service.BusinessService;
import org.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/businesses")
public class BusinessController {
	
    @Autowired
    private BusinessService businessService;
    
    @Autowired
    private DicService dicService;
    
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(BusinessDto bto,Model model) {
       List<BusinessDto> list=businessService.selectByPage(bto);
       model.addAttribute("list", list);
       model.addAttribute("searchParam",bto);
       return "/content/businessList";	
    }
    
    @RequestMapping(value="/addPage",method=RequestMethod.GET)
    public String add(Model model) {
    	model.addAttribute("cityList",dicService.selectByType(DicTypeConst.CITY));
    	model.addAttribute("categoryList", dicService.selectByType(DicTypeConst.CATEGORY));
    	return "/content/businessAdd";
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String modify(@PathVariable(value="id")int id,Model model) {
    	model.addAttribute("modifyObj",businessService.selectById(id));
    	model.addAttribute("cityList",dicService.selectByType(DicTypeConst.CITY));
    	model.addAttribute("categoryList", dicService.selectByType(DicTypeConst.CATEGORY));
    	return "/content/businessModify";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public String add(BusinessDto bto,Model model) {
    	if(businessService.add(bto)) {
    		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.BUSINESS_ADD_SUCCESS);
    	}else {
    		model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.BUSINESS_ADD_FAIL);
    	}
    	return "redirect:/businesses";
    }
	 
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public String delete(BusinessDto businessDto,Model model) {
    	if(businessService.delete(businessDto.getId())) {
    		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.BUSINESS_DELETE_SUCCESS);
    	}else {
    		model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.BUSINESS_DELETE_FAIL);
    	}
    	return "redirect:/businesses";
    }
    
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public String modify(BusinessDto bto,Model model) {
    	if(businessService.update(bto)) {
    		model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.BUSINESS_MODIFY_SUCCESS);
    	}else {
    		model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.BUSINESS_MODIFY_FAIL);
    	}
    	return "redirect:/businesses";
    }
    
	
}
