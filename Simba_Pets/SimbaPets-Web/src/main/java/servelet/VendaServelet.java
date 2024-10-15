package servelet;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.TaylorSz.model.Venda;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebServlet("/vendas")
public class VendaServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        BigDecimal valor = new BigDecimal(req.getParameter("valor"));
        LocalDate data = LocalDate.now();
        String formaPagamento = req.getParameter("formaPagamento");
        String[] produtos = req.getParameterValues("produtos[]");
        String[] quantidadeStr =  req.getParameterValues("quantidade[]");


        Integer[] quantidade = null;
        if (quantidadeStr != null) {
            quantidade = Arrays.stream(quantidadeStr)
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);
        }

        Venda venda = new Venda(Arrays.asList(produtos), Arrays.asList(quantidade), valor, formaPagamento, data);
        VendaDAO vendaDAO = new VendaDAO(connection);
        vendaDAO.save(venda);

        resp.sendRedirect("/confirmacaoVen");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        VendaDAO vendaDAO = new VendaDAO(connection);
        List<Produto> produtos = produtoDAO.findAll();
        List<Venda> vendas = vendaDAO.findAll();

        req.setAttribute("produtosVenda", produtos);
        req.setAttribute("vendas", vendas);

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(idStr);
            vendaDAO.delete(id);
            resp.sendRedirect("/SimbaPets/vendas");
            return;
        }

        String jsonAction = req.getParameter("jsonAction");
        if ("getProdutos".equals(jsonAction)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(produtos);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Venda.jsp");
        dispatcher.forward(req, resp);
    }
}
