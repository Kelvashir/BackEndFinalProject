package auto.control.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import auto.control.entity.RepairShop;

public interface RepairShopDao extends JpaRepository<RepairShop, Long> {

}
