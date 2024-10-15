package org.TaylorSz;
import org.TaylorSz.dao.ConnectionFactory;
import org.TaylorSz.dao.FornecedorDAO;
import org.TaylorSz.dao.ProdutoDAO;
import org.TaylorSz.dao.VendaDAO;
import org.TaylorSz.model.Fornecedor;
import org.TaylorSz.model.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            VendaDAO dao = new VendaDAO(connection);

            Produto produto = new Produto(1234567890123L, 1);

            BigDecimal lucro = dao.lucroDayVenda();
            System.out.println(lucro);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
