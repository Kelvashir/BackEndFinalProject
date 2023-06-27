package auto.control.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class RepairShop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long repairShopId;

	private String repairShopName;
	private String repairShopStreetAddress;
	private String repairShopCity;
	private String repairShopState;
	private String repairShopZip;
	private String repairShopPhone;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "repairShops", cascade = CascadeType.PERSIST)
	private Set<VehicleOwner> vehicleOwner = new HashSet<>();

}
