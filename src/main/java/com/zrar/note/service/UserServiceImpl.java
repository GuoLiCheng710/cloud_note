package com.zrar.note.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.NotFoundUserException;
import com.zrar.note.exception.PasswordException;
import com.zrar.note.util.Util;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	public User login(String name, String password) throws NameException, PasswordException {
		if(name == null || name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password == null || password.trim().isEmpty()){
			throw new NameException("密码不能为空");
		}
		User user = userDao.findUserByName(name);
		if(user == null){
			throw new NameException("用户不存在");
		}
		if(user.getPassword().equals(Util.salMD5(password))){
			return user;
		}
		throw new PasswordException("密码错误");
	}
	public User regist(String name,String nick,String password,String confirmPassword) throws NameException,PasswordException{
		if(name == null || name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		if(password == null || password.trim().isEmpty() ){
			throw new PasswordException("密码不能为空");
		}
		if(!password.equals(confirmPassword)){
			throw new PasswordException("输入密码不一致");
		}
		if(nick == null || nick.trim().isEmpty()){
			nick = name;
		}
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName(name);
		user.setNick(nick);
		user.setPassword(Util.salMD5(password));
		userDao.addUser(user);
		return user;
	}
	public boolean isUserExistByName(String name) throws NameException {
		if(name == null || name.trim().isEmpty()){
			throw new NameException("用户名不能为空");
		}
		User user = userDao.findUserByName(name);
		if(user == null){
			return true;
		}
		return false;
	}
	public boolean checkOldPassword(String userId, String oldPassword) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId不能为空");
		}
		User user = userDao.selectUserById(userId);
		if(user == null){
			throw new NotFoundUserException("不存在该用户");
		}
		if(user.getPassword().equals(Util.salMD5(oldPassword))){
			return true;
		}
		return false;
	}
	public boolean changePassword(String userId, String newPassword, String confirmPassword)
			throws NotFoundUserException,PasswordException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId 不能为空");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist != 1){
			throw new NotFoundUserException("该用户不存在");
		}
		if(newPassword == null || newPassword.trim().isEmpty()){
			throw new PasswordException("密码不能为空");
		}
		if(!newPassword.equals(confirmPassword)){
			throw new PasswordException("两次输入不一致");
		}
		User user = new User();
		user.setId(userId);
		user.setPassword(Util.salMD5(confirmPassword));
		int i = userDao.updatePassword(user);
		if(i == 1){
			return true;
		}
		return false;
	}
	
	
	
}
