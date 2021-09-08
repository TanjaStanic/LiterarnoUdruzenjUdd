package la.udd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column private String name;

	//Books belonging to a genre
	@ManyToMany(
			cascade = {CascadeType.ALL},
			fetch = FetchType.EAGER)
	@JoinTable(
			name = "genre_books",
			joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")})
	private Collection<Book> books;
}
