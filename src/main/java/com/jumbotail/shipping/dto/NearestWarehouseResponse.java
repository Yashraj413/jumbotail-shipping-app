package com.jumbotail.shipping.dto;

public class NearestWarehouseResponse {
    private String warehouseId;
    private double lat;
    private double lng;

    public NearestWarehouseResponse() {}
    public NearestWarehouseResponse(String warehouseId, double lat, double lng) {
        this.warehouseId = warehouseId;
        this.lat = lat;
        this.lng = lng;
    }

    public String getWarehouseId() { return warehouseId; }
    public void setWarehouseId(String warehouseId) { this.warehouseId = warehouseId; }
    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }
    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }
}
