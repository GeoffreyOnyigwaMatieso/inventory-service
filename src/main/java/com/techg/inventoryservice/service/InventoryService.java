package com.techg.inventoryservice.service;

import com.techg.inventoryservice.dto.InventoryResponse;
import com.techg.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.techg.inventoryservice.dto.InventoryResponse.*;


@Service
//@RequiredArgsConstructor
public class InventoryService {
    //lets inject our inventory repo here
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Transactional(readOnly = true)
    public  List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();

    }
}
