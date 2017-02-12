package com.zrar.note.dao;

import com.zrar.note.entity.User;

public interface UserDao {
	void addUser(User user);
	
	User findUserByName(String name);
	
	int findUserByUserId(String userId);
}
