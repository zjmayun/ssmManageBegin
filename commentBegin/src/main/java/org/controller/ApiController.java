package org.controller;

import java.util.List;

import org.bean.Business;
import org.bean.Member;
import org.bean.Page;
import org.constant.ApiCodeEnum;
import org.dto.AdDto;
import org.dto.ApiCodeDto;
import org.dto.BusinessDto;
import org.dto.BusinessListDto;
import org.dto.OrderForBuyDto;
import org.dto.OrdersDto;
import org.service.AdService;
import org.service.BusinessService;
import org.service.MemberService;
import org.service.OrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.util.CommonUtil;
import org.util.MD5Util;
@RestController
@RequestMapping(value="/api")
public class ApiController {
    
	@Value("${ad.number}")
	private int adNumber;
	
	@Value("${businessHome.number}")
	private int businessHomeNumber;
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="/homead",method=RequestMethod.GET)
	public List<AdDto> homead(){
		AdDto adDto=new AdDto();
		adDto.getPage().setPageNumber(adNumber);
		List<AdDto> list=adService.selectByPage(adDto);
		return list;
	}
	
	@RequestMapping(value="/homelist/{city}/{page.currentPage}",method=RequestMethod.GET)
	public BusinessListDto homeList(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectByPageForApi(businessDto);
	}
	
	@RequestMapping(value="/search/{page.currentPage}/{city}/{category}/{keyword}",method=RequestMethod.GET)
	public BusinessListDto Search(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectByPageForApi(businessDto);
	}
	
	@RequestMapping(value="/search/{page.currentPage}/{city}/{category}",method=RequestMethod.GET)
	public BusinessListDto SearchD(BusinessDto businessDto){
		businessDto.getPage().setPageNumber(businessHomeNumber);
		return businessService.selectByPageForApi(businessDto);
	}
	
	@RequestMapping(value="/detail/info/{id}",method=RequestMethod.GET)
	public BusinessDto searchById(@PathVariable(value="id")int id){
		return businessService.selectById(id);
	}
	
	
	
	@RequestMapping(value="/sms",method=RequestMethod.POST)
	public ApiCodeDto sms(@RequestParam(value="username")String phone){
		ApiCodeDto apiCodeDto;
		if (memberService.memberExistsByPhone(phone)) {
			String code = String.valueOf(CommonUtil.random(6));
			if (memberService.saveCode(phone, code)) {
				memberService.sendCode(code);
				apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
				apiCodeDto.setCode(code);
				return apiCodeDto;
			} else {
				return apiCodeDto = new ApiCodeDto(ApiCodeEnum.FAIL);
			}
		} else {
			    return apiCodeDto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
		}
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ApiCodeDto login(@RequestParam(value="username")String phone,
			@RequestParam(value="code")String code){
		ApiCodeDto dto;
		if(memberService.memberExistsByPhone(phone)) {
			String co=memberService.getCode(phone);
			String saveCode=MD5Util.getMD5(co);
			if(saveCode.equals(code)) {
				String token=CommonUtil.getUUID();
				memberService.saveToken(phone, token);
				dto=new ApiCodeDto(ApiCodeEnum.SUCCESS);
				dto.setToken(token);
			}else {
				return dto=new ApiCodeDto(ApiCodeEnum.SMS_ERROR);
			}
		}else {
			return dto=new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
		}
		return dto;
	}
	

	@RequestMapping(value="/order",method=RequestMethod.POST)
	public ApiCodeDto order(OrderForBuyDto orderForBuyDto){
		ApiCodeDto apiDto;
		String token=orderForBuyDto.getToken();
		//判断登录成功的token和已经保存在map中的token是否相等
		if(token.equals(memberService.getToken(orderForBuyDto.getUsername()))) {
			 //前端传过来的属性值为username，其实就是ipone
			 Member member=memberService.selectByPhone(orderForBuyDto.getUsername());
			 OrdersDto ordersDto=new OrdersDto();
			 ordersDto.setBusinessId(orderForBuyDto.getId());
			 ordersDto.setNum(orderForBuyDto.getNum());
			 ordersDto.setPrice(orderForBuyDto.getPrice());
			 ordersDto.setMemberId(member.getId());
			 ordersService.insert(ordersDto);
			 
			 //订单插入后，相对应的商户的订单数要进行更新操作
			 Business business=businessService.selectById(orderForBuyDto.getId());
			 business.setNumber(business.getNumber()+1);
			 BusinessDto busDto=new BusinessDto();
			 BeanUtils.copyProperties(business, busDto);
			 businessService.update(busDto);
			 return apiDto=new ApiCodeDto(ApiCodeEnum.SUCCESS);
			 
		}else {
			return apiDto=new ApiCodeDto(ApiCodeEnum.TOKEN_FAIL);
		}
	}
	
	@RequestMapping(value="/orderlist/{username}",method=RequestMethod.GET)
	public List<OrdersDto> orders(@PathVariable(value="username")String username){
		Member member=memberService.selectByPhone(username);
		return ordersService.selectByMemberId(member.getId());
	}
	
	
	
}
