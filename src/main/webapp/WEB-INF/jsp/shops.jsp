<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<section>
   <h3><spring:message code="shop.title"/></h3>
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="shop.name"/></th>
            <th><spring:message code="shop.description"/></th>
            <th><spring:message code="shop.address"/></th>
            <th><spring:message code="shop.contact"/></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.shops}" var="shop">
            <jsp:useBean id="shop" type="by.deliveryservice.model.Shop"/>
            <tr>
                <td>${shop.name}</td>
                <td>${shop.description}</td>
                <td>${shop.address}</td>
                <td>${shop.contact}</td>
                <td><a href="shops/update?id=${shop.id}"><spring:message code="common.update"/></a></td>
                <td><a href="shops/delete?id=${shop.id}"><spring:message code="common.delete"/></a></td>
                <td><a href="products/view-products-by-shop?shopId=${shop.id}&shopName=${shop.name}"><spring:message code="shop.viewProducts"/></a></td>
            </tr>
        </c:forEach>
        <hr>
        <a href="shops/create"><spring:message code="shop.add"/></a>
        <hr>
    </table>
</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
