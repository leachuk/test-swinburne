<div class="card ${componentProperties.cardSize} ${badgeClassAttr}">
    <img class="card-img-top" src="${componentProperties.pageThumbnail}" ${badgeImageAttr} alt="${componentProperties.title}">
    <div class="card-body">
        <${componentProperties.badgeTitleType} class="card-title">${componentProperties.title}</${componentProperties.badgeTitleType}>
        <c:if test="${not empty componentProperties.tags}">
            <ul class="tag-list">
                <c:forEach items="${componentProperties.tags}" var="tag" varStatus="entryStatus">
                    <li class="tag-list__item badge badge-default" data-value="${tag.value.value}">${tag.value.title}</li>
                </c:forEach>
            </ul>
        </c:if>
        <p><a class="card-link text-primary text-xsmall text-bold"
           href="${componentProperties.pageUrl}"
           target="${componentProperties.badgeLinkTarget}"
           title="${componentProperties.badgeLinkTitle}"
        ${badgeLinkAttr}>${componentProperties.badgeLinkText}</a></p>
        <div class="row">
            <div class="col-6">
                <c:if test="${not componentProperties.newsDateStatusText}">
                    <p class="text-xsmall text-g2">${componentProperties.newsDateStatusText}</p>
                </c:if>
            </div>
        </div>
    </div>
</div>

