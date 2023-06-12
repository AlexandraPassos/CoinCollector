function changePersonType() {
    let personType = document.querySelector('input[name="personType"]:checked').value;
    let cpfCnpjInputReference = document.getElementById("cpfCnpj");
    let cpfCnpjLabelReference = document.getElementById("cpfCnpj-label");

    if (personType === "PF") {
        cpfCnpjLabelReference.innerHTML='CPF:';
        cpfCnpjInputReference.placeholder='123.456.789-12';
        cpfCnpjInputReference.maxLength = 14;
    } else if (personType === "PJ") {
        cpfCnpjLabelReference.innerHTML='CNPJ:';
        cpfCnpjInputReference.placeholder='12.345.678/0001-00';
        cpfCnpjInputReference.maxLength = 18;
    }
}