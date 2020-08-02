package com.tutor.egate.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.tutor.egate.exception.UserExistsException;
import com.tutor.egate.model.User;
import org.springframework.core.env.Environment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tutor.egate.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private com.tutor.egate.repo.UserRepository userRepository;
	@Autowired
	private Environment env;
	@Autowired
	public UsersServiceImpl(com.tutor.egate.repo.UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;

	}

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}

		return builder.toString();
	}

	private void sendmail(UserDto userDetails, String password)
			throws AddressException, MessagingException, IOException {
			try {
				Properties props = System.getProperties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.host", env.getProperty("spring.mail.host"));
				props.put("mail.port", env.getProperty("spring.mail.port"));
				props.setProperty("mail.transport.protocol", "smtp");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(env.getProperty("spring.mail.username"), env.getProperty("spring.mail.password"));
					}
				});
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress(env.getProperty("spring.mail.username"), false));
				String email = userDetails.getEmail();

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				msg.setSubject("eGATE Tutor Login Credential");
				String message ="Dear GATE aspirant, <br/>" +
						"<br/>" +
						"Congratulations!!! <br/>" +
						"You have successfully registered eGATE Tutor. <br/>" +
						"\r\n" +
						"Login Credentials: <br/>" +
						"User Name  :    " + email + "<br/>" +
						"Password    :    " + password + "<br/>" +
						"Click here to Login <br/>" +
						"OR www.egatetutor.in/ <br/>" +
						"<br/>" +
						"Note: <br/>" +
						"Visit FAQs section on www.egatetutor.in <br/>" +
						"Admission issues related Queries: support@egatetutor.in <br/>" +
						"Technical Issues related Queries: egatetutor@gmail.com <br/>" +
						"(In case of queries call between 10:00 AM to 6:00 PM. Mon - Sun) <br/>" +
						"For more information regarding GATE, iPATE, PSU prepration. Connect with us:\n" +
						"Website: http://www.egatetutor.in/ <br/>" +
						"Facebook: https://www.facebook.com/egate.tutor.18 <br/>" +
						"Instagram: https://www.instagram.com/egatetutor/ <br/>" +
						"eGATETutor<br/>" +
						"Support Team eGATETutor";

				msg.setContent(message, "text/html");

				msg.setSentDate(new Date());
				Transport transport = session.getTransport("smtp");
				transport.send(msg, msg.getRecipients(Message.RecipientType.TO));
				transport.close();
			}catch (Exception e){
				System.out.println("Failed to send Email : " + e.getMessage() +" "+ e);
			}
	}

	@Override
	public UserDto createUser(UserDto userDetails)
			throws UserExistsException, AddressException, MessagingException, IOException {
		String password = randomAlphaNumeric(8);
		System.out.println(password);
		userDetails.setPassword(bCryptPasswordEncoder.encode(password));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User existingUser = userRepository.findByEmail(userDetails.getEmail());
		if (existingUser != null) {
			throw new UserExistsException("User already exists with this userId");
		}
		User userEntity = modelMapper.map(userDetails, User.class);

		userRepository.save(userEntity);
		sendmail(userDetails, password);

		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User userEntity = userRepository.findByEmail(username);
		if (userEntity == null)
			throw new UsernameNotFoundException(username);
		ArrayList grantedAuth = new ArrayList();
		return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
				true, true, true, true, grantedAuth);
	}

}
