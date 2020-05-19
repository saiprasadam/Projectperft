package com.springboot.csv.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.csv.model.StatusInfo;
import com.springboot.csv.processConfig.BatchConfiguration;

@Repository
@Transactional
public class StatusDaoImpl implements StatusDao {
	
//	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	

	public void addStatusDet(StatusInfo empDet) {
       String sql = "INSERT INTO daily_status(empid,name,date,task_for_today,work_done_tomorrow,blocker)" + "VALUES(:empid,:name,:dat,:todaywork,:tomoWork,:blocker)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		empDet.setDate(new Date());
		paramMap.put("empid", empDet.getEmpid());
		paramMap.put("name", empDet.getName());
		paramMap.put("dat", empDet.getDate());
		paramMap.put("todaywork",empDet.getTask_for_today());
		paramMap.put("tomoWork", empDet.getWork_done_tomorrow());
		paramMap.put("blocker", empDet.getBlocker());
		BatchConfiguration bc=new BatchConfiguration();
		
		//int status =bc.writer1(empDet);
		
		// Passing map containing named params
		// try {
	int status = bc.getNamedParameterJdbcTemplate().update(sql, paramMap);
				if (status != 0) {
			System.out.println("the value of org id inserted is " + empDet.getEmpid());
				
		//	bc.step1();
		} else {
			System.out.println("org id inserted is   " + empDet.getEmpid());
		}
	}
	
	
	@Override
	public boolean orgsExist(String orgName, String taskToday) {
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
