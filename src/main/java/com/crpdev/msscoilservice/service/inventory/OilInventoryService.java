package com.crpdev.msscoilservice.service.inventory;

import java.util.UUID;

public interface OilInventoryService {
    Integer getOnHandInventory(UUID oilId);
}
