package com.test.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.backend.model.User;
import com.test.backend.service.UserRoleService;
import com.test.backend.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User appUser = this.userService.findUserByUserName(userName);

		if (appUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}

		List<String> roleNames = this.userRoleService.findAllRolesForUsername(userName);

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
				appUser.getUserName(), //
				appUser.getEncryptedPassword(), grantList);

		return userDetails;
	}

}
