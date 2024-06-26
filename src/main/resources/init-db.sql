DROP TABLE if exists prices;

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

INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (UUID(), 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50,
        'EUR');
INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (UUID(), 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45,
        'EUR');
INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (UUID(), 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50,
        'EUR');
INSERT INTO prices (id, brand_id, start_date, end_date, price_list, product_id, priority, price, currency)
VALUES (UUID(), 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95,
        'EUR');