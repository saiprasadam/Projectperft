package com.springboot.csv.dao;

import com.springboot.csv.model.EmpDet;

public interface EmpDao {

	void addEmpDet(EmpDet empDet);	
	boolean orgsExist(String orgName, int yearofReg);
}
