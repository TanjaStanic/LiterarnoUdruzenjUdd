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
	private String genre;

	@Column 
	private Boolean activated;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;
	
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
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Book> getWriterBooks() {
		return writerBooks;
	}

	public void setWriterBooks(List<Book> writerBooks) {
		this.writerBooks = writerBooks;
	}

	public List<Book> getEditorBooks() {
		return editorBooks;
	}

	public void setEditorBooks(List<Book> editorBooks) {
		this.editorBooks = editorBooks;
	}
	
	
}
