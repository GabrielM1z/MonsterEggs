
let timer = 86400; // 24 hours in seconds
window.onload = function(){
    for (let i = 0; i < 10; i++) {
        newImage()
    }
    createItem()
    countdown(); // Lancer le compte à rebours au chargement de la page
    getDollards();
}

function newImage(){
    const inventory = document.querySelector('.inventory');
    const placeholderImg = document.createElement('img');
    placeholderImg.style.maxWidth = '200px';
    placeholderImg.style.maxHeight = '200px';
    placeholderImg.src = 'front/img/Albus.png'; // Ajouter le chemin de votre image placeholder
    inventory.appendChild(placeholderImg);
}

function countdown() {
    const timerElement = document.getElementById('timer');
    const timerInterval = setInterval(updateTimer, 1000);

    function updateTimer() {
        if (timer > 0) {
            timer--;
            const hours = Math.floor(timer / 3600);
            const minutes = Math.floor((timer % 3600) / 60);
            const seconds = timer % 60;
            timerElement.textContent = `${hours}:${minutes}:${seconds}`;
        } else {
            clearInterval(timerInterval);
            explose();
        }
    }
}

function getDollards(){
    $.get("http://localhost:8080/API/Joueur/GetDollards", function(data) {
        $("#money").text("Dollards:"+data);
    });
}

function explose() {
    alert("Boom! Le temps est écoulé!");
    //appel API shop refresh
}

function refreshShop() {
    //appel API shop refresh
    alert("Le magasin a été rafraîchi!");
}


function createItem(){
    $.get(`http://localhost:8080/API/Boutique/CreateItem`, function(data) {
        console.log("createItem() - Retour creation =", data);
    });

    setTimeout(function (){
        $.get(`http://localhost:8080/API/Boutique/Get`, function(data) {
            console.log("createItem() - Retour get =", data);
        });
    }, 150);

}

function buyItem(element){
    //element.parentNode.remove();
    element.src = "front/img/emptyShop.png";
    var itemId = element.id;
    console.log(itemId)
    $.get(`http://localhost:8080/API/Boutique/BuyItem/${itemId}`, function(data) {
        console.log("Retour click =", data);
    });
}

function MoneyADD(){
    $.get(`http://localhost:8080/API/Joueur/AddDollards`, function(data) {
        console.log("Retour1 =", data);
    });
    setTimeout(getDollards, 150);

}

function MoneyRemove(){
    $.get(`http://localhost:8080/API/Joueur/RemoveDollards`, function(data) {
        console.log("Retour2 =", data);
        setTimeout(getDollards, 150);
    });
}

function eclosion(){
    $.get(`http://localhost:8080/API/Monstre/Creation`, function(data) {
        console.log("Retour2 =", data);
        setTimeout(getDollards, 150);
    });
}