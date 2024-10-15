package servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.TaylorSz.dao.ConnectionFactory;
import org.TaylorSz.dao.ProdutoDAO;
import org.TaylorSz.dao.VendaDAO;
import org.TaylorSz.model.Produto;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

@WebServlet("/estatisticas")
public class EstatisticasServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        VendaDAO vendaDAO = new VendaDAO(connection);


        List<Produto> top10 = produtoDAO.top10Products();
        List<Produto> estoqueBaixo = produtoDAO.findLowStockProducts();
        List<Produto> margemLucro = produtoDAO.findHighMarginProducts();
        List<Produto> quantidadeProduto = produtoDAO.findAll();
        int quantidade = quantidadeProduto.size();
        int quantidadeVendidaDia = vendaDAO.countDayVenda();
        int quantidadeVendidaMes = vendaDAO.countMonthVenda();
        int quantidadeVendidaAno = vendaDAO.countYearVenda();
        BigDecimal lucroDia = vendaDAO.lucroDayVenda();
        BigDecimal lucroSemana = vendaDAO.lucroWeakVenda();
        BigDecimal lucroMes = vendaDAO.lucroMonthVenda();
        BigDecimal lucroAno = vendaDAO.lucroYearVenda();

        req.setAttribute("top10", top10);
        req.setAttribute("estoqueBaixo", estoqueBaixo);
        req.setAttribute("margemLucro",  margemLucro);
        req.setAttribute("quantidade", quantidade);
        req.setAttribute("vendasDia", quantidadeVendidaDia);
        req.setAttribute("vendasMes", quantidadeVendidaMes);
        req.setAttribute("vendasAno", quantidadeVendidaAno);
        req.setAttribute("lucroDia", lucroDia);
        req.setAttribute("lucroSemana", lucroSemana);
        req.setAttribute("lucroMes", lucroMes);
        req.setAttribute("lucroAno", lucroAno);

        RequestDispatcher dispatcher = req.getRequestDispatcher("Estatisticas.jsp");
        dispatcher.forward(req, resp);
    }
}
