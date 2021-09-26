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
public class QueryResponseDTO {

	private BookUnit bookUnit;
	private String highlights;
	public BookUnit getBookUnit() {
		return bookUnit;
	}
	public void setBookUnit(BookUnit bookUnit) {
		this.bookUnit = bookUnit;
	}
	public String getHighlights() {
		return highlights;
	}
	public void setHighlights(String highlights) {
		this.highlights = highlights;
	}
	public QueryResponseDTO(BookUnit bookUnit, String highlights) {
		super();
		this.bookUnit = bookUnit;
		this.highlights = highlights;
	}
	public QueryResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
				   
}
