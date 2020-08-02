package com.tutor.egate.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tutor.egate.model.User2test;

public interface User2TestRepository extends CrudRepository<User2test, Long> {

	@Query(value = "Select * from user2test where user_id=:email and test_id=:test_id",nativeQuery=true)
	User2test findstatusByUserIdandTestId(@Param("email")String email,@Param("test_id") String test_id);

}
