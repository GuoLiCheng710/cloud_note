package com.zrar.note.dao;

import java.util.List;
import java.util.Map;

public interface NoteDao {
	List<Map<String, Object>> findNotesByNotebookId(String notebookId);
	
	Map<String, Object> getNoteByNoteId(String noteId);
	
	int findNoteByNoteId(String noteId);
}
