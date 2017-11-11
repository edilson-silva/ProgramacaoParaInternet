window.onload = function () {
    
    var table = document.getElementById("mTable");
    
    if (table.rows.length === 1) {
        var intervalo = window.setInterval(function () {
            window.location = "filme";            
        }, 1000);

        window.setTimeout(function () {
            clearInterval(intervalo);
        }, 2000);
    }
};