INSERT INTO item (id, name, description, is_deleted)
VALUES (1, 'Classic T-Shirt', '100% Cotton, Unisex fit.', FALSE),
       (2, 'Slim Fit Jeans', 'Dark wash denim, five pockets.', FALSE),
       (3, 'Running Shoes', 'Lightweight with breathable mesh.', FALSE),
       (4, 'Wool Sweater', 'Warm, heavy knit for winter.', TRUE);


INSERT INTO variant (id, item_id, size, color, sku, is_deleted)
VALUES (1, 1, 'Small', 'White', 'TS-W-S', FALSE),
       (2, 1, 'Medium', 'White', 'TS-W-M', FALSE),
       (3, 1, 'Medium', 'Black', 'TS-B-M', FALSE),
       (4, 2, '30x32', 'Blue', 'JF-B-3032', FALSE),
       (5, 2, '32x34', 'Blue', 'JF-B-3234', FALSE),
       (6, 3, '9', 'Red', 'RS-R-9', FALSE),
       (7, 3, '10', 'Black', 'RS-B-10', FALSE),
       (8, 3, '11', 'Black', 'RS-B-11', FALSE),
       (9, 1, 'Large', 'Red', 'TS-R-L', FALSE),
       (10, 2, '34x32', 'Black', 'JF-K-3432', TRUE);


INSERT INTO pricing (id, variant_id, price, currency, is_deleted)
VALUES (101, 1, 19.99, 'USD', FALSE),
       (102, 2, 19.99, 'USD', FALSE),
       (103, 3, 21.99, 'USD', FALSE),
       (104, 4, 79.50, 'USD', FALSE),
       (105, 5, 79.50, 'USD', FALSE),
       (106, 6, 119.99, 'USD', FALSE),
       (107, 7, 129.99, 'USD', FALSE),
       (108, 8, 129.99, 'USD', FALSE),
       (109, 9, 19.99, 'USD', FALSE),
       (110, 10, 85.00, 'USD', TRUE);

INSERT INTO stock (id, variant_id, quantity, is_deleted)
VALUES (201, 1, 150, FALSE),
       (202, 2, 225, FALSE),
       (203, 3, 90, FALSE),
       (204, 4, 45, FALSE),
       (205, 5, 18, FALSE),
       (206, 6, 60, FALSE),
       (207, 7, 0, FALSE),
       (208, 8, 12, FALSE),
       (209, 9, 105, FALSE),
       (210, 10, 30, TRUE);