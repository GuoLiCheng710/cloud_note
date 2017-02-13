package com.zrar.note.dao;

import java.util.List;
import java.util.Map;

public interface NotebookDao {
	  List<Map<String, Object>> findNotebooksByUserId(String userId);
	  
	  int findNotebookByNotebookId(String notebookId);
}
