/*
 * 基础BEAN类
 *
 * VERSION     DATE        BY              REASON
 * -------- ----------- --------------- ------------------------------------------
 * 1.00    2014-08-10     gaoyj           程序.发布
 * -------- ----------- --------------- ------------------------------------------
 * Copyright 2014 cms  System. - All Rights Reserved.
 *
 */
package cn.com.cms.bean;

import java.io.Serializable;

import cn.com.cms.web.tag.PageInfo;

/**
 * <p>基础BEAN类</p>
 * @author gaoyj
 *
 */
public class BaseBean implements Serializable,IBean,Cloneable {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -7279166967099470479L;
	/**默认构造器*/
	public BaseBean(){}
	/** 开始时间 */
	private String date1;
	/** 结束时间 */
	private String date2;
	/** 分页对象 */
	private PageInfo pageInfo;
	/** 备注 */
	private String note;
	/** 删除 */
	private String del_flag;
	/** 数据输入日期 */
	private String date_created;
	/** 建立者id */
	private String create_id;
	/** 建立者ip */
	private String create_ip;
	/** 资料更新日期 */
	private String last_updated;
	/** 修改者ip */
	private String update_ip;
	/** 修改者id */
	private String update_id;
	/** 查询关键字 */
	private String keyword;
	/** VERSION */
	private Integer version;
	/** 错误代码*/
	private String errcode;
	/** 错误信息 */
	private String errmsg;
	/** 人工分页begin */
	private Integer limit_s;
	/** 人工分页end */
	private Integer limit_e;
	/**
	 * 开始时间取得
	 * @return 开始时间
	 */
	public String getDate1() {
	    return date1;
	}
	/**
	 * 开始时间设定
	 * @param date1 开始时间
	 */
	public void setDate1(String date1) {
	    this.date1 = date1;
	}
	/**
	 * 结束时间取得
	 * @return 结束时间
	 */
	public String getDate2() {
	    return date2;
	}
	/**
	 * 结束时间设定
	 * @param date2 结束时间
	 */
	public void setDate2(String date2) {
	    this.date2 = date2;
	}
	/**
	 * 分页对象取得
	 * @return 分页对象
	 */
	public PageInfo getPageInfo() {
	    return pageInfo;
	}
	/**
	 * 分页对象设定
	 * @param pageInfo 分页对象
	 */
	public void setPageInfo(PageInfo pageInfo) {
	    this.pageInfo = pageInfo;
	}
	/**
	 * 备注取得
	 * @return 备注
	 */
	public String getNote() {
	    return note;
	}
	/**
	 * 备注设定
	 * @param note 备注
	 */
	public void setNote(String note) {
	    this.note = note;
	}
	/**
	 * 删除取得
	 * @return 删除
	 */
	public String getDel_flag() {
	    return del_flag;
	}
	/**
	 * 删除设定
	 * @param del_flag 删除
	 */
	public void setDel_flag(String del_flag) {
	    this.del_flag = del_flag;
	}
	/**
	 * 数据输入日期取得
	 * @return 数据输入日期
	 */
	public String getDate_created() {
	    return date_created;
	}
	/**
	 * 数据输入日期设定
	 * @param date_created 数据输入日期
	 */
	public void setDate_created(String date_created) {
	    this.date_created = date_created;
	}
	/**
	 * 建立者id取得
	 * @return 建立者id
	 */
	public String getCreate_id() {
	    return create_id;
	}
	/**
	 * 建立者id设定
	 * @param create_id 建立者id
	 */
	public void setCreate_id(String create_id) {
	    this.create_id = create_id;
	}
	/**
	 * 建立者ip取得
	 * @return 建立者ip
	 */
	public String getCreate_ip() {
	    return create_ip;
	}
	/**
	 * 建立者ip设定
	 * @param create_ip 建立者ip
	 */
	public void setCreate_ip(String create_ip) {
	    this.create_ip = create_ip;
	}
	/**
	 * 资料更新日期取得
	 * @return 资料更新日期
	 */
	public String getLast_updated() {
	    return last_updated;
	}
	/**
	 * 资料更新日期设定
	 * @param last_updated 资料更新日期
	 */
	public void setLast_updated(String last_updated) {
	    this.last_updated = last_updated;
	}
	/**
	 * 修改者ip取得
	 * @return 修改者ip
	 */
	public String getUpdate_ip() {
	    return update_ip;
	}
	/**
	 * 修改者ip设定
	 * @param update_ip 修改者ip
	 */
	public void setUpdate_ip(String update_ip) {
	    this.update_ip = update_ip;
	}
	/**
	 * 修改者id取得
	 * @return 修改者id
	 */
	public String getUpdate_id() {
	    return update_id;
	}
	/**
	 * 修改者id设定
	 * @param update_id 修改者id
	 */
	public void setUpdate_id(String update_id) {
	    this.update_id = update_id;
	}
	/**
	 * 查询关键字取得
	 * @return 查询关键字
	 */
	public String getKeyword() {
	    return keyword;
	}
	/**
	 * 查询关键字设定
	 * @param keyword 查询关键字
	 */
	public void setKeyword(String keyword) {
	    this.keyword = keyword;
	}
	/**
	 * VERSION取得
	 * @return VERSION
	 */
	public Integer getVersion() {
	    return version;
	}
	/**
	 * VERSION设定
	 * @param version VERSION
	 */
	public void setVersion(Integer version) {
	    this.version = version;
	}
	/**
	 * 对象克隆
	 */
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return o;
	}
	/**
	 * 错误代码取得
	 * @return 错误代码
	 */
	public String getErrcode() {
	    return errcode;
	}
	/**
	 * 错误代码设定
	 * @param errcode 错误代码
	 */
	public void setErrcode(String errcode) {
	    this.errcode = errcode;
	}
	/**
	 * 错误信息取得
	 * @return 错误信息
	 */
	public String getErrmsg() {
	    return errmsg;
	}
	/**
	 * 错误信息设定
	 * @param errmsg 错误信息
	 */
	public void setErrmsg(String errmsg) {
	    this.errmsg = errmsg;
	}
	/**
	 * 人工分页begin取得
	 * @return 人工分页begin
	 */
	public Integer getLimit_s() {
	    return limit_s;
	}
	/**
	 * 人工分页begin设定
	 * @param limit_s 人工分页begin
	 */
	public void setLimit_s(Integer limit_s) {
	    this.limit_s = limit_s;
	}
	/**
	 * 人工分页end取得
	 * @return 人工分页end
	 */
	public Integer getLimit_e() {
	    return limit_e;
	}
	/**
	 * 人工分页end设定
	 * @param limit_e 人工分页end
	 */
	public void setLimit_e(Integer limit_e) {
	    this.limit_e = limit_e;
	}
}
