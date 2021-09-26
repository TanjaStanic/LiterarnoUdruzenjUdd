package la.udd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BetaReaderDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String city;
	private String country;
	private String genre;

}
