<%-- TODO: Refactor required to implement dropdown option for fields rather having explicit fields. --%>

<%@ page import="org.apache.sling.api.resource.ResourceUtil" %>
<%@ include file="/apps/swinburne/global/global.jsp" %>
<%@ include file="/apps/swinburne/global/components.jsp" %>
<%@ include file="/apps/swinburne/global/datetime.jsp" %>
<%@ include file="/apps/swinburne/global/images.jsp" %>

<%
  final String DEFAULT_THUMB_SELECTOR_LG = "cq5dam.web.1280.1280";
  final String DEFAULT_IMAGE_RENDITION_PATH = "/jcr:content/renditions/";
  final String IMAGE_RENDITION_1280 = "1280x1280";
  final String IMAGE_JPG_FORMAT = ".jpg";
  final String IMAGE_JPEG_FORMAT = ".jpeg";
  final String PROPERTY_IMAGE_RENDITION = "imageRendition";
  final String DEFAULT_OG_IMAGE = "/content/dam/media/brand/swin-logo-h-RGB.jpg";

  HashMap<String, Object> ogDataInfo = new HashMap<String, Object>();
  String detailsPath = findComponentInPage(_currentPage, DEFAULT_LIST_DETAILS_SUFFIX);
  ValueMap detailsProperties = _properties;
  Resource details = _resourceResolver.getResource(detailsPath);
  String resolveImageRendition = StringUtils.EMPTY;
  String selectedImageRendition = StringUtils.EMPTY;
  String imageExternalizedUrl = StringUtils.EMPTY;
  Externalizer externalizer = _resourceResolver.adaptTo(Externalizer.class);
  String externalizedUrl = externalizer.publishLink(_resourceResolver,  _currentPage.getPath());

  //get details properties if its found
  if (details != null && !ResourceUtil.isNonExistingResource(details)) {
    detailsProperties = details.adaptTo(ValueMap.class);
    if (detailsProperties == null) {
      detailsProperties = _properties;
    }
    Resource ogImageResource = details.getChild("ogImage");
    String imagePath = getResourceImagePath(details, "ogImage");

    if (StringUtils.isBlank(imagePath)) {
      imagePath = DEFAULT_OG_IMAGE;
    }

    String imageExtension = imagePath.substring(imagePath.lastIndexOf("."));
    if (ogImageResource != null) {
      ValueMap map = ogImageResource.getValueMap();
      if (map != null && map.containsKey(PROPERTY_IMAGE_RENDITION)) {
        selectedImageRendition = map.get(PROPERTY_IMAGE_RENDITION).toString();
      }
    }
    if (selectedImageRendition.equals(IMAGE_RENDITION_1280) || StringUtils.isBlank(selectedImageRendition)){
      resolveImageRendition = DEFAULT_THUMB_SELECTOR_LG;
    }
    if (imageExtension.equals(IMAGE_JPG_FORMAT)) {
      imageExtension = IMAGE_JPEG_FORMAT;
    }
    imageExternalizedUrl = externalizer.publishLink(_resourceResolver, imagePath + DEFAULT_IMAGE_RENDITION_PATH + resolveImageRendition + imageExtension);
  }

  ogDataInfo.put("ogType", detailsProperties.get("ogType", ""));
  ogDataInfo.put("ogTitle", detailsProperties.get("ogTitle", ""));
  ogDataInfo.put("ogDescription", detailsProperties.get("ogDescription", ""));
  ogDataInfo.put("ogUrl", externalizedUrl + DEFAULT_EXTENTION);
  ogDataInfo.put("ogImage", imageExternalizedUrl);

%>

<c:set var="ogData" value="<%= ogDataInfo %>"/>

<meta property="og:url" content="${ogData.ogUrl}" />
<meta property="og:type" content="${ogData.ogType}" />
<meta property="og:title" content="${ogData.ogTitle}" />
<meta property="og:description" content="${ogData.ogDescription}" />
<meta property="og:image" content="${ogData.ogImage}" />
