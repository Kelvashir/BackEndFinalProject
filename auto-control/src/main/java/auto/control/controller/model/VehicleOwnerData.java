package auto.control.controller.model;

import java.util.HashSet;
import java.util.Set;

import auto.control.entity.RepairShop;
import auto.control.entity.Vehicle;
import auto.control.entity.VehicleOwner;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleOwnerData {

	private Long vehicleOwnerId;
	private String vehicleOwnerFirstName;
	private String vehicleOwnerLastName;
	private String vehicleOwnerPhone;
	private String vehicleOwnerEmail;
	private Set<RepairShopData> repairShops = new HashSet<>();
	private Set<VehicleData> vehicles = new HashSet<>();

	public VehicleOwnerData(VehicleOwner vehicleOwner) {
		this.vehicleOwnerId = vehicleOwner.getVehicleOwnerId();
		this.vehicleOwnerFirstName = vehicleOwner.getVehicleOwnerFirstName();
		this.vehicleOwnerLastName = vehicleOwner.getVehicleOwnerLastName();
		this.vehicleOwnerPhone = vehicleOwner.getVehicleOwnerPhone();
		this.vehicleOwnerEmail = vehicleOwner.getVehicleOwnerEmail();

		for (RepairShop repairShop : vehicleOwner.getRepairShops()) {
			this.repairShops.add(new RepairShopData(repairShop));
		}

		for (Vehicle vehicle : vehicleOwner.getVehicles()) {
			this.vehicles.add(new VehicleData(vehicle));
		}
	}

}
