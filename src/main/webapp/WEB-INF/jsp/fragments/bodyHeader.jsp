<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
    <a href="clients"><spring:message code="client.title"/></a>
    | <a href="orders"><spring:message code="order.title"/></a>
    | <a href="product"><spring:message code="product.title"/></a>
    | <a href="shops"><spring:message code="shop.title"/></a>
    | <a href="categories"><spring:message code="category.title"/></a>
    |<a href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a>
</header>