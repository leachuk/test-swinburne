<c:if test="${not empty componentProperties.pageBackgroundImage}">
    <c:set var="extraAttr" value="${extraAttr} style=\"background-image: url(${componentProperties.pageBackgroundImage})\""/>
</c:if>
<div ${componentProperties.componentAttributes}${extraAttr}>
    <div class="container">
        <%@include file="news-details.header.jsp" %>
        <header>
            <c:if test="${not componentProperties.hideTitle}">
            <${componentProperties.titleType}>${componentProperties.title}</${componentProperties.titleType}>
        </c:if>
        <img src="${componentProperties.pageThumbnailImage}" ${badgeImageAttr} alt="${componentProperties.title}" class="card-img-top"/>
        </header>
        <%@include file="news-details.body.jsp" %>
        <%@include file="news-details.footer.jsp" %>
    </div>
</div>