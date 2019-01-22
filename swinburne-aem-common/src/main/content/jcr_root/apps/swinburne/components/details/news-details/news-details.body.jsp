<div>
    <img src="${componentProperties.pageThumbnailImage}" ${badgeImageAttr} alt="${componentProperties.title}" class="card-img-top"/>
   <%-- <c:if test="${not empty componentProperties.author}">
        <div class="author">${componentProperties.author}</div>
    </c:if>--%>
    <c:if test="${componentProperties.showBreadcrumb}">
        <cq:include path="breadcrumb" resourceType="aemdesign/components/layout/breadcrumb"/>
    </c:if>
    <c:if test="${not componentProperties.hideDescription}">
        <div class="description">${componentProperties.description}</div>
    </c:if>
</div>

