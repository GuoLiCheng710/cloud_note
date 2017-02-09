package com.zrar.note.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.PasswordException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	public User login(String name, String password) throws NameException, PasswordException {
		if(name == null || name.trim().isEmpty()){
			throw new NameException("登录名不能为空");
		}
		if(password == null || password.trim().isEmpty()){
			throw new NameException("密码不能为空");
		}
		User user = userDao.findUserByName(name);
		if(user == null){
			throw new NameException("用户不存在");
		}
		if(password.equals(user.getPassword())){
			return user;
		}
		throw new PasswordException("密码错误");
	}

}
