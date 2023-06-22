<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Listagem de pagadores</title>
    </head>
    <body>
        <a href="${ createLink(controller: "payer", action: "create", params: [params]) }" class="btn btn-default">Novo pagador</a>
        <g:if test="${!mustShowDeletedPayers}">
            <a href="${createLink(controller: "payer", action: "index", params: [mustShowDeletedPayers: true])}">Mostrar pagadores inativos</a>
        </g:if>
        <g:else>
            <a href="${createLink(controller: "payer", action: "index", params: [mustShowDeletedPayers: false])}">Mostrar pagadores ativos</a>
        </g:else>
        <h1>Lista de Pagadores</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>CPF/CNPJ</th>
                <th>Celular</th>
                <th>Status</th>
            </tr>
            <g:each in="${payerList}" var="payer">
                <tr>
                    <td>${payer?.id}</td>
                    <td><a href="${createLink(controller: 'payer', action: 'show', params: [id: payer?.id])}">${payer?.name}</a></td>
                    <td>${payer?.email}</td>
                    <td>${payer?.cpfCnpj}</td>
                    <td>${payer?.phoneNumber}</td>
                    <td>
                        <g:if test="${mustShowDeletedPayers}">
                            Inativo
                        </g:if>
                        <g:else>
                            Ativo
                        </g:else>
                </tr>
            </g:each>
        </table>
    </body>
</html>