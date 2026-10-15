package dev.m1stwng.stride.procurement.supplier.service;

import dev.m1stwng.stride.procurement.supplier.dto.request.CreateSupplierRequest;
import dev.m1stwng.stride.procurement.supplier.dto.request.UpdateSupplierRequest;
import dev.m1stwng.stride.procurement.supplier.dto.response.SupplierResponse;
import dev.m1stwng.stride.procurement.supplier.entity.Supplier;
import dev.m1stwng.stride.procurement.supplier.exception.SupplierNotFoundException;
import dev.m1stwng.stride.procurement.supplier.mapper.SupplierMapper;
import dev.m1stwng.stride.procurement.supplier.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public Page<SupplierResponse> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable)
                .map(supplierMapper::toResponse);
    }

    public SupplierResponse findById(UUID id) {
        return supplierRepository.findById(id)
                .map(supplierMapper::toResponse)
                .orElseThrow(() -> new SupplierNotFoundException(id));
    }

    public SupplierResponse create(CreateSupplierRequest request) {
        final Supplier supplier = Supplier.builder()
                .name(request.name())
                .email(request.email())
                .phone(request.phone())
                .build();

        return supplierMapper.toResponse(supplierRepository.save(supplier));
    }

    public SupplierResponse update(UUID id, UpdateSupplierRequest request) {
        final Supplier supplier =  supplierRepository.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id));

        supplier.setName(request.name());
        supplier.setEmail(request.email());
        supplier.setPhone(request.phone());

        return supplierMapper.toResponse(supplierRepository.save(supplier));
    }

    public void delete(UUID id) {
        if (!supplierRepository.existsById(id)) {
            throw new SupplierNotFoundException(id);
        }

        supplierRepository.deleteById(id);
    }
}
