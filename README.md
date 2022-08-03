Java Delivery service Project 
===============================

#### Pattern create
- create client pattern -> client/create/surname; name; middleName; residentialAddress; dateOfBirth
- create category pattern -> category/create/name;
- create shop pattern -> shop/create/name; address; description; contact
- create product pattern -> product/create/name; description; id_shop; price; price;
- create order pattern -> order/create/id_client; id_shop;

#### Pattern update
- update client pattern -> client/update/id_client/surname; name; middleName; residentialAddress; dateOfBirth
- update category pattern -> category/id_category/update/name;
- update shop pattern -> shop/update/id_shop/name; address; description; contact
- update product pattern -> product/id_product/update/name; description; id_shop; price; discount;
- update order pattern -> order/update/id_order/id_client; id_shop;

#### Pattern getAll
-getAll client(category, order, product, shop, pattern) -> entity/getAll

#### Pattern delete
-client(category, order, product, shop, pattern) -> entity/delete/id

#### Pattern findByAttributes
- product/findbyattributes/text in mame; text in mame description; id_shop; price; price; id_category_1, id_category_2 ...id_category_N

#### Example Create

- shop/create/Auchan; г.Лида, ул. Машерова 63; Продовольственный магазин; 80447324144
- shop/create/Евроопт; г.Лида, ул. Советская 1; Продовольственный магазин; 80297634349
- shop/create/5 Элемент; г.Лида, ул. Тавлая 10; Продовольственный магазин; 80294544545

- product/create/Телевизор LG; Smart; 1; 3000; 0
- product/create/Телевизор Samsung; Smart; 2; 3500; 0
- product/create/Фен Philips; c насадками; 1; 150; 10
- product/create/Фен Brun; без насадок; 3; 200; 10
- product/create/Микроволновка; с грилем; 3; 500; 10
- product/create/Апельсины; цена за кг.; 2; 10; 3
- product/create/Бананы; цена за кг.; 2; 7; 5

- category/create/продукты
- category/create/бытовая техника
- category/create/товары со скидкой

-client/create/Будевич; Андрей; Чеславович; г.Лида, ул. Радунская д.19; 02.04.1986
-client/create/Будевич; Татьяна; Александровна; г.Лида, ул. Машерова 21; 20.06.1990
-client/create/Будевич; Татьяна; Александровна; г.Лида, ул. Докучаева 63; 20.06.1979

-order/create/1; 1
-order/create/1; 2
-order/create/2; 1
-order/create/3; 3

#### Example Update
- shop/update/3/5 Элемент; г.Лида, ул. Тавлая 10; Магазин бытовой техники; 80294544545

#### Example getAll
- client/getAll

#### Example delete
- client/delete/1

#### Example getSortPrice Products
- product/getsortprice

#### Example addCategories in Product

- product/addcategories/1/2
- product/addcategories/2/2
- product/addcategories/3/2; 3
- product/addcategories/4/2; 3
- product/addcategories/5/2; 3
- product/addcategories/7/1; 3
- product/addcategories/7/1; 3

#### Example findByAttributes
- product/findbyattributes/Телевизор LG; Smart; 1; 3000; 0; 1, 2, 3
- product/findbyattributes/Теле; Sm; *; *; *; 1
- product/findbyattributes/Теле; Sm; *; *; *; *