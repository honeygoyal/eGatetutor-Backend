package com.tutor.egate.repo;

import com.tutor.egate.model.Question;
import com.tutor.egate.model.QuestionCompositeKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, QuestionCompositeKey> {
    /*
    TODO: Add Group by and Order by Condition for Sorting in Question Label
     */
    @Query(value = "SELECT * FROM exam e WHERE e.test_id=:test_id",nativeQuery = true)
    List<Question> findQuestionsById(@Param("test_id") String test_id);

}
