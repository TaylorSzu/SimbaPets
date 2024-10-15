document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.tab-link');
    const contents = document.querySelectorAll('.tab-content-item');

    // Manipulação das abas
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            contents.forEach(content => content.classList.remove('active'));
            tab.classList.add('active');
            document.getElementById(tab.getAttribute('data-tab')).classList.add('active');
        });
    });

    function formatarCNPJ(cnpj) {
        cnpj = cnpj.replace(/\D/g, '');

        if (cnpj.length > 12) {
            cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5');
        } else if (cnpj.length > 8) {
            cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{0,4})/, '$1.$2.$3/$4');
        } else if (cnpj.length > 5) {
            cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{0,3})/, '$1.$2.$3');
        } else if (cnpj.length > 2) {
            cnpj = cnpj.replace(/(\d{2})(\d{0,3})/, '$1.$2');
        }

        return cnpj;
    }

    function formatarTelefone(telefone) {
        telefone = telefone.replace(/\D/g, '');

        if (telefone.length > 10) {
            telefone = telefone.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
        } else if (telefone.length > 5) {
            telefone = telefone.replace(/(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
        } else if (telefone.length > 2) {
            telefone = telefone.replace(/(\d{2})(\d{0,5})/, '($1) $2');
        } else {
            telefone = telefone.replace(/(\d{0,2})/, '($1');
        }

        return telefone;
    }

    function adicionarProduto() {
        const select = document.getElementById("produtos-fornecidos");
        const selectedOption = select.options[select.selectedIndex];

        const codigo = selectedOption.value;
        const nome = selectedOption.text;
        const preco = selectedOption.getAttribute("data-preco");

        const tabela = document.getElementById("tabela-produtos").getElementsByTagName('tbody')[0];
        const novaLinha = tabela.insertRow();

        var cellCodigo = novaLinha.insertCell(0);
        var cellNome = novaLinha.insertCell(1);
        var cellPreco = novaLinha.insertCell(2);
        cellCodigo.innerHTML = codigo;
        cellNome.innerHTML = nome;
        cellPreco.innerHTML = preco;

        const acaoCell = novaLinha.insertCell(3);
        const botaoRemover = document.createElement('button');
        botaoRemover.textContent = 'Remover';
        botaoRemover.className = 'btn-remover';
        botaoRemover.style.color = "white";
        botaoRemover.style.backgroundColor = "blue";
        botaoRemover.style.width = "80px";
        botaoRemover.style.height = "30px";
        botaoRemover.style.border = "none";
        botaoRemover.style.borderRadius = "20px";
        botaoRemover.style.cursor = "pointer";

        botaoRemover.addEventListener('mouseover', function() {
            botaoRemover.style.backgroundColor = "darkblue";
        });
        botaoRemover.addEventListener('mouseout', function() {
            botaoRemover.style.backgroundColor = "blue";
        });
        botaoRemover.addEventListener('click', () => {
            tabela.deleteRow(novaLinha.rowIndex - 1);
            document.getElementById(`produto_${codigo}`).remove();
            document.getElementById(`preco_${codigo}`).remove();
        });

        acaoCell.appendChild(botaoRemover);
        const form = document.querySelector("form.product-form");

        const inputNome = document.createElement('input');
        inputNome.type = 'hidden';
        inputNome.name = 'produtos[]';
        inputNome.value = nome;
        inputNome.id = `produto_${nome}`;
        form.appendChild(inputNome);
    }

    function adicionarProdutoEditar() {
        const select = document.getElementById("produtosFornecidos");
        const selectedOption = select.options[select.selectedIndex];

        const codigo = selectedOption.value;
        const nome = selectedOption.text;
        const preco = selectedOption.getAttribute("data-preco");

        const tabela = document.getElementById("tabela-produtosEditar").getElementsByTagName('tbody')[0];
        const novaLinha = tabela.insertRow();

        var cellCodigo = novaLinha.insertCell(0);
        var cellNome = novaLinha.insertCell(1);
        var cellPreco = novaLinha.insertCell(2);
        cellCodigo.innerHTML = codigo;
        cellNome.innerHTML = nome;
        cellPreco.innerHTML = preco;

        const acaoCell = novaLinha.insertCell(3);
        const botaoRemover = document.createElement('button');
        botaoRemover.textContent = 'Remover';
        botaoRemover.className = 'btn-remover';
        botaoRemover.style.color = "white";
        botaoRemover.style.backgroundColor = "blue";
        botaoRemover.style.width = "80px";
        botaoRemover.style.height = "30px";
        botaoRemover.style.border = "none";
        botaoRemover.style.borderRadius = "20px";
        botaoRemover.style.cursor = "pointer";

        botaoRemover.addEventListener('mouseover', function() {
            botaoRemover.style.backgroundColor = "darkblue";
        });
        botaoRemover.addEventListener('mouseout', function() {
            botaoRemover.style.backgroundColor = "blue";
        });
        botaoRemover.addEventListener('click', () => {
            tabela.deleteRow(novaLinha.rowIndex - 1);
            document.getElementById(`produto_${codigo}`).remove();
            document.getElementById(`preco_${codigo}`).remove();
        });

        acaoCell.appendChild(botaoRemover);

        const form = document.querySelector("form.productEditar-form");

        const inputNome = document.createElement('input');
        inputNome.type = 'hidden';
        inputNome.name = 'produtosEditar[]';
        inputNome.value = nome;
        inputNome.id = `produto_${nome}`;
        form.appendChild(inputNome);

        console.log(inputNome);
    }

    function abrirTelaView(event) {
        const btn = event.currentTarget;
        const nomeEmpresa = btn.getAttribute('data-NomeEmpresa');
        const cnpj = btn.getAttribute('data-cnpj');
        const rua = btn.getAttribute('data-rua');
        const bairro = btn.getAttribute('data-bairro');
        const numero = btn.getAttribute('data-num');
        const cidade = btn.getAttribute('data-cidade');
        const estado = btn.getAttribute('data-estado');
        const nomeVendedor = btn.getAttribute('data-nomeVendedor');
        const telefone = btn.getAttribute('data-telefone');
        const email = btn.getAttribute('data-email');
        const produtos = btn.getAttribute('data-produtos');

        console.log(produtos);

        const conteiner = document.getElementById('conteiner');
        conteiner.style.position = 'fixed';
        conteiner.style.top = '50%';
        conteiner.style.left = '50%';
        conteiner.style.transform = 'translate(-50%, -50%)';
        conteiner.style.width = '150vh';
        conteiner.style.height = '56vh';
        conteiner.style.backgroundColor = 'white';
        conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
        conteiner.style.padding = '20px';
        conteiner.style.borderRadius = '8px';
        conteiner.style.zIndex = '1000';
        conteiner.style.display = 'block';
        conteiner.style.overflowY = 'auto';
        conteiner.style.maxHeight = '80vh';
        conteiner.innerHTML = `
            <form method="post" action="fornecedores">
                <div class="titulo">
                  <p><strong>FORNECEDOR DETALHES</strong></p>
                </div>
                <div class="row">
                    <div class="form-control">
                        <label for="nome">Nome do fornecedor</label>
                        <input type="text" name="nomeFornecedor" id="nome" value="${nomeEmpresa}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="cnpj">CNPJ</label>
                        <input type="text" name="cnpj" id="cnpj" value="${cnpj}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="rua">Rua</label>
                        <input type="text" name="rua" id="rua" value="${rua}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro" id="bairro" value="${bairro}" disabled=""/>
                    </div>
                    <div class="form-control numero-control">
                        <label for="numero">Número</label>
                        <input type="number" name="numero" id="numero" value="${numero}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade" id="cidade" value="${cidade}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="estado">Estado</label>
                        <input type="text" name="estado" id="estado" value="${estado}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="nomeVendedor">Nome do vendedor</label>
                        <input type="text" name="nomeVendedor" id="nomeVendedor" value="${nomeVendedor}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="telefone">Telefone</label>
                        <input type="text" name="telefone" id="telefone" value="${telefone}" disabled=""/>
                    </div>
                    <div class="form-control">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" value="${email}" disabled=""/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-control">
                        <table id="tabela-produtos" class="produtos">
                            <thead>
                                <tr>
                                   <th>Produto</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${produtos}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-buttons">
                    <button class="voltar" id="voltarView"><img src="img/voltar.png">Voltar</button>
                </div>
            </form>
        `;

        document.getElementById("voltarView").addEventListener('click', function(event){
            event.preventDefault();
            conteiner.style.display = 'none';
        });
    }

    function abrirTelaEditar(event) {
        const btn = event.currentTarget;
        const id = btn.getAttribute('data-id');
        const nomeEmpresa = btn.getAttribute('data-NomeEmpresa');
        const cnpj = btn.getAttribute('data-cnpj');
        const rua = btn.getAttribute('data-rua');
        const bairro = btn.getAttribute('data-bairro');
        const numero = btn.getAttribute('data-num');
        const cidade = btn.getAttribute('data-cidade');
        const estado = btn.getAttribute('data-estado');
        const nomeVendedor = btn.getAttribute('data-nomeVendedor');
        const telefone = btn.getAttribute('data-telefone');
        const email = btn.getAttribute('data-email');

        const conteiner = document.getElementById('conteiner');
        conteiner.style.position = 'fixed';
        conteiner.style.top = '50%';
        conteiner.style.left = '50%';
        conteiner.style.transform = 'translate(-50%, -50%)';
        conteiner.style.width = '150vh';
        conteiner.style.height = '100vh';
        conteiner.style.backgroundColor = 'white';
        conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
        conteiner.style.padding = '20px';
        conteiner.style.borderRadius = '8px';
        conteiner.style.zIndex = '1000';
        conteiner.style.display = 'block';
        conteiner.style.overflowY = 'auto';
        conteiner.style.maxHeight = '80vh';

        conteiner.innerHTML = `
            <form method="post" action="fornecedores" class="productEditar-form">
                <div class="titulo">
                  <p><strong>EDITAR FORNECEDOR</strong></p>
                </div>
                <div class="row">
                    <div class="form-control">
                        <label for="nome">Nome do fornecedor</label>
                        <input type="text" name="nomeFornecedor" id="nome" value="${nomeEmpresa}"/>
                    </div>
                    <div class="form-control">
                        <label for="cnpj">CNPJ</label>
                        <input type="text" name="cnpj" id="cnpjEdit" value="${cnpj}"/>
                    </div>
                    <div class="form-control">
                        <label for="rua">Rua</label>
                        <input type="text" name="rua" id="rua" value="${rua}"/>
                    </div>
                    <div class="form-control">
                        <label for="bairro">Bairro</label>
                        <input type="text" name="bairro" id="bairro" value="${bairro}"/>
                    </div>
                    <div class="form-control numero-control">
                        <label for="numero">Número</label>
                        <input type="number" name="numero" id="numero" value="${numero}"/>
                    </div>
                    <div class="form-control">
                        <label for="cidade">Cidade</label>
                        <input type="text" name="cidade" id="cidade" value="${cidade}"/>
                    </div>
                    <div class="form-control">
                        <label for="estado">Estado</label>
                        <input type="text" name="estado" id="estado" value="${estado}"/>
                    </div>
                    <div class="form-control">
                        <label for="nomeVendedor">Nome do vendedor</label>
                        <input type="text" name="nomeVendedor" id="nomeVendedor" value="${nomeVendedor}"/>
                    </div>
                    <div class="form-control">
                        <label for="telefone">Telefone</label>
                        <input type="text" name="telefone" id="telefoneEdit" value="${telefone}"/>
                    </div>
                    <div class="form-control">
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email" value="${email}"/>
                    </div>
                   <div class="form-control">
                        <input type="hidden" id="id" name="id" value="${id}"/>
                   </div>
                </div>
                <div class="row">
                    <div class="form-control">
                        <label for="produtos-fornecidos">Produtos fornecidos</label>
                        <div class="select-container">
                            <select name="produtos-fornecidos" id="produtosFornecidos" required>
                                <option value="" disabled selected>Selecione um produto</option>
                            </select>
                            <div class="btn-container">
                                <button type="button" class="btn-produtos" id="btnProduto">Adicionar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-control">
                        <table id="tabela-produtosEditar" class="produtos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Produto</th>
                                    <th>Preço</th>
                                    <th>Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Itens adicionados dinamicamente -->
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-buttons">
                    <button type="submit" class="save-button"><img src="img/registro.png">Atualizar</button>
                    <button type="reset" class="cancel-button" id="cancelButton"><img src="img/cancelar.png">Cancelar</button>
                </div>
            </form>
            `;

        fetch('/SimbaPets/fornecedores?jsonAction=getProdutos')
            .then(response => response.json())
            .then(produtos => {
                console.log(produtos);
                let escolherProduto = document.getElementById('produtosFornecidos');
                produtos.forEach(produto => {
                    let option = document.createElement('option');
                    option.text = produto.nome;
                    escolherProduto.add(option);
                });
            }).catch(error => console.error('Erro ao buscar produtos:', error));

        const inputCNPJ = document.getElementById('cnpjEdit');
        inputCNPJ.addEventListener('input', function() {
            this.value = formatarCNPJ(this.value);
        });

        const inputTelefone = document.getElementById('telefoneEdit');
        inputTelefone.addEventListener('input', function(){
            this.value = formatarTelefone(this.value);
        });

        document.querySelector(".productEditar-form").addEventListener("submit", function(event){
            if (!verificarFormularioEditar()) {
                event.preventDefault();
            }
        });

        document.getElementById('btnProduto').addEventListener('click', function(){
            adicionarProdutoEditar();
        });

        document.getElementById('cancelButton').addEventListener('click', function() {
            conteiner.style.display = 'none';
        });
    }

    const inputCNPJ = document.getElementById('cnpj');
    inputCNPJ.addEventListener('input', function() {
        this.value = formatarCNPJ(this.value);
    });

    const inputTelefone = document.getElementById('telefone');
    inputTelefone.addEventListener('input', function(){
        this.value = formatarTelefone(this.value);
    });

    function verificarFormulario(){
        var formulario = document.querySelector(".product-form");
        var inputs = document.querySelectorAll("input, select, textarea");

        for (var i = 0; i < inputs.length; i++){
            if (inputs[i].value.trim() === "") {
                alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                inputs[i].focus();
                return false;
            }
        }

        return true;
    }

    function verificarFormularioEditar(){
        var formulario = document.querySelector(".productEditar-form");
        var inputs = formulario.querySelectorAll("input, select, textarea");

        for (var i = 0; i < inputs.length; i++){
            if (inputs[i].value.trim() === "") {
                alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                inputs[i].focus();
                return false;
            }
        }

        return true;
    }

    document.querySelector(".product-form").addEventListener("submit", function(event){
        if (!verificarFormulario()) {
            event.preventDefault();
        }
    });

    document.querySelector(".btn-produtos").addEventListener("click", adicionarProduto);

    const viewBtns = document.querySelectorAll("#view");
    viewBtns.forEach(btn => {
        btn.addEventListener('click', abrirTelaView);
    });

    const editarBtns = document.querySelectorAll("#editar");
    editarBtns.forEach(btn => {
        btn.addEventListener('click', abrirTelaEditar);
    });

    document.getElementById('cancelar').addEventListener('click', function(){
        window.location.href="/SimbaPets/";
    });

    document.getElementById("voltar").addEventListener('click', function(){
        window.location.href="/SimbaPets/";
    });
});
