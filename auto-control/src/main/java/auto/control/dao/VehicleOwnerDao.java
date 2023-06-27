package auto.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import auto.control.entity.VehicleOwner;

public interface VehicleOwnerDao extends JpaRepository<VehicleOwner, Long> {

}
