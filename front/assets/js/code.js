$(document).ready(function() {
    $.get("http://localhost:8081/helloTest", function(data) {
        $("#helloMessage").text(data);
    });
});


let timer = 86400; // 24 hours in seconds

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

function explose() {
    alert("Boom! Le temps est écoulé!");
    //appel API shop refresh
}

function refreshShop() {
   	//appel API shop refresh
    alert("Le magasin a été rafraîchi!");
}

// Générer 10 images pour l'inventaire
document.addEventListener('DOMContentLoaded', () => {
    const inventory = document.querySelector('.inventory');
    for (let i = 0; i < 10; i++) {
        const placeholderImg = document.createElement('img');
        placeholderImg.style.maxWidth = '200px';
        placeholderImg.style.maxHeight = '200px';
        placeholderImg.src = 'img/Albus.png'; // Ajouter le chemin de votre image placeholder
        inventory.appendChild(placeholderImg);
    }
});

countdown(); // Lancer le compte à rebours au chargement de la page