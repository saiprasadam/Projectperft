package com.springboot.csv.processConfig;

import org.springframework.batch.item.ItemProcessor;

import com.springboot.csv.model.*;


public class EmpItemProcessor implements ItemProcessor<StatusInfo, StatusInfo> {

	@Override
	public StatusInfo process(StatusInfo emp) throws Exception {
		// TODO Auto-generated method stub
		return emp;
	}

}
