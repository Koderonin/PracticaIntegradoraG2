const cardNumber = document.getElementById("card-number");
const cardHolderName = document.getElementById("card-holder-name");
const cardNameInput = document.getElementById("card-name-input");
const displayValidity = document.getElementById("validity");
const validityInput = document.getElementById("validity-input");
const cardNumberDisplay = document.querySelectorAll(".card-number-display");
const cvvInput = document.getElementById("cvv");
const cvvDisplay = document.getElementById("cvv-display");
const tipoTarjeta = document.getElementById("tipo_tarjeta");
const logoTarjetaF = document.getElementById("logo-tarjeta-front");
const logoTarjetaB = document.getElementById("logo-tarjeta-back");
let currentSpanIndex = 0;
cardNumber.addEventListener("input", () => {
    const inputNumber = cardNumber.value.replace(/\D/g, "");
    cardNumber.value = cardNumber.value.slice(0, 16).replace(/\D/g, "");
    for (let i = 0; i < cardNumberDisplay.length; i++) {
        if (i < inputNumber.length) {
            cardNumberDisplay[i].textContent = inputNumber[i];
        } else {
            cardNumberDisplay[i].textContent = "_";
        }
    }
    if (inputNumber.length <= cardNumberDisplay.length) {
        currentSpanIndex = inputNumber.length;
    } else {
        currentSpanIndex = cardNumberDisplay.length;
    }
});
logoTarjetaF.src = '/img/' + tipoTarjeta.selectedOptions[0].dataset.logo;
logoTarjetaB.src = logoTarjetaF.src
tipoTarjeta.addEventListener("change", () => {
    logoTarjetaF.src = '/img/' + tipoTarjeta.selectedOptions[0].dataset.logo;
    logoTarjetaB.src = logoTarjetaF.src
})
$.getJSON('http://tomcat.da2.dva:8080/api/sesion/cliente', function (data) {
    if (data != null) {
        cardHolderName.innerText = data.nombre + "  " + data.apellidos;
    }
});
validityInput.addEventListener("input", () => {
    const inputString = validityInput.value;
    if (inputString.length < 1) {
        displayValidity.innerText = "06/28";
        return false;
    }
    const parts = inputString.split("-");
    const year = parts[0].slice(2);
    const month = parts[1];
    //Final formatted string
    const formattedString = `${month}/${year}`;
    displayValidity.innerText = formattedString;
});
//cvv
cvvInput.addEventListener("input", () => {
    const userInput = cvvInput.value;
    const sanitizedInput = userInput.slice(0, 3);
    const numericInput = sanitizedInput.replace(/\D/g, "");
    cvvInput.value = numericInput;
    cvvDisplay.innerText = numericInput;
});
//Flip
cvvInput.addEventListener("click", () => {
    document.getElementById("card").style.transform = "rotateY(180deg)";
});
//Reflip card
document.addEventListener("click", () => {
    if (document.activeElement.id != "cvv") {
        document.getElementById("card").style.transform = "rotateY(0deg)";
    }
});
window.onload = () => {
    cvvInput.value = "";
    validityInput.value = "";
    cardNameInput.value = "";
    cardNumber.value = "";
};