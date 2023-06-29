<%@ page import="utils.paymentStatus.PaymentStatus" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" >
        <title>CoinCollector - Cobrança</title>
    </head>
    <body>
        <g:link action="create">
            <button>Gerar Cobrança</button>
        </g:link>
        <g:form action = "index">
            <label for="status">Filtrar por Situação:</label>
            <g:select
                    name="status"
                    class="marg-5"
                    data-constraint="select"
                    from="${PaymentStatus.values()}"
                    value="${params.status}"
                    valueMessagePrefix="ENUM.PaymentStatus"
                    noSelection="${['': '']}"/>
            <input type="submit" value="Aplicar"/>
        </g:form>
        <h1>Lista de Cobranças</h1>
        <table>
            <tr>
                <th>Pagador</th>
                <th>Forma de Pagamento</th>
                <th>Data de Vencimento</th>
                <th>Valor</th>
                <th>Situação</th>
            </tr>
            <g:each in="${paymentList}" var="payment">
                <tr>
                    <td><a href="${createLink(controller: 'payer', action: 'show', params: [id: payment.payerId])}">${payment.payer.name}</a></td>
                    <td><g:message code="ENUM.BillingType.${payment?.billingType}"/></td>
                    <td><g:formatDate format="dd/MM/yyyy" date="${payment?.dueDate}"/></td>
                    <td><g:formatNumber number="${payment?.value}" type="currency" currencyCode="BRL"/></td>
                    <td><a href="${createLink(controller: 'payment', action: 'show', params: [id: payment.id])}"><g:message code="ENUM.PaymentStatus.${payment?.status}"/></a></td>
                </tr>
            </g:each>
        </table>
    </body>
</html>