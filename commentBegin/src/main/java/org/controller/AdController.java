package org.controller;

import java.util.List;

import org.constant.PageCodeEnum;
import org.dto.AdDto;
import org.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/ad")
public class AdController {
	
	 @Autowired
	 private AdService adService;
	
	 @RequestMapping(method=RequestMethod.GET)
     public String adInit(AdDto adDto,Model model) {
    	 List<AdDto> list=adService.selectByPage(adDto);
    	 model.addAttribute("list", list);
    	 model.addAttribute("searchParam",adDto);
    	 return "/content/adList";
     }
	 
	 @RequestMapping(value="/addInit",method=RequestMethod.GET)
	 public String addInit() {
		 return "/content/adAdd";
	 }
	 
	 @RequestMapping(value="/adModify/{id}",method=RequestMethod.GET)
	 public String addModifyInit(@PathVariable(value="id")int id,Model model) {
		 AdDto adDto=adService.selectById(id);
		 model.addAttribute("modifyObj", adDto);
		 return "/content/adModify";
	 }
	 
	 @RequestMapping(value="/add",method=RequestMethod.POST)
	 public String add(AdDto adDto,RedirectAttributes model) {
		 if(adService.add(adDto)) {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_SUCCESS);
		 }else {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_FAIL);
		 }
		 return "redirect:/ad";
	 }
	 
	 @RequestMapping(value="/delete",method=RequestMethod.DELETE)
	 public String add(@RequestParam(value="id")int id,Model model) {
		 if(adService.delete(id)) {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_DELETE_SUCCESS);
		 }else {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_DELETE_FAIL);
		 }
		 return "redirect:/ad";
	 }
	 
	 
	 @RequestMapping(value="/update",method=RequestMethod.PUT)
	 public String modify(AdDto adDto,Model model) {
		 if(adService.update(adDto)) {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_MODIFY_SUCCESS);
		 }else {
			 model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.AD_MODIFY_FAIL);
		 }
		 return "redirect:/ad";
	 }
	 
	 
	 
	 
}
