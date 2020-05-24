package com.uplooking.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.uplooking.pojo.Tie;
import com.uplooking.pojo.User;

public interface UserService {
	Map<String, Object> userLogin(String name,String pwd) throws Exception;
	Map<String, Object> userRegist(User user) throws Exception;
	Map<String , Object> tieUpload(Tie tie) throws Exception;
	Tie tieDownload(int id) throws Exception;
    Page<Tie> getTielist(int page,int size) throws Exception;
	byte[] getPhoto(int id) throws Exception;
}
