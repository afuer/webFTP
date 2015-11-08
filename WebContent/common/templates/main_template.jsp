<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>

<stripes:layout-definition>


<!DOCTYPE html PUBLIC "-//W3//DTD XHTML 1.0 Strict//EN" 
"HTTP://WWW.W3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="web FTP tool">
    <meta name="author" content="Shaah_team">
	<meta name="generator" content="HAPedit 3.1">
	
	
    <title>${pageTitle}</title>
 <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/business-casual.css" rel="stylesheet">

    <!-- Fonts -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<stripes:layout-component name="html_head"/>
</head>
<body>




<div class="container">


<div class="brand">FTP utilities</div>
    <div class="address-bar">
    
    
    <c:if test="${sessionScope.user !=null}">
   					<a href="${pageContext.request.contextPath}/logOut">logout</a>
				
	</c:if>
    
    </div>

    <!-- Navigation -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
                <!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
                <a class="navbar-brand" href="index.html">Business Casual</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                   
                    <li>
                        <a href="${pageContext.request.contextPath}/ftp/ftpConfig.jsp">FTP Config</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/common/fileUpload.jsp">File Upload</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>


 <div class="container">

        <div class="row">
            <div class="box">
			
				<div class="row">
						<div class="col-lg-3">
							
							
							
							<stripes:layout-component name="leftMenu">
         						<jsp:include page="/common/templates/left_menu.jsp"/>
							</stripes:layout-component>
							
						</div>
						
						
						<div class="col-lg-9">
						<stripes:layout-component name="contents"/>
							
						</div>
				
				</div> <!-- end of enclosed row-->
				
            </div>
        </div>

       

    </div>


</div>
 <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </div>
    </footer>

   

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


</body>
</html>


</stripes:layout-definition>