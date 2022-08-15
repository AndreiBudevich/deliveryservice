<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="/WEB-INF/jsp/fragments/bodyHeader.jsp"/>

<section>
    <h3><spring:message code="category.title"/></h3>
    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th><spring:message code="category.name"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.categories}" var="category">
            <jsp:useBean id="category" type="by.deliveryservice.model.Category"/>
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td><a href="categories/update?id=${category.id}"><spring:message code="common.update"/></a></td>
                <td><a href="categories/delete?id=${category.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
        <hr>
        <a href="categories/create"><spring:message code="category.add"/></a>
        <hr>
    </table>
</section>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>