let shop ;
let routes = [
    "APIGateway",
    "Boutique",
    "Cave",
    "Coffre",
    "Combat",
    "Joueur",
    "Log",
    "Monstre"
];

window.onload = function(){
    checkAllRoute();
    getBoutique();
    getDollards();

    setInterval(function() {
        getBoutique();
    }, 100);
}

/**
 * Permet de savoir si tout les micros services sont up
 */
function checkAllRoute() {
    routes.forEach(function(route, index) {
        $.get("http://localhost:8080/" + route, function(data) {
            var tabId = "#tab_" + (index + 1);
            var color = data ? "green" : "red";
            $(tabId).css("color", color);

            setTimeout(function() {
                console.log(data,index)
            }, 100);
        });
    });
}

/**
 * Permet d'actualliser l'affichage le nombre de dollards
 */
function getDollards(){
    $.get("http://localhost:8080/API/Joueur/GetDollards", function(data) {
        $("#money").text("Dollards:"+data);
    });
}

/**
 * Ajout de 1 dollards
 */
function addOneDollards(){
    $.get("http://localhost:8080/API/Joueur/addOneDollards", function(data) {
        getDollards();
    });
}