<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Registro de Cliente</title>
    </head>
    <body>
        <div id="content" role="main">
            <div class="container">
                <section class="row">
                    <div id="show-customer" class="col-12 content scaffold-show" role="main">
                        <h1>Registro de Cliente</h1>
                        <div class="data-field">
                            <label>Nome:</label>
                            <span>${customer.name}</span>
                        </div>
                        <div class="data-field">
                            <label>E-mail:</label>
                            <span>${customer.email}</span>
                        </div>
                        <div class="data-field">
                            <label>Tipo de Cliente:</label>
                            <span>${customer.personType}</span>
                        </div>
                        <div class="data-field">
                            <label>CPF/CNPJ:</label>
                            <span>${customer.cpfCnpj}</span>
                        </div>
                        <div class="data-field">
                            <label>CEP:</label>
                            <span>${customer.cep}</span>
                        </div>
                        <div class="data-field">
                            <label>Estado:</label>
                            <span>${customer.state}</span>
                        </div>
                        <div class="data-field">
                            <label>Cidade:</label>
                            <span>${customer.city}</span>
                        </div>
                        <div class="data-field">
                            <label>Bairro:</label>
                            <span>${customer.district}</span>
                        </div>
                        <div class="data-field">
                            <label>Endereço:</label>
                            <span>${customer.address}</span>
                        </div>
                        <div class="data-field">
                            <label>Número do endereço:</label>
                            <span>${customer.addressNumber}</span>
                        </div>
                        <div class="data-field">
                            <label>Complemento:</label>
                            <span>${customer.complement}</span>
                        </div>
                        <div class="data-field">
                            <label>Celular:</label>
                            <span>${customer.phoneNumber}</span>
                        </div>
                        <a href="${ createLink(controller: "customer", action: "edit", params: [id: customer.id]) }" class="btn btn-default">Editar Cliente</a>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>