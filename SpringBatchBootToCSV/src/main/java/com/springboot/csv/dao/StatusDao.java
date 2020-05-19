package com.springboot.csv.dao;

import com.springboot.csv.model.StatusInfo;

public interface StatusDao {

	void addStatusDet(StatusInfo empDet);	
	boolean orgsExist(String orgName, String taskToday);
}
