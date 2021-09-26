package la.udd.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import la.udd.config.JwtTokenUtils;
import la.udd.model.User;
import la.udd.model.auth.JwtAuthenticationRequest;
import la.udd.repository.UserRepository;
@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	
	@Lazy @Autowired AuthenticationManager authenticationManager;
	
	@Autowired 
	JwtTokenUtils jwtTokenUtils;

	@Autowired 
	UserRepository userRepository;
	
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody @Valid JwtAuthenticationRequest request)
			throws AuthenticationException, IOException {

		System.out.println(request.getUsername() + " " + request.getPassword());
		try {
		Authentication authenticate =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
	                  request.getUsername(), 
	                  request.getPassword()
	            ));

	      SecurityContextHolder.getContext().setAuthentication(authenticate);
	      Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	      User user = userRepository.findByUsername(loggedInUser.getName());
	      System.out.println("Token: " + jwtTokenUtils.generateToken(user.getUsername()));
	      return new ResponseEntity<>(
	          jwtTokenUtils.generateToken(user.getUsername()), HttpStatus.OK);
		
		} catch (Exception exception){
		      System.out.println("heree");

	        return (ResponseEntity<?>) ResponseEntity.badRequest().body(exception.getMessage());
	    }
	
	}
	
	
	@RequestMapping(value = "/userprofile/{token}", method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProfile(@PathVariable("token") String token) {

		System.out.println("IMA TOKEN: " + token);
		String username = jwtTokenUtils.getUsername(token);
		
		System.out.println("USERNAME: " + username);
	    User user = (User) this.userRepository.findByUsername(username);
	    
	    System.out.println("Korisnik: " + user.getEmail());
	    		
		return  new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@RequestMapping(value="/logout", method = RequestMethod.GET,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
			public void logOutUser( HttpServletRequest request){
		
				System.out.println("Logout u auth-service");
								 
				SecurityContextHolder.clearContext();
			}
}
