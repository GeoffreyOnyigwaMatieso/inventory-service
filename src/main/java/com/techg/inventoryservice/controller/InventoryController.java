package com.techg.inventoryservice.controller;

import com.techg.inventoryservice.dto.InventoryResponse;
import com.techg.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    //http://localhost:8082/api/inventory/iphone-13
    //http://localhost:8082/api/inventory/iphone-13,iphone13-red
    /*Displaying as request parameter*/
    //http://localhost:8082/api/inventory?skuCode=iphone-13&sku-code=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  List<InventoryResponse> isInStock(@RequestParam List<String>  skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
