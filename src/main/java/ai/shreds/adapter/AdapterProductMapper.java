package ai.shreds.adapter;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.shared.AdapterCreateProductRequest;
import ai.shreds.shared.AdapterUpdateProductRequest;
import ai.shreds.shared.AdapterProductResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdapterProductMapper {

    AdapterProductMapper INSTANCE = Mappers.getMapper(AdapterProductMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "category_id", source = "request.category_id")
    @Mapping(target = "stock_quantity", source = "request.stock_quantity")
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "updatedAt", expression = "java(Instant.now())")
    DomainProductEntity mapToDomainEntity(AdapterCreateProductRequest request);

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "category_id", source = "request.category_id")
    @Mapping(target = "stock_quantity", source = "request.stock_quantity")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(Instant.now())")
    DomainProductEntity mapToDomainEntity(AdapterUpdateProductRequest request);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "category_id", source = "entity.category_id")
    @Mapping(target = "stock_quantity", source = "entity.stock_quantity")
    @Mapping(target = "created_at", source = "entity.createdAt")
    @Mapping(target = "updated_at", source = "entity.updatedAt")
    AdapterProductResponse mapToAdapterResponse(DomainProductEntity entity);
}