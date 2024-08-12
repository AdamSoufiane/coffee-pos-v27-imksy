package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateCategoryInputPort;
import ai.shreds.application.ApplicationUpdateCategoryInputPort;
import ai.shreds.application.ApplicationDeleteCategoryInputPort;
import ai.shreds.application.ApplicationRetrieveCategoryHierarchyInputPort;
import ai.shreds.adapter.dto.AdapterCategoryRequestParams;
import ai.shreds.adapter.dto.AdapterCategoryResponseDTO;
import ai.shreds.adapter.mapper.AdapterCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class AdapterCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryController.class);

    private final ApplicationCreateCategoryInputPort createCategoryInputPort;
    private final ApplicationUpdateCategoryInputPort updateCategoryInputPort;
    private final ApplicationDeleteCategoryInputPort deleteCategoryInputPort;
    private final ApplicationRetrieveCategoryHierarchyInputPort retrieveCategoryHierarchyInputPort;
    private final AdapterCategoryMapper categoryMapper;

    public AdapterCategoryController(ApplicationCreateCategoryInputPort createCategoryInputPort,
                                     ApplicationUpdateCategoryInputPort updateCategoryInputPort,
                                     ApplicationDeleteCategoryInputPort deleteCategoryInputPort,
                                     ApplicationRetrieveCategoryHierarchyInputPort retrieveCategoryHierarchyInputPort,
                                     AdapterCategoryMapper categoryMapper) {
        this.createCategoryInputPort = createCategoryInputPort;
        this.updateCategoryInputPort = updateCategoryInputPort;
        this.deleteCategoryInputPort = deleteCategoryInputPort;
        this.retrieveCategoryHierarchyInputPort = retrieveCategoryHierarchyInputPort;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<AdapterCategoryResponseDTO> createCategory(@Valid @RequestBody AdapterCategoryRequestParams request) {
        AdapterCategoryResponseDTO response;
        try {
            response = createCategoryInputPort.createCategory(request);
        } catch (Exception e) {
            logger.error("Error creating category", e);
            return ResponseEntity.badRequest().body(new AdapterCategoryResponseDTO(null, "Error creating category: " + e.getMessage(), null));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterCategoryResponseDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody AdapterCategoryRequestParams request) {
        AdapterCategoryResponseDTO response;
        try {
            response = updateCategoryInputPort.updateCategory(id, request);
        } catch (Exception e) {
            logger.error("Error updating category", e);
            return ResponseEntity.badRequest().body(new AdapterCategoryResponseDTO(null, "Error updating category: " + e.getMessage(), null));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            deleteCategoryInputPort.deleteCategory(id);
        } catch (Exception e) {
            logger.error("Error deleting category", e);
            return ResponseEntity.badRequest().body("Error deleting category: " + e.getMessage());
        }
        return ResponseEntity.ok("Category deleted successfully.");
    }

    @GetMapping
    public ResponseEntity<List<AdapterCategoryResponseDTO>> retrieveCategoryHierarchy(@RequestParam(required = false) Long parent_id) {
        List<AdapterCategoryResponseDTO> response;
        try {
            response = retrieveCategoryHierarchyInputPort.retrieveCategoryHierarchy(parent_id);
        } catch (Exception e) {
            logger.error("Error retrieving category hierarchy", e);
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(response);
    }
}
