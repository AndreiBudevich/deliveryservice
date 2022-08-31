<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>
<br>
<a href="clients"><h6 class="text-start"><spring:message code="client.title"/></h6></a>
<a href="orders"><h6 class="text-start"><spring:message code="order.title"/></h6></a>
<a href="products"><h6 class="text-start"><spring:message code="product.title"/></h6></a>
<a href="shops"><h6 class="text-start"><spring:message code="shop.title"/></h6></a>
<a href="categories"><h6 class="text-start"><spring:message code="category.title"/></h6></a>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>


