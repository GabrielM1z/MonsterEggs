
let timer = 86400; // 24 hours in seconds
window.onload = function(){
    for (let i = 0; i < 10; i++) {
        newImage()
    }
    checkAllRoute();
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

function checkAllRoute() {
    var routes = [
        "APIGateway",
        "Boutique",
        "Cave",
        "Coffre",
        "Combat",
        "Joueur",
        "Log",
        "Monstre"
        // Ajoutez d'autres routes ici si nécessaire
    ];

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
            var elements = JSON.parse(data);
            var shopDiv = document.querySelector('.sousshop');

            elements.forEach(function(element) {
                var itemDiv = document.createElement('div');
                itemDiv.classList.add('item');


                var img = document.createElement('img');
                img.classList.add('shoppicture');
                img.onclick = function() { buyItem(element,itemDiv); };
                if (element.nom == "Oeufs"){
                    img.src = 'front/img/O1.png';
                }else{
                    img.src = 'front/img/I1.png';
                }


                var title = document.createElement('h4');
                title.textContent = element.quantity + ' ' + element.nom + ' ' + element.price + '$';



                itemDiv.appendChild(img);
                itemDiv.appendChild(title);


                shopDiv.appendChild(itemDiv);
            });
        });
    }, 100);

}

function buyItem(element,itemDiv){
    itemId = element.id;
    console.log(element)

    $.get(`http://localhost:8080/API/Boutique/BuyItem/${itemId}`, function(data) {
        if(data == "ok"){
            itemDiv.remove();
            getDollards();
        }else{
            alert(data)
        }
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