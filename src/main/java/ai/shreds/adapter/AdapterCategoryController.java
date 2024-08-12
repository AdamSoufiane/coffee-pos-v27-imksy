package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateCategoryInputPort;
import ai.shreds.application.ApplicationUpdateCategoryInputPort;
import ai.shreds.application.ApplicationDeleteCategoryInputPort;
import ai.shreds.application.ApplicationRetrieveCategoryHierarchyInputPort;
import ai.shreds.adapter.dto.AdapterCategoryRequestParams;
import ai.shreds.adapter.dto.AdapterCategoryResponseDTO;
import ai.shreds.adapter.mapper.AdapterCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class AdapterCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class);

    private final ApplicationCreateCategoryInputPort createCategoryInputPort;
    private final ApplicationUpdateCategoryInputPort updateCategoryInputPort;
    private final ApplicationDeleteCategoryInputPort deleteCategoryInputPort;
    private final ApplicationRetrieveCategoryHierarchyInputPort retrieveCategoryHierarchyInputPort;
    private final AdapterCategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<AdapterCategoryResponseDTO> createCategory(@Valid @RequestBody AdapterCategoryRequestParams request) {
        try {
            AdapterCategoryResponseDTO response = createCategoryInputPort.createCategory(categoryMapper.toDomainDTO(request));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error creating category", e);
            return ResponseEntity.badRequest().body(new AdapterCategoryResponseDTO(null, "Error creating category: " + e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterCategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody AdapterCategoryRequestParams request) {
        try {
            AdapterCategoryResponseDTO response = updateCategoryInputPort.updateCategory(id, categoryMapper.toDomainDTO(request));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error updating category", e);
            return ResponseEntity.badRequest().body(new AdapterCategoryResponseDTO(null, "Error updating category: " + e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            deleteCategoryInputPort.deleteCategory(id);
            return ResponseEntity.ok("Category deleted successfully.");
        } catch (Exception e) {
            logger.error("Error deleting category", e);
            return ResponseEntity.badRequest().body("Error deleting category: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AdapterCategoryResponseDTO>> retrieveCategoryHierarchy(@RequestParam(required = false) Long parent_id) {
        try {
            List<AdapterCategoryResponseDTO> response = retrieveCategoryHierarchyInputPort.retrieveCategoryHierarchy(parent_id).stream().map(categoryMapper::toAdapterDTO).collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error retrieving category hierarchy", e);
            return ResponseEntity.badRequest().body(null);
        }
    }
}