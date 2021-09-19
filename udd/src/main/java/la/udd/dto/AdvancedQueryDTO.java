package la.udd.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdvancedQueryDTO {

	private String operation;
	
	private String field;
	
	private String query;
	
	private boolean phrase;
}
