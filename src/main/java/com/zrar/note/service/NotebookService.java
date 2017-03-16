package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import com.zrar.note.entity.Notebook;
import com.zrar.note.exception.NotFoundUserException;

public interface NotebookService {
	List<Map<String, Object>> listNotebook(String userId,int pageNum) throws NotFoundUserException;
	Notebook addNotebook(String userId,String name) throws NotFoundUserException;
}
