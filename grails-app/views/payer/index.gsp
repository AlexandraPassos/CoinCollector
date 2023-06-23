<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Listagem de pagadores</title>
    </head>
    <body>
        <a href="${ createLink(controller: "payer", action: "create", params: [params]) }" class="btn btn-default">Novo pagador</a>
        <g:form action = "index">
            <label for="deletedOnly">Filtrar:</label>
            <g:select
                    name="deletedOnly"
                    class="marg-5"
                    data-constraint="select"
                    from="${[
                            [value: "", label: "Exibir somente pagadores ativos"],
                            [value: "true", label: "Exibir somente pagadores inativos"],
                            [value: "false", label: "Exibir todos os pagadores"]
                    ]}"
                    optionKey="value"
                    optionValue="label"
                    value="${params.deletedOnly}"/>
            <input type="submit" value="Aplicar" />
        </g:form>

        <h1>Lista de Pagadores</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>CPF/CNPJ</th>
                <th>Celular</th>
                <th>Situação</th>
            </tr>
            <g:each in="${payerList}" var="payer">
                <tr>
                    <td>${payer?.id}</td>
                    <td><a href="${createLink(controller: 'payer', action: 'show', params: [id: payer?.id])}">${payer?.name}</a></td>
                    <td>${payer?.email}</td>
                    <td>${payer?.cpfCnpj}</td>
                    <td>${payer?.phoneNumber}</td>
                    <td>
                        <g:if test="${payer.deleted}">
                            Inativo
                        </g:if>
                        <g:else>
                            Ativo
                        </g:else>
                    </td>
                </tr>
            </g:each>
        </table>
    </body>
</html>