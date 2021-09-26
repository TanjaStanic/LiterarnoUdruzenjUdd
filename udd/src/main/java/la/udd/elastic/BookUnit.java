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


	
	
	
	
}
