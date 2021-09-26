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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public GeoPoint getLocation() {
		return location;
	}

	public void setLocation(GeoPoint location) {
		this.location = location;
	}

	public static String getIndexName() {
		return INDEX_NAME;
	}

	public static String getTypeName() {
		return TYPE_NAME;
	}

	public static String getDatePattern() {
		return DATE_PATTERN;
	}

	public BetaReader(Long id, String firstName, String lastName, String email, String username, String city,
			String country, String genre, GeoPoint location) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.city = city;
		this.country = country;
		this.genre = genre;
		this.location = location;
	}
	
	

}
