package ai.shreds.domain;

import java.util.UUID;

public interface DomainInventoryServicePort {
    void updateInventory(DomainProductEntity product) throws InventoryUpdateException;
    DomainInventoryEntity getInventoryByProductId(UUID id) throws InventoryNotFoundException;
    void validateUUIDFormat(UUID id) throws InvalidUUIDFormatException;
    void deleteInventoryByProductId(UUID id);
}