!DOCTYPE html>
<%@  page import="utils.billingType.BillingType" %>
<%@  page import="utils.paymentStatus.PaymentStatus" %>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>CoinCollector - Cobrança</title>
    </head>

    <body>
        <div id="content" role="main">
            <div class="container">
                <section class="row">
                    <div id="show-payment" class="col-12 content scaffold-show" role="main">
                        <h1>Detalhes da Cobrança</h1>

                        <div class="data-field">
                            <label>Pagador:</label>
                            <span>${payment.payer.name}</span>
                        </div>

                        <div class="data-field">
                            <label>Forma de Pagamento:</label>
                            <span><g:message code="ENUM.BillingType.${payment.billingType}"/></span>
                        </div>

                        <div class="data-field">
                            <label>Data de Vencimento:</label>
                            <span><g:formatDate format="dd/MM/yyyy" date="${payment?.dueDate}"/></span>
                        </div>

                        <div class="data-field">
                            <label>Valor:</label>
                            <span><g:formatNumber number="${payment?.value}" type="currency" currencyCode="BRL"/></span>
                        </div>

                        <div class="data-field">
                            <label>Status:</label>
                            <span><g:message code="ENUM.PaymentStatus.${payment?.status}"/></span>
                        </div>

                        <g:if test="${payment.status == PaymentStatus.RECEIVED}">

                        </g:if>
                        <g:else>
                            <a href="${createLink(controller: "payment", action: "update", params: [id: payment.id])}"
                               class="btn btn-default">Recebido!</a>
                        </g:else>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>