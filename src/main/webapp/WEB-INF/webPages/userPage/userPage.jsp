<!DOCTYPE HTML>
<!--
Overflow by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%@ page import="ua.pomanitskiy.classes.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Overflow by HTML5 UP</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!--[if lte IE 8]>
    <script src="resources/assets/js/ie/html5shiv.js"></script><![endif]-->
    <link rel="stylesheet" href="resources/assets/css/main.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="resources/assets/css/ie8.css"/><![endif]-->
</head>
<body>

<!-- Header -->
<section id="header">
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="get">
        <button style="position: absolute; top: 0%; right: 0;">Logout</button>
    </form>
    <header>
        <h1>Hello</h1>
        <p>my dear user</p>
    </header>
    <footer>
        <a href="#banner" class="button style2 scrolly-middle">Press me</a>
    </footer>


</section>

<!-- Banner -->
<section id="banner">
    <header>
        <h2>This is general information about you</h2>
    </header>
    <p>
        <bold>
            <% User user = (User) request.getAttribute("user"); %>

            <%=user.getLastName().substring(0,1).toUpperCase()%><%=user.getLastName().substring(1)%>
            <%=user.getFirstName().substring(0,1).toUpperCase()%><%=user.getFirstName().substring(1)%><br/>

            Your birthday date is <%= user.getBirthday()%><br/>
            Your e-mail address: <%= user.getEmail() %>
        </bold>
    </p>
    <footer>
        <a href="#header" class="button style2 scrolly">General</a>
    </footer>
</section>


<section id="footer">
    <ul class="icons">
        <li><a class="icon fa-twitter"><span class="label">Twitter</span></a></li>
        <li><a class="icon fa-facebook"><span class="label">Facebook</span></a></li>
        <li><a class="icon fa-google-plus"><span class="label">Google+</span></a></li>
        <li><a class="icon fa-pinterest"><span class="label">Pinterest</span></a></li>
        <li><a class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
        <li><a class="icon fa-linkedin"><span class="label">LinkedIn</span></a></li>
    </ul>
</section>

<!-- Scripts -->
<script src="resources/assets/js/jquery.min.js"></script>
<script src="resources/assets/js/jquery.scrolly.min.js"></script>
<script src="resources/assets/js/jquery.poptrox.min.js"></script>
<script src="resources/assets/js/skel.min.js"></script>
<script src="resources/assets/js/util.js"></script>
<!--[if lte IE 8]>
<script src="resources/assets/js/ie/respond.min.js"></script><![endif]-->
<script src="resources/assets/js/main.js"></script>

</body>
</html>