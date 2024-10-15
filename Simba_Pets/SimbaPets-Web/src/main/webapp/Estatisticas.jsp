<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estatistica.css">
        <title>SimbaPets</title>
    </head>
    <body>
        <div class="container">
            <div class="produtos">
                <button onclick="window.location.href='/SimbaPets'"><img src="" alt="">Voltar</button>
                <h1>PRODUTOS</h1>
                <hr>
                <br><br>
            <div class="top-10">
                <h1>Produtos mais vendidos</h1>
                <table>
                    <thead>
                        <tr>
                            <td>Código</td>
                            <td>Produto</td>
                            <td>Total Vendido</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="produto" items="${top10}">
                        <tr>
                            <td>${produto.getProdutoCodigo()}</td>
                            <td>${produto.getProdutoNome()}</td>
                            <td>${produto.getTotalVendido()}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="estoque-baixo">
                <h1>Produtos com baixo estoque</h1>
                <table>
                    <thead>
                        <tr>
                            <td>Código</td>
                            <td>Produto</td>
                            <td>Estoque</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="produto" items="${estoqueBaixo}">
                        <tr>
                            <td>${produto.getProdutoCodigo()}</td>
                            <td>${produto.getProdutoNome()}</td>
                            <td>${produto.getEstoqueProduto()}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="margem-lucro">
                <h1>Produtos com maior lucro</h1>
                <table>
                    <thead>
                        <tr>
                            <td>Código</td>
                            <td>Produto</td>
                            <td>Lucro</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="produto" items="${margemLucro}">
                        <tr>
                            <td>${produto.getProdutoCodigo()}</td>
                            <td>${produto.getProdutoNome()}</td>
                            <td>${produto.getMargemLucro()}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="fundo-venda">
                    <h1>Quantidade de Produtos cadastrados</h1>
                    <h2>${quantidade}</h2>
                </div>
            </div>
        </div>
        <div class="vendas">
            <div class="total-vendas">
                <h1>VENDAS</h1>
                <hr>
                <br>
                <div class="fundo-venda">
                    <h1>Total de vendas hoje</h1>
                    <h2>${vendasDia}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Total de vendas no mês</h1>
                    <h2>${vendasMes}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Total de vendas no ano</h1>
                    <h2>${vendasAno}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Lucro do dia</h1>
                    <h2>R$ ${lucroDia}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Lucro da semana</h1>
                    <h2>R$ ${lucroSemana}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Lucro do mês</h1>
                    <h2>R$ ${lucroMes}</h2>
                </div>
                <div class="fundo-venda">
                    <h1>Lucro do ano</h1>
                    <h2>R$ ${lucroAno}</h2>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
