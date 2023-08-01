package com.esercizio5_spring.esercizio5_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizio5_spring.esercizio5_spring.model.User;

@Service
public class UserService {

	@Autowired
	private List<User> users;

	public User save(User u) {

		users.add(u);
		for (User us : users) {
			if (us.getId() == u.getId()) {
				return us;
			}
		}
		return null;
	}

	public List<User> findAll() {
		return users;
	}

	public Optional<User> findById(Long id) {
		for (User u : users) {
			if (u.getId().equals(id)) {
				return Optional.ofNullable(u);
			}
		}
		return Optional.ofNullable(null);
	}

}
