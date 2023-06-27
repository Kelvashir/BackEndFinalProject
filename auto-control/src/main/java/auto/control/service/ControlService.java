package auto.control.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auto.control.controller.model.RepairShopData;
import auto.control.controller.model.VehicleData;
import auto.control.controller.model.VehicleOwnerData;
import auto.control.dao.RepairShopDao;
import auto.control.dao.VehicleDao;
import auto.control.dao.VehicleOwnerDao;
import auto.control.entity.RepairShop;
import auto.control.entity.Vehicle;
import auto.control.entity.VehicleOwner;

@Service
public class ControlService {

	@Autowired
	private VehicleOwnerDao vehicleOwnerDao;

	@Autowired
	private VehicleDao vehicleDao;

	@Autowired
	private RepairShopDao repairShopDao;

	// POST / PUT - Vehicle Owner
	@Transactional(readOnly = false)
	public VehicleOwnerData saveVehicleOwner(VehicleOwnerData vehicleOwnerData) {
		Long vehicleOwnerId = vehicleOwnerData.getVehicleOwnerId();
		VehicleOwner vehicleOwner = findOrCreateVehicleOwner(vehicleOwnerId);
		copyVehicleOwnerFields(vehicleOwner, vehicleOwnerData);

		return new VehicleOwnerData(vehicleOwnerDao.save(vehicleOwner));
	}

	// GET - All Vehicle Owners
	@Transactional(readOnly = true)
	public List<VehicleOwnerData> retrieveAllVehicleOwners() {
		return vehicleOwnerDao.findAll().stream().map(VehicleOwnerData::new).toList();
	}

	// GET - Specific Vehicle Owner Using Vehicle Owner Id
	@Transactional(readOnly = true)
	public VehicleOwnerData retrieveVehicleOwnerUsingVehicleOwnerId(Long vehicleOwnerId) {
		VehicleOwner vehicleOwner = findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		return new VehicleOwnerData(vehicleOwner);

	}

	// DELETE - Specific Vehicle Owner Using Vehicle Owner Id
	@Transactional(readOnly = false)
	public void deleteVehicleOwnerUsingVehicleOwnerId(Long vehicleOwnerId) {
		VehicleOwner vehicleOwner = findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		vehicleOwnerDao.delete(vehicleOwner);
	}

	private VehicleOwner findOrCreateVehicleOwner(Long vehicleOwnerId) {

		if (Objects.isNull(vehicleOwnerId)) {
			return new VehicleOwner();
		} else {
			return findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		}
	}

	private VehicleOwner findVehicleOwnerUsingVehicleOwnerId(Long vehicleOwnerId) {
		return vehicleOwnerDao.findById(vehicleOwnerId).orElseThrow(
				() -> new NoSuchElementException("Vehicle owner with ID=" + vehicleOwnerId + " does not exist."));

	}

	private void copyVehicleOwnerFields(VehicleOwner vehicleOwner, VehicleOwnerData vehicleOwnerData) {
		vehicleOwner.setVehicleOwnerId(vehicleOwnerData.getVehicleOwnerId());
		vehicleOwner.setVehicleOwnerFirstName(vehicleOwnerData.getVehicleOwnerFirstName());
		vehicleOwner.setVehicleOwnerLastName(vehicleOwnerData.getVehicleOwnerLastName());
		vehicleOwner.setVehicleOwnerPhone(vehicleOwnerData.getVehicleOwnerPhone());
		vehicleOwner.setVehicleOwnerEmail(vehicleOwnerData.getVehicleOwnerEmail());

	}

	// POST / PUT - Vehicle Using Vehicle Owner Id
	@Transactional(readOnly = false)
	public VehicleData saveVehicleUsingVehicleOwnerId(Long vehicleOwnerId, VehicleData vehicleData) {
		VehicleOwner vehicleOwner = findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		Vehicle vehicle = findOrCreateVehicle(vehicleOwnerId, vehicleData);
		copyVehicleFields(vehicle, vehicleData);
		vehicle.setVehicleOwner(vehicleOwner);
		vehicleOwner.getVehicles().add(vehicle);
		Vehicle dbVehicle = vehicleDao.save(vehicle);

		return new VehicleData(dbVehicle);

	}

	private Vehicle findOrCreateVehicle(Long vehicleOwnerId, VehicleData vehicleData) {

		if (Objects.isNull(vehicleData.getVehicleId())) {
			return new Vehicle();
		} else {
			return findVehicleById(vehicleOwnerId, vehicleData.getVehicleId());
		}
	}

	private Vehicle findVehicleById(Long vehicleOwnerId, Long vehicleId) {
		Vehicle vehicle = vehicleDao.findById(vehicleId)
				.orElseThrow(() -> new NoSuchElementException("Vehicle with ID=" + vehicleId + " was not found."));

		if (vehicle.getVehicleOwner().getVehicleOwnerId() == vehicleOwnerId) {
			return vehicle;
		} else {
			throw new IllegalArgumentException(
					"Vehicle owner with ID=" + vehicleOwnerId + " does not have a vehicle with ID=" + vehicleId);
		}
	}

	private void copyVehicleFields(Vehicle vehicle, VehicleData vehicleData) {
		vehicle.setVehicleId(vehicleData.getVehicleId());
		vehicle.setVehicleYear(vehicleData.getVehicleYear());
		vehicle.setVehicleMake(vehicleData.getVehicleMake());
		vehicle.setVehicleModel(vehicleData.getVehicleModel());
		vehicle.setVehicleEngine(vehicleData.getVehicleEngine());
		vehicle.setVehicleMileage(vehicleData.getVehicleMileage());
	}

	// READ - All Vehicles Using Vehicle Owner Id
	@Transactional(readOnly = true)
	public List<VehicleData> retrieveAllVehiclesUsingVehicleOwnerId(Long vehicleOwnerId) {
		VehicleOwner vehicleOwner = findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		List<VehicleData> vehicleList = new LinkedList<>();

		for (Vehicle vehicle : vehicleOwner.getVehicles()) {
			VehicleData vd = new VehicleData(vehicle);

			vehicleList.add(vd);
		}

		return vehicleList;
	}

	// DELETE - Vehicle Using Vehicle Id Only
	@Transactional(readOnly = false)
	public void deleteVehicleByVehicleIdOnly(Long vehicleId) {
		Vehicle vehicle = findVehicleByVehicleIdOnly(vehicleId);
		vehicleDao.delete(vehicle);
	}

	private Vehicle findVehicleByVehicleIdOnly(Long vehicleId) {
		Vehicle vehicle = vehicleDao.findById(vehicleId)
				.orElseThrow(() -> new NoSuchElementException("Vehicle with ID=" + vehicleId + " was not found."));
		return vehicle;
	}

	// PUT - Vehicle Using Vehicle Id Only
	@Transactional(readOnly = false)
	public VehicleData saveVehicleByVehicleIdOnly(Long vehicleId, VehicleData vehicleData) {
		Vehicle vehicle = findVehicleByVehicleIdOnly(vehicleId);
		copyVehicleFields(vehicle, vehicleData);
		Vehicle dbVehicle = vehicleDao.save(vehicle);

		return new VehicleData(dbVehicle);
	}

	// PUT / POST - Repair Shop
	@Transactional(readOnly = false)
	public RepairShopData saveRepairShop(RepairShopData repairShopData) {
		Long repairShopId = repairShopData.getRepairShopId();
		RepairShop repairShop = findOrCreateRepairShop(repairShopId);
		copyRepairShopFields(repairShop, repairShopData);

		return new RepairShopData(repairShopDao.save(repairShop));
	}

	private RepairShop findOrCreateRepairShop(Long repairShopId) {

		if (Objects.isNull(repairShopId)) {
			return new RepairShop();
		} else {
			return findRepairShopByRepairShopId(repairShopId);
		}
	}

	private RepairShop findRepairShopByRepairShopId(Long repairShopId) {
		return repairShopDao.findById(repairShopId).orElseThrow(
				() -> new NoSuchElementException("Repair shop with ID=" + repairShopId + " does not exist."));
	}

	private void copyRepairShopFields(RepairShop repairShop, RepairShopData repairShopData) {
		repairShop.setRepairShopId(repairShopData.getRepairShopId());
		repairShop.setRepairShopName(repairShopData.getRepairShopName());
		repairShop.setRepairShopStreetAddress(repairShopData.getRepairShopStreetAddress());
		repairShop.setRepairShopCity(repairShopData.getRepairShopCity());
		repairShop.setRepairShopState(repairShopData.getRepairShopState());
		repairShop.setRepairShopZip(repairShopData.getRepairShopZip());
		repairShop.setRepairShopPhone(repairShopData.getRepairShopPhone());
	}

	// READ - All Repair Shops
	@Transactional(readOnly = true)
	public List<RepairShopData> retrieveAllRepairShops() {
		return repairShopDao.findAll().stream().map(RepairShopData::new).toList();
	}

	// READ = All Repair Shops Using Vehicle Owner Id
	@Transactional(readOnly = true)
	public List<RepairShopData> retrieveAllRepairShopsUsingVehicleOwnerId(Long vehicleOwnerId) {
		VehicleOwner vehicleOwner = findVehicleOwnerUsingVehicleOwnerId(vehicleOwnerId);
		List<RepairShopData> repairShopList = new LinkedList<>();

		for (RepairShop repairShop : vehicleOwner.getRepairShops()) {
			RepairShopData rsd = new RepairShopData(repairShop);

			repairShopList.add(rsd);
		}

		return repairShopList;
	}

}
