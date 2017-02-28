package com.zrar.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrar.note.dao.NoteDao;
import com.zrar.note.dao.NotebookDao;
import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.Note;
import com.zrar.note.exception.NotFoundNoteException;
import com.zrar.note.exception.NotFoundNotebookException;
import com.zrar.note.exception.NotFoundUserException;
import com.zrar.note.util.Constant;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	@Resource
	private NoteDao noteDao;
	@Resource
	private NotebookDao notebookDao;
	@Resource
	private UserDao userDao;
	public List<Map<String, Object>> listNote(String notebookId) throws NotFoundNotebookException {
		if(notebookId == null || notebookId.trim().isEmpty()){
			throw new NotFoundNotebookException("notebookId 不能为空");
		}
		int isExist = notebookDao.findNotebookByNotebookId(notebookId);
		if(isExist == 0){
			throw new NotFoundNotebookException("笔记本不存在");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("notebookId", notebookId);
		map.put("statusId", Constant.NOTE_STATUS_ID_1);
		List<Map<String, Object>> list = noteDao.findNotesByNotebookId(map);
		return list;
	}
	public Map<String, Object> showNote(String noteId) throws NotFoundNoteException {
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NotFoundNoteException("noteId不能为空");
		}
		int isExist = noteDao.findNoteByNoteId(noteId);
		if(isExist == 0){
			throw new NotFoundNoteException("笔记不存在");
		}
		Map<String, Object> map = noteDao.getNoteByNoteId(noteId);
		return map;
	}
	public boolean saveNote(String noteId, String noteTitle, String noteBody) throws NotFoundNoteException {
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NotFoundNoteException("noteId不能为空");
		}
		int isExist = noteDao.findNoteByNoteId(noteId);
		if(isExist == 0){
			throw new NotFoundNoteException("笔记不存在");
		}
		if(noteBody == null){
			noteBody = "";
		}
		if(noteTitle == null || noteTitle.trim().isEmpty()){
			String reg = "<p>[^<]<\\/p>";
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(noteBody);
			if(m.find()){
				String str = m.group();
				noteTitle = str.substring(3,str.length()>17?13:str.length()-4).trim();
			}else{
				noteTitle = "新建笔记";
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noteId", noteId);
		map.put("noteTitle",noteTitle);
		map.put("noteBody", noteBody);
		map.put("lastModifyTime", System.currentTimeMillis());
		int n = noteDao.updateNote(map);
		return n==1;
	}
	public Note addNote(String notebookId, String userId, String title)
			throws NotFoundNotebookException, NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId 不能为空");
		}
		int userIsExist = userDao.findUserByUserId(userId);
		if(userIsExist == 0){
			throw new NotFoundUserException("用户不存在");
		}
		if(notebookId == null || notebookId.trim().isEmpty()){
			throw new NotFoundNotebookException("notebookId 不能为空");
		}
		int notebookIsExist = notebookDao.findNotebookByNotebookId(notebookId);
		if(notebookIsExist == 0){
			throw new NotFoundNotebookException("笔记本不存在");
		}
		if(title == null || title.trim().isEmpty()){
			title = "新建笔记";
		}
		Note note = new Note();
		note.setId(UUID.randomUUID().toString());
		note.setNotebookId(notebookId);
		note.setUserId(userId);
		note.setStatusId(Constant.NOTE_STATUS_ID_1);
		note.setTypeId("1");
		note.setTitle(title);
		note.setBody("");
		note.setCreateTime(System.currentTimeMillis());
		note.setLastModifyTime(System.currentTimeMillis());
		int i = noteDao.insertNote(note);
		if(i != 1){
			throw new NotFoundNotebookException("笔记创建失败");
		}
		return note;
	}
	public boolean updateNoteState(String noteId,String stateType) throws NotFoundNoteException {
		if(noteId == null || noteId.trim().isEmpty()){
			throw new NotFoundNoteException("noteId 不能为空");
		}
		int isExist = noteDao.findNoteByNoteId(noteId);
		if(isExist != 1){
			throw new NotFoundNoteException("笔记不存在");
		}
		if(stateType == null || stateType.trim().isEmpty()){
			throw new NotFoundNoteException("deleteType为空");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noteId", noteId);
		if("1".equals(stateType)){
			//1 时删除至回收站
			map.put("noteStatusId", Constant.NOTE_STATUS_ID_2);
		} else if("2".equals(stateType)){
			//2 时彻底删除，这里处理为假删
			map.put("noteStatusId", Constant.NOTE_STATUS_ID_0);
		} else {
			//3时从回收站还原
			map.put("noteStatusId", Constant.NOTE_STATUS_ID_1);
		}
		int i = noteDao.updateNote(map);
		return i == 1;
	}
	public List<Map<String, Object>> listNoteOnRecycle(String userId) throws NotFoundUserException {
		if(userId == null || userId.trim().isEmpty()){
			throw new NotFoundUserException("userId不能为空");
		}
		int i = userDao.findUserByUserId(userId);
		if(i != 1){
			throw new NotFoundUserException("用户不存在");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("statusId", Constant.NOTE_STATUS_ID_2);
		List<Map<String, Object>> list = noteDao.findNotesOnRecycle(map);
		return list;
	}
}




