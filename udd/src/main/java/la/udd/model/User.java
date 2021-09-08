package la.udd.model;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column 
	private String username;

	@Column 
	private String password;

	@Column(name="first_name") 
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column 
	private String email;

	@Column 
	private String city;

	@Column 
	private String country;

	@Column 
	private Boolean activated;
	  
	@Column 
	private Boolean confirmed;

	@Column 
	private int points;
	
	@Column
	private Role role;
	
	  // Genres readers are interested in
	  @JsonIgnore
	  @ManyToMany(
	      cascade = {CascadeType.ALL},
	      fetch = FetchType.LAZY)
	  @JoinTable(
	      name = "reader_genres",
	      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	      inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
	  private Collection<Genre> genres;

	  // Genres beta-readers are interested in
	  @JsonIgnore
	  @ManyToMany(
	      cascade = {CascadeType.ALL},
	      fetch = FetchType.LAZY)
	  @JoinTable(
	      name = "beta_genres",
	      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	      inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
	  private Collection<Genre> betaGenres;

	  // Books writers have contributed to/authored
	    @JsonIgnore
	  @ManyToMany(
	      cascade = {CascadeType.ALL},
	      fetch = FetchType.LAZY)
	  @JoinTable(
	      name = "writers_books",
	      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	      inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
	  private Collection<Book> books;
	
	public User() {
		    activated = false;
		    confirmed = false;
		    this.points = 0;
	}
}
