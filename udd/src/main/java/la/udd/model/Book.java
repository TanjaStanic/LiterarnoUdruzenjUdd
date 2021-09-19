package la.udd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


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
	private String filename;
	
	@Column 
	private String title;
	
	@Column
	private String isbn;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="writer_id")
	private User writer;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="editor_id")
	private User editor;
	
	@Column private String status;
		  
	@Column private String synopsis;
	
	@Column private Boolean isOpenAccess;
	
	@Column private Boolean activated;
	
	@Column private String genre;
	
	
	
	
}
