package com.zrar.note.dao;

import java.util.List;
import java.util.Map;

import com.zrar.note.entity.Note;

public interface NoteDao {
	List<Map<String, Object>> findNotesByNotebookId(String notebookId);
	
	Map<String, Object> getNoteByNoteId(String noteId);
	
	int findNoteByNoteId(String noteId);
	
	int updateNote(Map<String, Object> map);
	
	int insertNote(Note note);
}
