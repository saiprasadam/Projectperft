package com.springboot.csv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.csv.dao.EmpDao;
import com.springboot.csv.model.EmpDet;

@Service
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	EmpDao empDao;

	@Override
	public boolean addOrganzationDet(EmpDet empDet) {
		// TODO Auto-generated method stub
	
	if (empDao.orgsExist(empDet.getEmpName(),empDet.getSalary())) {
 	   return false;
    }
	empDao.addEmpDet(empDet);
	 
	return true;
}

}
