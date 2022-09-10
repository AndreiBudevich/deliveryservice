Java Delivery service Project 
===============================
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8c11d384cb914092b7037e31eeaafdd4)](https://www.codacy.com/gh/AndreiBudevich/deliveryservice/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AndreiBudevich/deliveryservice&amp;utm_campaign=Badge_Grade)

#### Pattern create
- create client pattern -> client/create/surname; name; middleName; residentialAddress; birthday
- create category pattern -> category/create/name;
- create shop pattern -> shop/create/name; address; description; contact
- create product pattern -> product/create/name; description; id_shop; price; price;
- create order pattern -> order/create/id_client; id_shop;

#### Pattern update
- update client pattern -> client/update/id_client/surname; name; middleName; residentialAddress; birthday
- update category pattern -> category/id_category/update/name;
- update shop pattern -> shop/update/id_shop/name; address; description; contact
- update product pattern -> product/id_product/update/name; description; id_shop; price; discount;
- update order pattern -> order/update/id_order/id_client

#### Pattern getAll
- getAll client(category, order, product, shop, pattern) -> entity/getAll

#### Pattern delete
- client(category, order, product, shop, pattern) -> entity/delete/id

#### Pattern find By Attributes
- product/findbyattributes/text in mame; text in mame description; id_shop; price; price; id_category_1, id_category_2 ...id_category_N

#### Pattern add Products in Shop
- shop/addProducts/id_shop/id_shop, id_product_1, id_product_2 ... id_product_N

#### Pattern delete Products in Shop
- shop/addProducts/id_shop/id_shop, id_product_1, id_product_2 ... id_product_N

#### Pattern add Products in Order
- order/addProducts/id_shop/id_shop, id_product_1, id_product_2 ... id_product_N

#### Pattern delete Products in Order
- order/addProducts/id_shop/id_shop, id_product_1, id_product_2 ... id_product_N

#### Pattern add Categories in Product
- product/addCategory/id_product/id_category_1

#### Pattern delete Categories in Product
- product/deleteCategory/id_product/id_category_1

#### Pattern get Shop grouping Products in Shop 

- shop/getshopproducts/id_shop

#### Pattern set Address in Order
- order/setAddress/id_order/text in address


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

- client/create/Будевич; Андрей; Чеславович; г.Лида, ул. Радунская д.19; 1986-04-02
- client/create/Будевич; Татьяна; Александровна; г.Лида, ул. Машерова 21; 1990-06-20
- client/create/Будевич; Татьяна; Александровна; г.Лида, ул. Докучаева 63; 1979-09-21

- order/create/1
- order/create/1
- order/create/2
- order/create/3

#### Example Update
- shop/update/3/5 Элемент; г.Лида, ул. Тавлая 10; Магазин бытовой техники; 80294544545

#### Example getAll
- client/getAll

#### Example delete
- client/delete/1

#### Example getSortPrice Products
- product/getsortprice

#### Example add Categories in Product

- product/addCategory/1/2

#### Example delete Categories in Product
- product/deleteCategory/1/2

#### Example add Products in Shop
- shop/addProducts/1/1, 2, 3
- shop/addProducts/2/2, 6, 8

#### Example delete Products in Shop
- shop/deleteProducts/1/1, 2, 3
- shop/deleteProducts/2/2, 6, 8

#### Example add Products in Order
- order/addProducts/1/1, 2, 3
- order/addProducts/2/2, 6, 8

#### Example delete Products in Order
- order/deleteProducts/1/1, 2, 3
- order/deleteProducts/2/2, 6, 8

#### Example findByAttributes
- product/findbyattributes/Телевизор LG; Smart; 1; 3000; 0; *
- product/findbyattributes/Теле; Sm; *; *; *; 1
- product/findbyattributes/Теле; Sm; *; *; *; *

#### Example get Shop Products in Shop

- shop/getshopproducts/1

#### Example  set Address in Order
- order/setAddress/1/Докучаева 63