let shop ;
let poche ;
let equipe ;
let coffre ;
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

var liste_img_monstre = {
    "Tentacouille": 'front/img/Monstre1.jpg',
    "DamienTg": 'front/img/Monstre2.jpg',
    "GabBravo": 'front/img/Monstre3.jpg',
    "MaxOuTonPere": 'front/img/Monstre4.jpg',
    "Selamouche": 'front/img/Monstre5.jpg',
    "QuoiCouBERH": 'front/img/Monstre6.jpg',
    // Ajoutez d'autres monstres avec leur chemin d'image
};

window.onload = function(){
    checkAllRoute();
    getBoutique();

    getPoche();
    getEquipe();
    getCoffre() ;

    setInterval(function() {
        getBoutique();
        getPoche();
        getEquipe();
        getCoffre();
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