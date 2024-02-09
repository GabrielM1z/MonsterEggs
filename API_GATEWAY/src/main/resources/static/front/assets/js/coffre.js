/**
 * Affichage des monstres de l'équipe
 */
// Fonction pour récupérer les données des poches
function getCoffre(){
    $.get(`http://localhost:8080/API/Coffre/Get`, function(data) {
        var testCoffre = JSON.parse(data);
        if (!isEqual(coffre, testCoffre)) {
            coffre = testCoffre;
            displayCoffre();
        }
    });
}

// Fonction pour afficher l'equipe
function displayCoffre(){
    $.get(`http://localhost:8080/API/Coffre/Get`, function(data) {
        var elements = JSON.parse(data);
        document.querySelector('.coffre-container').innerHTML = '';
        elements.forEach(function(element, index) {
            var coffreDiv = document.createElement('div');
            coffreDiv.classList.add('coffre-item');

            var nomMonstre = document.createElement('h3');
            nomMonstre.textContent = element.nom;

            var imgMonstre = document.createElement('img');
            imgMonstre.classList.add('coffrepicture');
            imgMonstre.src = liste_img_monstre[element.nom]; // Utilisation de la liste_img_monstre pour récupérer le chemin de l'image

            coffreDiv.appendChild(nomMonstre);
            coffreDiv.appendChild(imgMonstre);

            // Ajouter coffreDiv à l'élément de la page où vous voulez l'ajouter
            // Par exemple, si vous avez une div avec la classe 'coffre-container' dans votre HTML :
            document.querySelector('.coffre-container').appendChild(coffreDiv);
        });
    });
}
