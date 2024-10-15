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

        function formatarMoeda(valor) {
            return valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }

        function atualizarValorTotal() {
            let total = 0;
            document.querySelectorAll('#tabela-produto tbody tr').forEach(row => {
                const preco = parseFloat(row.cells[3].textContent.replace('R$', '').replace(',', '.').trim());
                if (!isNaN(preco)) {
                    total += preco;
                }
            });
            document.querySelector('.valor-total').textContent = formatarMoeda(total);
            calcularTroco();
        }

        function calcularTroco() {
            const valorTotal = parseFloat(document.querySelector('.valor-total').textContent.replace('R$', '').replace(',', '.').trim());
            let valorPago = parseFloat(document.getElementById('valor-pago').value.replace(',', '.').trim());

            if (isNaN(valorPago)) {
                valorPago = 0;
            }

            document.querySelector('.valor-pago-display').textContent = formatarMoeda(valorPago);

            if (valorPago >= valorTotal) {
                const troco = valorPago - valorTotal;
                document.querySelector('.troco').textContent = formatarMoeda(troco);
            } else {
                document.querySelector('.troco').textContent = formatarMoeda(0);
            }
        }

        document.getElementById('valor-pago').addEventListener('input', function(e) {
            let input = e.target.value.replace(/\D/g, '');
            input = (input / 100).toFixed(2);
            e.target.value = formatarMoeda(input).replace('R$', '').trim();
            calcularTroco();
        });

        function adicionarProduto() {
            const select = document.getElementById("produto-venda");
            const quantidadeElement = document.getElementById("quantidade");

            if (!select || !quantidadeElement) {
                console.error("Elementos de select ou quantidade não encontrados.");
                return;
            }

            const selectOption = select.options[select.selectedIndex];

            if (!selectOption) {
                alert("Nenhum produto selecionado!");
                return;
            }

            const codigo = selectOption.value;
            const nome = selectOption.text;
            const preco = parseFloat(selectOption.getAttribute("data-preco"));
            const quantidade = parseFloat(quantidadeElement.value);

            if (isNaN(preco)) {
                alert("Erro: Preço do produto inválido ou não definido.");
                return;
            }

            if (isNaN(quantidade) || quantidade <= 0) {
                alert("Por favor, insira uma quantidade válida.");
                return;
            }

            const valorTotalProduto = preco * quantidade;

            const tabela = document.getElementById("tabela-produto");
            if (!tabela || !tabela.getElementsByTagName('tbody')[0]) {
                console.error("Erro: Tabela de produtos não encontrada ou mal formatada.");
                return;
            }

            const novaLinha = tabela.getElementsByTagName('tbody')[0].insertRow();

            var cellCodigo = novaLinha.insertCell(0);
            var cellNome = novaLinha.insertCell(1);
            var cellQuantidade = novaLinha.insertCell(2);
            var cellPreco = novaLinha.insertCell(3);

            cellCodigo.innerHTML = codigo;
            cellNome.innerHTML = nome;
            cellQuantidade.innerHTML = quantidade;
            cellPreco.innerHTML = formatarMoeda(valorTotalProduto);

            const acaoCell = novaLinha.insertCell(4);
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

            botaoRemover.addEventListener('click', (e) => {
                e.preventDefault();
                tabela.deleteRow(novaLinha.rowIndex);
                atualizarValorTotal();
                document.getElementById(`produto_${codigo}`).remove();
                document.getElementById(`quantidade_${codigo}`).remove();
                document.getElementById(`valorTotal_${codigo}`).remove();
            });

            acaoCell.appendChild(botaoRemover);

            const form = document.querySelector("form.product-form");
            if (!form) {
                console.error("Formulário de produto não encontrado.");
                return;
            }

            const inputCodigo = document.createElement('input');
            inputCodigo.type = 'hidden';
            inputCodigo.name = 'produtos[]';
            inputCodigo.value = codigo;
            inputCodigo.id = `produto_${codigo}`;
            form.appendChild(inputCodigo);

            const inputQuantidade = document.createElement('input');
            inputQuantidade.type = 'hidden';
            inputQuantidade.name = 'quantidade[]';
            inputQuantidade.value = quantidade;
            inputQuantidade.id = `quantidade_${codigo}`;
            form.appendChild(inputQuantidade);

            const inputValorTotal = document.createElement('input');
            inputValorTotal.type = 'hidden';
            inputValorTotal.name = 'valorTotal[]';
            inputValorTotal.value = valorTotalProduto;
            inputValorTotal.id = `valorTotal_${codigo}`;
            form.appendChild(inputValorTotal);

            atualizarValorTotal();
            quantidadeElement.value = "0";
        }

        /*function adicionarProdutoEditar() {
            const select = document.getElementById("produtoVenda");
            const quantidadeElement = document.getElementById("quantidadeEdit");

            if (!select || !quantidadeElement) {
                console.error("Elementos de select ou quantidade não encontrados.");
                return;
            }

            const selectOption = select.options[select.selectedIndex];

            if (!selectOption) {
                alert("Nenhum produto selecionado!");
                return;
            }

            const codigo = selectOption.value;
            const nome = selectOption.text;
            const preco = parseFloat(selectOption.getAttribute("data-preco"));
            const quantidade = parseFloat(quantidadeElement.value);

            if (isNaN(preco)) {
                alert("Erro: Preço do produto inválido ou não definido.");
                return;
            }

            if (isNaN(quantidade) || quantidade <= 0) {
                alert("Por favor, insira uma quantidade válida.");
                return;
            }

            const valorTotalProduto = preco * quantidade;

            const tabela = document.getElementById("tabela-produtoEdit");
            if (!tabela || !tabela.getElementsByTagName('tbody')[0]) {
                console.error("Erro: Tabela de produtos não encontrada ou mal formatada.");
                return;
            }

            const novaLinha = tabela.getElementsByTagName('tbody')[0].insertRow();

            var cellCodigo = novaLinha.insertCell(0);
            var cellNome = novaLinha.insertCell(1);
            var cellQuantidade = novaLinha.insertCell(2);
            var cellPreco = novaLinha.insertCell(3);

            cellCodigo.innerHTML = codigo;
            cellNome.innerHTML = nome;
            cellQuantidade.innerHTML = quantidade;
            cellPreco.innerHTML = formatarMoeda(valorTotalProduto);

            const acaoCell = novaLinha.insertCell(4);
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

            botaoRemover.addEventListener('click', (e) => {
                e.preventDefault();
                tabela.deleteRow(novaLinha.rowIndex);
                atualizarValorTotal();
                document.getElementById(`produto_${codigo}`).remove();
                document.getElementById(`quantidade_${codigo}`).remove();
                document.getElementById(`valorTotal_${codigo}`).remove();
            });

            acaoCell.appendChild(botaoRemover);

            const form = document.querySelector("form.product-form");
            if (!form) {
                console.error("Formulário de produto não encontrado.");
                return;
            }

            const inputCodigo = document.createElement('input');
            inputCodigo.type = 'hidden';
            inputCodigo.name = 'produtos[]';
            inputCodigo.value = codigo;
            inputCodigo.id = `produto_${codigo}`;
            form.appendChild(inputCodigo);

            const inputQuantidade = document.createElement('input');
            inputQuantidade.type = 'hidden';
            inputQuantidade.name = 'quantidade[]';
            inputQuantidade.value = quantidade;
            inputQuantidade.id = `quantidade_${codigo}`;
            form.appendChild(inputQuantidade);

            const inputValorTotal = document.createElement('input');
            inputValorTotal.type = 'hidden';
            inputValorTotal.name = 'valorTotal[]';
            inputValorTotal.value = valorTotalProduto;
            inputValorTotal.id = `valorTotal_${codigo}`;
            form.appendChild(inputValorTotal);

            atualizarValorTotal();
            quantidadeElement.value = "0";
        }

        function abrirTelaEditar(event) {
            const btn = event.currentTarget;
            const data = btn.getAttribute('data-data');

            const conteiner = document.getElementById('conteiner');
            conteiner.style.position = 'fixed';
            conteiner.style.top = '50%';
            conteiner.style.left = '50%';
            conteiner.style.transform = 'translate(-50%, -50%)';
            conteiner.style.width = '150vh';
            conteiner.style.height = 'auto';
            conteiner.style.backgroundColor = 'white';
            conteiner.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
            conteiner.style.padding = '20px';
            conteiner.style.borderRadius = '8px';
            conteiner.style.zIndex = '1000';
            conteiner.style.display = 'block';
            conteiner.style.overflowY = 'auto';
            conteiner.style.maxHeight = '80vh';

            conteiner.innerHTML = `
                <form method="post" action="vendas" class="product-formEdit">
                  <div class="row">
                    <div class="venda-form">
                      <div class="input-group">
                        <div class="form-control produto-container">
                          <label for="produto">Produto</label>
                          <div class="produto-input-buttons">
                            <select name="produtosVenda" id="produtoVenda" class="seleção">
                            </select>
                          </div>
                        </div>
                        <div class="form-control produto-container">
                            <label for="data">Data</label>
                            <input type="date" id="data" name="data" value="${data}" disabled/>
                        </div>
                      </div>
                      <div class="quantidade-valor">
                        <div class="form-control">
                          <label for="quantidade">Quantidade</label>
                          <input type="number" id="quantidadeEdit" name="quantidade" value="0" />
                        </div>
                        <div class="form-control">
                          <label for="valor-pago">Valor pago</label>
                          <input type="number" id="valor-pago" name="valor" placeholder="R$0,00" step="0.01"/>
                        </div>
                        <div class="form-control">
                          <label for="forma-pagamento">Forma de pagamento</label>
                          <select name="formaPagamento" id="forma-pagamento">
                            <option value="Pix">Pix</option>
                            <option value="Dinheiro">Dinheiro</option>
                            <option value="Debito">Débito</option>
                            <option value="Credito">Crédito</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-buttons-inline">
                        <button type="button" class="btn-produtos"  id="addProduto" onclick="adicionarProduto()">Adicionar</button>
                      </div>
                      </br>
                      <h3>Produtos adicionados</h3>
                      <table class="produtos" id="tabela-produtoEdit">
                        <thead>
                          <tr>
                            <th>Código</th>
                            <th>Produto</th>
                            <th>Quantidade</th>
                            <th>Preço</th>
                            <th>Ação</th>
                          </tr>
                        </thead>
                        <tbody>
                        </tbody>
                      </table>
                    </div>
                    <div class="venda-info">
                      <div class="info-box-edit">
                        <p>Valor Total</p>
                        <h2 class="valor-total">R$ 0,00</h2>
                      </div>
                      <div class="info-box-edit">
                        <p>Valor Pago</p>
                        <h2  class="valor-pago-display">R$ 0,00</h2>
                      </div>
                      <div class="info-box-edit">
                        <p>Troco</p>
                        <h2 class="troco">R$ 0,00</h2>
                      </div>
                      <div class="form-buttons">
                        <button type="submit" class="save-button">
                          <img src="img/carrinho-de-compras.png" /><strong>Vender</strong>
                        </button>
                        <button type="button" class="cancel-button" id="cancelar">
                          <img src="img/cancelar.png" /><strong >Cancelar</strong>
                        </button>
                      </div>
                    </div>
                  </div>
                </form>
            `;

            document.querySelector("#addProduto").addEventListener("click", adicionarProdutoEditar);

            document.querySelector(".product-formEdit").addEventListener("submit", function(event){
                if (!verificarFormularioEditar()) {
                    event.preventDefault();
                }
            });

            fetch('/SimbaPets/vendas?jsonAction=getProdutos')
                .then(response => response.json())
                .then(produtos => {
                    console.log(produtos);
                    let escolherProduto = document.getElementById('produtoVenda');
                    produtos.forEach(produto => {
                        let option = document.createElement('option');
                        option.text = produto.nome;
                        option.value = produto.codigo;
                        option.setAttribute('data-preco', produto.precoFornecimento);
                        option.setAttribute('data-granel', produto.vendaGranel);
                        escolherProduto.add(option);
                    });
                }).catch(error => console.error('Erro ao buscar produtos:', error));
        }*/

        function verificarFormulario(){
            var formulario = document.querySelector(".product-form");
            var inputs = document.querySelectorAll("input, select, textarea");

            for (var i = 0; i < inputs.length; i++){
                if (inputs[i].value.trim() === "" || inputs[i].value.trim() == 0 || inputs[i].value.trim() == null) {
                    alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                    inputs[i].focus();
                    return false;
                }
            }

            return true;
        }

        function verificarFormularioEditar(){
            var formulario = document.querySelector(".product-formEdit");
            var inputs = formulario.querySelectorAll("input, select, textarea");

            for (var i = 0; i < inputs.length; i++){
                if (inputs[i].value.trim() === "" || inputs[i].value.trim() == 0 || inputs[i].value.trim() == null) {
                    alert("Por favor, preencha o campo: " + inputs[i].previousElementSibling.innerText);
                    inputs[i].focus();
                    return false;
                }
            }

            return true;
        }

        function isTabelaVazia(){
            const tabela = document.getElementById("tabela-produto");
            const tbody = tabela.getElementsByTagName('tbody')[0];
            return tbody.rows.length === 0;
        }

        document.querySelector(".product-form").addEventListener("submit", function(event){
            if (isTabelaVazia()) {
                event.preventDefault();
                alert("Adicione um produto para registrar a venda");
            }
        });

        const selectProduto = document.getElementById('produto-venda');
        const quantidadeElement = document.getElementById('quantidade');

        selectProduto.addEventListener('change', function(){
            selectOption = selectProduto.options[selectProduto.selectedIndex];
            const isGranel = selectOption.getAttribute('data-granel') === "true";

            if (isGranel) {
                quantidadeElement.value = 1;
                quantidadeElement.disabled = true;
            }else{
                quantidadeElement.value = 0;
                quantidadeElement.disabled = false;
            }
        });

        document.querySelector(".product-form").addEventListener("submit", function(event){
            if (!verificarFormulario()) {
                event.preventDefault();
            }
        });

        document.getElementById('cancelar').addEventListener('click', function(){
            window.location.href="/SimbaPets/";
        });

        document.getElementById("voltar").addEventListener('click', function(){
            window.location.href="/SimbaPets/";
        });

        document.querySelector(".btn-produtos").addEventListener("click", adicionarProduto);

        const editarBtns = document.querySelectorAll("#editar");
        editarBtns.forEach(btn => {
          btn.addEventListener('click', abrirTelaEditar);
        });
    });