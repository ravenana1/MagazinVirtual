$(document).ready(function() {
    console.log("bauuusdfa");
	$('#submit').click(function(){
	    var firstName = $("#firstName").val();
	    var lastName = $("#lastName").val();
	    var email = $("#email").val();
	    var telephone = $("#telephone").val();
	    var pwd = $("#pwd").val();
	    var address = $("#address").val();

        var registerData = {
        					firstName: firstName,
        					lastName: lastName,
        					email: email,
        					telephone: telephone,
        					password: pwd,
        					address: address
        			       };
        registrationData = JSON.stringify(registrationData);
        console.log("bauuusdfa");
        $.ajax({
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            type: "POST",
            url: "http://localhost/MagazinVirtual/registerUser",
            dataType : 'json',
            data: registrationData,
            contentType: 'application/json',
            success :function(data) {
                console.log("-------------" + data);
                if(data == 1){
                    $("#errorMessage").text("Email already registered. Please insert another email");
                }
                else  if(data == -1){
                    $("#errorMessage").text("Internal server error");
                }
                else if(data == 0){
                    $("#formRegister").remove();
                    $('body').append('<div id="success"> You can now login here</div>');
                }
            },
            async: false
        });
	});
});