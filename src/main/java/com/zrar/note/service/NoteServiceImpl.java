package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrar.note.dao.NoteDao;
import com.zrar.note.dao.NotebookDao;
import com.zrar.note.exception.NotFoundNoteException;
import com.zrar.note.exception.NotFoundNotebookException;
import com.zrar.note.util.JsonResult;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;
	public List<Map<String, Object>> listNote(String notebookId) throws NotFoundNotebookException {
		if(notebookId == null || notebookId.trim().isEmpty()){
			throw new NotFoundNotebookException("notebookId ����Ϊ��");
		}
		int isExist = notebookDao.findNotebookByNotebookId(notebookId);
		if(isExist == 0){
			throw new NotFoundNotebookException("�ʼǱ�������");
		}
		List<Map<String, Object>> list = noteDao.findNotesByNotebookId(notebookId);
		return list;
	}
	public Map<String, Object> showNote(String noteId) throws NotFoundNoteException {
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NotFoundNoteException("noteId����Ϊ��");
		}
		int isExist = noteDao.findNoteByNoteId(noteId);
		if(isExist == 0){
			throw new NotFoundNoteException("�ʼǲ�����");
		}
		Map<String, Object> map = noteDao.getNoteByNoteId(noteId);
		return map;
	}

}




