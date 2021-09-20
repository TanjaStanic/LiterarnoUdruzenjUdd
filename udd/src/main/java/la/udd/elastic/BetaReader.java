package la.udd.elastic;

import org.springframework.data.elasticsearch.annotations.Document;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(indexName = BetaReader.INDEX_NAME,shards = 1, replicas = 0)
@Getter
@Setter
@NoArgsConstructor
public class BetaReader {
	public static final String INDEX_NAME = "betareaderlibrary";
	public static final String TYPE_NAME = "betareader";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
	private List<String> areas = new ArrayList<String>();
	
	@Id
	@Field(type = FieldType.Text, store = true)
    private Long id;
	
	@Field(type = FieldType.Text, store = true)
	private String username;
	
	@GeoPointField
	private GeoPoint location;
	
	@Field(type = FieldType.Text, analyzer="serbian", store = true)
	private List<String> genres = new ArrayList<String>();
}
