package com.tutor.egate.repo;

import com.tutor.egate.model.UserAnswer;
import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepo extends CrudRepository<UserAnswer,Long> {
}
