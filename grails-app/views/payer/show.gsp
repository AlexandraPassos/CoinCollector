<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Registro de Pagador</title>
    </head>

    <body>
        <div id="content" role="main">
            <div class="container">
                <section class="row">
                    <div id="show-payer" class="col-12 content scaffold-show" role="main">
                        <h1>Registro de Pagador</h1>

                        <div class="data-field">
                            <label>Nome:</label>
                            <span>${payer.name}</span>
                        </div>

                        <div class="data-field">
                            <label>E-mail:</label>
                            <span>${payer.email}</span>
                        </div>

                        <div class="data-field">
                            <label>Tipo de pagador:</label>
                            <span>${payer.personType}</span>
                        </div>

                        <div class="data-field">
                            <label>CPF/CNPJ:</label>
                            <span>${payer.cpfCnpj}</span>
                        </div>

                        <div class="data-field">
                            <label>CEP:</label>
                            <span>${payer.cep}</span>
                        </div>

                        <div class="data-field">
                            <label>Estado:</label>
                            <span>${payer.state}</span>
                        </div>

                        <div class="data-field">
                            <label>Cidade:</label>
                            <span>${payer.city}</span>
                        </div>

                        <div class="data-field">
                            <label>Bairro:</label>
                            <span>${payer.district}</span>
                        </div>

                        <div class="data-field">
                            <label>Endereço:</label>
                            <span>${payer.address}</span>
                        </div>

                        <div class="data-field">
                            <label>Número do endereço:</label>
                            <span>${payer.addressNumber}</span>
                        </div>

                        <div class="data-field">
                            <label>Complemento:</label>
                            <span>${payer.complement}</span>
                        </div>

                        <div class="data-field">
                            <label>Celular:</label>
                            <span>${payer.phoneNumber}</span>
                        </div>
                        <g:if test="${payer.deleted}">
                            <a href="${createLink(controller: "payer", action: "restore", params: [id: payer.id])}"
                               class="btn btn-default">Restaurar Pagador</a>
                        </g:if>
                        <g:else>
                            <a href="${createLink(controller: "payer", action: "edit", params: [id: payer.id])}"
                               class="btn btn-default">Editar Pagador</a>
                            <a href="${createLink(controller: "payer", action: "delete", params: [id: payer.id])}"
                               class="btn btn-default">Deletar Pagador</a>
                        </g:else>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
