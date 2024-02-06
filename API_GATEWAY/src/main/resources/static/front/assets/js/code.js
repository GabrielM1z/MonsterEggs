let shop ;
let poche ;
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
    getPoche();

    setInterval(function() {
        getBoutique();
        getPoche();
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


/**
 * TOOLBOX
 */

// Fonction pour vérifier l'égalité de deux objets JSON
function isEqual(obj1, obj2) {
    return JSON.stringify(obj1) === JSON.stringify(obj2);
}