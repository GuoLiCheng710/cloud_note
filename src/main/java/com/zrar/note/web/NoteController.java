package com.zrar.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrar.note.entity.Note;
import com.zrar.note.exception.NotFoundNoteException;
import com.zrar.note.exception.NotFoundNotebookException;
import com.zrar.note.exception.NotFoundUserException;
import com.zrar.note.service.NoteService;
import com.zrar.note.util.JsonResult;

@Controller
@RequestMapping("/note")
public class NoteController extends AbstractController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/list.do")
	@ResponseBody
	public JsonResult listNote(String notebookId){
		List<Map<String, Object>> list = noteService.listNote(notebookId);
		return new JsonResult(list);
	}
	@RequestMapping("/body.do")
	@ResponseBody
	public JsonResult showNote(String noteId){
		Map<String, Object> map = noteService.showNote(noteId);
		return new JsonResult(map);
	}
	@RequestMapping("/save.do")
	@ResponseBody
	public JsonResult saveNote(String noteId,String noteTitle,String noteBody){
		boolean b = noteService.saveNote(noteId, noteTitle, noteBody);
		return new JsonResult(b);
	}
	@RequestMapping("/add.do")
	@ResponseBody
	public JsonResult addNote(String notebookId, String userId, String title){
		Note note = noteService.addNote(notebookId, userId, title);
		return new JsonResult(note);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteNote(String noteId){
		boolean b = noteService.deleteNoteToRecycle(noteId);
		return new JsonResult(b);
	}
	
	
	@ExceptionHandler(NotFoundNotebookException.class)
	@ResponseBody
	public Object notebookExp(NotFoundNotebookException e){
		e.printStackTrace();
		return new JsonResult(e);
	}
	@ExceptionHandler(NotFoundNoteException.class)
	@ResponseBody
	public Object noteExp(NotFoundNoteException e){
		e.printStackTrace();
		return new JsonResult(e);
	}
	@ExceptionHandler(NotFoundUserException.class)
	@ResponseBody
	public Object userExp(NotFoundUserException e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}
