<!DOCTYPE html>
<html>
    <head>
        <title>Eword</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootflat/2.0.4/css/bootflat.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/0.8.2/css/flag-icon.min.css">
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.formvalidation/0.6.1/js/formValidation.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.formvalidation/0.6.1/js/framework/bootstrap.min.js"></script>
        <script src="//cdn.bootcss.com/zxcvbn/4.4.1/zxcvbn.js"></script>
        <script src="<c:url value="/js/script.js"/>"></script>
    </head>

    <body>
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li ${navbar == "home" ? 'class="active"' : ""}><a href="<c:url value="/" />">HOME</a></li>
                        <c:if test="${!empty user_id}">
                        <li ${navbar == "lists" ? 'class="active"' : ""}><a href="<c:url value="/lists" />">LISTS</a></li>
                        </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${!empty user_id}">
                        <li><a href="<c:url value="/signout" />">SIGN OUT</a></a></li>
                        </c:if>
                        <c:if test="${empty user_id}">
                        <li class="dropdown signin_form_navbar">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Sign in  <span class="caret"></span></a>
                            <ul class="dropdown-menu" style="padding: 15px;min-width: 250px;">
                                <li>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="signin_form" role="form" method="post" action="">
                                                <div class="form-group">
                                                    <label class="sr-only" for="navbar_username">*Username</label>
                                                    <input type="text" class="form-control" id="navbar_username" placeholder="Username" name="signin_username"
                                                           data-fv-notempty="true"
                                                           data-fv-notempty-message="The username is required">
                                                </div>
                                                <div class="form-group">
                                                    <label class="sr-only" for="navbar_password">*Password</label>
                                                    <input type="password" class="form-control" id="navbar_password" placeholder="Password" name="signin_password"
                                                           data-fv-notempty="true"
                                                           data-fv-notempty-message="The password is required">
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary btn-block" name="signin_submit" id="signin_submit" >Sign in</button>
                                                </div>
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox"> Remember me
                                                    </label>
                                                </div>
                                                <div class="text-center" id="signin_feedback"></div>
                                            </form>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li><a ${navbar == "signup" ? 'class="active"' : ""} href="<c:url value="/signup"/>" id="signup">Sign up</a></li>
                        </c:if>
                    <li class="dropdown language_picker">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="flag-icon flag-icon-${empty language ? "us" : language}"></span> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="<c:url value="/lang/us" />"><span class="flag-icon flag-icon-us"></span> English</a></li>
                            <li><a href="<c:url value="/lang/fr" />"><span class="flag-icon flag-icon-fr"></span> Français</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <header>
            <div class="container">
                <div class="row">
                    <div class="col-xs-2 header_title">Eword</div>
                    <div class="col-xs-2 header_image"></div>
                </div>
            </div>
        </header>

        <div class="content">