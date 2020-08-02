package com.tutor.egate.repo;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tutor.egate.model.Test;

public interface TestRepository extends CrudRepository<Test, String> {

//	@Query(value = "SELECT test.duration,test.test_title,test.test_subtitle,test.total_marks,test.test_id,test.total_questions,test.exam_ref,user2test.status,user2test.user_id FROM test  inner join user2test on test.id=user2test.test_id where user_id=:email and exam_ref=:id")
//	@Query("SELECT new com.tutor.egate.model.TestResponseModel(u.duration,u.test_Title,u.test_subtitle,u.total_marks,u.total_questions,t.status,t.user_id) FROM  Test u  LEFT JOIN u.user2test  t   ")
	@Query(value="SELECT * FROM test where exam_ref=:id",nativeQuery=true)
	Test[] findTestByExamRef(@Param("id")Long id);

}

