package la.udd.elastic;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName=BookUnit.INDEX_NAME, shards = 1, replicas = 0)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookUnit {

	public static final String INDEX_NAME = "booklibrary";
	public static final String TYPE_NAME = "book";

	@Id
	@Field(type = FieldType.Text, store = true)
    private Long id;
	
	@Field(type = FieldType.Text)
    private String filename;
	
	@Field(type = FieldType.Text, analyzer="serbian",store = true)
    private String title;
		
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
	private String writer;
	
	@Field(type = FieldType.Text,analyzer="serbian", store = true)
	private String genre;
	
	@Field(type = FieldType.Boolean, store = true)
    private Boolean openaccess;
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
    private String content;

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Boolean getOpenaccess() {
		return openaccess;
	}

	public void setOpenaccess(Boolean openaccess) {
		this.openaccess = openaccess;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static String getIndexName() {
		return INDEX_NAME;
	}

	public static String getTypeName() {
		return TYPE_NAME;
	}

	public BookUnit(Long id, String filename, String title, String writer, String genre, Boolean openaccess,
			String content) {
		super();
		this.id = id;
		this.filename = filename;
		this.title = title;
		this.writer = writer;
		this.genre = genre;
		this.openaccess = openaccess;
		this.content = content;
	}

	public BookUnit() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
	
}
