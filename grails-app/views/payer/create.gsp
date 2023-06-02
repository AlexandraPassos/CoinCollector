<html>
<head>
    <meta name="layout" content="main" >
    <title>Registrar Novo Pagador</title>
</head>
<body>
    <h1>Registro de Novo Pagador</h1>
    <form action="${createLink(controller: 'payer', action: 'save')}" method="post">
        <div>
            <label for="name">Nome:</label>
            <input type="text" name="name" id="name" placeholder="Digite seu nome">
        </div>

        <div>
            <label for="email">E-mail:</label>
            <input type="email" name="email" placeholder="seuemail@exemplo.com" id="email">
        </div>

        <div>
            <label for="personType">Tipo de Pagador:</label>
            <input type="radio" name="personType" id="pfRadio" value="PF" onchange="changePersonType()" checked> <label for="pfRadio">Pessoa Física</label>
            <input type="radio" name="personType" id="pjRadio" value="PJ" onchange="changePersonType()"> <label for="pjRadio">Pessoa Jurídica</label>

            <div id="cpfCnpj-container">
                <label for="cpfCnpj" id="cpfCnpj-label">CPF:</label>
                <input type="text" name="cpfCnpj" placeholder="123.456.789-12" maxLength=14 id="cpfCnpj">
            </div>

        </div>

        <div>
            <label for="cep">CEP:</label>
            <input type="text" name="cep" placeholder="12345-678" maxLength=9 id="cep"/>
        </div>

        <div>
            <label for="state">Estado:</label>
            <input type="text" name="state" placeholder="Digite seu estado" id="state">
        </div>

        <div>
            <label for="city">Cidade:</label>
            <input type="text" name="city" placeholder="Digite sua cidade" id="city">
        </div>

        <div>
            <label for="district">Bairro:</label>
            <input type="text" name="district" placeholder="Digite seu bairro" id="district">
        </div>

        <div>
            <label for="address">Endereço:</label>
            <input type="text" name="address" placeholder="Digite seu endereço" id="address">
        </div>

        <div>
            <label for="addressNumber">Número do endereço:</label>
            <input type="text" name="addressNumber" placeholder="Digite o número do endereço" id="addressNumber">
        </div>

        <div>
            <label for="complement">Complemento:</label>
            <input type="text" name="complement" placeholder="Apt. 32" id="complement">
        </div>

        <div>
            <label for="phoneNumber">Celular:</label>
            <input type="text" name="phoneNumber" placeholder="(47) 99999-9999" id="phoneNumber"/>
        </div>

        <button type="submit">Salvar</button>

    </form>

        <script>
            function changePersonType() {
                var personType = document.querySelector('input[name="personType"]:checked').value;
                var cpfCnpjInputReference = document.getElementById("cpfCnpj")
                var cpfCnpjContainer = document.getElementById("cpfCnpj-container");
                var cpfCnpjLabelReference = document.getElementById("cpfCnpj-label");

                if (personType === "PF") {
                    cpfCnpjLabelReference.innerHTML='CPF:'
                    cpfCnpjInputReference.placeholder='123.456.789-12'
                    cpfCnpjInputReference.maxLength = 14; 
                } else if (personType === "PJ") {
                    cpfCnpjLabelReference.innerHTML='CNPJ:'
                    cpfCnpjInputReference.placeholder='12.345.678/0001-00'
                    cpfCnpjInputReference.maxLength = 18; 
                }
            }
        </script>

</body>
</html>
