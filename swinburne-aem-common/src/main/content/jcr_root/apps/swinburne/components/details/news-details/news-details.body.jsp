<div ${componentProperties.componentAttributes}>
    <c:if test="${not empty componentProperties.author}">
        <div class="author">${componentProperties.author}</div>
    </c:if>
    <c:if test="${not empty componentProperties.tags}">
        <div class="tags">
            <c:forEach items="${componentProperties.tags}" var="tag">
                <span class="tag badge badge-default" data-value="${tag.value.value}">${tag.value.title}</span>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${not empty componentProperties.newsDateStatusText}">
        <div class="published">${componentProperties.newsDateStatusText}</div>
    </c:if>
    <c:if test="${not componentProperties.hideDescription}">
        <div class="description">${componentProperties.description}</div>
    </c:if>
</div>

