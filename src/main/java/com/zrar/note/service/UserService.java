package com.zrar.note.service;

import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.PasswordException;

public interface UserService {
	User login (String name,String password) throws NameException,PasswordException;
	User regist (String name,String nick,String password,String comfirmPassword) throws NameException,PasswordException;
	boolean isUserExistByName(String name) throws NameException;
}
