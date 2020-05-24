package com.uplooking.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.uplooking.dao.TieDomain;
import com.uplooking.dao.UserDomain;
import com.uplooking.pojo.Tie;
import com.uplooking.pojo.User;
import com.uplooking.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	@Qualifier("userDomain")
	private UserDomain userDomain;
	
	@Autowired
	@Qualifier("tieDomain")
	private TieDomain tieDomain;
	private Map<String, Object> result;
	
	@Override
	@Transactional(value = TxType.SUPPORTS)
	public Map<String, Object> userLogin(final String name, String pwd) throws Exception {
		result = new HashMap<String, Object>();	
		
		User user = userDomain.findOne(new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				
				
				return cb.equal(root.get("uname").as(String.class),name);
			}
		});
		if (user!=null&&user.getUpwd().equals(pwd)) {
			result.put("code", 200);
			result.put("msg", "登录成功");
			result.put("user", user);
			
		}else {
			result.put("code", 300);
			result.put("msg", "用户名或密码错误");
		}
		
		return  result;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Map<String, Object> userRegist(User user) throws Exception {
		result = new HashMap<String, Object>();	
		long count = userDomain.count(new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.get("uname").as(String.class), user.getUname());
			}
		});
		if (count==0) {
			result.put("code", 200);
			result.put("msg", "注册成功");
			result.put("user", userDomain.save(user));
		}else {
			result.put("code", 300);
			result.put("msg", "用户名存在");
		}
		return result;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Map<String, Object> tieUpload(Tie tie) throws Exception {
		result = new HashMap<String, Object>();
		Tie tie2 = tieDomain.save(tie);
		result.put("code", 200);
		result.put("msg", "上传成功");
		result.put("tie", tie2);
		
		return result;
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public Tie tieDownload(int id) throws Exception {
		
		return tieDomain.findOne(id);
	}

	@Override
	@Transactional(value = TxType.SUPPORTS)
	
	public Page<Tie> getTielist(int page, int size) throws Exception {
		        PageRequest pageRequest = new PageRequest(page, size);
		        
		return tieDomain.findAll(pageRequest);
	}

	@Override
	public byte[] getPhoto(int id) throws Exception {
		
		return null;
	}

}
