package auto.control.controller.model;

import auto.control.entity.RepairShop;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RepairShopData {

	private Long repairShopId;
	private String repairShopName;
	private String repairShopStreetAddress;
	private String repairShopCity;
	private String repairShopState;
	private String repairShopZip;
	private String repairShopPhone;

	public RepairShopData(RepairShop repairShop) {
		this.repairShopId = repairShop.getRepairShopId();
		this.repairShopName = repairShop.getRepairShopName();
		this.repairShopStreetAddress = repairShop.getRepairShopStreetAddress();
		this.repairShopCity = repairShop.getRepairShopCity();
		this.repairShopState = repairShop.getRepairShopState();
		this.repairShopZip = repairShop.getRepairShopZip();
		this.repairShopPhone = repairShop.getRepairShopPhone();

	}

}
