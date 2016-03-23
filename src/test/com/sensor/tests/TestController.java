package com.sensor.tests;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sensor.domain.Alert;
import com.sensor.domain.Metric;

/**
 * This class is used to test all methods defined in SensorController.
 * It also tests the integration of controller with API and DAO classes too.
 * 
 * MicroServices are to be started, before executing below test cases.
 */

@SpringBootApplication
@Configuration
@ComponentScan("com.sensor.dao , com.sensor.service , com.sensor.rules")
@ImportResource({"/applicationContext-dao.xml","/applicationContext-rules.xml"})
public class TestController {

	static RestTemplate restTemplate = new TestRestTemplate();
	
	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = SpringApplication.run(TestController.class, args);
		
		testCreate();
		testMetricRead();
		testAlertRead() ;
		testMetricByRange();
		testAlertByRange();
		
		SpringApplication.exit(ctx, getExitGenerator());
	}

	public static void testCreate() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();  
        headers.setContentType( MediaType.APPLICATION_JSON );  
        String json = "{\"timeStamp\": \"" + String.valueOf(System.currentTimeMillis()) + "\", \"value\": \"" + 20 + "\",\"baseWeight\": \"" + 10 + "\"}";
        System.out.println("json--->"+json);
        HttpEntity request= new HttpEntity( json, headers );
        Metric result = restTemplate.postForObject("http://localhost:1111/sensor/create",request, Metric.class);
		System.out.println("testCreate output : "+result);
	}
	
	public static void testMetricRead() throws Exception {
        
		String result = restTemplate.getForObject("http://localhost:1111/sensor/metrics", String.class);
		System.out.println("testMetricRead output : "+result);
		
	}
	
	public static void testAlertRead() throws Exception {
		String result = restTemplate.getForObject("http://localhost:1111/sensor/alerts", String.class);
		System.out.println("testAlertRead output : "+result);
		
	}
	
	public static void testMetricByRange() throws Exception {
		HttpHeaders headers = new HttpHeaders();  
        headers.setContentType( MediaType.APPLICATION_JSON );  
       
        String json = "{\"startTime\": \"" + 20 + "\", \"endTime\": \"" + 70 + "\"}";
        System.out.println("json--->"+json);
        HttpEntity request= new HttpEntity( json, headers );
        
        ResponseEntity<Metric[]> responseEntity = restTemplate.postForEntity("http://localhost:1111/sensor/metrics/range", request,Metric[].class);
        
		System.out.println("testMetricByRange output : "+responseEntity);
		
	}
	
	public static void testAlertByRange() throws Exception {
		
		HttpHeaders headers = new HttpHeaders();  
        headers.setContentType( MediaType.APPLICATION_JSON );  
       
        String json = "{\"startTime\": \"" + 20 + "\", \"endTime\": \"" + 70 + "\"}";
        System.out.println("json--->"+json);
        HttpEntity request= new HttpEntity( json, headers );
        
        ResponseEntity<Alert[]> responseEntity = restTemplate.postForEntity("http://localhost:1111/sensor/alerts/range", request,Alert[].class);
        
		System.out.println("testAlertByRange output : "+responseEntity);
		
	}
	
	public static ExitCodeGenerator getExitGenerator(){
		  ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {
		    @Override
		    public int getExitCode() {
		      return 0;
		    }
		  };
		  return exitCodeGenerator;
	}
}
