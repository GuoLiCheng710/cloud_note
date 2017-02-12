package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrar.note.dao.NotebookDao;
import com.zrar.note.dao.UserDao;
import com.zrar.note.exception.NotFoundUserException;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	
	public List<Map<String, Object>> listNotebook(String userId) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId 不能为空");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist == 0){
			throw new NotFoundUserException("用户不存在");
		}
		List<Map<String, Object>> list = notebookDao.findNotebooksByUserId(userId);
		
		return list;
	}
	
	

}
