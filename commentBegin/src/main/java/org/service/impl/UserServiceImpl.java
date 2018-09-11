package org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bean.User;
import org.dao.UserDao;
import org.dto.UserDto;
import org.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.util.CommonUtil;
import org.util.MD5Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public boolean validate(UserDto userDto) {
		User user=new User();
		BeanUtils.copyProperties(userDto, user);
        User userO=userDao.selectByNameAndPass(user);
        if(userO!=null) {
        userDto.setGroupId(userO.getGroupId());
        }
        return CommonUtil.isNull(userO);
	}

	//根据zTree的规则，设置pId=0
	public List<UserDto> selectListUser() {
		List<UserDto> list=new ArrayList<UserDto>();
		List<User> listUser=userDao.selectListUser();
		for(User user:listUser) {
			UserDto userDto=new UserDto();
			BeanUtils.copyProperties(user, userDto);
			userDto.setpId(0);
			list.add(userDto);
		}
		return list;
	}

	@Override
	public boolean add(UserDto userDto) {
		User user=new User();
		BeanUtils.copyProperties(userDto, user);
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		int i=userDao.insert(user);
		return i==1;
	}

	@Override
	public boolean delete(int id) {
		int i=userDao.delete(id);
		return i==1;
	}

	@Override
	public boolean modify(UserDto userDto) {
		User user=new User();
		BeanUtils.copyProperties(userDto, user);
		int i=userDao.modify(user);
		return i==1;
	}

	@Override
	public UserDto selectById(int id) {
        User user=userDao.selectById(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setpId(0);
		return userDto;
	}
	


	

}
