package ai.shreds.domain; 
  
 import javax.persistence.*; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "Category") 
 public class DomainCategoryEntity { 
  
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Long id; 
  
     @Column(name = "name", nullable = false) 
     private String name; 
  
     @Column(name = "parent_id") 
     private Long parentId; 
  
     // Self-referencing relationship to represent the hierarchy 
     @ManyToOne(fetch = FetchType.LAZY) 
     @JoinColumn(name = "parent_id", insertable = false, updatable = false) 
     private DomainCategoryEntity parent; 
  
     @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true) 
     private List<DomainCategoryEntity> children = new ArrayList<>(); 
 }