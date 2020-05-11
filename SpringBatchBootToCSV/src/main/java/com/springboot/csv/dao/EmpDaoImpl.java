package com.springboot.csv.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.csv.model.EmpDet;
import com.springboot.csv.processConfig.BatchConfiguration;

@Repository
@Transactional
public class EmpDaoImpl implements EmpDao {
	
//	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public void addEmpDet(EmpDet empDet) {
       String sql = "INSERT INTO empdetail(empId,empName,salary,age)" + "VALUES(:org_tid,:orgName,:noOfemp,:age)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("org_tid", empDet.getEmpId());
		paramMap.put("orgName", empDet.getEmpName());
		paramMap.put("noOfemp", empDet.getSalary());
		paramMap.put("age", empDet.getAge());
		BatchConfiguration bc=new BatchConfiguration();
		
		//int status =bc.writer1(empDet);
		
		// Passing map containing named params
		// try {
	int status = bc.getNamedParameterJdbcTemplate().update(sql, paramMap);
				if (status != 0) {
			System.out.println("the value of org id inserted is " + empDet.getEmpId());
				
		//	bc.step1();
		} else {
			System.out.println("org id inserted is   " + empDet.getEmpId());
		}
	}
	
	public boolean orgsExist(String orgName, int yearofReg) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Scheduled(cron = "0 */1 * * * ?")
/*	public void perform() throws Exception{
		
		 JobParameters params = new JobParametersBuilder()
	                .addString("JobID", "exportUserJob")
	                .toJobParameters();
		// logger.info("job id execute"+String.valueOf(System.currentTimeMillis()));
		 	jobLauncher.run((Job) params,null);
	     //   j.run(job, params);
 }*/
	 
	 /*   public void perform() throws Exception
	    {
	        JobParameters params = new JobParametersBuilder()
	                .addString("JobID", String.valueOf(System.currentTimeMillis()))
	                .toJobParameters();
	        jobLauncher.run(job, params);
	    }*/
}
