package com.tutor.egate.service;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.tutor.egate.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import com.tutor.egate.exception.UserExistsException;

public interface UsersService {
	UserDto createUser(UserDto userDto) throws  UserExistsException, AddressException, MessagingException, IOException;

	UserDetails loadUserByUsername(String username);


}
