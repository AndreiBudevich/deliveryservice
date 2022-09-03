const parameters = getUrlParameters();
const orderDetailsAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2] + "/details/";
const orderAjaxUrl = "api/clients/" + parameters[1] + "/orders/" + parameters[2];
const orderAddProduct = orderAjaxUrl + "/add-product/";
const orderDeleteProduct = orderAjaxUrl + "/delete-product/";
const linkOrderDetails = "order_details?clientId=" + parameters[1] + "&orderId=" + parameters[2];
const linkOrderDetailsText = "Заказ общая стоимость: ";