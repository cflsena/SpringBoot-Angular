package com.example.dev.backend.api.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dev.backend.api.entity.UserEntity;
import com.example.dev.backend.api.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserEntity> userOptional = UserRepository.findByEmail(email);
		UserEntity userEntity = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválidos"));
		return new User(userEntity.getName(), userEntity.getPassword(), this.getPermissions(userEntity));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserEntity userEntity) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		userEntity.getPermissions()
				.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getDescription())));
		return authorities;
	}
}
