package com.springboot.csv.model;

import java.util.Date;

public class StatusInfo {

	private Integer empid;
	private String name;
	private Date date;
	private String task_for_today;
	private String work_done_tomorrow;
	private String blocker;
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTask_for_today() {
		return task_for_today;
	}
	public void setTask_for_today(String task_for_today) {
		this.task_for_today = task_for_today;
	}
	public String getWork_done_tomorrow() {
		return work_done_tomorrow;
	}
	public void setWork_done_tomorrow(String work_done_tomorrow) {
		this.work_done_tomorrow = work_done_tomorrow;
	}
	public String getBlocker() {
		return blocker;
	}
	public void setBlocker(String blocker) {
		this.blocker = blocker;
	}
	
		
}
