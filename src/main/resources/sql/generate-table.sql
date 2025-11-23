CREATE TABLE IF NOT EXISTS item
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    is_deleted  BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at  TIMESTAMP    null,
    updated_at  TIMESTAMP    null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS variant
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    item_id    BIGINT       NOT NULL,
    size       VARCHAR(50),
    color      VARCHAR(50),
    sku        VARCHAR(100) NOT NULL UNIQUE,
    is_deleted BOOLEAN      NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP    null,
    updated_at TIMESTAMP    null,
    PRIMARY KEY (id),
    FOREIGN KEY (item_id) REFERENCES item (id)
);

CREATE TABLE IF NOT EXISTS pricing
(
    id         BIGINT         NOT NULL AUTO_INCREMENT,
    variant_id BIGINT         NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    currency   VARCHAR(10)    NOT NULL DEFAULT 'IDR',
    created_at TIMESTAMP      null,
    updated_at TIMESTAMP      null,
    PRIMARY KEY (id),
    FOREIGN KEY (variant_id) REFERENCES variant (id)
);

CREATE TABLE IF NOT EXISTS stock
(
    id         BIGINT    NOT NULL AUTO_INCREMENT,
    variant_id BIGINT    NOT NULL,
    quantity   INTEGER   NOT NULL DEFAULT 0,
    created_at TIMESTAMP null,
    updated_at TIMESTAMP null,
    PRIMARY KEY (id),
    FOREIGN KEY (variant_id) REFERENCES variant (id)
);

CREATE TABLE IF NOT EXISTS _rev_info
(
    id        bigint auto_increment
        primary key,
    timestamp bigint       not null,
    user      varchar(255) null
);

create INDEX idx_rev_info_timestamp
    on _rev_info (timestamp);

create INDEX idx_rev_info_user
    on _rev_info (user);

CREATE TABLE IF NOT EXISTS aud_stock
(
    id       bigint  not null,
    rev      int     not null,
    revtype  tinyint null,
    quantity int     null,
    primary key (rev, id)
);

CREATE TABLE IF NOT EXISTS aud_pricing
(
    id       bigint         not null,
    rev      int            not null,
    revtype  tinyint        null,
    price    decimal(10, 2) not null,
    currency varchar(10)    not null,
    primary key (rev, id)
);
