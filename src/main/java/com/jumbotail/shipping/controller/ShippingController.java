package com.jumbotail.shipping.controller;

import com.jumbotail.shipping.dto.*;
import com.jumbotail.shipping.service.NearestWarehouseService;
import com.jumbotail.shipping.service.ShippingChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ShippingController {
    @Autowired private NearestWarehouseService nearestService;
    @Autowired private ShippingChargeService chargeService;

    @GetMapping("/warehouse/nearest")
    public ResponseEntity<NearestWarehouseResponse> getNearestWarehouse(
            @RequestParam String sellerId, @RequestParam String productId) {
        try {
            return ResponseEntity.ok(nearestService.getNearestWarehouse(sellerId, productId));
        } catch (IllegalArgumentException | RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/shipping-charge")
    public ResponseEntity<ShippingChargeResponse> getShippingCharge(
            @RequestParam String warehouseId,
            @RequestParam String customerId,
            @RequestParam String deliverySpeed,
            @RequestParam String productId) { 
        try {
            return ResponseEntity.ok(chargeService.getShippingChargeFromWarehouse(warehouseId, customerId, deliverySpeed, productId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/shipping-charge/calculate")
    public ResponseEntity<?> calculateShippingCharge(@RequestBody CalculateChargeRequest request) {
        try {
            NearestWarehouseResponse nearest = nearestService.getNearestWarehouse(request.getSellerId(), "456"); 
            ShippingChargeResponse charge = chargeService.getShippingChargeFromWarehouse(
                nearest.getWarehouseId(), request.getCustomerId(), request.getDeliverySpeed(), "456");

            var response = new Object() {
                public double shippingCharge = charge.getShippingCharge();
                public NearestWarehouseResponse nearestWarehouse = nearest;
            };
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
