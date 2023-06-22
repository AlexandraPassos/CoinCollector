function cepSearch(valor) {
    let cep = valor.replace(/\D/g, '');
    if (cep === "") {
        cleanAddressFields();
        return;
    }
    let validateCep = /^[0-9]{8}$/;
    if (!validateCep.test(cep)) {
        cleanAddressFields();
        alert("Formato de CEP inválido.");
        return;
    }
    waitingFormFields();
    fetchCepData(cep);
}
function viaCepReceiver(json) {
    if ("erro" in json) {
        cleanAddressFields();
        alert("CEP inválido");
        return;
    }
    document.getElementById('address').value=(json.logradouro);
    document.getElementById('district').value=(json.bairro);
    document.getElementById('city').value=(json.localidade);
    document.getElementById('state').value=(json.uf);
}
function fetchCepData(cep) {
    let script = document.createElement('script');
    script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=viaCepReceiver';
    document.body.appendChild(script);
}

function cleanAddressFields() {
    document.getElementById('address').value=("");
    document.getElementById('district').value=("");
    document.getElementById('city').value=("");
    document.getElementById('state').value=("");
}

function waitingFormFields() {
    document.getElementById('address').value="...";
    document.getElementById('district').value="...";
    document.getElementById('city').value="...";
    document.getElementById('state').value="...";
}
