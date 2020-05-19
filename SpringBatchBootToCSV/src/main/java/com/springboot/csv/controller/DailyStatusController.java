package com.springboot.csv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springboot.csv.model.StatusInfo;
import com.springboot.csv.service.StatusService;

@RestController
@Service
@RequestMapping("/statusInfo")
public class DailyStatusController {
	
	@Autowired
	StatusService empService;
	
	
	@RequestMapping(value = "/inser", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
	public Boolean addorgs(@RequestBody StatusInfo empDet, UriComponentsBuilder builder) {
        boolean flag = empService.addStatusDet(empDet);
		
        System.out.println("------"+flag);
        if (flag == false) {
        	//return CustomCommonException.checkBoolen(flag); 
        	//return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/orgs/{id}").buildAndExpand(empDet.getEmpid()).toUri());
       // return addorgs(organization, builder);
        if(flag==true) {
        	
        }
        return flag;
       // return CustomCommonException.checkBoolen(flag);
	}

}
