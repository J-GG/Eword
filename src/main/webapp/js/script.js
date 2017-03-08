$(document).ready(function() {
    $('.datatable').DataTable();

    //****** SIGN UP FORM ******//
    $('#signup_form').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    }).on('err.validator.fv', function(e, data) {
        data.element
                .data('fv.messages')
                .find('.help-block[data-fv-for="' + data.field + '"]').hide()
                .filter('[data-fv-validator="' + data.validator + '"]').show();
    });

    $(".unmask_password").click(function()
    {
        input = $(this).siblings("input");
        icon = $(this).children("i");
        if (input.prop("type") === "password")
        {
            input.prop("type", "text");
            icon.removeClass("glyphicon-eye-open");
            icon.addClass("glyphicon-eye-close");
        } else
        {
            input.prop("type", "password");
            icon.removeClass("glyphicon-eye-close");
            icon.addClass("glyphicon-eye-open");
        }
    });

    //Remove the success/error icon for the password fiel as there is already the 'unmask" icon
    $("#signup_form [data-fv-icon-for='password']").remove();

    //After typing a character, remove the icon added on the server-side to leave only the javascript-generated icons
    $("#signup_form input").keyup(function(event)
    {
        $(this).siblings("span:not(.add-on)").remove();
    });

    $("#signup_username").keyup(function()
    {
        $("[data-validator='perso']").remove();
    });

    //****** END SIGN UP FORM ******//

    //****** SIGN IN FORM ******//
    //AJAX request to authenticate the user
    $("#signin_form").submit(function(event)
    {
        data = {username: $("#navbar_username").val(), password: $("#navbar_password").val(), remember_me: $("#navbar_remember_me").is(':checked')};
        text = $("#signin_submit").text();
        $("#signin_submit").html("<div class='loading'>&nbsp</div>");
        $("#signin_submit").prop("disabled", true);

        $.post({
            url: "/Eword/authenticate",
            data: data,
            success: function(authenticationData)
            {
                $("#signin_submit").text(text);
                $("#signin_feedback").text(authenticationData.message);

                if (authenticationData.authentication)
                {
                    location.reload();
                    $("#signin_feedback").show().addClass("text-success").removeClass("text-danger").delay(1500).fadeOut(1500);
                } else
                {
                    $("#signin_submit").prop("disabled", false);
                    $("#signin_feedback").show().removeClass("text-success").addClass("text-danger").delay(1500).fadeOut(1500);
                }
            }
        });

        return false;
    });
    //****** END SIGN IN FORM ******//

});

function passwordStrength(value, validator) {
    var password = value;
    if (password === '') {
        return true;
    }
    var result = zxcvbn(password),
            score = result.score;

    // Update the progress bar width and add alert class
    var $bar = $('#strengthBar');
    switch (score) {
        case 0:
            $bar.attr('class', 'progress-bar progress-bar-danger')
                    .css('width', '1%');
            break;
        case 1:
            $bar.attr('class', 'progress-bar progress-bar-danger')
                    .css('width', '25%');
            break;
        case 2:
            $bar.attr('class', 'progress-bar progress-bar-success')
                    .css('width', '50%');
            break;
        case 3:
            $bar.attr('class', 'progress-bar progress-bar-success')
                    .css('width', '75%');
            break;
        case 4:
            $bar.attr('class', 'progress-bar progress-bar-success')
                    .css('width', '100%');
            break;
    }

    // We will treat the password as an invalid one if the score is less than 2
    if (score < 2) {
        return false;
    }

    return true;
}