<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="product" type="by.deliveryservice.model.Product" scope="request"/>
    <h3><spring:message code="${shop.isNew() ? 'product.add' : 'product.edit'}"/></h3>
    <hr>
    <form method="post" action="products">
        <input type="hidden" name="shopId" value="${param.shopId}">
        <input type="hidden" name="id" value="${product.id}">
        <dl>
            <dt><spring:message code="product.name"/>:<dt>
            <dd><input type="text" value="${product.name}" size=25 name="name" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="product.description"/>:<dt>
            <dd><input type="text" value="${product.description}" size=25 name="description" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="product.price"/>:<dt>
            <dd><input type="number" step="1" value="${product.price}" size=25 name="price" required><dd>
        </dl>
        <dl>
            <dt><spring:message code="product.discount"/>:<dt>
            <dd><input type="number" step="1" value="${product.discount}" size=25 name="discount" required><dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>