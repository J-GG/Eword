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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.formvalidation/0.6.1/js/formValidation.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.formvalidation/0.6.1/js/framework/bootstrap.min.js"></script>
        <script src="//cdn.bootcss.com/zxcvbn/4.4.1/zxcvbn.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/locales/bootstrap-datepicker.fr.min.js"></script>
        <script src="<c:url value="/js/script.js"/>"></script>
    </head>

    <body>
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <ul class="nav navbar-nav">
                    <li ${navbar == "home" ? 'class="active"' : ""}><a href="<c:url value="/" />">${lang["HOME"]}</a></li>
                        <c:if test="${!empty user_id}">
                        <li ${navbar == "lists" ? 'class="active"' : ""}><a href="<c:url value="/lists" />">${lang["LISTS"]}</a></li>
                        </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${!empty user_id}">
                        <li class="dropdown membership_navbar  ${navbar == "membership" ? 'active' : ""}">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <span class="picture_membership_navbar" style="background-image:url('<c:url value="/images/pictures/${user_picture}" />')" alt="Your picture" /></span>
                                <span class="username_membership_navbar"><c:out value="${user_username}" /> <span class="caret"></span></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="<c:url value="/membership/account" />"><span class="fa fa-user" aria-hidden="true"></span> ${lang["MY_ACCOUNT"]}</a></li>
                                <li><a href="<c:url value="/signout" />"><span class="fa fa-power-off" aria-hidden="true"></span> ${lang["SIGN_OUT"]}</a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${empty user_id}">
                        <li class="dropdown signin_form_navbar">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${lang["SIGN_IN"]}  <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <form id="signin_form" role="form" method="post" action="<c:url value="/authenticate" />">
                                                <div class="form-group">
                                                    <label class="sr-only" for="navbar_username">*Username</label>
                                                    <input type="text" class="form-control" id="navbar_username" placeholder="${lang["USERNAME"]}" name="signin_username">
                                                </div>
                                                <div class="form-group">
                                                    <label class="sr-only" for="navbar_password">*Password</label>
                                                    <input type="password" class="form-control" id="navbar_password" placeholder="${lang["PASSWORD"]}" name="signin_password">
                                                </div>
                                                <div class="checkbox">
                                                    <label>
                                                        <input type="checkbox" name="signin_remember_me" id="navbar_remember_me"> ${lang["REMEMBER_ME"]}
                                                    </label>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="btn btn-primary btn-block" name="signin_submit" id="signin_submit" >${lang["SIGN_IN"]}</button>
                                                </div>
                                                <div class="text-center" id="signin_feedback"></div>
                                            </form>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li><a ${navbar == "signup" ? 'class="active"' : ""} href="<c:url value="/signup"/>" id="signup">${lang["SIGN_UP"]}</a></li>
                        </c:if>
                    <li class="dropdown language_picker_navbar">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="flag-icon flag-icon-${lang["LANG_CODE"]}"></span> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <c:forEach var="language" items="${listLanguages}">
                                <li><a href="<c:url value="/lang/${language.code}" />"><span class="flag-icon flag-icon-${language.code}"></span> ${language.name}</a></li>
                                </c:forEach>
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