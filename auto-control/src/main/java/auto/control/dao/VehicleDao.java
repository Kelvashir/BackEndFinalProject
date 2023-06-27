package auto.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import auto.control.entity.Vehicle;

public interface VehicleDao extends JpaRepository<Vehicle, Long> {

}
