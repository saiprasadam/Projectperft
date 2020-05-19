package com.springboot.csv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.csv.dao.StatusDao;
import com.springboot.csv.model.StatusInfo;

@Service
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	StatusDao statusDao;

	@Override
	public boolean addStatusDet(StatusInfo empDet) {
		// TODO Auto-generated method stub
	
	if (statusDao.orgsExist(empDet.getName(),empDet.getTask_for_today())) {
 	   return false;
    }
	statusDao.addStatusDet(empDet);
	 
	return true;
}

}
