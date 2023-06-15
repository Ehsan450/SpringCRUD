let validateUsername = () => {
    let value = $("#username").val();
    let regex = /^[a-zA-Z0-9_]+$/;

    if (regex.test(value)) {
        $.get("/open/check-username/" + value, (response) => {
            if (response["isUnique"] === true) {
                $("#username_not_unique").hide();
            } else {
                $("#username_not_unique").show();
            }
        });
    }
}