var Contact = {

    addMessage:function (message) {
        var alertTemplate = Handlebars.compile($("#template-alert-message").html());
        $("#message-holder").html(alertTemplate({message:message}));
        $("#alert-message").alert().delay(5000).fadeOut("fast", function () {
            $(this).remove();
        })
    }
};

$(document).ready(function () {

    var feedBackMessage = $(".messageblock");
    if (feedBackMessage.length > 0) {
        Contact.addMessage(feedBackMessage.text());
    }
});