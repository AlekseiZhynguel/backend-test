CREATE TABLE prices
(
    id         CHAR(36) PRIMARY KEY,
    brand_id   INT,
    start_date DATETIME,
    end_date   DATETIME,
    price_list INT,
    product_id INT,
    priority   INT,
    price      DECIMAL(10, 2),
    currency   VARCHAR(3)
);