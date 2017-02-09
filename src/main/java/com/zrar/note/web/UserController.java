package com.zrar.note.web;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrar.note.entity.User;
import com.zrar.note.service.UserService;
import com.zrar.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {
		
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public Object login(String name,String password){
		try {
			User user = userService.login(name, password);
			return new JsonResult(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
		
	}
}
