package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import com.zrar.note.exception.NotFoundNoteException;
import com.zrar.note.exception.NotFoundNotebookException;

public interface NoteService {
	List<Map<String, Object>> listNote(String notebookId) throws NotFoundNotebookException;
	
	Map<String, Object> showNote(String noteId) throws NotFoundNoteException;
	
	boolean saveNote(String noteId,String noteTitle,String noteBody) throws NotFoundNoteException;
}
