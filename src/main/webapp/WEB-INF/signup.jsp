<c:import url="inc/header.jsp" />
<div class="container">

    <div class="row">
        <h2 class="col-xs-12">Sign Up</h2>
    </div>

    <c:if test="${!empty form}">
        <div class="row">
            <div class="col-xd-12">
                <div class="alert alert-${empty form.errors ? "success" : "danger"} alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <c:choose>
                        <c:when test="${empty form.errors}">
                            You successfully registered ! We're pleased that you have chosen to become part of the community.<br />
                            <ul>
                                <li><a href="<c:url value="/" />">Return to the home page</a></li>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            Your registration failed
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </c:if>


    <c:if test="${empty form || !empty form.errors}">
        <div class="row">
            <form class="form-horizontal col-lg-12" id="signup_form" action="<c:url value="/signup" />" method="POST">

                <div class="row">
                    <div class="form-group
                         ${!empty form && empty form.errors['username'] ? 'has-feedback has-success' : ''}
                         ${!empty form && !empty form.errors['username'] ? 'has-feedback has-error' : ''}">
                        <label for="username" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">*${lang["USERNAME"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <input type="text" class="form-control" id="signup_username" name="username" value="${user.username}"
                                   data-fv-notempty="true"
                                   data-fv-notempty-message="The username is required"

                                   data-fv-stringlength="true"
                                   data-fv-stringlength-min="4"
                                   data-fv-stringlength-max="30"
                                   data-fv-stringlength-message="The username must be at least 4 and no more than 30 characters long">

                            <c:if test="${!empty form}">
                                <span class="form-control-feedback glyphicon
                                      ${!empty form && empty form.errors['username'] ? 'glyphicon-ok' : ''}
                                      ${!empty form && !empty form.errors['username'] ? 'glyphicon-remove' : ''}"
                                      ></span>
                                <c:if test="${!empty form.errors['username']}"><small class="help-block" data-fv-for="username" data-validator="perso">${form.errors["username"]}</small></c:if>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group
                         ${!empty form && !empty form.errors['password'] ? 'has-feedback has-error' : ''}">
                        <label for="password" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2  control-label">*${lang["PASSWORD"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <div class="input-append input-group">
                                <input type="password" class="form-control" id="signup_password" name="password"
                                       data-fv-notempty="true"
                                       data-fv-notempty-message="The password is required"
                                       data-fv-stringlength="true"

                                       data-fv-stringlength-min="4"
                                       data-fv-stringlength-message="The password must be at least 4 characters"

                                       data-fv-callback="true"
                                       data-fv-callback-message="The password is weak"
                                       data-fv-callback-callback="passwordStrength">
                                <span tabindex="100" class="add-on input-group-addon unmask_password"><span class="icon-eye-open glyphicon glyphicon-eye-open"></span></span>
                            </div>
                            <div class="progress password-progress">
                                <div id="strengthBar" class="progress-bar" role="progressbar" style="width: 0;"></div>
                            </div>
                            <c:if test="${!empty form}">
                                <c:if test="${!empty form.errors['password']}"><small class="help-block" data-fv-for="password" data-validator="perso">${form.errors["password"]}</small></c:if>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-xs-offset-9 col-xs-1 col-sm-offset-5 col-sm-1">
                        <button type="submit" class="pull-right btn btn-primary" name="signup_submit">${lang["SIGN_UP"]}</button>
                    </div>
                </div>

            </form>
        </div>
    </c:if>
</div>

<c:import url="inc/footer.jsp" />
