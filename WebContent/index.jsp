<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>

<stripes:layout-render name="/common/templates/big_bg_templ.jsp" pageTitle=".:: Login Page ::.">

<stripes:layout-component name="html_head">


</stripes:layout-component>


    <stripes:layout-component name="contents">


	
		   <form method="post" action="${pageContext.request.contextPath}/loginServ" class="login">
    <p>
    
      <label for="login">userName : </label>
      <input type="text" name="userName" id="login" placeholder="userName">
    </p>

    <p>
      <label for="password">Password : </label>
      <input type="password" name="password" id="password">
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button">Login</button>
    </p>
    <p><font face="Tahoma" style="font-size: 10pt;" color="red">&nbsp; ${requestScope.loginStatus}</font></p>

    
  </form>
		 
        
        
        
 


 </stripes:layout-component>
</stripes:layout-render>