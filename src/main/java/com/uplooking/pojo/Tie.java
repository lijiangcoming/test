package com.uplooking.pojo;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tbl_tie",catalog = "jpa")
public class Tie {
	
	@Id
	@Column(name = "tid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tid;
	@Column(name = "tname")
	private String tname;
	@Column(name = "tsize")
	private long tsize;
	@Column(name = "ttype")
	private String ttype;
	@Column(name = "tsuffix")
	private String tsuffix;
	@Column(name = "tuid")
	private Integer tuid;
	@Column(name = "tdate")
	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss",timezone = "GTM+8")
	private Timestamp tdate;
	
	
	
	
	@Lob
	@Column(name = "tinfo")
	private byte[] tinfo;
	@ManyToOne
	@JoinColumn(name = "tuid",insertable = false,updatable = false)
	private User user;
	
	
	public Tie() {
		// TODO Auto-generated constructor stub
	}
	
	public Tie(Integer tid, String tname, long tsize, String ttype, String tsuffix, Integer tuid, Timestamp tdate,
			byte[] tinfo) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tsize = tsize;
		this.ttype = ttype;
		this.tsuffix = tsuffix;
		this.tuid = tuid;
		this.tdate = tdate;
		this.tinfo = tinfo;
	}

	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public long getTsize() {
		return tsize;
	}
	public void setTsize(long tsize) {
		this.tsize = tsize;
	}
	public String getTtype() {
		return ttype;
	}
	public void setTtype(String ttype) {
		this.ttype = ttype;
	}
	public String getTsuffix() {
		return tsuffix;
	}
	public void setTsuffix(String tsuffix) {
		this.tsuffix = tsuffix;
	}
	public Integer getTuid() {
		return tuid;
	}
	public void setTuid(Integer tuid) {
		this.tuid = tuid;
	}
	public Timestamp getTdate() {
		return tdate;
	}
	public void setTdate(Timestamp tdate) {
		this.tdate = tdate;
	}
	public byte[] getTinfo() {
		return tinfo;
	}
	public void setTinfo(byte[] tinfo) {
		this.tinfo = tinfo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Tie [tid=" + tid + ", tname=" + tname + ", tsize=" + tsize + ", ttype=" + ttype + ", tsuffix=" + tsuffix
				+ ", tuid=" + tuid + ", tdate=" + tdate + ", tinfo=" + Arrays.toString(tinfo) + ", user=" + user + "]";
	}
	
	
	
}
