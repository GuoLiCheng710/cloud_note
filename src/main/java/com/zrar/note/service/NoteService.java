package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import com.zrar.note.entity.Note;
import com.zrar.note.exception.NotFoundNoteException;
import com.zrar.note.exception.NotFoundNotebookException;
import com.zrar.note.exception.NotFoundUserException;

public interface NoteService {
	List<Map<String, Object>> listNote(String notebookId) throws NotFoundNotebookException;
	
	Map<String, Object> showNote(String noteId) throws NotFoundNoteException;
	
	boolean saveNote(String noteId,String noteTitle,String noteBody) throws NotFoundNoteException;
	
	Note addNote(String notebookId,String userId,String title) throws NotFoundNotebookException,NotFoundUserException;
	
	boolean updateNoteState(String noteId,String stateType) throws NotFoundNoteException;
	
	List<Map<String, Object>> listNoteOnRecycle(String userId) throws NotFoundUserException;
}
