package org.TaylorSz.dao;

import org.TaylorSz.model.Financeiro;
import org.TaylorSz.controller.IFinanceiroDAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinanceiroDAO implements IFinanceiroDAO {

    private final Connection connection;

    public FinanceiroDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public Financeiro save(Financeiro financeiro) { //Testado(Funcionando)
        String sp = "{CALL add_conta(?,?,?,?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setString(1, financeiro.getBeneficiario());
            callableStatement.setDate(2, java.sql.Date.valueOf(financeiro.getDataVencimento()));
            callableStatement.setBigDecimal(3, financeiro.getValor());
            callableStatement.setString(4, financeiro.getStatus());

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return financeiro;
    }

    @Override
    public Financeiro update(Financeiro financeiro) { //Testado(Funcionando)

        String sp = "{CALL update_conta(?,?,?,?,?)}";

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, financeiro.getId());
            callableStatement.setString(2, financeiro.getBeneficiario());
            callableStatement.setDate(3, java.sql.Date.valueOf(financeiro.getDataVencimento()));
            callableStatement.setBigDecimal(4, financeiro.getValor());
            callableStatement.setString(5, financeiro.getStatus());

            callableStatement.executeUpdate();

        } catch (SQLException ex) {
            new RuntimeException(ex);
        }
        return financeiro;
    }

    @Override
    public void delete(int id) { //Testado(Funcionando)
        String sp = "{CALL delete_conta(?)}";

        try (Connection connection = ConnectionFactory.getConnection(); CallableStatement callableStatement = connection.prepareCall(sp)) {

            callableStatement.setInt(1, id);

            callableStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Financeiro> findAll() { //Testado(Funcionando)
        String sql = "{CALL findAll_conta()}";
        List<Financeiro> financeiros = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection(); CallableStatement callableStatement = connection.prepareCall(sql)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String beneficiario = rs.getString("beneficiario");
                java.sql.Date sqlDate = rs.getDate("data_vencimento");
                LocalDate data_vencimento = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                BigDecimal valor = rs.getBigDecimal("valor");
                String status = rs.getString("status_conta");

                Financeiro financeiro = new Financeiro(id, beneficiario, data_vencimento, valor, status);
                financeiros.add(financeiro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return financeiros;
    }

    @Override
    public Optional<Financeiro> findById(int id) { //Testado(Funcionando)
        String sql = "{CALL findById_conta(?)}";
        Financeiro financeiro = null;

        try (Connection connection = ConnectionFactory.getConnection(); 
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id1 = rs.getInt("id");
                String beneficiario = rs.getString("beneficiario");
                java.sql.Date sqlDate = rs.getDate("data_vencimento");
                LocalDate data_vencimento = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                BigDecimal valor = rs.getBigDecimal("valor");
                String status = rs.getString("status_conta");

                financeiro = new Financeiro(id1, beneficiario, data_vencimento, valor, status);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(financeiro);
    }

    @Override
    public List<Financeiro> contasPendentes() { //Testado(Funcionando)
        String sp = "{CALL contas_pendentes()}";
        List<Financeiro> financeiros = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("conta_id");
                String beneficiario = rs.getString("beneficiario");
                String dataStr = rs.getString("data_vencimento");
                LocalDate data_vencimento = (dataStr != null) ? LocalDate.parse(dataStr, formatter) : null;
                BigDecimal valor = rs.getBigDecimal("valor");
                String status = rs.getString("status_conta");

                Financeiro financeiro = new Financeiro(id, beneficiario, data_vencimento, valor, status);
                financeiros.add(financeiro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return financeiros;
    }

    @Override
    public List<Financeiro> contasPagas() { //Testado(Funcionando)
        String sp = "{CALL contas_pagas()}";
        List<Financeiro> financeiros = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("conta_id");
                String beneficiario = rs.getString("beneficiario");
                String dataStr = rs.getString("data_vencimento");

                // Converte a String para LocalDate usando o DateTimeFormatter
                LocalDate data_vencimento = (dataStr != null) ? LocalDate.parse(dataStr, formatter) : null;

                BigDecimal valor = rs.getBigDecimal("valor");
                String status = rs.getString("status_conta");

                Financeiro financeiro = new Financeiro(id, beneficiario, data_vencimento, valor, status);
                financeiros.add(financeiro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return financeiros;
    }

    @Override
    public List<Financeiro> contasVencidas() { //Testado(Funcionando)
        String sp = "{CALL contas_vencidas()}";
        List<Financeiro> financeiros = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Connection connection = ConnectionFactory.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sp)) {

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("conta_id");
                String beneficiario = rs.getString("beneficiario");
                String dataStr = rs.getString("data_vencimento");

                // Converte a String para LocalDate usando o DateTimeFormatter
                LocalDate data_vencimento = (dataStr != null) ? LocalDate.parse(dataStr, formatter) : null;

                BigDecimal valor = rs.getBigDecimal("valor");
                String status = rs.getString("status_conta");

                Financeiro financeiro = new Financeiro(id, beneficiario, data_vencimento, valor, status);
                financeiros.add(financeiro);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return financeiros;
    }
}
