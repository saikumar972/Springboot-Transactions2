package com.esrx.transaction.service;

import com.esrx.transaction.dao.InventoryRepo;
import com.esrx.transaction.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;

    public Inventory saveProduct(Inventory inventory){
        return inventoryRepo.save(inventory);
    }

    public List<Inventory> saveProducts(List<Inventory> inventoryList){
        return inventoryRepo.saveAll(inventoryList);
    }

    public Inventory findProductById(int id){
        return inventoryRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid product id"));
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Inventory updateProduct(Inventory inventory){
        Inventory updatedInventory=findProductById(inventory.getId());
        updatedInventory.setPrice(inventory.getPrice());
        updatedInventory.setName(inventory.getName());
        updatedInventory.setQuantity(inventory.getQuantity());
        inventoryRepo.save(updatedInventory);
        System.out.println("Inventory transaction "+ TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("Inventory transaction Active/NotActive "+ TransactionSynchronizationManager.isActualTransactionActive());
        return updatedInventory;
    }

    public String deleteProducts(Inventory inventory){
        inventoryRepo.delete(inventory);
        return "Inventory got deleted successfully";
    }

    public String deleteById(int id){
        inventoryRepo.deleteById(id);
        return "Products with the "+id+" is deleted successfully";
    }
}
