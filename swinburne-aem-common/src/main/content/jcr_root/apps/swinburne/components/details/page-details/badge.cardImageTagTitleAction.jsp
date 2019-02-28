<div class="card ${componentProperties.cardSize} ${badgeClassAttr}">
    <div class="card-img-top">
        <img src="${componentProperties.pageThumbnail}" ${badgeImageAttr} alt="${componentProperties.title}">
    </div>

    <div class="card-body">
        <c:if test="${not empty componentProperties['cq:tags']}">
            <div class="card-taglist card-taglist--basic">
                ${fn:join(componentProperties['cq:tags'], ', ')}
            </div>
        </c:if>

        <${componentProperties.badgeTitleType} class="card-title">${componentProperties.pageNavTitle}</${componentProperties.badgeTitleType}>

        <div class="card-action">
            <a class="card-link ${fn:join(componentProperties.badgeLinkStyle, ' ')}"
               href="${componentProperties.pageUrl}"
               target="${componentProperties.badgeLinkTarget}"
               title="${componentProperties.badgeLinkTitle}"
            ${badgeLinkAttr}><span>${componentProperties.badgeLinkText}</span></a>
        </div>
    </div>
</div>
