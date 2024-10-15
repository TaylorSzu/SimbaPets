<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="icon" href="img/icon.png" type="image/x-icon"/>
    <title>Simba Pets</title>
</head>
<body>
    <div class="menu">
        <h2><img src="img/icon.png"><b>Simba </b><span><b>Pets</b></span></h2>
        <div class="menu-button">
            <button type="button" onclick="window.location.href='/SimbaPets/produtos';"><img src="img/caixa.png"><b>Produtos</b></button>
            <button type="button" onclick="window.location.href='/SimbaPets/vendas';"><img src="img/carrinho.png"><b>Vendas</b></button>
            <button type="button" onclick="window.location.href='/SimbaPets/fornecedores';"><img src="img/correio.png"><b>Fornecedores</b></button>
            <button type="button" onclick="window.location.href='/SimbaPets/estoque';"><img src="img/em-estoque.png"><b>Estoque</b></button>
            <button type="button" onclick="window.location.href='/SimbaPets/contas';"><img src="img/crescimento.png"><b>Financeiro</b></button>
            <button type="button" onclick="window.location.href='/SimbaPets/estatisticas';"><img src="img/receita.png"><b>Estatísticas</b></button>
        </div>
    </div>
    <div class="main-content">
        <img src="img/icon.png">
    </div>
</body>
</html>