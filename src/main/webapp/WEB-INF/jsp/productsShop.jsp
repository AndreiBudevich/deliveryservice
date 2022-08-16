<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://by.deliveryservice.util/functions" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<section>
   <h3><spring:message code="product.title"/> ${param.shopName}</h3>
    <input type="hidden" name="shopId" value="${param.shopId}">
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="product.name"/></th>
            <th><spring:message code="product.description"/></th>
            <th><spring:message code="product.price"/></th>
            <th><spring:message code="product.discount"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.products}" var="product">
            <jsp:useBean id="product" type="by.deliveryservice.model.Product"/>
            <tr>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.discount}</td>
                <td><a href="products/update?shopId=${param.shopId}&id=${product.id}"><spring:message code="common.update"/></a></td>
                <td><a href="products/delete?shopId=${param.shopId}&id=${product.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
        <hr>
        <td><a href="products/create?shopId=${param.shopId}"><spring:message code="product.add"/></a></td>
        <hr>
    </table>
</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>

