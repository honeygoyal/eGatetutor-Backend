package com.tutor.egate.service;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutor.egate.model.User;
import com.tutor.egate.repo.UserRepository;


@Service
@Transactional
public class StorageService {
	@Autowired UserRepository userRepository;
	
	public User save (User user) {
		return userRepository.save(user);
	}

}
