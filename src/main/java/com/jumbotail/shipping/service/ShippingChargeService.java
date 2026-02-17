package com.jumbotail.shipping.service;

import com.jumbotail.shipping.dto.ShippingChargeResponse;
import com.jumbotail.shipping.entity.Customer;
import com.jumbotail.shipping.entity.Product;
import com.jumbotail.shipping.entity.Warehouse;
import com.jumbotail.shipping.repository.CustomerRepository;
import com.jumbotail.shipping.repository.ProductRepository;
import com.jumbotail.shipping.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ShippingChargeService {
    @Autowired private CustomerRepository customerRepo;
    @Autowired private WarehouseRepository warehouseRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private DistanceService distanceService;

    public ShippingChargeResponse getShippingChargeFromWarehouse(String warehouseId, String customerId, String deliverySpeed, String productId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Warehouse warehouse = warehouseRepo.findById(warehouseId).orElseThrow(() -> new IllegalArgumentException("Warehouse not found"));
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        double distanceKm = distanceService.calculateDistance(warehouse.getLocation(), customer.getLocation());
        double weight = product.getWeightKg();
        double baseCharge = calculateBaseCharge(distanceKm, weight); 
        double expressExtra = "express".equals(deliverySpeed) ? 1.2 * weight : 0;
        double total = baseCharge + expressExtra;

        return new ShippingChargeResponse(total);
    }

    @Cacheable(value = "shippingCharge")
    private double calculateBaseCharge(double distKm, double weight) {
        double ratePerKmPerKg;
        if (distKm > 500) {
            ratePerKmPerKg = 1.0; 
        } else if (distKm > 100) {
            ratePerKmPerKg = 2.0; 
        } else {
            ratePerKmPerKg = 3.0; 
        }
        return 10.0 + (distKm * ratePerKmPerKg * weight); 
    }
}
