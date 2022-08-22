<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark py-0">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}"><spring:message code="app.title"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="clients"><spring:message code="client.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="orders"><spring:message code="order.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="products"><spring:message code="product.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="shops"><spring:message code="shop.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="categories"><spring:message code="category.title"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}"><spring:message code="app.home"/></a>
                </li>
            </ul>
        </div>
    </div>
    <div class="container">
        <a href="${pageContext.request.contextPath}" class="navbar-brand"><img src="resources/images/icon.png"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
</nav>

