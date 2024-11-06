package springthymeleaf.Model;

import java.io.Serializable;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	@Column(name = "category_name", length = 200, columnDefinition = "nvarchar(200) not null")
	private String name;
	@Column(name = "action", length = 200, columnDefinition = "nvarchar(200)")
	private String action;
	private Boolean IsEdit;
}
