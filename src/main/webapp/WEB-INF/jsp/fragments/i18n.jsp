<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript">
    const i18n = [];
    i18n["addTitle"] = '<spring:message code="${param.page}.add"/>';
    i18n["editTitle"] = '<spring:message code="${param.page}.edit"/>';
    <c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled",
    "common.errorStatus","common.confirm", "product.order.added", "product.order.notAdded", "category.added",
    "category.notAdded", "category.deleted", "category.notDeleted", "product.order.deleted","product.order.notDeleted",
     "order.shipped", "order.notShipped","exception.order.shipmentStatus"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
    </c:forEach>
</script>