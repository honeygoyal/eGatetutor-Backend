package com.tutor.egate.controller;


import com.tutor.egate.exception.TestExistException;
import com.tutor.egate.model.*;
import com.tutor.egate.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tutor.egate.service.TestService;
import javax.validation.Valid;

@RestController
@RequestMapping("/tests")
public class TestController {

	@Autowired
	private TestService testService;
	@Autowired
	private Environment env;

	@GetMapping("/getTest")
	public ResponseEntity<TestResponseModel[]> getTest(@RequestParam("service") String service,@RequestParam("email")String email) {
		TestResponseModel[] test;
		test = testService.getTest(service,email);
		return ResponseEntity.status(HttpStatus.OK).body(test);
	}
	@GetMapping("/getAllExam")
	public ResponseEntity<ExamReference[]> getExamReference(){
		return ResponseEntity.status(HttpStatus.OK).body(testService.getExamReference());
	}

	@PostMapping("/createTest")
	public ResponseEntity<String> createTest(@Valid @RequestBody Test testdetails) {
		try {

			testService.createTest(testdetails);
		} catch (TestExistException ex) {
			return  new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<String>("DONE", HttpStatus.OK);
	}
	}
