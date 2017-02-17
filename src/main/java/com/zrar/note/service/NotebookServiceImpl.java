package com.zrar.note.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrar.note.dao.NotebookDao;
import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.Notebook;
import com.zrar.note.exception.NotFoundUserException;
import com.zrar.note.util.Constant;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {

	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	
	public List<Map<String, Object>> listNotebook(String userId) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId ����Ϊ��");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist == 0){
			throw new NotFoundUserException("�û�������");
		}
		List<Map<String, Object>> list = notebookDao.findNotebooksByUserId(userId);
		
		return list;
	}
	
	public Notebook addNotebook(String userId, String name) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId ����Ϊ��");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist == 0){
			throw new NotFoundUserException("�û�������");
		}
		if(name == null || name.trim().isEmpty()){
			name = "�½��ʼǱ�";
		}
		Notebook notebook = new Notebook();
		notebook.setId(UUID.randomUUID().toString());
		notebook.setUserId(userId);
		notebook.setTypeId(Constant.NOTEBOOK_TYPE_ID_1);
		notebook.setName(name);
		notebook.setDesc("");
		notebook.setCreateTime(new Date());
		int i = notebookDao.insertNotebook(notebook);
		if(i != 1){
			throw new RuntimeException("�½��ʼǱ�ʧ��");
		}
		return notebook;
	}
}
