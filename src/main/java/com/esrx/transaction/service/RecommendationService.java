package com.esrx.transaction.service;

import com.esrx.transaction.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
public class RecommendationService {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Products> getAllInventoryList(){
        return Stream.of(new Products("TV",255500),
                new Products("Washing machine",244400),
                new Products("Mobile",245500),
                new Products("Laptop",5600),
                new Products("Power bank",5600)).toList();
    }
}
