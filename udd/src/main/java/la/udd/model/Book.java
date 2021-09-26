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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Boolean getIsOpenAccess() {
		return isOpenAccess;
	}

	public void setIsOpenAccess(Boolean isOpenAccess) {
		this.isOpenAccess = isOpenAccess;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	
}
