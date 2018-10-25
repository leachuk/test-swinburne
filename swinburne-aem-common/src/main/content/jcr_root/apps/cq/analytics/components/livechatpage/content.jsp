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
			contentType="text/html"
            pageEncoding="utf-8"%><%
%><%@taglib prefix="cq" uri="http://www.day.com/taglibs/cq/1.0" %><%
%><cq:defineObjects/>
<div>
    <h3>General Tracker Settings</h3>   
    <ul>
        <li><div class="li-bullet"><strong>JavaScript Snippet: </strong><br><%= xssAPI.encodeForHTML(properties.get("snippetCode", "")).replaceAll("\\&\\#xa;","<br>") %></div></li>
    </ul>
</div>

