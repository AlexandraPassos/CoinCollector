<html>
    <head>
        <meta name="layout" content="main" >
        <title>Criação de conta</title>
        <asset:javascript src="changepersontype/changePersonType.js"/>
    </head>
    <body>
        <h1>Criação de conta</h1>
        <form action="${createLink(controller: 'customer', action: 'save')}" method="post">
            <div>
                <label for="name">Nome:</label>
                <input type="text" name="name" id="name" placeholder="Digite seu nome">
            </div>

            <div>
                <label for="email">E-mail:</label>
                <input type="email" name="email" placeholder="seuemail@exemplo.com" id="email">
            </div>

            <div>
                <label for="password">Senha:</label>
                <input type="password" name="password" placeholder="Digite sua senha" id="password">
            </div>

            <div>
                <label for="password">Confirme sua senha:</label>
                <input type="password" name="confirmPassword" placeholder="Digite novamente sua senha" id="confirmPassword">
            </div>

            <div>
                <label for="personType">Tipo de Cliente:</label>
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
    </body>
</html>