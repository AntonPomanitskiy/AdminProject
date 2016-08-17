<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="resources/css/normalize.css">

    <link rel="stylesheet" href="resources/css/style.css">


</head>

<body>

<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#login">Log In</a></li>
        <li class="tab"><a href="#signup">Sign Up</a></li>
    </ul>

    <div class="tab-content">

        <div id="login">
            <h1>Welcome Back!</h1>

            <br />
            <div class="field-wrap">
                <label style="color: orangered">
                    We Are Sorry, But This Account Has Been Suspended By The Administrator<span>*</span>
                </label>
            </div>
            <br />
            <br />
            <form action="${pageContext.request.contextPath}/MainServlet" method="post">
             <!--   <form action="/" method="post"> -->



                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input type="email" name="email" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off"/>
                </div>

                <!-- <p class="forgot"><a href="#">Forgot Password?</a></p> -->

                <button class="button button-block"/>
                Log In</button>

            </form>
        </div>
        <div id="signup">
            <h1>Sign Up For Free</h1>

            <form action="${pageContext.request.contextPath}/SignUpServlet" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input name="firstname" type="text" required autocomplete="off"/>
                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input name="lastname" type="text" required autocomplete="off"/>
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <input name="email" type="email" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <input id="password" name="password" type="password" required autocomplete="off"/>
                </div>

                <div class="field-wrap">
                    <label>
                        Repeat A Password<span class="req">*</span>
                    </label>
                    <input id="confirm_password" type="password" required />
                </div>

                <!--
                <div class="field-wrap">

                    <label>
                        <span style='padding-left:130px;'> </span>Birthday date<span class="req">*</span>
                    </label>
                    <input type="date" required autocomplete="off"/>
                </div> -->
                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            Select Date Of Birthday<span class="req">*</span>
                        </label>
                    </div>

                    <div class="field-wrap">
                        <input name="birthday" type="date" required autocomplete="off"/>
                    </div>
                </div>

                <br />
                <button class="button button-block"/>
                Get Started</button>

            </form>

        </div>


    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="resources/js/index.js"></script>
<script src="resources/js/passConf.js"></script>


</body>

</html>
