package org.TaylorSz.dao;

import org.TaylorSz.model.Fornecedor;
import org.TaylorSz.controller.IFornecedorDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements IFornecedorDAO {

    private final Connection connection;

    public FornecedorDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) { //Testado(Funcionando)
        String sp = "{CALL add_fornecedor(?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setString(1, fornecedor.getNomeEmpresa());
            callableStatement.setString(2, fornecedor.getCnpj());
            callableStatement.setString(3, fornecedor.getRua());
            callableStatement.setString(4, fornecedor.getBairro());
            callableStatement.setInt(5, fornecedor.getNumero());
            callableStatement.setString(6, fornecedor.getCidade());
            callableStatement.setString(7, fornecedor.getEstado());
            callableStatement.setString(8, fornecedor.getNomeVendedor());
            callableStatement.setString(9, fornecedor.getTelefone());
            callableStatement.setString(10, fornecedor.getEmail());

            JSONArray jsonArray = new JSONArray();
            List<String> produtosFornecidos = fornecedor.getProdutosFornecidos();

            for (int i = 0; i < produtosFornecidos.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("produto_id", seachProductIdName(produtosFornecidos.get(i)));
                jsonArray.put(jsonObject);
            }

            callableStatement.setString(11, jsonArray.toString());

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fornecedor;
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {  //Testado(Funcionando)
        String sp = "{CALL update_fornecedor(?, ? ,? ,? ,? ,? ,? ,? ,? ,? , ?, ?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, fornecedor.getId());
            callableStatement.setString(2, fornecedor.getNomeEmpresa());
            callableStatement.setString(3, fornecedor.getCnpj());
            callableStatement.setString(4, fornecedor.getRua());
            callableStatement.setString(5, fornecedor.getBairro());
            callableStatement.setInt(6, fornecedor.getNumero());
            callableStatement.setString(7, fornecedor.getCidade());
            callableStatement.setString(8, fornecedor.getEstado());
            callableStatement.setString(9, fornecedor.getNomeVendedor());
            callableStatement.setString(10, fornecedor.getTelefone());
            callableStatement.setString(11, fornecedor.getEmail());

            JSONArray jsonArray = new JSONArray();
            List<String> produtosFornecidos = fornecedor.getProdutosFornecidos();

            for (int i = 0; i < produtosFornecidos.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("produto_id", seachProductIdName(produtosFornecidos.get(i)));
                jsonArray.put(jsonObject);
            }

            callableStatement.setString(12, jsonArray.toString());

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fornecedor;
    }

    @Override
    public void delete(String nomeEmpresa) { //Testado(Funcionado)
        String sp = "{CALL delete_fornecedor(?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setString(1, nomeEmpresa);

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Fornecedor> findAll() { //Testado(Funcionando)
        String sp = "{CALL findAll_fornecedor()}";
        List<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("fornecedor_id");
                String nomeEmpresa = rs.getString("nomeEmpresa");
                String cnpj = rs.getString("cnpj");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                int numero = rs.getInt("numero");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String nomeVendedor = rs.getString("nomeVendedor");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String produtoNome = rs.getString("produto_nome");

                Fornecedor fornecedor = new Fornecedor(id, nomeEmpresa, cnpj, rua, bairro, numero, cidade, estado, nomeVendedor, telefone, email, List.of(produtoNome));
                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fornecedores;
    }

    @Override
    public List<Fornecedor> findByName(String name) { //Testado(Funcionando)
        String sp = "{CALL findByName_fornecedor(?)}";
        List<Fornecedor> fornecedores = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setString(1, name);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("fornecedor_id");
                String nomeEmpresa = rs.getString("nome_fornecedor");
                String cnpj = rs.getString("cnpj");
                String rua = rs.getString("rua");
                String bairro = rs.getString("bairro");
                int numero = rs.getInt("numero");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String nomeVendedor = rs.getString("nomeVendedor");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                String produtoNome = rs.getString("produto_nome");

                Fornecedor fornecedor = new Fornecedor(id, nomeEmpresa, cnpj, rua, bairro, numero, cidade, estado, nomeVendedor, telefone, email, List.of(produtoNome));
                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fornecedores;
    }

    private int seachProductIdName(String name){
        String sql = "SELECT id FROM produtos WHERE nome = ?";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new RuntimeException("Produto: " + name +" não encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
