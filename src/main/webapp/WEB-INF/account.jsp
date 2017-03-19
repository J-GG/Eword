<c:import url="inc/header.jsp" />
<div class="container">

    <div class="row">
        <h2 class="col-xs-12">${lang["MY_ACCOUNT"]}</h2>
    </div>

    <div class="row">
        <div class="col-xs-12 col-md-2">
            <div class="row text-center">
                <div class="account_username"><c:out value="${user_username}" /></div>
            </div>
            <div class="row">
                <div class="t">
                    <form class="img_form" id="user_account_picture_form" action="<c:url value="/membership/account" />" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <div class="img_box img_box_user_account">
                                <div class="img_overlay">
                                    <span class="fa fa-pencil-square-o edit_icon" aria-hidden="true"></span>
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
                            <small class="help-block" data-validator="perso"></small>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-xs-12 col-md-10">
            <div class="row">
                <fieldset class="form-group col-xs-12">
                    <legend>${lang["SETTINGS"]}</legend>
                    <div class="row form-group">
                        <label for="old_password" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["PASSWORD"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <div class="account_edit_value">****** <span class="fa fa-pencil-square-o text-success" aria-hidden="true"></span></div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="email" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["EMAIL_ADDRESS"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <div class="account_edit_value">${user.email} <span class="fa fa-pencil-square-o text-success" aria-hidden="true"></span></div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <label for="email" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["LANGUAGE"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <div class="account_edit_value">${lang["DISPLAY_NAME"]} <span class="fa fa-pencil-square-o text-success" aria-hidden="true"></span></div>
                        </div>
                    </div>

                </fieldset>
            </div>

            <div class="row">
                <fieldset class="form-group col-xs-12">
                    <legend>${lang["PERSONAL_INFORMATION"]}</legend>
                    <div class="row form-group">
                        <label for="username" class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-2 control-label">${lang["BIRTH_DATE"]} : </label>
                        <div class="col-xs-offset-2 col-xs-8 col-sm-offset-0 col-sm-4">
                            <div class="account_edit_value"><c:out value="${user.localBirthDate}" default="N/A"/> <span class="fa fa-pencil-square-o text-success" aria-hidden="true"></span></div>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

</div>

<c:import url="inc/footer.jsp" />
