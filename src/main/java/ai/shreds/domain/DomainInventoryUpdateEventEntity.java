package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import org.hibernate.annotations.GenericGenerator; 
  
 import javax.persistence.*; 
 import java.util.UUID; 
  
 @Entity 
 @Table(name = "InventoryUpdateEvent") 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainInventoryUpdateEventEntity { 
  
     @Id 
     @GeneratedValue(generator = "UUID") 
     @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") 
     @Column(name = "id", updatable = false, nullable = false) 
     private UUID id; 
  
     @ManyToOne 
     @JoinColumn(name = "productId", nullable = false) 
     private UUID productId; 
  
     @Column(name = "quantityChange", nullable = false) 
     private Integer quantityChange; 
  
     @Column(name = "eventType", nullable = false) 
     private String eventType; 
  
     @Column(name = "timestamp", nullable = false, updatable = false) 
     private java.sql.Timestamp timestamp; 
  
     @PrePersist 
     protected void onCreate() { 
         this.timestamp = new java.sql.Timestamp(System.currentTimeMillis()); 
     } 
 } 
 