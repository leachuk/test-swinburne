<%--
  Copyright 1997-2009 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================



--%><%@page session="false" 
			import="org.apache.sling.api.resource.Resource,
                	org.apache.sling.api.resource.ValueMap,
                	org.apache.sling.api.resource.ResourceUtil,
                	com.day.cq.wcm.webservicesupport.Configuration,
                	com.day.cq.wcm.webservicesupport.ConfigurationManager" %><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects/><%

String[] services = pageProperties.getInherited("cq:cloudserviceconfigs", new String[]{});
ConfigurationManager cfgMgr = resource.getResourceResolver().adaptTo(ConfigurationManager.class);
if(cfgMgr != null) {
    String snippetCode = null;
    Configuration cfg = cfgMgr.getConfiguration("livechat", services);
    if(cfg != null) {
        snippetCode = cfg.get("snippetCode", null);
    }

    if(snippetCode != null) {
    %>
    <script type="text/javascript">
    <%= snippetCode %>
    </script><%
    }
}
%>
