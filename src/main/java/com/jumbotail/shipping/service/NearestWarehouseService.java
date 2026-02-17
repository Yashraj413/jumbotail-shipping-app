package com.jumbotail.shipping.service;

import com.jumbotail.shipping.dto.NearestWarehouseResponse;
import com.jumbotail.shipping.entity.Product;
import com.jumbotail.shipping.entity.Seller;
import com.jumbotail.shipping.entity.Warehouse;
import com.jumbotail.shipping.repository.ProductRepository;
import com.jumbotail.shipping.repository.SellerRepository;
import com.jumbotail.shipping.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NearestWarehouseService {
    @Autowired private SellerRepository sellerRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private WarehouseRepository warehouseRepo;
    @Autowired private DistanceService distanceService;

    @Cacheable(value = "nearestWarehouse", key = "#sellerId + '_' + #productId")
    public NearestWarehouseResponse getNearestWarehouse(String sellerId, String productId) {
        Seller seller = sellerRepo.findById(sellerId).orElseThrow(() -> new IllegalArgumentException("Seller not found: " + sellerId));
        productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
 
        Warehouse nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Warehouse wh : warehouseRepo.findAll()) {
            double dist = distanceService.calculateDistance(seller.getLocation(), wh.getLocation());
            if (dist < minDist) {
                minDist = dist;
                nearest = wh;
            }
        }
        if (nearest == null) throw new RuntimeException("No warehouses available");
        return new NearestWarehouseResponse(nearest.getId(), nearest.getLocation().getLat(), nearest.getLocation().getLng());
    }
}
