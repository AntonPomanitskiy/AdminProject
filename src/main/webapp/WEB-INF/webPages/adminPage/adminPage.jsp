<%@ page import="ua.pomanitskiy.classes.JdbcUserDao" %>
<%@ page import="ua.pomanitskiy.classes.User" %>
<%@ page import="static ua.pomanitskiy.web.filters.InitFilter.URL1" %>
<%@ page import="static ua.pomanitskiy.web.filters.InitFilter.USER1" %>
<%@ page import="static ua.pomanitskiy.web.filters.InitFilter.PASSWORD1" %>
<%@ page import="static ua.pomanitskiy.web.filters.InitFilter.DRIVER1" %>
<%@ page import="ua.pomanitskiy.interfaces.UserDao" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <title>AdminPanel by Pomanitskiy</title>
    <link rel="stylesheet" href="resources/css/style1.css" type="text/css" media="all"/>
</head>
<body>
<%
    UserDao userDao = JdbcUserDao.creatingUserDao(DRIVER1, URL1, USER1, PASSWORD1);
    List<User> allUsers = userDao.findAll();
%>
<!-- Header -->
<div id="header">
    <div class="shell">
        <!-- Logo + Top Nav -->
        <div id="top">
            <h1><p>Admin Panel</p></h1>
            <div id="top-navigation">
                <form action="${pageContext.request.contextPath}/LogoutServlet">
                    <button>Log out</button>
                </form>
            </div>
        </div>
        <!-- End Logo + Top Nav -->

        <!-- Main Nav -->
        <div id="navigation">
            <ul>
                <li><a href="#" class="active"><span>Dashboard</span></a></li>
            </ul>
        </div>
        <!-- End Main Nav -->
    </div>
</div>
<!-- End Header -->

<!-- Container -->
<div id="container">
    <div class="shell">

        <!-- Small Nav -->
        <!-- End Small Nav -->

        <!-- Message OK
        <div class="msg msg-ok">
            <p><strong>Your file was uploaded succesifully!</strong></p>
            <a href="#" class="close">close</a>
        </div>
        <!-- End Message OK -->

        <!-- Message Error
        <div class="msg msg-error">
            <p><strong>You must select a file to upload first!</strong></p>
            <a href="#" class="close">close</a>
        </div>
        <!-- End Message Error -->
        <br/>
        <!-- Main -->
        <div id="main">
            <div class="cl">&nbsp;</div>

            <!-- Content -->
            <div id="content">

                <!-- Box -->
                <div class="box">
                    <!-- Box Head -->
                    <div class="box-head">
                        <h2 class="left">Current Users</h2>
                    </div>
                    <!-- End Box Head -->

                    <!-- Table -->

                    <div class="table">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <th>Number</th>
                                <th>Email</th>
                                <th>Birthday date</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Role</th>
                                <th>Blocked</th>
                                <th width="110" class="ac">Control</th>
                            </tr>
                            <% int number = 0;%>
                            <%
                                int sizeForPagging = 1;
                                int allUsersSize = allUsers.size();
                                while (allUsersSize - 12 > 0) {
                                    sizeForPagging++;
                                }

                            %>

                            <%
                                int rest = 12 - (allUsers.size() % 12);
                                for (User user : allUsers) {
                                    //if (number + 1 % 13 != 0) {
                                        ++number;
                            %>
                            <tr>
                                <td><%= user.getId() %>
                                </td>
                                <td><h3><%= user.getEmail() %>
                                </h3></td>
                                <td><%= user.getBirthday() %>
                                </td>
                                <td><p><%= user.getFirstName() %>
                                </p></td>
                                <td><p><%= user.getLastName() %>
                                </p></td>
                                <td><p><%= user.getRole().getName() %>
                                </p></td>
                                <td><p><%= user.getBlocked() %>
                                </p></td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/DeleteUserServlet" method="post">
                                        <button name="emailForDelete" value="<%= user.getEmail()%>" class="ico del">Delete</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/BlockUserServlet" method="post">
                                        <button name="emailForBlock" value="<%= user.getEmail()%>" class="ico block">Block </button>
                                    </form>
                                </td>
                            </tr>
                            <% //}
                                }
                            if(allUsers.size() < 12) {
                                for (int i = 0; i < rest; i++) {
                            %>
                            <tr>
                                <td><%= ++number %>
                                </td>
                                <td><h3></h3></td>
                                <td></td>
                                <td><p></p></td>
                                <td><p></p></td>
                                <td><p></p></td>
                                <td></td>
                            </tr>
                            <% }
                            }%>

                            <!--              qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq
                            <!--
                            <tr class="odd">
                                <td><input type="checkbox" class="checkbox" /></td>
                                <td><h3><p>qewr@qwer</p></h3></td>
                                <td>12.05.09</td>
                                <td><p>Anton</p></td>
                                <td><p>Pomanitskiy</p></td>
                                <td><p>Admin or User</p></td><td><a href="#" class="ico del">Delete</a><a href="#" class="ico edit">Edit</a></td>
                            </tr> -->
                        </table>


                        <!-- Pagging -->
                        <div class="pagging">
                            <div class="right">
                                <b><a href="#">1</a></b>
                            </div>
                        </div>
                        <!-- End Pagging -->

                    </div>
                    <!-- Table -->

                </div>
                <!-- End Box -->

                <!-- Box -
                <div class="box">
                    <!-- Box Head
                    <div class="box-head">
                        <h2>Add New User</h2>
                    </div>
                    <!-- End Box Head

                    <form action="" method="post">

                        <!-- Form
                        <div class="form">
                                <p>
                                    <span class="req">max 100 symbols</span>
                                    <label>Article Title <span>(Required Field)</span></label>
                                    <input type="text" class="field size1" />
                                </p>
                                <p class="inline-field">
                                    <label>Date</label>
                                    <select class="field size2">
                                        <option value="">23</option>
                                    </select>
                                    <select class="field size3">
                                        <option value="">July</option>
                                    </select>
                                    <select class="field size3">
                                        <option value="">2009</option>
                                    </select>
                                </p>

                                <p>
                                    <span class="req">max 100 symbols</span>
                                    <label>Content <span>(Required Field)</span></label>
                                    <textarea class="field size1" rows="10" cols="30"></textarea>
                                </p>

                        </div>
                        <!-- End Form -

                        <!-- Form Buttons --
                        <div class="buttons">
                            <input type="submit" class="button" value="submit" />
                        </div>
                        <!-- End Form Buttons --
                    </form>
                </div>
                <!-- End Box -->

            </div>
            <!-- End Content -->

            <!-- Sidebar -->
            <div id="sidebar">

                <!-- Box -->
                <div class="box">

                    <!-- Box Head -->
                    <div class="box-head">
                        <h2>Information</h2>
                    </div>
                    <!-- End Box Head-->

                    <div class="box-content">

                        <!-- Sort -->
                        <div class="sort">
                            <label>This page is intended for database management</label>
                            <!--
                            <select class="field">
                                <option value="">Email</option>
                            </select> -->
                        </div>
                        <!-- End Sort -->

                    </div>
                </div>
                <!-- End Box -->
            </div>
            <!-- End Sidebar -->

            <div class="cl">&nbsp;</div>
        </div>
        <!-- Main -->
    </div>
</div>
<!-- End Container -->

<!-- Footer -->
<div id="footer">
    <div class="shell">
        <span class="left">&copy; 2016 - Pomanitskiy</span>
		<span class="right">
			Design by <a href="#">Pomanitskiy</a>
		</span>
    </div>
</div>
<!-- End Footer -->

</body>
</html>