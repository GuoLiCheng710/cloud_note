package com.zrar.note.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrar.note.service.NotebookService;
import com.zrar.note.util.JsonResult;

@Controller
@RequestMapping("/notebook")
public class NotebookController extends AbstractController {
	
	@Resource
	private NotebookService notebookService;
	
	@RequestMapping("list.do")
	@ResponseBody
	public JsonResult listNotebook(String userId){
		List<Map<String, Object>> list = notebookService.listNotebook(userId);
		return new JsonResult(list);
	}
	

}
