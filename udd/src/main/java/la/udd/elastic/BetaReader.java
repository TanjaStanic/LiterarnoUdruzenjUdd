package la.udd.elastic;

import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.Id;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName = BetaReader.INDEX_NAME,shards = 1, replicas = 0)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetaReader {
	public static final String INDEX_NAME = "betareaderlibrary";
	public static final String TYPE_NAME = "betareader";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
		
	@Id
	@Field(type = FieldType.Text, store = true)
    private Long id;
	
	@Field(type = FieldType.Text, store = true)
	private String firstName;
	
	@Field(type = FieldType.Text, store = true)
	private String lastName;
	
	@Field(type = FieldType.Text, store = true)
	private String email;
	
	@Field(type = FieldType.Text, store = true)
	private String username;
	
	@Field(type = FieldType.Text, store = true)
	private String city;
	
	@Field(type = FieldType.Text, store = true)
	private String country;
	
	@Field(type = FieldType.Text, store = true)
	private String genre;
	
	@GeoPointField
	private GeoPoint location;

}
