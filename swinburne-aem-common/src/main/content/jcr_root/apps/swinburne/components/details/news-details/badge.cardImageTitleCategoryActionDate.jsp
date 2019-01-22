<div class="card ${componentProperties.cardSize} ${badgeClassAttr}">
    <img class="card-img-top" src="${componentProperties.pageThumbnail}" ${badgeImageAttr} alt="${componentProperties.title}">
    <div class="card-body">
        <${componentProperties.badgeTitleType} class="card-title">${componentProperties.title}</${componentProperties.badgeTitleType}>
        <c:if test="${not empty componentProperties.tags}">
            <ul class="taglist text-bold mb-15">
                <c:forEach items="${componentProperties.tags}" var="tag" varStatus="entryStatus">
                    <li class="taglist__item" data-value="${tag.value.value}">#${tag.value.title}</li>
                </c:forEach>
            </ul>
        </c:if>
        <a class="card-link text-primary text-xsmall text-bold mb-20"
           href="${componentProperties.pageUrl}"
           target="${componentProperties.badgeLinkTarget}"
           title="${componentProperties.badgeLinkTitle}"
        ${badgeLinkAttr}>${componentProperties.badgeLinkText}</a>
        <div class="row">
            <div class="col-6">
                <c:if test="${not componentProperties.newsDateStatusText}">
                    <p class="text-xsmall text-g2">${componentProperties.newsDateStatusText}</p>
                </c:if>
            </div>
        </div>
    </div>
</div>

