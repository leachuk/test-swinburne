<c:choose>
    <c:when test="${not empty componentProperties.linkIcon}">
        <a ${componentProperties.componentAttributes}>
            <c:if test="${componentProperties.linkIconDirection eq 'left'}">
                <i class="icon ${fn:join(componentProperties.linkIcon, ' ')}"></i>
            </c:if>
                ${componentProperties.label}
            <c:if test="${componentProperties.linkIconDirection eq 'right'}">
                <i class="icon ${fn:join(componentProperties.linkIcon, ' ')}"></i>
            </c:if>
        </a>
    </c:when>
    <c:otherwise>
        <a ${componentProperties.componentAttributes}>
                ${componentProperties.label}
        </a>
    </c:otherwise>
</c:choose>
