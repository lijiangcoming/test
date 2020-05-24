package com.uplooking.controller;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.uplooking.pojo.Tie;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(description = "用户api接口")
public class UserController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping(value = "/")
	@ApiOperation(value = "首页")
	public String index(HttpServletRequest request) {
		try {
			System.out.println(userService.getTielist(0, 10));
			request.setAttribute("pager", userService.getTielist(0, 10));
		} catch (Exception e) {
				return "user/error";
		}
		return "user/index";
		
	}
	
	@GetMapping(value = "/user/login")
	public String loginIndex() {
		return "user/login";
	}
	
	@PostMapping(value = "/user/login")
	public String login(@RequestParam("uname")String uname,@RequestParam("upwd")String upwd,HttpServletRequest request,
			HttpServletResponse response
			) {
		    try {
				Map<String, Object> login = userService.userLogin(uname, upwd);
				if (200==Integer.parseInt(login.get("code").toString())) {
					   request.getSession().setAttribute("user", login.get("user"));
					   response.sendRedirect("/");
				}else {
				  request.setAttribute("message", login.get("msg"));		
					
				}
				return "/user/login";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return "user/error";
			}
		
	}
	
	
	@GetMapping(value = "/user/lagout")
	public void loginout(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getSession().invalidate();;
			response.sendRedirect("/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@GetMapping(value = "/user/photo")
	public void name(HttpServletResponse response,HttpServletRequest request) {
			try {
				ServletOutputStream outputStream = response.getOutputStream();
				User user = (User) request.getSession().getAttribute("user");
				outputStream.write(user.getUphoto());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	@GetMapping(value = "/user/reg")
	public String regIndex() {
		try {
			
			return "user/reg";
		} catch (Exception e) {
			e.printStackTrace();
		return "user/error";
		}
	}
	@PostMapping(value = "/user/reg")
	public String reg(User user,HttpServletRequest request,@RequestParam("photo")MultipartFile photo,
			HttpServletResponse response) {
		try {
			user.setUphoto(photo.getBytes());
			Map<String, Object> regist = userService.userRegist(user);
			request.getSession().setAttribute("user",regist.get("user"));
			if (200==Integer.parseInt(regist.get("code").toString())) {
				response.sendRedirect("/");
			}else {
				request.setAttribute("message",regist.get("message"));
			}
			return "user/reg";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "user/error";
		}
	}
	
	
	@GetMapping("/tie/upload")
	public String uploadIndex() {
		try {
			return "tie/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
	}
	@PostMapping("/tie/upload")
	public String upload(@RequestParam("tie")MultipartFile file,HttpServletRequest request) {
		try {
			User user = (User) request.getSession().getAttribute("user");
			 Tie tie = new  Tie(0, file.getOriginalFilename(), file.getSize(), file.getContentType(), file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")),user.getUid(), new Timestamp(new Date().getTime()), file.getBytes());
			  request.setAttribute("message", userService.tieUpload(tie).get("msg"));
			return "tie/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "user/error";
		}
		
		
	}
	@GetMapping(value = "/tie/download/{id}")
	public void  downLoad(@PathVariable("id")int id,HttpServletResponse response) {
		try {
			Tie tieDownload = userService.tieDownload(id);
			ServletOutputStream out = response.getOutputStream();
			 response.addHeader("content-Type", "application/octet-stream");
	         response.addHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(tieDownload.getTname(), "utf-8"));
	         out.write(tieDownload.getTinfo());
	         out.flush();
	         out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(value="/nologin")
	public String method25(HttpServletRequest request){
		try {
			request.setAttribute("message", "上传下载需先登录");
			return "user/login";
		} catch (Exception e) {
			return "user/error";
		}
	}
	
	
	@GetMapping(value="/testJson")
	@ResponseBody
	public Object getJson(){
		Page<Tie> tielist =null;
		try {
			 tielist = userService.getTielist(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tielist;
		
	}
	
}
