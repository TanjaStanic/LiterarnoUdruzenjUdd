package la.udd.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import la.udd.model.Role;
import la.udd.model.User;
import la.udd.repository.UserRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
	    if (user == null) {
	      return new org.springframework.security.core.userdetails.User(
	          " ", " ", true, true, true, true, new ArrayList<>());
	    }
	    
	    return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
	            user.getActivated(),
	            true,
	            true,
	            true,
	            getAuthorities(user.getRole()));
	  }
	
	
	
	private Collection<? extends GrantedAuthority> getAuthorities(Role role) {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));

		return authorities;
  }
}
