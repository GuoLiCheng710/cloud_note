package com.zrar.note.dao;

import java.util.List;
import java.util.Map;

import com.zrar.note.entity.Notebook;

public interface NotebookDao {
	  List<Map<String, Object>> findNotebooksByUserId(Map<String, Object> map);
	  
	  int findNotebookByNotebookId(String notebookId);
	  
	  int insertNotebook(Notebook notebook);
	  
	  List<Map<String,Object>> findNotebooksByParam(Map<String, Object> map);
	  
}
