<c:import url="inc/header.jsp" />
<div class="container">

    <div class="row">
        <h2 class="col-xs-12">${lang["MY_ACCOUNT"]}</h2>
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="row">

                <!-- Picture -->
                <div class="col-xs-12 col-md-2 text-center">
                    <form class="form-horizontal" id="user_account_picture_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                        <div class="account_username"><c:out value="${user_username}" /></div>

                        <div class="img_form">
                            <div class="form-group">
                                <div class="img_box img_box_user_account">
                                    <div class="img_overlay">
                                        <span class="fa fa-pencil edit_icon" aria-hidden="true"></span>
                                        <div class='img_overlay_loading'></div>
                                    </div>
                                    <img src="<c:url value="/images/pictures/${user.picture}" />" alt="Your picture"/>
                                </div>

                                <input type ="file" class="img_uploader" id="user_account_picture_upload" name="picture"
                                       data-fv-file="true"
                                       data-fv-file-extension="jpeg,jpg,png"
                                       data-fv-file-type="image/jpeg,image/png"
                                       data-fv-file-maxsize="1048576"
                                       data-fv-file-message="The file must be an image (jpg, jpeg, png) and less than 1MB"/>
                                <small class="help-block" data-fv-for="picture" data-validator="perso"></small>
                            </div>
                        </div>

                    </form>
                </div>

                <div class="col-xs-12 col-md-10">

                    <!-- Settings -->
                    <div class="row">
                        <fieldset class="col-xs-12">
                            <legend>${lang["SETTINGS"]}</legend>
                            <form class="form-horizontal ajax_forms" id="user_account_password_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                                <div class="row form-group no-feedback-icon">
                                    <label for="new_password" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["PASSWORD"]} : </label>
                                    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-8">
                                        <div class="row">
                                            <div class="col-xs-12 col-md-6">
                                                <div class="input_box input-group">
                                                    <input type="password" class="form-control ajax_forms_edit_input hidden" id="new_password" name="new_password" placeholder="New password"
                                                           data-fv-notempty="true"
                                                           data-fv-notempty-message="The password is required"

                                                           data-fv-stringlength="true"
                                                           data-fv-stringlength-min="4"
                                                           data-fv-stringlength-message="The password must be at least 4 characters"

                                                           data-fv-callback="true"
                                                           data-fv-callback-message="The password is weak"
                                                           data-fv-callback-callback="passwordStrength">
                                                    <div class="input_overlay"></div>
                                                    <div class="ajax_forms_edit_value">********** <span class="fa fa-pencil" aria-hidden="true"></span></div>
                                                    <span tabindex="100" class="add-on input-group-addon unmask_password ajax_forms_edit_input hidden"><span class="icon-eye-open glyphicon glyphicon-eye-open"></span></span>
                                                </div>
                                                <div class="progress password-progress ajax_forms_edit_input hidden">
                                                    <div id="strengthBar" class="progress-bar" role="progressbar" style="width: 0;"></div>
                                                </div>
                                                <small class="help-block" data-fv-for="new_password" data-validator="perso"></small>
                                            </div>
                                            <div class="col-xs-12 col-md-6">
                                                <div class="input_box input-group">
                                                    <input type="password" class="form-control ajax_forms_edit_input hidden" id="old_password" name="old_password" placeholder="Old password"
                                                           data-fv-notempty="true"
                                                           data-fv-notempty-message="The password is required">
                                                    <span class="input-group-btn ajax_forms_edit_input hidden">
                                                        <button class="btn btn-primary" type="submit">Ok</button>
                                                    </span>
                                                </div>
                                                <small class="help-block" data-fv-for="old_password" data-validator="perso"></small>
                                            </diV>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form class="form-horizontal ajax_forms" id="user_account_email_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                                <div class="row form-group">
                                    <label for="email" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["EMAIL_ADDRESS"]} : </label>
                                    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                                        <div class="input_box input-group">
                                            <input type="text" class="form-control ajax_forms_edit_input hidden" id="email" name="email" value="${user.email}"
                                                   data-fv-regexp="true"
                                                   data-fv-regexp-regexp="^[a-z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$"
                                                   data-fv-regexp-message="The value is not a valid email address">
                                            <div class="input_overlay"></div>
                                            <div class="ajax_forms_edit_value">
                                                <span class="ajax_forms_edit_value_text">${user.email}</span>
                                                <span class="fa fa-pencil" aria-hidden="true"></span>
                                            </div>

                                            <span class="input-group-btn ajax_forms_edit_input hidden">
                                                <button class="btn btn-primary" type="submit">Ok</button>
                                            </span>
                                        </div>
                                        <small class="help-block" data-fv-for="email" data-validator="perso"></small>
                                    </div>
                                </div>
                            </form>
                            <form class="form-horizontal ajax_forms" id="user_account_language_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                                <div class="row form-group">
                                    <label for="language" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["LANGUAGE"]} : </label>
                                    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                                        <div class="input_box input-group">
                                            <select class="form-control ajax_forms_edit_input hidden" name="language"
                                                    data-fv-notempty="true"
                                                    data-fv-notempty-message="The language is required">
                                                <c:forEach var="language" items="${listLanguages}">
                                                    <option value="${language.code}" <c:if test="${user.language == language}">selected="selected"</c:if>>${language.name}</option>
                                                </c:forEach>
                                            </select>
                                            <div class="input_overlay"></div>
                                            <div class="ajax_forms_edit_value">
                                                <span class="ajax_forms_edit_value_text">${lang["DISPLAY_NAME"]}</span>
                                                <span class="fa fa-pencil" aria-hidden="true"></span>
                                            </div>

                                            <span class="input-group-btn ajax_forms_edit_input hidden">
                                                <button class="btn btn-primary" type="submit">Ok</button>
                                            </span>
                                        </div>
                                        <small class="help-block"  data-fv-for="language" data-validator="perso"></small>
                                    </div>
                                </div>
                            </form>
                        </fieldset>
                    </div>

                    <!-- Personal information -->
                    <div class="row">
                        <fieldset class="col-xs-12">
                            <form class="form-horizontal ajax_forms" id="user_account_birthdate_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                                <legend>${lang["PERSONAL_INFORMATION"]}</legend>
                                <div class="row form-group">
                                    <label for="birthdate" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["BIRTH_DATE"]} : </label>
                                    <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                                        <div class="input_box input-group">
                                            <input type="text" class="form-control ajax_forms_edit_input hidden" id="birthdate" name="birthdate" value="${user.localBirthDate}" data-date-language="${lang["LANG_CODE"]}"
                                                   data-fv-regexp="true"
                                                   data-fv-regexp-regexp="^[0-9]{2,4}\/[0-9]{2,4}\/[0-9]{2,4}$"
                                                   data-fv-regexp-message="The value is not a valid date format">
                                            <div class="input_overlay"></div>
                                            <div class="ajax_forms_edit_value">
                                                <span class="ajax_forms_edit_value_text">${user.localBirthDate}</span>
                                                <span class="fa fa-pencil" aria-hidden="true"></span>
                                            </div>

                                            <span class="input-group-btn ajax_forms_edit_input hidden">
                                                <button class="btn btn-primary" type="submit">Ok</button>
                                            </span>
                                        </div>
                                        <small class="help-block" data-fv-for="birthdate" data-validator="perso"></small>
                                    </div>
                                </div>
                            </form>
                        </fieldset>
                    </div>

                </div>

            </div>
        </div>
    </div>

</div>

<c:import url="inc/footer.jsp" />
