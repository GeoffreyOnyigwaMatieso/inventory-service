package com.techg.inventoryservice.service;

import com.techg.inventoryservice.dto.InventoryResponse;
import com.techg.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.techg.inventoryservice.dto.InventoryResponse.*;


@Service
//@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    //lets inject our inventory repo here
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    @Transactional(readOnly = true)
    @SneakyThrows
    public  List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                //mapping the inventory object to the inventory respond object
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            //Check if product is in stock
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList(); //send list of InventoryResponse

    }
}
