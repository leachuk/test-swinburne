<c:choose>
    <c:when test="${not empty componentProperties.linkIcon}">
        <button ${componentProperties.componentAttributes}>
            <c:if test="${componentProperties.linkIconDirection eq 'left'}">
                <i class="icon ${fn:join(componentProperties.linkIcon, ' ')}"></i>
            </c:if>
                ${componentProperties.label}
            <c:if test="${componentProperties.linkIconDirection eq 'right'}">
                <i class="icon ${fn:join(componentProperties.linkIcon, ' ')}"></i>
            </c:if>
        </button>
    </c:when>
    <c:otherwise>
        <button ${componentProperties.componentAttributes}>
                ${componentProperties.label}
        </button>
    </c:otherwise>
</c:choose>
