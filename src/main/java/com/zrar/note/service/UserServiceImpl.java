package com.zrar.note.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.PasswordException;
import com.zrar.note.util.Util;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	public User login(String name, String password) throws NameException, PasswordException {
		if(name == null || name.trim().isEmpty()){
			throw new NameException("�û�������Ϊ��");
		}
		if(password == null || password.trim().isEmpty()){
			throw new NameException("���벻��Ϊ��");
		}
		User user = userDao.findUserByName(name);
		if(user == null){
			throw new NameException("�û�������");
		}
		if(user.getPassword().equals(Util.salMD5(password))){
			return user;
		}
		throw new PasswordException("�������");
	}
	public User regist(String name,String nick,String password,String confirmPassword) throws NameException,PasswordException{
		if(name == null || name.trim().isEmpty()){
			throw new NameException("�û�������Ϊ��");
		}
		if(password == null || password.trim().isEmpty() ){
			throw new PasswordException("���벻��Ϊ��");
		}
		if(!password.equals(confirmPassword)){
			throw new PasswordException("�������벻һ��");
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
			throw new NameException("�û�������Ϊ��");
		}
		User user = userDao.findUserByName(name);
		if(user == null){
			return true;
		}
		return false;
	}
}
