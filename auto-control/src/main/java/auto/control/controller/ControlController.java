package auto.control.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import auto.control.controller.model.RepairShopData;
import auto.control.controller.model.VehicleData;
import auto.control.controller.model.VehicleOwnerData;
import auto.control.service.ControlService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auto_control")
@Slf4j
public class ControlController {

	@Autowired
	private ControlService controlService;

	// CREATE - Vehicle Owner
	@PostMapping("/vehicleOwner")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VehicleOwnerData createVehicleOwner(@RequestBody VehicleOwnerData vehicleOwnerData) {
		log.info("Creating vehicle owner {}", vehicleOwnerData);
		return controlService.saveVehicleOwner(vehicleOwnerData);
	}

	// READ - All Vehicle Owners
	@GetMapping("/vehicleOwner")
	public List<VehicleOwnerData> retrieveAllVehicleOwners() {
		log.info("Retrieving all vehicle owners");
		return controlService.retrieveAllVehicleOwners();
	}

	// READ - Vehicle Owner Using Vehicle Owner Id
	@GetMapping("/vehicleOwner/{vehicleOwnerId}")
	public VehicleOwnerData retrieveVehicleOwnerUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId) {
		log.info("Retrieving vehicle owner with ID={}", vehicleOwnerId);
		return controlService.retrieveVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
	}

	// UPDATE - Vehicle Owner Using Vehicle Owner Id
	@PutMapping("/vehicleOwner/{vehicleOwnerId}")
	public VehicleOwnerData updateVehicleOwnerUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId,
			@RequestBody VehicleOwnerData vehicleOwnerData) {
		vehicleOwnerData.setVehicleOwnerId(vehicleOwnerId);
		log.info("Updating vehicle owner {}", vehicleOwnerId);
		return controlService.saveVehicleOwner(vehicleOwnerData);
	}

	// DELETE - Vehicle Owner Using Vehicle Owner Id
	@DeleteMapping("/vehicleOwner/{vehicleOwnerId}")
	public Map<String, String> deleteVehicleOwnerUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId) {
		log.info("Deleting vehicle owner with ID=" + vehicleOwnerId + ".");
		controlService.deleteVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);

		return Map.of("message", "Vehicle owner with ID=" + vehicleOwnerId + " was deleted.");
	}

	// CREATE - Vehicle Using Vehicle Owner Id
	@PostMapping("/vehicleOwner/{vehicleOwnerId}/vehicle")
	@ResponseStatus(code = HttpStatus.CREATED)
	public VehicleData createVehicleUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId,
			@RequestBody VehicleData vehicleData) {
		log.info("Inserting vehicle {} for vehicle owner with ID={}", vehicleData, vehicleOwnerId);
		return controlService.saveVehicleUsingVehicleOwnerId(vehicleOwnerId, vehicleData);
	}

	// READ - All Vehicles For Specific Vehicle Owner
	@GetMapping("/vehicleOwner/{vehicleOwnerId}/vehicle")
	public List<VehicleData> retrieveAllVehiclesUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId) {
		log.info("Retrieving all vehicles for owner with ID={}", vehicleOwnerId);
		return controlService.retrieveAllVehiclesUsingVehicleOwnerId(vehicleOwnerId);
	}

	// UPDATE - Vehicle Using Vehicle Id Only
	@PutMapping("/vehicle/{vehicleId}")
	public VehicleData updateVehicleByVehicleIdOnly(@PathVariable Long vehicleId,
			@RequestBody VehicleData vehicleData) {
		vehicleData.setVehicleId(vehicleId);
		log.info("Updating vehicle {}", vehicleId);
		return controlService.saveVehicleByVehicleIdOnly(vehicleId, vehicleData);
	}

	// DELETE - Vehicle Using Vehicle Id Only
	@DeleteMapping("/vehicle/{vehicleId}")
	public Map<String, String> deleteVehicleByVehicleIdOnly(@PathVariable Long vehicleId) {
		log.info("Deleting vehicle with ID=" + vehicleId + ".");
		controlService.deleteVehicleByVehicleIdOnly(vehicleId);

		return Map.of("message", "Vehicle with ID=" + vehicleId + " was deleted.");
	}

	// CREATE - Repair Shop
	@PostMapping("/repairShop")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RepairShopData createRepairShop(@RequestBody RepairShopData repairShopData) {
		log.info("Creating repair shop {}", repairShopData);
		return controlService.saveRepairShop(repairShopData);
	}

	// READ - All Repair Shops
	@GetMapping("/repairShop")
	public List<RepairShopData> retrieveAllRepairShops() {
		log.info("Retrieving all repair shops.");
		return controlService.retrieveAllRepairShops();
	}

	// READ - All Repair Shops Using Vehicle Owner Id
	@GetMapping("/vehicleOwner/{vehicleOwnerId}/repairShop")
	public List<RepairShopData> retrieveAllRepairShopsUsingVehicleOwnerId(@PathVariable Long vehicleOwnerId) {
		log.info("Retrieving all repair shops for vehicle owner with ID={}.", vehicleOwnerId);
		return controlService.retrieveAllRepairShopsUsingVehicleOwnerId(vehicleOwnerId);

	}
}
