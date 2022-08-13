<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<a href="clients"><spring:message code="client.title"/></a>
<br>
<a href="orders"><spring:message code="order.title"/></a>
<br>
<a href="products"><spring:message code="product.title"/></a>
<br>
<a href="shops"><spring:message code="shop.title"/></a>
<br>
<a href="categories"><spring:message code="category.title"/></a>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>


