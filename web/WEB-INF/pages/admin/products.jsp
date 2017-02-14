<%@ page import="am.pm.dataaccess.model.Product" %>
<%@ page import="am.pm.dataaccess.model.User" %>
<%@ page import="am.pm.util.Constant" %>
<%@ page import="am.pm.util.Utils" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 4/2/2016
  Time: 3:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%
    User user = (User) session.getAttribute(Constant.SESSION_USER);
    %>
    <title><%=Constant.PROJECT_NAME%>-<%=user.getName()%>&nbsp;<%=user.getSurname()%></title>

    <!-- Global stylesheets -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/icons/icomoon/styles.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/core.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/components.min.css" rel="stylesheet" type="text/css">
    <link href="<%=request.getContextPath()%>/assets/css/colors.min.css" rel="stylesheet" type="text/css">
    <!-- /global stylesheets -->

    <!-- Core JS files -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/plugins/loaders/pace.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/core/libraries/jquery.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/core/libraries/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/plugins/loaders/blockui.min.js"></script>
    <!-- /core JS files -->

    <!-- Theme JS files -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/plugins/forms/styling/uniform.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/plugins/tables/datatables/datatables.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/plugins/forms/selects/select2.min.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/core/app.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/assets/js/pages/home.js"></script>
    <!-- /theme JS files -->

</head>

<body>

<!-- Main navbar -->
<div class="navbar navbar-inverse">
    <div class="navbar-header">
        <a class="navbar-brand" href="/admin/home"><img src="<%=request.getContextPath()%>/assets/images/logo_light.png" alt=""></a>
    </div>

    <div class="navbar-collapse collapse" id="navbar-mobile">
        <ul class="nav navbar-nav">
            <li>
                <a class="sidebar-control sidebar-main-toggle hidden-xs">
                    <i class="icon-paragraph-justify3"></i>
                </a>
            </li>
        </ul>

        <ul class="nav navbar-nav navbar-right">

            <li class="dropdown dropdown-user">
                <a class="dropdown-toggle" data-toggle="dropdown">
                    <img src="<%=request.getContextPath()%>/assets/images/placeholder.jpg" alt="">
                    <span><%=user.getName()%></span>
                    <i class="caret"></i>
                </a>

                <ul class="dropdown-menu dropdown-menu-right">
                    <li><a href="/sign-out"><i class="icon-switch2"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- /main navbar -->


<!-- Page container -->
<div class="page-container">

    <!-- Page content -->
    <div class="page-content">

        <!-- Main sidebar -->
        <div class="sidebar sidebar-main">
            <div class="sidebar-content">

                <!-- User menu -->
                <div class="sidebar-user">
                    <div class="category-content">
                        <div class="media">
                            <a href="#" class="media-left"><img src="<%=request.getContextPath()%>/assets/images/placeholder.jpg" class="img-circle img-sm" alt=""></a>
                            <div class="media-body">
                                <span class="media-heading text-semibold"><%=user.getName()%>&nbsp;<%=user.getSurname()%></span>
                            </div>

                            <div class="media-right media-middle">
                                <ul class="icons-list">
                                    <li>
                                        <a href="#"><i class="icon-cog3"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /user menu -->


                <!-- Main navigation -->
                <div class="sidebar-category sidebar-category-visible">
                    <div class="category-content no-padding">
                        <ul class="navigation navigation-main navigation-accordion">

                            <!-- Main -->
                            <li class="navigation-header"><span>Main</span> <i class="icon-menu" title="Main pages"></i></li>
                            <li><a href="/admin/home"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
                            <li>
                                <a href="#"><i class="icon-stack2"></i> <span>Product layouts</span></a>
                                <ul>
                                    <li><a href="/products/view">Products</a></li>
                                    <li><a href="/product-categories/view">Product Categories</a></li>
                                    <li><a href="/product-details/view">Products Details</a></li>
                                    <li><a href="/product-types/view">Product Types</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="icon-copy"></i> <span>Survey Layouts</span></a>
                                <ul>
                                    <li><a href="/admin/surveys/view">Surveys</a></li>
                                </ul>
                            </li>

                            <li>
                                <a href="#" target="_blank" ><i class="icon-file-download"></i><span>Download</span></a>
                            </li>


                        </ul>
                    </div>
                </div>
                <!-- /main navigation -->

            </div>
        </div>
        <!-- /main sidebar -->


        <!-- Main content -->
        <div class="content-wrapper">

            <!-- Page header -->
            <div class="page-header">
                <div class="page-header-content">
                    <div class="page-title">
                        <h4><i class="icon-arrow-left52 position-left"></i> <span class="text-semibold">Products</span> - Names</h4>
                    </div>
                </div>

                <div class="breadcrumb-line">
                    <ul class="breadcrumb">
                        <li><a href="/admin/home"><i class="icon-home2 position-left"></i> Home</a></li>
                        <li><a href="/products/view">Products</a></li>
                        <li class="active">Names</li>
                    </ul>
                </div>
            </div>
            <!-- /page header -->


            <!-- Content area -->
            <div class="content">

                <!-- Form horizontal -->
                <div class="panel panel-flat">
                    <div class="panel-heading">
                        <h5 class="panel-title">Add Product name</h5>
                        <div class="heading-elements">
                            <ul class="icons-list">
                                <li><a data-action="collapse"></a></li>
                                <li><a data-action="reload"></a></li>
                                <li><a data-action="close"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="panel-body">
                         <form class="form-horizontal" action="/product/add" method="post">
                            <fieldset class="content-group">

                                <div class="form-group">
                                    <label class="control-label col-lg-2">Name</label>
                                    <div class="col-lg-10">
                                        <input type="text" class="form-control" name="name">
                                    </div>
                                </div>


                            </fieldset>

                            <div class="text-right">
                                <button type="submit" class="btn btn-primary">Submit <i class="icon-arrow-right14 position-right"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- /form horizontal -->
                <!-- Basic datatable -->
                <div class="panel panel-flat">
                    <div class="panel-heading">
                        <h5 class="panel-title">Product names</h5>
                        <div class="heading-elements">
                            <ul class="icons-list">
                                <li><a data-action="collapse"></a></li>
                                <li><a data-action="reload"></a></li>
                                <li><a data-action="close"></a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="panel-body">
                        The <code>DataTables</code> is a highly flexible tool, based upon the foundations of progressive enhancement, and will add advanced interaction controls to any HTML table. DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function. Searching, ordering, paging etc goodness will be immediately added to the table, as shown in this example. <strong>Datatables support all available table styling.</strong>
                    </div>

                    <table class="table datatable-basic">
                        <thead>
                        <tr>
                            <th>Index</th>
                            <th>Name</th>
                            <th class="text-center">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%  List<Product> products = (List<Product>)request.getAttribute("products");
                            if(!Utils.isEmpty(products)){
                                int index = 1;
                                for(Product product : products){
                        %>
                        <tr>
                            <td><%=index++%></td>
                            <td><%=product.getName()%></td>
                            <td class="text-center">
                                <ul class="icons-list">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <i class="icon-menu9"></i>
                                        </a>

                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li><a href="/product/delete?id=<%=product.getId()%>">Delete</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        </tbody>
                    </table>
                </div>
                <!-- /basic datatable -->


                <!-- Footer -->
                <div class="footer text-muted">
                    &copy; <%=Constant.PUBLISHED_AT%>. <a href="#"><%=Constant.PROJECT_NAME%> </a> by <a href="#"
                                                                                                         target="_blank"><%=Constant.PUBLISHED_BY%></a>
                </div>
                <!-- /footer -->

            </div>
            <!-- /content area -->

        </div>
        <!-- /main content -->

    </div>
    <!-- /page content -->

</div>
<!-- /page container -->

</body>
</html>
