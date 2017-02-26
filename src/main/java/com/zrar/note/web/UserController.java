package com.zrar.note.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zrar.note.entity.User;
import com.zrar.note.exception.NameException;
import com.zrar.note.exception.NotFoundUserException;
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
	public Object login(String name,String password,HttpServletRequest req,HttpServletResponse res){
		//try {
			HttpSession session = req.getSession();
			session.setAttribute("LoginAuthorization", "LonginSuccessful");
//			Cookie cookie = new Cookie("LoginAuthorization","Longin successful");
//			cookie.setPath("/");
//			res.addCookie(cookie);
			User user = userService.login(name, password);
			return new JsonResult(user);
//		} catch(NameException e) {
//			return new JsonResult(2,e);
//		} 
	}
	@ResponseBody
	@RequestMapping("/regist.do")
	public Object regist(String name,String nick,String password,String confirmPassword){
		try {
			User user = userService.regist(name, nick, password,confirmPassword);
			return new JsonResult(user);
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
			return  new JsonResult(isExist);	//true 可用；false不可用
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	@ResponseBody
	@RequestMapping("/checkOldPassword.do")
	public Object checkOldPassword(String userId,String oldPassword){
		boolean isMatch = userService.checkOldPassword(userId, oldPassword);
		return new JsonResult(isMatch);
	}
	@ResponseBody
	@RequestMapping("/changePassword.do")
	public Object changePassword(String userId,String newPassword,String confirmPassword,HttpServletRequest req){
		boolean isSuccess = userService.changePassword(userId, newPassword, confirmPassword);
		if(isSuccess){
			HttpSession session = req.getSession();
			session.removeAttribute("LoginAuthorization");
			session.invalidate();
			
		}
		return new JsonResult(isSuccess);	//true 修改成功；false修改失败
	}
	@ResponseBody
	@RequestMapping("/logout.do")
	public Object logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.removeAttribute("LoginAuthorization");
		session.invalidate();
		response.setHeader("cache-control", "no-cache");
		response.setHeader("cache-control", "no-store");
		response.setDateHeader("expires", 0);
		response.setHeader("pragma","no-cache");
		return new JsonResult(0);
	}
	
	
	@ExceptionHandler(NotFoundUserException.class)
	@ResponseBody
	public Object userExp(NotFoundUserException e){
		e.printStackTrace();
		return new JsonResult(e);
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
