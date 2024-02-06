/**
 * Affichage de la boutique
 */
// Fonction pour récupérer les données de la boutique
function getBoutique(){
    $.get(`http://localhost:8080/API/Boutique/Get`, function(data) {
        var testShop = JSON.parse(data);
        if (!isEqual(shop, testShop)) {
            shop = testShop;
            displayBoutique();
        }
    });
}

// Fonction pour afficher la boutique
function displayBoutique(){
    $.get(`http://localhost:8080/API/Boutique/Get`, function(data) {
        var elements = JSON.parse(data);
        var shopDiv = document.querySelector('.sousshop');
        shopDiv.innerHTML = '';
        elements.forEach(function(element) {
            var itemDiv = document.createElement('div');
            itemDiv.classList.add('item');

            var img = document.createElement('img');
            img.classList.add('shoppicture');
            img.onclick = function() { buyItem(element,itemDiv); };
            if (element.nom == "oeufs"){
                img.src = 'front/img/oeuf.jpg';
            }else{
                img.src = 'front/img/incubateur_vide.png';
            }


            var title = document.createElement('h4');
            title.textContent = element.quantity + ' ' + element.nom + ' ' + element.price + '$';



            itemDiv.appendChild(img);
            itemDiv.appendChild(title);
            shopDiv.appendChild(itemDiv);
        });

    });
}

/**
 * Achat dans la boutique d'un item
 */
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

/**
 * Refresh la boutique
 */
function refreshShop(){
    $.get(`http://localhost:8080/API/Boutique/refresh`, function(data) {
        getDollards();
    });

}