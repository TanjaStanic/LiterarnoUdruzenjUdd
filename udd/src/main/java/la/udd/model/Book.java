package la.udd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column 
	private String title;
	
	@Column(unique = true)
	private String isbn;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "books")
	private List<User> writers = new ArrayList<>();
	
	@Column private String keyTerms;
	
	@Column private String publisher;
	
	@Column private String yearPublished;

	@Column private String placePublished;
	
	@Column private String pages;
	  
	@Column private String synopsis;
}
