INSERT INTO client (name, surname, middleName, residential_address, birthday)
VALUES ('Андрей', 'Будевич', 'Чеславович', 'г.Лида, ул. Радунская д.19', '1986-04-02'),
       ('Татьяна', 'Будевич', 'Александровна', 'г.Лида, ул. Машерова 21', '1990-06-20'),
       ('Ксения', 'Будевич', 'Андреева', 'г.Лида, ул. Машерова 27', '2012-07-26');

INSERT INTO category (name)
VALUES ('Продукты'),
       ('Бытовая техника'),
       ('товары со скидкой');

INSERT INTO shop (name, address, description, contact)
VALUES ('Auchan', 'г.Лида, ул. Машерова 63', 'Продовольственный магазин', '80447324144'),
       ('Евроопт', ' г.Лида, ул. Советская 1', 'Продовольственный магазин', '80297634349'),
       ('5 Элемент', 'г.Лида, ул. Тавлая 10', 'Магазин бытовой техники', '80294544545');

INSERT INTO product (name, description, shop_id, price, discount)
VALUES ('Телевизор LG', 'Smart', 1, 3000, 0),
       ('Телевизор Samsung', 'Smart', 2, 3500, 0),
       ('Фен Philips', 'c насадками', 1, 150, 10),
       ('Фен Braun', 'с грилем', 3, 200, 10),
       ('Микроволновка', 'без насадок', 3, 500, 10),
       ('Апельсины', 'цена за кг.', 2, 10, 3),
       ('Бананы', 'цена за кг.', 2, 7, 5);

INSERT INTO product_category (product_id, category_id)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (3, 3),
       (4, 2),
       (4, 3),
       (5, 2),
       (5, 3),
       (6, 1),
       (6, 3),
       (7, 1),
       (7, 3);

INSERT INTO client_order (client_id, registered, total_cost, delivery_address)
VALUES (1, '2022-04-02', 0, 'г.Лида, ул. Радунская д.19'),
       (2, '2022-06-10', 0, 'г.Лида, г.Лида, ул. Машерова 21'),
       (2, '2022-08-01', 0, 'г.Лида, ул. Машерова 21'),
       (3, '2022-10-01', 0, 'г.Лида, ул. Машерова 27');;

INSERT INTO order_details (order_id, product_id, price, quanity, amount)
VALUES (1, 2, 3000, 2, 600),
       (1, 7, 3500, 1, 3500),
       (2, 6, 150, 1, 150),
       (2, 4, 200, 2, 400),
       (2, 3, 500, 2, 1000),
       (3, 1, 10, 4, 40),
       (3, 5, 7, 4, 24);
