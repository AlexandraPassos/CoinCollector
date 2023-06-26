<%@  page import="utils.billingType.BillingType" %>
<html>
    <head>
        <meta name="layout" content="main">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>CoinCollector - Cadastro de Cobrança</title>
    </head>

    <body>
        <h1>Cadastro de nova cobrança</h1>
        <form action="${createLink(controller: 'payment', action: 'save')}" method="post">
            <div>
                <label>Pagador:</label>
                <g:select
                        name="payer"
                        data-constraint="select"
                        from="${payerList}"
                        optionKey="id"
                        optionValue="name"
                        noSelection="${['': 'Selecione o pagador']}"/>
                </select>
            </div>

            <div>
                <label>Forma de Pagamento:</label>
                <g:select
                        name="billingType"
                        data-constraint="select"
                        from="${BillingType.values()}"
                        id="${params.billingType}"
                        valueMessagePrefix="ENUM.BillingType"
                        noSelection="${['': 'Selecione a forma de pagamento']}"/>
            </div>


            <div>
                <label>Data de Vencimento:</label>
                <input type="date" name="dueDate" id="dueDate">
            </div>

            <div>
                <label>Valor a Receber:</label>
                <input type="text" name="value" id="value">
            </div>

            <button type="submit">Salvar</button>
        </form>
    </body>
</html>