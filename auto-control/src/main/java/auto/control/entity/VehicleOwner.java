package auto.control.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class VehicleOwner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleOwnerId;

	private String vehicleOwnerFirstName;
	private String vehicleOwnerLastName;
	private String vehicleOwnerPhone;
	private String vehicleOwnerEmail;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "vehicle_owner_repair_shop", joinColumns = @JoinColumn(name = "vehicle_owner_id"), inverseJoinColumns = @JoinColumn(name = "repair_shop_id"))
	private Set<RepairShop> repairShops = new HashSet<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "vehicleOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Vehicle> vehicles = new HashSet<>();

}
