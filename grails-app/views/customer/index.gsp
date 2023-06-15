<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" >
        <title>Listagem de Clientes</title>
    </head>
    <body>
        <g:link action="create">
            <button>Novo Cliente</button>
        </g:link>
        <h1>Lista com Clientes</h1>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>CPF/CNPJ</th>
                    <th>Celular</th>
                    <th>Mais informações</th>
                </tr>
                <g:each in="${customerList}" var="customer">
                    <tr>
                        <td>${customer?.id}</td>
                        <td>${customer?.name}</td>
                        <td>${customer?.email}</td>
                        <td>${customer?.cpfCnpj}</td>
                        <td>${customer?.phoneNumber}</td>
                        <td><g:link action="show" id="${customer?.id}">Visualizar mais</g:link></td>
                    </tr>
                </g:each>
            </table>
    </body>
</html>