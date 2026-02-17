-- Customers [file:1]
INSERT INTO customers (id, name, phone, location_lat, location_lng) VALUES
('Cust-123', 'Shree Kirana Store', '9847', 11.232, 23.445495),
('Cust-124', 'Andheri Mini Mart', '9101', 17.232, 33.445495);

-- Sellers (added locations creatively)
INSERT INTO sellers (id, name, location_lat, location_lng) VALUES
('123', 'Nestle Seller', 12.5, 30.0), -- near BLR
('124', 'Rice Seller', 18.0, 35.0);  -- near MUM

-- Products [file:1]
INSERT INTO products (id, name, seller_id, weight_kg) VALUES
('456', 'Maggie 500g Packet', '123', 0.5),
('457', 'Rice Bag 10Kg', '124', 10.0),
('458', 'Sugar Bag 25kg', '124', 25.0);

-- Warehouses [file:1]
INSERT INTO warehouses (id, name, location_lat, location_lng) VALUES
('789', 'BLRWarehouse', 12.99999, 37.923273),
('790', 'MUMBWarehouse', 11.99999, 27.923273);
