Java Delivery service Project
===============================
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/8c11d384cb914092b7037e31eeaafdd4)](https://www.codacy.com/gh/AndreiBudevich/deliveryservice/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AndreiBudevich/deliveryservice&amp;utm_campaign=Badge_Grade)

#### Pattern create

- create client pattern -> client/create/surname; name; middleName; residentialAddress; birthday
- create category pattern -> category/create/name;
- create shop pattern -> shop/create/name; address; description; contact
- create product pattern -> product/create/id_shop; name; description; price; discount;
- create order pattern -> order/create/id_client; id_shop;

#### Pattern update

- update client pattern -> client/update/id_client/surname; name; middleName; residentialAddress; birthday
- update category pattern -> category/update/id_category/name;
- update shop pattern -> shop/update/id_shop/name; address; description; contact
- update product pattern -> product/update/id_product/id_shop; name; description;  price; discount

#### Pattern getAll

- getAll client(category, order, product, shop, pattern) -> entity/getAll

#### Pattern delete

- client(category, order, product, shop, pattern) -> entity/delete/id

#### Pattern find By Attributes

- product/findByAttributes/text in mame; text in mame description; id_shop; price; price; id_category_1, id_category_2
  ...id_category_N

#### Pattern set quantity Products in Shop

- shop/setQuantity/id_product_1/quantity

#### Pattern add Products in Order

- order/addProducts/id_order/id_product_N

#### Pattern delete Products in Order

- order/addProducts/id_order/id_product_N

#### Pattern add Categories in Product

- product/addCategory/id_product/id_category_N

#### Pattern delete Categories in Product

- product/deleteCategory/id_product/id_category_N

#### Pattern get Shop grouping Products in Shop

- shop/getShopProducts/id_shop

#### Pattern set Address in Order

- order/setAddress/id_order/text in address

#### Pattern ship an Order

order/ship/id_order

#### Example Create

- shop/create/Auchan; г.Лида, ул. Машерова 63; Продовольственный магазин; 80447324144
- shop/create/Евроопт; г.Лида, ул. Советская 1; Продовольственный магазин; 80297634349
- shop/create/5 Элемент; г.Лида, ул. Тавлая 10; Продовольственный магазин; 80294544545

- product/create/1; Телевизор LG; Smart; 3000; 0
- product/create/2; Телевизор Samsung; Smart; 3500; 0
- product/create/1; Фен Philips; c насадками; 150; 10
- product/create/3; Фен Brun; без насадок; 200; 10
- product/create/3; Микроволновка; с грилем; 500; 10
- product/create/2; Апельсины; цена за кг.; 10; 3
- product/create/2; Бананы; цена за кг.; 7; 5

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
- client/update/3/Будевич; Ксения; Андреевна; г.Лида, ул. Радунская д.25; 2012-26-07
- category/update/4/Свежайшие продукты;
- product/update/7/1; персик; поштучно; 1; 0

#### Example getAll

- client/getAll

#### Example delete

- client/delete/1

#### Example getSortPrice Products

- product/getSortPrice

#### Example add Categories in Product

- product/addCategory/1/2

#### Example delete Categories in Product

- product/deleteCategory/1/2

#### Example add Products in Shop

- shop/setQuantity/1/400
- shop/setQuantity/2/400

#### Example add Products in Order

- order/addProduct/1/1
- order/addProduct/2/2

#### Example delete Products in Order

- order/deleteProduct/1/1
- order/deleteProduct/2/2

#### Example findByAttributes

- product/findByAttributes/Телевизор LG; Smart; 1; 3000; 0; *
- product/findByAttributes/Теле; Sm; *; *; *; 1
- product/findByAttributes/Теле; Sm; *; *; *; *

#### Example get Shop Products in Shop

- shop/getShopProducts/1

#### Example  set Address in Order

- order/setAddress/1/Докучаева 63

#### Example ship an Order

- order/ship/1