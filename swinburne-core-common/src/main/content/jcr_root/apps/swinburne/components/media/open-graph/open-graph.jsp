<%-- TODO: Refactor required to implement dropdown option for fields rather having explicit fields. --%>

<%@ page import="org.apache.sling.api.resource.ResourceUtil" %>
<%@ include file="/apps/swinburne/global/global.jsp" %>
<%@ include file="/apps/swinburne/global/components.jsp" %>
<%@ include file="/apps/swinburne/global/datetime.jsp" %>
<%@ include file="/apps/swinburne/global/images.jsp" %>

<%
    HashMap<String, Object> ogDataInfo = new HashMap<String, Object>();
    String detailsPath = findComponentInPage(_currentPage, DEFAULT_LIST_DETAILS_SUFFIX);
    ValueMap detailsProperties = _properties;
    Resource details = _resourceResolver.getResource(detailsPath);
    //get details properties if its found
    if (details != null && !ResourceUtil.isNonExistingResource(details)) {
        detailsProperties = details.adaptTo(ValueMap.class);
        if (detailsProperties == null) {
            detailsProperties = _properties;
        }
    }

    ogDataInfo.put("ogType", detailsProperties.get("ogType", ""));
    ogDataInfo.put("ogTitle", detailsProperties.get("ogTitle", ""));
    ogDataInfo.put("ogDescription", detailsProperties.get("ogDescription", ""));
    ogDataInfo.put("ogUrl", _currentPage.getPath());
    ogDataInfo.put("ogImage", getResourceImagePath(details, "ogImage"));

%>

<c:set var="ogData" value="<%= ogDataInfo %>"/>

<meta property="og:url" content="${ogData.ogUrl}" />
<meta property="og:type" content="${ogData.ogType}" />
<meta property="og:title" content="${ogData.ogTitle}" />
<meta property="og:description" content="${ogData.ogDescription}" />
<meta property="og:image" content="${ogData.ogImage}" />
