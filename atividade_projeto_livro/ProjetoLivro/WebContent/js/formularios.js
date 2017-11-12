const BACKSPACE = 8;
const NUMBER_BEGIN = 47;
const NUMBER_END = 58;

var dataLancamento = document.getElementById("data_lancamento");
dataLancamento.addEventListener("keypress", function (event) {

    var KEY_PRESSED = event.which || event.keyCode;
    var valor = this.value;

    if (KEY_PRESSED === BACKSPACE) {
        if (valor[valor.length - 2] === '/') {
            this.value = valor.substring(0, valor.length - 1);
        }
    }

    if (KEY_PRESSED > NUMBER_BEGIN && KEY_PRESSED < NUMBER_END) {
        if (valor.length === 2 || valor.length === 5) {
            this.value = valor + '/';
        }
    }

});

var duracao = document.getElementById("duracao");
duracao.addEventListener("keypress", function (event) {

    var KEY_PRESSED = event.which || event.keyCode;
    var valor = this.value;

    if (KEY_PRESSED === BACKSPACE) {
        if (valor[valor.length - 2] === ':') {
            this.value = valor.substring(0, valor.length - 1);
        }
    }

    if (KEY_PRESSED > NUMBER_BEGIN && KEY_PRESSED < NUMBER_END) {
        if (valor.length === 2 || valor.length === 5) {
            this.value = valor + ':';
        }
    }

});