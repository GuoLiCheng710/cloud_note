package com.zrar.note.web;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.PasswordException;
import com.zrar.note.service.UserService;
import com.zrar.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {
		
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public Object login(String name,String password){
		try {
			User user = userService.login(name, password);
			return new JsonResult(user);
		} catch (NameException e) {
			e.printStackTrace();
			return new JsonResult(e);
		} catch (PasswordException e){
			e.printStackTrace();
			return new JsonResult(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	@ResponseBody
	@RequestMapping("/regist.do")
	public Object regist(String name,String nick,String password,String confirmPassword){
		try {
			User user = userService.regist(name, nick, password,confirmPassword);
			return new JsonResult(user);
		} catch (NameException e) {
			e.printStackTrace();
			return new JsonResult(e);
		} catch (PasswordException e) {
			e.printStackTrace();
			return new JsonResult(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	@ResponseBody
	@RequestMapping("/checkname.do")
	public Object checkName(String name){
		try {
			boolean isExist = userService.isUserExistByName(name);
			return  new JsonResult(isExist);	//true 不存在；false 已存在
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	@ExceptionHandler(NameException.class)
	@ResponseBody
	public Object nameExp(NameException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public Object pwdExp(PasswordException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}
	
}
