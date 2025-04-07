package com.esrx.transaction.controller;

import com.esrx.transaction.entity.Inventory;
import com.esrx.transaction.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @PostMapping("/save")
    public Inventory saveProduct(@RequestBody Inventory inventory){
        return inventoryService.saveProduct(inventory);
    }
    @PostMapping("/saveAll")
    public List<Inventory> saveProducts(@RequestBody List<Inventory> inventoryList){
        return inventoryService.saveProducts(inventoryList);
    }
    @GetMapping("/id/{id}")
    public Inventory findProductById(@PathVariable int id){
        return inventoryService.findProductById(id);
    }
    @PutMapping("/update")
    public Inventory updateProduct(@RequestBody Inventory inventory){
        return inventoryService.updateProduct(inventory);
    }
    @DeleteMapping("/delete")
    public String deleteProducts(@RequestBody Inventory inventory){
        return inventoryService.deleteProducts(inventory);
    }
    @DeleteMapping("/deleteById/{id}")
    public String deleteProductById(@PathVariable int id){
        return inventoryService.deleteById(id);
    }
}
