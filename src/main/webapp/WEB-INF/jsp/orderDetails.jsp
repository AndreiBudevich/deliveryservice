<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/commonBefore.js"></script>
<script type="text/javascript" src="resources/js/orderDetails.js" defer></script>
<script type="text/javascript" src="resources/js/common.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="order.details.title"/></h3>

        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="product.name"/></th>
                <th><spring:message code="product.price"/></th>
                <th><spring:message code="order.details.quanity"/></th>
                <th><spring:message code="order.details.amount"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="order"/>
</jsp:include>
</html>