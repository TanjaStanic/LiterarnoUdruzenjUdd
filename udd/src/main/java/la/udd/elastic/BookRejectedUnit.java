package la.udd.elastic;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName=BookUnit.INDEX_NAME, shards = 1, replicas = 0)
@Getter
@Setter
@NoArgsConstructor
public class BookRejectedUnit {
	
	public static final String INDEX_NAME = "digitallibraryrejected";
	public static final String TYPE_NAME = "bookrejected";
	
	@Id
	@Field(type = FieldType.Text, store = true)
    private Long id;
	
	@Field(type = FieldType.Text, analyzer="serbian",store = true)
    private String title;
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
    private String booktitle;
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
	private String author;
	
	@Field(type = FieldType.Text, analyzer="serbian",store = true)
    private String keywords;
	
	@Field(type = FieldType.Text,analyzer="serbian", store = true)
	private String genre;
	
	@Field(type = FieldType.Boolean, store = true)
    private Boolean status;
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
    private String content;
}
