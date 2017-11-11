window.onload = function () {
    var messageBox = document.getElementById("messageBox");

    // CÓDIGO DA MENSAGEM
    var messageCode = document.getElementById("message_code");
    var code = messageCode.innerHTML;

    // MENSAGEM
    var messageText = document.getElementById("message_text");
    var message = messageText.innerHTML;

    var intervalo = window.setInterval(function () {
        if (message !== '' && message !== undefined) {
            if (code === '-1') {
                messageBox.style.backgroundColor = "#ff0418";
            } else {
                messageBox.style.backgroundColor = "#0099ff";
            }

            messageBox.style.display = "block";
        }
    }, 50);

    window.setTimeout(function () {
        if (message !== '' && message !== undefined) {
            messageBox.style.display = "none";
            messageCode.innerHTML = '';
            messageText.innerHTML = '';
        }

        clearInterval(intervalo);
    }, 2000);

};