<c:if test="${not empty componentProperties.pageBackgroundImage}">
    <c:set var="extraAttr" value="${extraAttr} style=\"background-image: url(${componentProperties.pageBackgroundImage})\""/>
</c:if>
<div ${componentProperties.componentAttributes}${extraAttr}>
    <div class="container">
        <%@include file="news-details.header.jsp" %>
        <header>
            <c:if test="${not empty componentProperties.newsDateStatusText}">
                <div class="published">${componentProperties.newsDateStatusText}</div>
            </c:if>
            <c:if test="${not empty componentProperties.tags}">
                <div class="tags">
                    <c:forEach items="${componentProperties.tags}" var="tag">
                        <span class="tag badge badge-default" data-value="${tag.value.value}">${tag.value.title}</span>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${not componentProperties.hideTitle}">
            <${componentProperties.titleType}>${componentProperties.title}</${componentProperties.titleType}>
        </c:if>

        </header>
        <%@include file="news-details.body.jsp" %>
        <%@include file="news-details.footer.jsp" %>
    </div>
</div>