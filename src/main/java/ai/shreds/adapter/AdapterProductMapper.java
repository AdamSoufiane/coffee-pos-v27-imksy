package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.shared.AdapterCreateProductRequest; 
 import ai.shreds.shared.AdapterUpdateProductRequest; 
 import ai.shreds.shared.AdapterProductResponse; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
 import org.mapstruct.Mapper; 
 import org.mapstruct.Mapping; 
 import org.mapstruct.factory.Mappers; 
  
 @Mapper 
 public interface AdapterProductMapper { 
  
     AdapterProductMapper INSTANCE = Mappers.getMapper(AdapterProductMapper.class); 
  
     @Mapping(target = "id", expression = "java(UUID.randomUUID())") 
     @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))") 
     @Mapping(target = "updatedAt", expression = "java(new Timestamp(System.currentTimeMillis()))") 
     DomainProductEntity mapToDomainEntity(AdapterCreateProductRequest request); 
  
     @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))") 
     @Mapping(target = "updatedAt", expression = "java(new Timestamp(System.currentTimeMillis()))") 
     DomainProductEntity mapToDomainEntity(AdapterUpdateProductRequest request); 
  
     AdapterProductResponse mapToAdapterResponse(DomainProductEntity entity); 
 } 
 