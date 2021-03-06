<%@ page import="com.day.cq.wcm.api.WCMMode" %>
<%@ page import="com.day.cq.wcm.foundation.External" %>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ include file="/apps/swinburne/global/global.jsp"%>
<%@ include file="/apps/swinburne/global/components.jsp"%>
<%

    Object[][] componentFields = {
            {"html", ""},
            {FIELD_VARIANT, DEFAULT_VARIANT}
    };

    ComponentProperties componentProperties = getComponentProperties(
            pageContext,
            componentFields,
            DEFAULT_FIELDS_STYLE,
            DEFAULT_FIELDS_ACCESSIBILITY);

    String html = componentProperties.get("html","");
    if (isNotEmpty(html)) {
        html = html.replaceAll("&nbsp;", " ");
        html = html.replaceAll("\\s+", " ");
        html = html.replaceAll(" = ", "=");
        html = org.apache.commons.lang.StringEscapeUtils.unescapeHtml(html);
    }
    componentProperties.put("html", html);

%>

<c:set var="componentProperties" value="<%= componentProperties %>"/>
<div ${componentProperties.componentAttributes}>
    <c:choose>
        <c:when test="${componentProperties.variant == 'default' and not empty componentProperties.html}">
            <c:import url="${componentProperties.html}" />
        </c:when>
    </c:choose>
</div>
<%@include file="/apps/swinburne/global/component-badge.jsp" %>
