package la.udd.dto;

import la.udd.elastic.BookUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicQueryResponseDTO {

	private BookUnit bookUnit;
	private String higlights;
}
