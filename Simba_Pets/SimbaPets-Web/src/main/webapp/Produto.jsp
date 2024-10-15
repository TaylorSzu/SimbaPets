<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8" />
    <meta name="author" content="TaylorSz" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>TabsProduto</title>
    <!-- Google Fonts -->
    <link
        href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
        rel="stylesheet"
    />
    <!-- Stylesheet -->
    <link rel="stylesheet" href="css/tabsProduct.css" />
</head>
<body>
    <div class="container">
        <div class="tabs">
            <h3 class="tab-link active" data-tab="tab1">Cadastrar Produto</h3>
            <h3 class="tab-link" data-tab="tab2">Lista de Produtos</h3>
        </div>
        <div class="tab-content">
            <!-- Aba de Cadastro de Produto -->
            <div id="tab1" class="tab-content-item active">
                <div class="fundo">
                    <form method="post" action="produtos" class="product-form">
                        <div class="row">
                            <div class="form-control">
                                <label for="codigo">Código de barras</label>
                                <input type="text" name="codigo" id="codigo" placeholder="0 000000 000000"/>
                            </div>
                            <div class="form-control">
                                <label for="nome">Nome do Produto</label>
                                <input type="text" name="nome" id="nome" placeholder="Digite o nome do produto"/>
                            </div>
                            <div class="form-control">
                                <label for="marca">Marca</label>
                                <input type="text" name="marca" id="marca" placeholder="Digite a marca do produto"/>
                            </div>
                            <div class="form-control">
                                <label for="categoria">Categoria</label>
                                <input type="text" name="categoria" id="categoria" placeholder="Digite a categoria do produto"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-control">
                                <label for="tipo-peso">Tipo de peso</label>
                                <select name="tipoPeso" id="tipo-peso">
                                    <option value="seleção" selected>Selecione</option>
                                    <option value="KG">Kg</option>
                                    <option value="G">g</option>
                                    <option value="L">L</option>
                                    <option value="ML">mL</option>
                                </select>
                            </div>
                            <div class="form-control">
                                <label for="Peso">Peso</label>
                                <input type="number" name="peso" id="Peso" placeholder="Informe o peso do produto"/>
                            </div>
                            <div class="form-control">
                                <label for="preco-compra">Preço de fornecimento</label>
                                <input type="number" name="precoFornecimento" id="preco-compra" placeholder="Digite o preço de fornecimento"/>
                            </div>
                            <div class="form-control">
                                <label for="preco-venda">Preço de revenda</label>
                                <input type="number" name="precoRevenda" id="preco-venda" placeholder="Digite o preço de revenda"/>
                            </div>
                            <div class="form-control">
                                <label for="estoque">Estoque</label>
                                <input type="number" name="estoque" id="estoque" placeholder="Informe a quantidade"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-control checkbox-group">
                                <label for="select">Venda a granel
                                    <input type="checkbox" name="vendaGranel" id="select"/>
                                </label>
                            </div>
                            <div class="form-control porcentagem">
                                <label for="porcentagem">Porcentagem de lucro</label>
                                <input type="number" name="porcentagem" id="porcentagem" placeholder="Informe a porcentagem"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-control">
                                <label for="descricao">Descrição do produto</label>
                                <textarea id="descricao" name="descricao" cols="30" rows="5" placeholder="Informe uma descrição ou característica do produto (Opcional)"></textarea>
                            </div>
                        </div>
                        <div class="form-buttons">
                            <button type="submit" class="save-button">
                                <img src="img/registro.png" alt="Cadastrar" />
                                <strong>Cadastrar</strong>
                            </button>
                            <button type="button" class="cancel-button" id="cancelar">
                                <img src="img/cancelar.png" alt="Cancelar" />
                                <strong>Cancelar</strong>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Aba de Lista de Produtos -->
            <div id="tab2" class="tab-content-item">
                <div class="fundo">
                    <!-- Conteúdo para a segunda aba -->
                    <button class="voltar" id="voltar"><img src="img/voltar.png">Voltar</button>
                    <table class="lista-produto">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Estoque</th>
                                <th>Preço</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="produto" items="${produtos}">
                                <tr>
                                    <td>${produto.getCodigo()}</td>
                                    <td>${produto.getNome()}</td>
                                    <td>${produto.getEstoque()}</td>
                                    <td>${produto.getPrecoRevenda()}</td>
                                    <td>
                                        <button type="button" class="btn-acao" id="view" data-id="${produto.getId()}" data-codigo="${produto.getCodigo()}" data-nome="${produto.getNome()}" data-marca="${produto.getMarca()}"
                                         data-categoria="${produto.getCategoria()}" data-tipoPeso="${produto.getTipoPeso()}" data-peso="${produto.getPeso()}" data-pf="${produto.getPrecoFornecimento()}"
                                         data-pr="${produto.getPrecoRevenda()}" data-isVendaGranel="${produto.isVendaGranel()}" data-porcentagem="${produto.getPorcentagem()}"
                                         data-descricao="${produto.getDescricao()}" >
                                            <img src="img/olho.png" alt="Visualizar" width="25px"/>
                                        </button>
                                        <button type="button" class="btn-acao" id="editar" data-id="${produto.getId()}" data-codigo="${produto.getCodigo()}" data-nome="${produto.getNome()}" data-marca="${produto.getMarca()}"
                                        data-categoria="${produto.getCategoria()}" data-tipoPeso="${produto.getTipoPeso()}" data-peso="${produto.getPeso()}" data-pf="${produto.getPrecoFornecimento()}"
                                        data-pr="${produto.getPrecoRevenda()}" data-isVendaGranel="${produto.isVendaGranel()}" data-porcentagem="${produto.getPorcentagem()}"
                                        data-descricao="${produto.getDescricao()}" onclick="abrirTelaEditar()">
                                            <img src="img/editar.png" alt="Editar" width="25px"/>
                                        </button>
                                        <button type="button" class="btn-acao" onclick="window.location.href='produtos?action=delete&codigo=${produto.getCodigo()}'">
                                            <img src="img/excluir.png" alt="Excluir" width="25px"/>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="conteiner"></div>
        </div>
    </div>
    <!-- Script -->
    <script src="js/tabsProduct.js"></script>
</body>
</html>
