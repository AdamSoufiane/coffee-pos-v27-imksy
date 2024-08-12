package ai.shreds.adapter;

import ai.shreds.application.ApplicationCategoryServicePort;
import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.AdapterCategoryResponseDTO;
import ai.shreds.shared.AdapterCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class AdapterCategoryController {

    private final ApplicationCategoryServicePort applicationCategoryServicePort;
    private final AdapterCategoryMapper adapterCategoryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AdapterCategoryResponseDTO> getCategoryDetails(@PathVariable Long id) {
        try {
            validateId(id);
            SharedCategoryDTO sharedCategoryDTO = applicationCategoryServicePort.getCategoryDetails(id);
            AdapterCategoryResponseDTO responseDTO = adapterCategoryMapper.toAdapterCategoryResponseDTO(sharedCategoryDTO);
            return ResponseEntity.ok(responseDTO);
        } catch (NotFoundException e) {
            log.error("Category not found for id: {}", id, e);
            return handleNotFoundException(e.getMessage());
        } catch (InternalServerErrorException e) {
            log.error("Internal server error while fetching category details for id: {}", id, e);
            return handleInternalServerError(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AdapterCategoryResponseDTO>> getAllCategories() {
        try {
            List<SharedCategoryDTO> sharedCategoryDTOList = applicationCategoryServicePort.getAllCategories();
            List<AdapterCategoryResponseDTO> responseDTOList = sharedCategoryDTOList.stream()
                    .map(adapterCategoryMapper::toAdapterCategoryResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseDTOList);
        } catch (InternalServerErrorException e) {
            log.error("Internal server error while fetching all categories", e);
            return handleInternalServerError(e.getMessage());
        }
    }

    @GetMapping("/{parent_id}/subcategories")
    public ResponseEntity<List<AdapterCategoryResponseDTO>> getSubcategories(@PathVariable Long parent_id) {
        try {
            validateId(parent_id);
            List<SharedCategoryDTO> sharedCategoryDTOList = applicationCategoryServicePort.getSubcategories(parent_id);
            List<AdapterCategoryResponseDTO> responseDTOList = sharedCategoryDTOList.stream()
                    .map(adapterCategoryMapper::toAdapterCategoryResponseDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseDTOList);
        } catch (NotFoundException e) {
            log.error("Parent category not found for id: {}", parent_id, e);
            return handleNotFoundException(e.getMessage());
        } catch (InternalServerErrorException e) {
            log.error("Internal server error while fetching subcategories for parent id: {}", parent_id, e);
            return handleInternalServerError(e.getMessage());
        }
    }

    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }
    }

    private ResponseEntity<AdapterCategoryResponseDTO> handleNotFoundException(String message) {
        return ResponseEntity.status(404).body(new AdapterCategoryResponseDTO(null, message, null));
    }

    private ResponseEntity<List<AdapterCategoryResponseDTO>> handleInternalServerError(String message) {
        return ResponseEntity.status(500).body(null);
    }
}

// Custom Exceptions
class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}