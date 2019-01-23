<div class="card card--taglist-share ${badgeClassAttr}">
    <img class="card-img-top" src="${componentProperties.pageThumbnail}" ${badgeImageAttr} alt="${componentProperties.title}">
    <div class="card-body">
        <${componentProperties.badgeTitleType} class="card-title h5">${componentProperties.title}</${componentProperties.badgeTitleType}>
        <c:if test="${not empty componentProperties.tags}">
            <div class="card-taglist">
                <ul class="taglist text-bold">
                    <c:forEach items="${componentProperties.tags}" var="tag" varStatus="entryStatus">
                        <li class="taglist__item" data-value="${tag.value.value}">#${tag.value.title}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <a class="card-link text-primary text-xsmall text-bold"
           href="${componentProperties.pageUrl}"
           target="${componentProperties.badgeLinkTarget}"
           title="${componentProperties.badgeLinkTitle}"
        ${badgeLinkAttr}>Read full post</a>
        <c:if test="${not componentProperties.newsDateStatusText}">
            <p class="text-xsmall text-g2">${componentProperties.newsDateStatusText}</p>
        </c:if>
    </div>
</div>
