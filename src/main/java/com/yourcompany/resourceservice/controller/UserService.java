package com.yourcompany.resourceservice.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public UserDTO getUserById(Long id) {
		User u = userDAO.findById(id);
		if (u == null)
			return null;
		UserDTO dto = new UserDTO();
		// dto.setUserId(u.getUserId());
		dto.setUsername(u.getUsername());
		dto.setEmail(u.getEmail());
		return dto;
	}

	public List<UserDTO> getAllUsers() {
		List<User> list = userDAO.findAll();
		List<UserDTO> out = new ArrayList<>();
		if (list != null) {
			for (User u : list) {
				UserDTO dto = new UserDTO();
				// dto.setUserId(u.getUserId());
				dto.setUsername(u.getUsername());
				dto.setEmail(u.getEmail());
				out.add(dto);
			}
		}
		return out;
	}

	public ResponseStatusDTO updateUser(Long id, UserDTO dto) {
		return userDAO.updateUser(id, dto.getUsername(), dto.getEmail());
	}

	public ResponseStatusDTO deleteUser(Long id) {
		return userDAO.deleteUser(id);
	}
}
