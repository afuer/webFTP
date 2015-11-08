<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="templates/main_template.jsp" pageTitle="file upload">

<stripes:layout-component name="html_head">


</stripes:layout-component>


    <stripes:layout-component name="contents">




	<form method="POST" action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data" >
            File:
            <input type="file" name="file" id="file" /> <br/>
            
            </br></br></br>
            <input type="submit" value="Upload" name="upload" id="upload" />
        </form>

</stripes:layout-component>
</stripes:layout-render>