package by.deliveryservice;

import by.deliveryservice.controller.Controller;

public class Main {
    public static void main(String[] args) {

        /*
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Example create
        create client pattern -> client/create/surname; name; middleName; residentialAddress; dateOfBirth
        example create client -> client/create/Будевич; Андрей; Чеславович; г.Лида, ул. Докучаева 63; 20.06.1976

        create category pattern -> category/create/name;
        example create category  -> category/create/товары для дома;

        create shop pattern -> shop/create/name; address; description; contact
        example create shop ->  shop/create/Auchan; г.Лида, ул. Машерова 63; Продовольственный магазин; 80447324144

        create product pattern -> product/create/name; description; id_shop; price; discount;
        example create product->  product/create/рыба; свежая цена за кг; 0; 8; 10

        create order pattern -> order/create/id_client; id_shop;
        example create order>  order/create/1; 1

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Example update
        update client pattern -> client/update/id_client/surname; name; middleName; residentialAddress; dateOfBirth
        example update client -> client/update/1/Будевич; Андрей; Чеславович; г.Лида, ул. Докучаева 63; 20.06.1976

        update category pattern -> category/id_category/update/name;
        example update category  -> category/update/1/товары для дома;

        update shop pattern -> shop/update/id_shop/name; address; description; contact
        example update shop ->  shop/update/1/Auchan; г.Лида, ул. Машерова 63; Продовольственный магазин; 80447324144

        update product pattern -> product/id_product/update/name; description; id_shop; price; discount;
        example update product->  product/update/1/рыба; свежая цена за кг; 0; 8; 10

        update order pattern -> order/update/id_order/id_client; id_shop;
        example update order>  order/update/1/1; 1

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Example getAll
        getAll client(category, order, product, shop, pattern) -> entity/getAll
        example getAll client -> client/getAll

        Example delete
        delete client(category, order, product, shop, pattern) -> entity/delete/id
        example delete client -> client/delete/1

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         */
        Controller.runAplication();
    }
}



