package org.TaylorSz.dao;

import org.TaylorSz.model.Venda;
import org.TaylorSz.controller.IVendaDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class VendaDAO implements IVendaDAO {

    private final Connection connection;

    public VendaDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Venda save(Venda venda) { //Testado(Funcionando)
        String sp = "{CALL add_venda(?, ?, ?, ?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setBigDecimal(1, venda.getValorTotal());
            callableStatement.setString(2, venda.getFormaPagamento());

            //formatção de data para br
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
            String formatarData = venda.getDataVenda().format(formatter);
            callableStatement.setString(3, formatarData);

            //n faço ideia de como eu vou usar JSON para inserir varios produtos, mas nada que o chat gpt n resolva
            JSONArray produtosJSON = new JSONArray();
            List<String> produtos = venda.getProduto();
            List<Integer> quantidades = venda.getQuantidade();

            for (int i = 0; i < produtos.size(); i++) {
                JSONObject produtoJSON = new JSONObject();
                produtoJSON.put("produto_id", seachProductIdName(produtos.get(i)));
                produtoJSON.put("quantidade", quantidades.get(i));
                produtosJSON.put(produtoJSON);
            }

            callableStatement.setString(4, produtosJSON.toString());
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return venda;
    }

    @Override
    public Venda update(Venda venda) { //Testado(Funcionando)
        String sp = "{CALL update_venda(?, ?, ?, ?, ?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, venda.getId());
            callableStatement.setBigDecimal(2, venda.getValorTotal());
            callableStatement.setString(3, venda.getFormaPagamento());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formatterDate = venda.getDataVenda().format(formatter);
            callableStatement.setString(4, formatterDate);

            JSONArray jsonArray = new JSONArray();
            List<String> produtos = venda.getProduto();
            List<Integer> quantidade = venda.getQuantidade();

            for (int i = 0; i < produtos.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("produto_id", seachProductIdName(produtos.get(i)));
                jsonObject.put("quantidade", quantidade.get(i));
                jsonArray.put(jsonObject);
            }

            callableStatement.setString(5, jsonArray.toString());
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return venda;
    }

    @Override
    public void delete(int id) { // Testado(Funcionando)
        String sp = "{CALL delete_venda(?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Venda> findAll() { //Testado(Funcionando)
        String sp = "{CALL findAll_venda()}";
        List<Venda> vendas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while (rs.next()) {
                int venda_id = rs.getInt("venda_id");
                BigDecimal valor_total = rs.getBigDecimal("valor_total");
                String forma_pagamento = rs.getString("forma_pagamento");
                LocalDate data_venda = LocalDate.parse(rs.getString("data_venda"), formatter);
                String produto_nome = rs.getString("produto_nome");
                int quantidade = rs.getInt("quantidade");

                Venda venda = new Venda(venda_id, List.of(produto_nome), List.of(quantidade), valor_total, forma_pagamento, data_venda);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vendas;
    }

    @Override
    public List<Venda> findByDate(LocalDate date) { //Testado(Funcionando)
        String sp = "{CALL findByDate_venda(?)}";
        List<Venda> vendas = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateFormatted = date.format(formatter);

            callableStatement.setString(1, dateFormatted);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int venda_id = rs.getInt("venda_id");
                BigDecimal valor_total = rs.getBigDecimal("valor_total");
                String forma_pagamento = rs.getString("forma_pagamento");
                LocalDate data_venda = LocalDate.parse(rs.getString("data_venda"), formatter);
                String produto_nome = rs.getString("produto_nome");
                int quantidade = rs.getInt("quantidade");

                Venda venda = new Venda(venda_id, List.of(produto_nome), List.of(quantidade), valor_total, forma_pagamento, data_venda);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vendas;
    }

    @Override
    public int countDayVenda() { //Testado(Funcionando)

        String sp = "{CALL countDay_venda()}";
        int quantidade = 0;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int totalVendido = rs.getInt("total_venda_dia");

                quantidade = totalVendido;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quantidade;
    }

    @Override
    public int countMonthVenda() { //Testado(Funcionando)

        String sp = "{CALL countMonth_venda()}";
        int quantidade = 0;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int totalVendido = rs.getInt("total_venda_mes");

                quantidade = totalVendido;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quantidade;
    }

    @Override
    public int countYearVenda() { //Testado(Funcionando)

        String sp = "{CALL countYear_venda()}";
        int quantidade = 0;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int totalVendido = rs.getInt("total_vendas_ano");

                quantidade = totalVendido;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quantidade;
    }

    @Override
    public  BigDecimal lucroDayVenda() { //Testado(Funcionado)
        String sp = "{CALL lucro_dia()}";
        BigDecimal lucro = null;
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()){
                BigDecimal totalVendido = rs.getBigDecimal("lucro_total_diario");
                lucro = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lucro;
    }

    @Override
    public BigDecimal lucroWeakVenda() { //Testado(Funcionado)
        String sp = "{CALL lucro_semana()}";
        BigDecimal lucro =  null;
        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()){
                BigDecimal totalVendido = rs.getBigDecimal("lucro_total_semanal");
                lucro = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lucro;
    }

    @Override
    public BigDecimal lucroMonthVenda() { //Testado(Funcionado)
        String sp = "{CALL lucro_mes()}";
        BigDecimal lucro = null;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()){
                BigDecimal totalVendido = rs.getBigDecimal("lucro_total_mensal");
                lucro = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lucro;
    }

    @Override
    public BigDecimal lucroYearVenda() { //Testado(Funcionado)
        String sp = "{CALL lucro_ano()}";
        BigDecimal lucro = null;

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()){
                BigDecimal totalVendido = rs.getBigDecimal("lucro_total_anual");
                lucro = (totalVendido != null) ? totalVendido : BigDecimal.ZERO;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lucro;
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
