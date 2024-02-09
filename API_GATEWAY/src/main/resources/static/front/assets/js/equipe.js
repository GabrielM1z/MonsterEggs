/**
 * Affichage des monstres de l'équipe
 */
// Fonction pour récupérer les données des poches
function getEquipe(){
    $.get(`http://localhost:8080/API/Equipe/Get`, function(data) {
        var testEquipe = JSON.parse(data);
        if (!isEqual(equipe, testEquipe)) {
            equipe = testEquipe;
            displayEquipe();
        }
    });
}

// Fonction pour afficher l'equipe
function displayEquipe(){
    $.get(`http://localhost:8080/API/Equipe/Get`, function(data) {
        var elements = JSON.parse(data);

        for (var index = 0; index < 6; index++) {
            let element = elements[index];
            var cellID = "EquipeCell" + (index + 1);
            var cell = document.getElementById(cellID);
            cell.innerHTML ='';
            if(element != undefined) {
                var img = document.createElement('img');
                img.classList.add('equipepicture');
                img.src = liste_img_monstre[element.nom];

                // Créer des boutons pour mettre dans le coffre, combattre et vendre
                var boutonCoffre = document.createElement('button');
                boutonCoffre.textContent = 'Mettre dans le coffre';
                boutonCoffre.onclick = function () {
                    mettreDansCoffre(element, cellID);
                };

                var boutonCombattre = document.createElement('button');
                boutonCombattre.textContent = 'Combattre';
                boutonCombattre.onclick = function () {
                    combattreAvec(element);
                };

                var boutonVendre = document.createElement('button');
                boutonVendre.textContent = 'Relacher';
                boutonVendre.onclick = function () {
                    relacherElement(element);
                };

                cell.appendChild(img);
                cell.appendChild(boutonCoffre);
                cell.appendChild(boutonCombattre);
                cell.appendChild(boutonVendre);
            }
        }
    });
}



function mettreDansCoffre(element,cellID){
    console.log(element,cellID)
    document.getElementById(cellID).innerHTML = '';
    console.log("transfert")
    id = element.id
    $.get("http://localhost:8080/API/TransfertEquipeCoffre/"+id, function(data) {
        console.log(data)
    });
}

function combattreAvec(element){
    var radios = document.querySelectorAll('input[name="difficulte"]');

// Parcourez les boutons radio pour trouver celui qui est coché
    var valeurChoisie;

    radios.forEach(function(radio) {
        if (radio.checked) {
            valeurChoisie = radio.value;
        }
    });

    alert("Ouais tkt je pars à la guerre avec " + valeurChoisie + " fois rien !")
}

function relacherElement(element){
    alert("En construction chef !")
}