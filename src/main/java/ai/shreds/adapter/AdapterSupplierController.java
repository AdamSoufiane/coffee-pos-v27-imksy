package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateSupplierInputPort;
import ai.shreds.application.ApplicationGetSupplierByIdInputPort;
import ai.shreds.application.ApplicationGetAllSuppliersInputPort;
import ai.shreds.application.ApplicationUpdateSupplierInputPort;
import ai.shreds.application.ApplicationDeleteSupplierInputPort;
import ai.shreds.shared.ApplicationCreateSupplierDTO;
import ai.shreds.shared.ApplicationUpdateSupplierDTO;
import ai.shreds.shared.ApplicationSupplierDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/suppliers")
@Validated
public class AdapterSupplierController {

    private final ApplicationCreateSupplierInputPort createSupplierService;
    private final ApplicationGetSupplierByIdInputPort getSupplierByIdService;
    private final ApplicationGetAllSuppliersInputPort getAllSuppliersService;
    private final ApplicationUpdateSupplierInputPort updateSupplierService;
    private final ApplicationDeleteSupplierInputPort deleteSupplierService;
    private final AdapterSupplierMapper mapper;
    private static final Logger logger = LoggerFactory.getLogger(AdapterSupplierController.class);

    public AdapterSupplierController(ApplicationCreateSupplierInputPort createSupplierService,
                                     ApplicationGetSupplierByIdInputPort getSupplierByIdService,
                                     ApplicationGetAllSuppliersInputPort getAllSuppliersService,
                                     ApplicationUpdateSupplierInputPort updateSupplierService,
                                     ApplicationDeleteSupplierInputPort deleteSupplierService,
                                     AdapterSupplierMapper mapper) {
        this.createSupplierService = createSupplierService;
        this.getSupplierByIdService = getSupplierByIdService;
        this.getAllSuppliersService = getAllSuppliersService;
        this.updateSupplierService = updateSupplierService;
        this.deleteSupplierService = deleteSupplierService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<AdapterSupplierResponse> createSupplier(@Valid @RequestBody AdapterCreateSupplierRequest request) {
        logger.info("Received request to create supplier: {}", request);
        try {
            ApplicationCreateSupplierDTO createDTO = mapper.toApplicationCreateDTO(request);
            ApplicationSupplierDTO supplierDTO = createSupplierService.createSupplier(createDTO);
            AdapterSupplierResponse response = mapper.toAdapterResponse(supplierDTO);
            logger.info("Successfully created supplier: {}", response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdapterSupplierResponse> getSupplierById(@NotNull @PathVariable Long id) {
        logger.info("Received request to retrieve supplier with ID: {}", id);
        try {
            ApplicationSupplierDTO supplierDTO = getSupplierByIdService.getSupplierById(id);
            AdapterSupplierResponse response = mapper.toAdapterResponse(supplierDTO);
            logger.info("Successfully retrieved supplier: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<AdapterSupplierResponse>> getAllSuppliers() {
        logger.info("Received request to retrieve all suppliers");
        try {
            List<ApplicationSupplierDTO> supplierDTOs = getAllSuppliersService.getAllSuppliers();
            List<AdapterSupplierResponse> responses = supplierDTOs.stream()
                    .map(mapper::toAdapterResponse)
                    .collect(Collectors.toList());
            logger.info("Successfully retrieved all suppliers");
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving all suppliers: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterSupplierResponse> updateSupplier(@NotNull @PathVariable Long id, @Valid @RequestBody AdapterUpdateSupplierRequest request) {
        logger.info("Received request to update supplier with ID: {}", id);
        try {
            ApplicationUpdateSupplierDTO updateDTO = mapper.toApplicationUpdateDTO(request);
            ApplicationSupplierDTO supplierDTO = updateSupplierService.updateSupplier(id, updateDTO);
            AdapterSupplierResponse response = mapper.toAdapterResponse(supplierDTO);
            logger.info("Successfully updated supplier: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@NotNull @PathVariable Long id) {
        logger.info("Received request to delete supplier with ID: {}", id);
        try {
            deleteSupplierService.deleteSupplier(id);
            logger.info("Successfully deleted supplier with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting supplier: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}