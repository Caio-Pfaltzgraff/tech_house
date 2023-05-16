<script th:inline="javascript">
      /*<![CDATA[*/

      function adicionarLinha() {
        var dadosJson = /*[[${produtosJson}]]*/ '[]';
        var dados = JSON.parse(dadosJson);
        console.log(dados);
        var tabela = document.getElementById('tabelaProdutos');

        // Criar a nova linha
        var novaLinha = document.createElement('tr');

        // Criar as células da nova linha
        var colunaProduto = document.createElement('td');
        var colunaQuantidade = document.createElement('td');
        var colunaRemover = document.createElement('td');

        // Adicionar os elementos de input à célula do produto
        var selectProduto = document.createElement('select');
        selectProduto.classList.add('form-select');
        /* ... adicione opções ao selectProduto se necessário ... */
        colunaProduto.appendChild(selectProduto);

        // Adicionar o elemento de input à célula da quantidade
        var inputQuantidade = document.createElement('input');
        inputQuantidade.type = 'number';
        inputQuantidade.min = '0';
        inputQuantidade.classList.add('form-control');
        colunaQuantidade.appendChild(inputQuantidade);

        // Adicionar o botão de remover à célula de remover
        var botaoRemover = document.createElement('button');
        botaoRemover.classList.add('btn', 'btn-secondary');
        botaoRemover.onclick = function () {
          removerLinha(novaLinha);
        };
        botaoRemover.innerHTML = '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16"> <!-- ... conteúdo do SVG ... --> </svg>';
        colunaRemover.appendChild(botaoRemover);

        // Adicionar as células à nova linha
        novaLinha.appendChild(colunaProduto);
        novaLinha.appendChild(colunaQuantidade);
        novaLinha.appendChild(colunaRemover);

        // Adicionar a nova linha à tabela
        tabela.appendChild(novaLinha);
      }

      function removerLinha(linha) {
        linha.remove();
      }

    /*]]>*/
    </script>