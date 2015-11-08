<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<stripes:layout-definition>
    <html >
        <head>
            <title>${pageTitle}</title>
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
           <link href="${pageContext.request.contextPath}/css/index_stylesheet.css" rel="stylesheet" type="text/css"/>
            <stripes:layout-component name="html_head"/>
            
        </head>
        
        <body>
        <div id="content_container">
       	  
           
                <div id="header_lower">  
          
          <div id="header_content_boxcontent" class="loginBody">


						<stripes:layout-component name="contents"/>
					 </div>
          
          </div>     
        </div>
        
        <div id="bottom_bar_black"> 

 <div id="copywriteblock">
 <span> <a href="http://www.google.com">http://www.your_domian.com </a></span>
</div>
</div>
</body>
        
    </html>
</stripes:layout-definition>