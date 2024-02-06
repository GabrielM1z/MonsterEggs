/**
 * Affichage des poches
 */
// Fonction pour récupérer les données des poches
function getPoche(){
    $.get(`http://localhost:8080/API/Inventaire/Get`, function(data) {
        console.log(data)
        var testPoche = JSON.parse(data);
        if (!isEqual(poche, testPoche)) {
            poche = testPoche;
            displayPoche();
        }
    });
}

// Fonction pour afficher les poches
function displayPoche(){
    $.get(`http://localhost:8080/API/Inventaire/Get`, function(data) {
        var elements = JSON.parse(data);
        var poche = document.querySelector('.poche');
        poche.innerHTML = ''
        elements.forEach(function (element) {
            if (element.type == "oeufs") {
                var itemDiv = document.createElement('div');
                var itemDiv = document.createElement('div');
                itemDiv.classList.add('item');

                var img = document.createElement('img');
                img.classList.add('itemPoche');
                img.src = 'front/img/oeuf.jpg';
                var title = document.createElement('h4');
                title.textContent = element.type + ' ' + element.quantity;

                // Créer un bouton pour vendre un œuf
                var sellButton = document.createElement('button');
                sellButton.textContent = 'Vendre 1 œuf';
                sellButton.onclick = function() { vendreOeuf(); };

                // Créer un bouton pour mettre dans un incubateur
                var incubatorButton = document.createElement('button');
                incubatorButton.textContent = 'Mettre dans un incubateur';
                incubatorButton.onclick = function() { incuberOeuf(); };

                itemDiv.appendChild(img);
                itemDiv.appendChild(title);
                itemDiv.appendChild(sellButton);
                itemDiv.appendChild(incubatorButton);
                poche.appendChild(itemDiv);
            }
        });
    });
}

function vendreOeuf(){
    console.log("Vente")
    $.get("http://localhost:8080/API/VendreOeuf", function(data) {
        getDollards();
    });
}

function incuberOeuf(){
    console.log("incuber oeuf")
}