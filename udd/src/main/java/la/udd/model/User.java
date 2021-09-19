package la.udd.model;

import java.util.List;

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
public class User  {

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
	
	@JsonIgnore
    @OneToMany(mappedBy="writer")
    protected List<Book> writerBooks;
	
	@JsonIgnore
    @OneToMany(mappedBy="editor")
    protected List<Book> editorBooks;
	
	public User() {
		    activated = false;
		    confirmed = false;
		    this.points = 0;
	}
}
