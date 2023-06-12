<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" >
        <title>Listagem de pagadores</title>
    </head>
    <body>
        <g:link action="create">
            <button>Novo pagador</button>
        </g:link>
        <h1>Lista com pagadores</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>CPF/CNPJ</th>
                    <th>Celular</th>
                    <th>Mais informações</th>
                </tr>
                <g:each in="${payerList}" var="payer">
                    <tr>
                        <td>${payer?.id}</td>
                        <td>${payer?.name}</td>
                        <td>${payer?.email}</td>
                        <td>${payer?.cpfCnpj}</td>
                        <td>${payer?.phoneNumber}</td>
                        <td><g:link action="show" id="${payer?.id}">Visualizar mais</g:link></td>
                    </tr>
                </g:each>
            </table>
    </body>
</html>