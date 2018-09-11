package org.controller.system;

import java.util.List;

import org.constant.PageCodeEnum;
import org.dto.PageCodeDto;
import org.dto.UserDto;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UsersController {
     
	@Autowired
    private UserService userService;   
 	
	/*
	 * 返回user列表
	 */
	@RequestMapping(method=RequestMethod.GET)
	public List<UserDto> listUser(){
	    return userService.selectListUser();
	}
	
	/*
	 * 查询单个user
	 */
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public UserDto selectOne(UserDto userDto) {
		return userService.selectById(userDto.getId());
	}
	
	/*
	 * 用户添加
	 */
	@RequestMapping(method=RequestMethod.POST)
	public PageCodeDto addOne(UserDto userDto) {
		PageCodeDto pageDto;
		if(userService.add(userDto)) {
			return pageDto=new PageCodeDto(PageCodeEnum.ADD_SUCCESS);
		}else {
			return pageDto=new PageCodeDto(PageCodeEnum.USER_EXISTS);
		}
	}
	
	/*
	 * 用户删除
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public PageCodeDto delete(@PathVariable(value="id")int id) {
		PageCodeDto pageDto;
		if(userService.delete(id)){
			return pageDto=new PageCodeDto(PageCodeEnum.REMOVE_SUCCESS);
		}else {
			return pageDto=new PageCodeDto(PageCodeEnum.USER_DELETE_FAIL);
		}
	}
	
	/*
	 * 用户修改
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public PageCodeDto mofigy(UserDto userDto) {
		PageCodeDto pageDto;
		if(userService.modify(userDto)) {
			return pageDto=new PageCodeDto(PageCodeEnum.MODIFY_SUCCESS);
		}else {
			return pageDto=new PageCodeDto(PageCodeEnum.USER_MOFIGY_FAIL);
		}
	}
	 
	/*
	 * 用户修改界面
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public UserDto mofigyInit(@PathVariable(value="id")int id) {
		return userService.selectById(id);
	}
	
}
