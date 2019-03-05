<div class="body">
    <img src="${componentProperties.pageThumbnailImage}" ${badgeImageAttr} alt="${componentProperties.title}">

    <c:if test="${componentProperties.showBreadcrumb}">
        <cq:include path="breadcrumb" resourceType="swinburne/components/layout/breadcrumb"/>
    </c:if>
    <c:if test="${not componentProperties.hideDescription}">
        <div class="description">${componentProperties.description}</div>
    </c:if>
</div>