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
                    ${form.result}
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
                                   data-fv-stringlength-message="The username must be more than 4 and less than 30 characters long"

                                   data-fv-regexp="true"
                                   data-fv-regexp-regexp="^[a-zA-Z0-9_\.]+$"
                                   data-fv-regexp-message="The username can only consist of alphabetical, number, dot and underscore">

                            <c:if test="${!empty form}">
                                <span class="form-control-feedback glyphicon
                                      ${!empty form && empty form.errors['username'] ? 'glyphicon-ok' : ''}
                                      ${!empty form && !empty form.errors['username'] ? 'glyphicon-remove' : ''}"
                                      ></span>
                                <c:if test="${!empty form.errors['username']}"><small class="help-block" data-fv-for="username" data-validator="perso" >${form.errors["username"]}</small></c:if>
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
                                       data-fv-stringlength-message="The password must be more than 4 characters long"

                                       data-fv-callback="true"
                                       data-fv-callback-message="The password is weak"
                                       data-fv-callback-callback="passwordStrength">
                                <span tabindex="100" class="add-on input-group-addon unmask_password"><i class="icon-eye-open glyphicon glyphicon-eye-open"></i></span>
                            </div>
                            <div class="progress password-progress">
                                <div id="strengthBar" class="progress-bar" role="progressbar" style="width: 0;"></div>
                            </div>
                            <c:if test="${!empty form}">
                                <c:if test="${!empty form.errors['password']}"><small class="help-block" data-fv-for="password">${form.errors["password"]}</small></c:if>
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
