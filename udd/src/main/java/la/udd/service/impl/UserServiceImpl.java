package la.udd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import la.udd.model.Role;
import la.udd.model.User;
import la.udd.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getBetaReaders(){
		List<User> users = new ArrayList<User>();
		List<User> retVal = new ArrayList<User>();
		users = userRepository.findAll();
		
		for (User u : users) {
			if (u.getRole().equals(Role.BETA_READER)) {
				retVal.add(u);
			}
		}
		return retVal;
	}
}
