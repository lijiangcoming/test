package com.uplooking.tets;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uplooking.pojo.Tie;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Mytets {
	
	@Autowired
	@Qualifier("userService")
    private UserService userService;
	
	@Test
	public void test() throws Exception {
//		String name ="‪D:"+File.separator+"javaTest"+File.separator+"struts2"+File.separator+"1.jpg";
//		InputStream inputStream = new FileInputStream(name);
//		System.out.println(inputStream.available());
//		byte[] flsh = new byte[inputStream.available()];
//		
//		inputStream.read(flsh);
		
//		User user = new User(0, "张三","123456", null);
//		System.out.println(userService.userRegist(user));
		Page<Tie> page = userService.getTielist(0, 10);
		System.out.println(page.getTotalElements());
//		
		
		
	}

}
