CREATE TABLE IF NOT EXISTS  item (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      name VARCHAR(255) NOT NULL UNIQUE,
                      description TEXT,
                      is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                      PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS variant (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         item_id BIGINT NOT NULL,
                         size VARCHAR(50),
                         color VARCHAR(50),
                         sku VARCHAR(100) NOT NULL UNIQUE,
                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                         PRIMARY KEY (id),
                         FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS pricing (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         variant_id BIGINT NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         currency VARCHAR(10) NOT NULL DEFAULT 'IDR',
                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                         PRIMARY KEY (id),
                         FOREIGN KEY (variant_id) REFERENCES variant(id)
);

CREATE TABLE IF NOT EXISTS stock (
                       id BIGINT NOT NULL AUTO_INCREMENT,
                       variant_id BIGINT NOT NULL,
                       quantity INTEGER NOT NULL DEFAULT 0,
                       is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                       PRIMARY KEY (id),
                       FOREIGN KEY (variant_id) REFERENCES variant(id)
);