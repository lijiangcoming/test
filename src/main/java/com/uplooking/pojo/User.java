package com.uplooking.pojo;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author 10274
 *
 */
@Entity
@Table(name = "tbl_user",catalog = "jpa")
public class User  implements Serializable{

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	@Column(name = "uname")
	private String uname;
	@Column(name = "upwd")
	private String upwd;
	@Lob
	@Column(name = "uphoto")
	private byte[] uphoto;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer uid, String uname, String upwd, byte[] uphoto) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.uphoto = uphoto;
	}

	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public byte[] getUphoto() {
		return uphoto;
	}
	public void setUphoto(byte[] uphoto) {
		this.uphoto = uphoto;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", uphoto=" + Arrays.toString(uphoto) + "]";
	}
	
	
	
}
