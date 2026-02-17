package com.jumbotail.shipping.service;

import com.jumbotail.shipping.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Haversine formula for distance between two lat/lng points in KM [file:1]
     */
    public double calculateDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLat());
        double lng1 = Math.toRadians(loc1.getLng());
        double lat2 = Math.toRadians(loc2.getLat());
        double lng2 = Math.toRadians(loc2.getLng());

        double dLat = lat2 - lat1;
        double dLng = lng2 - lng1;

        double a = Math.sin(dLat / 2).pow(2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLng / 2).pow(2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
