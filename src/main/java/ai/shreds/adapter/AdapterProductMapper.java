package ai.shreds.adapter;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterUpdateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdapterProductMapper {

    AdapterProductMapper INSTANCE = Mappers.getMapper(AdapterProductMapper.class);

    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "categoryId", source = "request.category_id")
    @Mapping(target = "stockQuantity", source = "request.stock_quantity")
    @Mapping(target = "createdAt", expression = "java(new java.sql.Timestamp(System.currentTimeMillis()))")
    @Mapping(target = "updatedAt", expression = "java(new java.sql.Timestamp(System.currentTimeMillis()))")
    DomainProductEntity mapToDomainEntity(AdapterCreateProductRequest request);

    @Mapping(target = "id", source = "request.id")
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "categoryId", source = "request.category_id")
    @Mapping(target = "stockQuantity", source = "request.stock_quantity")
    @Mapping(target = "updatedAt", expression = "java(new java.sql.Timestamp(System.currentTimeMillis()))")
    void mapToDomainEntity(AdapterUpdateProductRequest request, @MappingTarget DomainProductEntity entity);

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "categoryId", source = "entity.categoryId")
    @Mapping(target = "stockQuantity", source = "entity.stockQuantity")
    @Mapping(target = "createdAt", source = "entity.createdAt")
    @Mapping(target = "updatedAt", source = "entity.updatedAt")
    AdapterProductResponse mapToAdapterResponse(DomainProductEntity entity);
}