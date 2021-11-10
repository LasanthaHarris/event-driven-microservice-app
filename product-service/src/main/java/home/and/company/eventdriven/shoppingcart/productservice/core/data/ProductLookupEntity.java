package home.and.company.eventdriven.shoppingcart.productservice.core.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products_lookup")
@Data
public class ProductLookupEntity implements Serializable {

	private static final long serialVersionUID = 438319959096023625L;

	@Id
	@Column(unique = true)
	private String productId;
	
	@Column(unique = true)
	private String title;
	

}
