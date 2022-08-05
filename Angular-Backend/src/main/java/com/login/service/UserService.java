package com.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.domain.User;
import com.login.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User getUserInfo(String userId, String passwd) {
		
		return userRepository.findByIdAndPasswd(userId, passwd);
//		return null;
	}
}
