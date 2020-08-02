package com.tutor.egate.service;

import com.tutor.egate.exception.TestExistException;
import com.tutor.egate.model.ExamReference;
import com.tutor.egate.model.Test;
import com.tutor.egate.model.TestResponseModel;

public interface TestService {
	TestResponseModel[]  getTest(String service,String email);
	ExamReference[] getExamReference();
	void createTest(Test test) throws TestExistException;
}
