# Jumbotail Shipping Estimator

## Run
mvn spring-boot:run

## APIs
1. GET /api/v1/warehouse/nearest?sellerId=123&productId=456 → nearest wh [file:1]
Sample: {"warehouseId":"789","lat":12.99999,"lng":37.923273}

2. GET /api/v1/shipping-charge?warehouseId=789&customerId=Cust-123&deliverySpeed=standard&productId=456 → charge
Sample: {"shippingCharge":150.0} (depends on dist/weight)

3. POST /api/v1/shipping-charge/calculate { "sellerId":"123", "customerId":"Cust-123", "deliverySpeed":"express" }
Sample: {"shippingCharge":180.0, "nearestWarehouse":{...}}

## Features
- Haversine distance [file:1]
- Transport selection: MiniVan(0-100km,3Rs/km/kg), Truck(100-500,2), Aeroplane(>500,1)
- Express: +1.2/kg
- Fixed 10Rs standard courier
- Caching, exceptions, tests, JPA/H2

