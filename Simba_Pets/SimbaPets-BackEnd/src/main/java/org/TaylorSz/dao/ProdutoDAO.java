package org.TaylorSz.dao;

import org.TaylorSz.model.Produto;
import org.TaylorSz.controller.IProdutoDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO implements IProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Produto save(Produto produto) { //Testado(Funcionado)
        String sp = "{CALL add_produto(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp);) {

            callableStatement.setLong(1, produto.getCodigo());
            callableStatement.setString(2, produto.getNome());
            callableStatement.setString(3, produto.getMarca());
            callableStatement.setString(4, produto.getCategoria());
            callableStatement.setString(5, produto.getTipoPeso());
            callableStatement.setBigDecimal(6, produto.getPeso());
            callableStatement.setBigDecimal(7, produto.getPrecoFornecimento());
            callableStatement.setBigDecimal(8, produto.getPrecoRevenda());
            callableStatement.setInt(9, produto.getEstoque());
            callableStatement.setBoolean(10, produto.isVendaGranel());
            callableStatement.setFloat(11, produto.getPorcentagem());
            callableStatement.setString(12, produto.getDescricao());

            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            new RuntimeException("Erro ao adicionar", ex);
        }

        return produto;
    }

    @Override
    public Produto update(Produto produto) { //Testado(Funcionado)
        String sp = "{CALL update_produto(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, produto.getId());
            callableStatement.setLong(2, produto.getCodigo());
            callableStatement.setString(3, produto.getNome());
            callableStatement.setString(4, produto.getMarca());
            callableStatement.setString(5, produto.getCategoria());
            callableStatement.setString(6, produto.getTipoPeso());
            callableStatement.setBigDecimal(7, produto.getPeso());
            callableStatement.setBigDecimal(8, produto.getPrecoFornecimento());
            callableStatement.setBigDecimal(9, produto.getPrecoRevenda());
            callableStatement.setBoolean(10, produto.isVendaGranel());
            callableStatement.setFloat(11, produto.getPorcentagem());
            callableStatement.setString(12, produto.getDescricao());

            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar", ex);
        }
        return produto;
    }

    @Override
    public void delete(Long codigo) { //Testado(Funcionado)
        String sp = "{CALL delete_produto(?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setLong(1, codigo);

            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            new RuntimeException(ex);
        }

    }

    @Override
    public List<Produto> findAll() { //Testado(Funcionado)
        String sql = "select * from produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Long codigo = rs.getLong("codigo");
                String nome = rs.getString("nome");
                String marca = rs.getString("marca");
                String categoria = rs.getString("categoria");
                String tipoPeso = rs.getString("tipoPeso");
                BigDecimal peso = rs.getBigDecimal("peso");
                BigDecimal precoFornecimento = rs.getBigDecimal("preco_fornecimento");
                BigDecimal precoRevenda = rs.getBigDecimal("preco_revenda");
                int estoque = rs.getInt("estoque");
                boolean vendaGranel = rs.getBoolean("venda_granel");
                float porcentagem = rs.getFloat("porcentagem_lucro");
                String descricao = rs.getString("descricao");

                Produto produto = new Produto(id, codigo, nome, marca, categoria, tipoPeso, peso, precoFornecimento, precoRevenda, estoque ,vendaGranel, porcentagem, descricao);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            new RuntimeException("Erro ao buscar produtos", ex);
        }
        return produtos;
    }

    @Override
    public Produto findById(Long codigo) { // Testado (Funcionando)

        String sp = "{CALL findById_produto(?)}";
        Produto produto = null; // Inicialize a variável

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setLong(1, codigo);
            ResultSet rs = callableStatement.executeQuery();

            if (rs.next()) { // Altere para if, pois espera-se no máximo um resultado

                int id = rs.getInt("id");
                Long codigoProduto = rs.getLong("codigo");
                String nome = rs.getString("nome");
                String marca = rs.getString("marca");
                String categoria = rs.getString("categoria");
                String tipoPeso = rs.getString("tipoPeso");
                BigDecimal peso = rs.getBigDecimal("peso");
                BigDecimal precoFornecimento = rs.getBigDecimal("preco_fornecimento");
                BigDecimal precoRevenda = rs.getBigDecimal("preco_revenda");
                int estoque = rs.getInt("estoque");
                boolean vendaGranel = rs.getBoolean("venda_granel");
                float porcentagem = rs.getFloat("porcentagem_lucro");
                String descricao = rs.getString("descricao");

                // Crie a instância de Produto
                produto = new Produto(id, codigoProduto, nome, marca, categoria, tipoPeso, peso, precoFornecimento, precoRevenda, estoque, vendaGranel, porcentagem, descricao);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }

    @Override
    public Produto updateEstque(Produto produto) {

        String sp = "{CALL update_estoque(?,?)}";

        try (Connection connection = ConnectionFactory.getConnection();
         CallableStatement callableStatement = connection.prepareCall(sp)){

            callableStatement.setLong(1, produto.getCodigo());
            callableStatement.setInt(2, produto.getEstoque());

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produto;
    }


    @Override
    public List<Produto> top10Products() { //Testado(Funcionado)

        String sp = "{CALL top10_produtos()}";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int produtoID = rs.getInt("produto_id");
                Long produtoCodigo = rs.getLong("produto_codigo");
                String produtoNome = rs.getString("produto_nome");
                int totalVendido = rs.getInt("total_vendido");

                Produto produto = new Produto(produtoID, produtoCodigo, produtoNome, totalVendido);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    @Override
    public List<Produto> findLowStockProducts() { //Testado(Funcionado)

        String sp = "{CALL findLowStock_produto()}";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int produtoId = rs.getInt("produto_id");
                Long produtoCodigo = rs.getLong("produto_codigo");
                String produtoNome = rs.getString("produto_nome");
                int estoque = rs.getInt("estoque");
                BigDecimal margemLucro = rs.getBigDecimal("margem_lucro");

                Produto produto = new Produto(produtoId, produtoCodigo, produtoNome, estoque, margemLucro);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }

    @Override
    public List<Produto> findHighMarginProducts() { //Testado(Funcionado)

        String sp = "{CALL findHighMargin_produtos()}";
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while(rs.next()){
                int produtoId = rs.getInt("produto_id");
                Long produtoCodigo = rs.getLong("produto_codigo");
                String nomeProduto = rs.getString("nome_produto");
                BigDecimal margemLucro = rs.getBigDecimal("margem_lucro");

                Produto produto = new Produto(produtoId, produtoCodigo, nomeProduto, margemLucro);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return produtos;
    }
}
