package servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.TaylorSz.dao.ConnectionFactory;
import org.TaylorSz.dao.ProdutoDAO;
import org.TaylorSz.model.Produto;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/estoque")
public class EstoqueServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);

        Long codigo = Long.parseLong(req.getParameter("codigo"));
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        Produto produto = new Produto(codigo, quantidade);
        produtoDAO.updateEstque(produto);

        resp.sendRedirect("/SimbaPets/estoque");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        List<Produto> produtos = produtoDAO.findAll();

        req.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Estoque.jsp");
        dispatcher.forward(req, resp);
    }
}
