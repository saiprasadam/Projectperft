package com.springboot.csv.processConfig;

import org.springframework.batch.item.ItemProcessor;

import com.springboot.csv.model.*;


public class EmpItemProcessor implements ItemProcessor<EmpDet, EmpDet> {

	@Override
	public EmpDet process(EmpDet emp) throws Exception {
		// TODO Auto-generated method stub
		return emp;
	}

}
