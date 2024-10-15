document.addEventListener('DOMContentLoaded', function() {
    const tabs = document.querySelectorAll('.tab-link');
    const contents = document.querySelectorAll('.tab-content-item');

    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            contents.forEach(content => content.classList.remove('active'));
            tab.classList.add('active');
            document.getElementById(tab.getAttribute('data-tab')).classList.add('active');
        });
    });

    function estatistica(){
        const tabela = document.getElementById("tabela-produtos").getElementsByTagName('tbody')[0];

        if (tabela.rows.length > 0) {
            calcularLucroProduto();
            calcularValorInvestido();
        }
    }

    function calcularValorInvestido() {
        const tabela = document.getElementById("tabela-produtos").getElementsByTagName('tbody')[0];
        const quantidadeInput = document.getElementById("quantidade").value;
        const valorInvestido = document.getElementById("valor-investido");

        let total = 0;

        if (tabela.rows.length > 0) {
            let preco = parseFloat(tabela.rows[0].cells[3].innerText);
            let quantidade = parseFloat(quantidadeInput);

            if (!isNaN(preco) && !isNaN(quantidade)) {
                total = preco * quantidade;
                valorInvestido.innerHTML = `R$ ${total.toFixed(2)}`;
            } else {
                valorInvestido.innerHTML = 'Valor inválido';
            }
        }
    }

    function calcularLucroProduto(){
        const tabela = document.getElementById("tabela-produtos").getElementsByTagName('tbody')[0];
        const quantidadeInput = document.getElementById("quantidade").value;
        const lucroVenda = document.getElementById("lucro-venda");
        const select = document.getElementById("produtos");
        const selectedOption = select.options[select.selectedIndex];
        const precoRevenda = selectedOption.getAttribute("data-precoVenda");

        if(tabela.rows.length > 0){
            let preco = parseFloat(tabela.rows[0].cells[3].innerText);
            let lucro = precoRevenda - preco;
            let quantidade = parseFloat(quantidadeInput);
            let total = lucro * quantidade;
            lucroVenda.innerHTML = `R$ ${total.toFixed(2)}`;
        }else {
            valorInvestido.innerHTML = 'valor invalido'
        }
    }

    function adicionarProduto() {
        const select = document.getElementById("produtos");
        const selectedOption = select.options[select.selectedIndex];

        const codigo = selectedOption.value;
        const nome = selectedOption.text;
        const preco = selectedOption.getAttribute("data-preco");
        const estoque = selectedOption.getAttribute("data-estoque");

        const tabela = document.getElementById("tabela-produtos").getElementsByTagName('tbody')[0];

        if (tabela.rows.length > 0) {
            alert("Atualize o estoque de um produto por vez");
            return;
        }

        const novaLinha = tabela.insertRow();

        var cellCodigo = novaLinha.insertCell(0);
        var cellNome = novaLinha.insertCell(1);
        var cellEstoque = novaLinha.insertCell(2);
        var cellPreco = novaLinha.insertCell(3);

        cellCodigo.innerHTML = codigo;
        cellNome.innerHTML = nome;
        cellEstoque.innerHTML = estoque;
        cellPreco.innerHTML = preco;

        cellCodigo.style.textAlign = 'center';
        cellNome.style.textAlign = 'center';
        cellEstoque.style.textAlign = 'center';
        cellPreco.style.textAlign = 'center';

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

        const form = document.querySelector("form.product-form");

        const inputCodigo = document.createElement('input');
        inputCodigo.type = 'hidden';
        inputCodigo.name = 'codigo';
        inputCodigo.value = codigo;
        form.appendChild(inputCodigo);

        console.log(inputCodigo);

        botaoRemover.addEventListener('mouseover', function() {
            botaoRemover.style.backgroundColor = "darkblue";
        });
        botaoRemover.addEventListener('mouseout', function() {
            botaoRemover.style.backgroundColor = "blue";
        });
        botaoRemover.addEventListener('click', () => {
            tabela.deleteRow(novaLinha.rowIndex - 1);
            const produtoElement = document.getElementById(`produto_${codigo}`);
            const precoElement = document.getElementById(`preco_${codigo}`);
            if (produtoElement) produtoElement.remove();
            if (precoElement) precoElement.remove();

            if (inputCodigo.parentNode) {
                inputCodigo.parentNode.removeChild(inputCodigo);
                console.log("deletado");
            }
        });

        acaoCell.style.textAlign = 'center';
        acaoCell.appendChild(botaoRemover);
    }

    function verificarFormulario(){
        var formulario = document.querySelector(".product-form");
        var inputs = document.querySelectorAll("input, select, textarea");

        for (var i = 0; i < inputs.length; i++){
            if (inputs[i].value.trim() === "" || inputs[i].value.trim() == 0) {
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
    document.getElementById("quantidade").addEventListener("input", estatistica);
    document.getElementById('cancelar').addEventListener('click', function(){
        window.location.href="/SimbaPets/";
    });
});
