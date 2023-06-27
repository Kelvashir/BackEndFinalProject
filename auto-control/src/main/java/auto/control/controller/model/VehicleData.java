package auto.control.controller.model;

import auto.control.entity.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleData {

	private Long vehicleId;
	private String vehicleYear;
	private String vehicleMake;
	private String vehicleModel;
	private String vehicleEngine;
	private Long vehicleMileage;

	public VehicleData(Vehicle vehicle) {
		this.vehicleId = vehicle.getVehicleId();
		this.vehicleYear = vehicle.getVehicleYear();
		this.vehicleMake = vehicle.getVehicleMake();
		this.vehicleModel = vehicle.getVehicleModel();
		this.vehicleEngine = vehicle.getVehicleEngine();
		this.vehicleMileage = vehicle.getVehicleMileage();

	}

}
