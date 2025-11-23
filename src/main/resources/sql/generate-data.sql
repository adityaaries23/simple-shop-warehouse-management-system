INSERT INTO item (id, name, description, is_deleted, created_at)
VALUES (1, 'Classic T-Shirt', '100% Cotton, Unisex fit.', FALSE,NOW()),
       (2, 'Slim Fit Jeans', 'Dark wash denim, five pockets.', FALSE,NOW()),
       (3, 'Running Shoes', 'Lightweight with breathable mesh.', FALSE, NOW()),
       (4, 'Wool Sweater', 'Warm, heavy knit for winter.', TRUE, NOW());


INSERT INTO variant (id, item_id, size, color, sku, is_deleted,created_at)
VALUES (1, 1, 'Small', 'White', 'TS-W-S', FALSE,NOW()),
       (2, 1, 'Medium', 'White', 'TS-W-M', FALSE,NOW()),
       (3, 1, 'Medium', 'Black', 'TS-B-M', FALSE,NOW()),
       (4, 2, '30x32', 'Blue', 'JF-B-3032', FALSE,NOW()),
       (5, 2, '32x34', 'Blue', 'JF-B-3234', FALSE,NOW()),
       (6, 3, '9', 'Red', 'RS-R-9', FALSE,NOW()),
       (7, 3, '10', 'Black', 'RS-B-10', FALSE,NOW()),
       (8, 3, '11', 'Black', 'RS-B-11', FALSE,NOW()),
       (9, 1, 'Large', 'Red', 'TS-R-L', FALSE,NOW()),
       (10, 2, '34x32', 'Black', 'JF-K-3432', TRUE,NOW());


INSERT INTO pricing (id, variant_id, price, currency, created_at)
VALUES (101, 1, 19.99, 'USD', NOW()),
       (102, 2, 19.99, 'USD', NOW()),
       (103, 3, 21.99, 'USD', NOW()),
       (104, 4, 79.50, 'USD', NOW()),
       (105, 5, 79.50, 'USD', NOW()),
       (106, 6, 119.99, 'USD', NOW()),
       (107, 7, 129.99, 'USD', NOW()),
       (108, 8, 129.99, 'USD', NOW()),
       (109, 9, 19.99, 'USD', NOW()),
       (110, 10, 85.00, 'USD', NOW());

INSERT INTO stock (id, variant_id, quantity, created_at)
VALUES (201, 1, 150,  NOW()),
       (202, 2, 225,  NOW()),
       (203, 3, 90,  NOW()),
       (204, 4, 45,  NOW()),
       (205, 5, 18,  NOW()),
       (206, 6, 60,  NOW()),
       (207, 7, 0,  NOW()),
       (208, 8, 12,  NOW()),
       (209, 9, 105,  NOW()),
       (210, 10, 30,  NOW());