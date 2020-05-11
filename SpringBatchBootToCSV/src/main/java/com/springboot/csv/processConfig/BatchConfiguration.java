package com.springboot.csv.processConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.Scheduled;

import com.springboot.csv.model.EmpDet;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	static Logger logger = Logger.getLogger(BatchConfiguration.class.getName());

	@Autowired
	public static NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
			throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Value("${spring.datasource.driver-class-name}")
	 private  String drivClassName;
	@Value("${spring.datasource.url}")
	 private  String dbUrl;
	@Value("${spring.datasource.username}")
	 private  String dbUser;
	 @Value("${spring.datasource.password}")
	 private  String dbPwd;
	 
	
	
	@Autowired
	 public JobBuilderFactory jobBuilderFactory;
	 
	 @Autowired
	 public StepBuilderFactory stepBuilderFactory;
	 
	 @Autowired
	 public JobLauncher JobLauncher;
	 
	 
	 @Bean
	 public  DataSource dataSource() {
	  final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  dataSource.setDriverClassName(drivClassName);
	  dataSource.setUrl(dbUrl);  //"jdbc:mysql://localhost:3306/emp"
	  dataSource.setUsername(dbUser);
	  dataSource.setPassword(dbPwd);
	  
	  return dataSource;
	 }
	 
	 @Bean
	 public JdbcCursorItemReader<EmpDet> reader(){
	  JdbcCursorItemReader<EmpDet> reader = new JdbcCursorItemReader<EmpDet>();
	  reader.setDataSource(dataSource());
	  reader.setSql("SELECT empid,empname,salary,age FROM empdetail");
	  reader.setRowMapper(new UserRowMapper());
	  
	  return reader;
	 }
	 
	 
	 public class UserRowMapper implements RowMapper<EmpDet>{

	  @Override
	  public EmpDet mapRow(ResultSet rs, int rowNum) throws SQLException {
		  EmpDet user = new EmpDet();
	   user.setEmpId(rs.getInt("EmpId"));
	   user.setEmpName(rs.getString("EmpName"));
	   user.setSalary(rs.getInt("Salary"));
	   user.setAge(rs.getInt("Age"));
	   
	   return user;
	  }
	  
	 }
	 
	 @Bean
	 public EmpItemProcessor processor(){
	  return new EmpItemProcessor();
	 }
	 
	 private Resource outputResource = new FileSystemResource("output/Emp1.csv");

	    @Bean
	    public FlatFileItemWriter<EmpDet> writer() 
	    {
	        //Create writer instance
	        FlatFileItemWriter<EmpDet> writer = new FlatFileItemWriter<>();
	         
	        //Set output file location
	        writer.setResource(outputResource);
	         
	        //All job repetitions should "append" to same output file
	        writer.setAppendAllowed(true);
	      
	  writer.setLineAggregator(new DelimitedLineAggregator<EmpDet>() {{
	   setDelimiter(",");
	      setFieldExtractor(new BeanWrapperFieldExtractor<EmpDet>() {{
	    setNames(new String[] { "empId", "empName","salary","age" });
	   }});
	  }});
	  
	  return writer;
	 }	    
	 
	 @Bean
	 public Step step1() {
	  return stepBuilderFactory.get("step1").<EmpDet, EmpDet> chunk(10)
	    .reader(reader())
	    .processor(processor())
	    .writer(writer())
	    .build();
	 }
	 
	@Bean
	 public Job exportUserJob() {
	  return jobBuilderFactory.get("exportUserJob")
	    .incrementer(new RunIdIncrementer())
	    .flow(step1())
	    .end()
	    .build();
	 }
	 
	
	@Scheduled(fixedRate = 84100)
	public void run() throws Exception {
		 JobParameters params = new JobParametersBuilder()
	                .addString("JobID", String.valueOf(System.currentTimeMillis()))
	                .toJobParameters();
	    JobExecution execution = JobLauncher.run(
	    		exportUserJob(),params);
	       
	    
   	}
	}