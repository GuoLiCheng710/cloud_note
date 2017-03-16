package com.zrar.note.service;

import java.util.Date;
import java.util.HashMap;
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
	
	public List<Map<String, Object>> listNotebook(String userId,int pageNum) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId 不能为空");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist == 0){
			throw new NotFoundUserException("用户不存在");
		}
		int size = 6;
		int start = size * pageNum;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("statusId", Constant.NOTEBOOK_STATUS_ID_1);
		map.put("tableName", "cn_notebook");
		map.put("start", start);
		map.put("rows", size);
		List<Map<String, Object>> list = notebookDao.findNotebooksByUserId(map);
		
		return list;
	}
	
	public Notebook addNotebook(String userId, String name) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId 不能为空");
		}
		int isExist = userDao.findUserByUserId(userId);
		if(isExist == 0){
			throw new NotFoundUserException("用户不存在");
		}
		if(name == null || name.trim().isEmpty()){
			name = "新建笔记本";
		}
		Notebook notebook = new Notebook();
		notebook.setId(UUID.randomUUID().toString());
		notebook.setUserId(userId);
		notebook.setTypeId(Constant.NOTEBOOK_STATUS_ID_1);
		notebook.setName(name);
		notebook.setDesc("");
		notebook.setCreateTime(new Date());
		int i = notebookDao.insertNotebook(notebook);
		if(i != 1){
			throw new RuntimeException("笔记本新建失败");
		}
		return notebook;
	}
}
