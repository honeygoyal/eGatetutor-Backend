package com.tutor.egate.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tutor.egate.model.ExamReference;

import java.util.List;

public interface ExamReferenceRepository extends CrudRepository<ExamReference, Long> {

	@Query(value = "SELECT * FROM exam_Reference e where e.exam_code = ?1",nativeQuery = true)
	ExamReference findByexam_code(String service);

	@Query(value = "SELECT * FROM exam_Reference",nativeQuery = true)
	ExamReference[] findAllExam();
}
