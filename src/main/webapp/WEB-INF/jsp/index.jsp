<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<a href="clients">Client</a>
<br>
<a href="orders">Order</a>
<br>
<a href="products">Product</a>
<br>
<a href="shops">Shop</a>
<br>
<a href="categories">Category</a>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>

