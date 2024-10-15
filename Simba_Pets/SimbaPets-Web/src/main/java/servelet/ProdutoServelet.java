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
import com.google.gson.Gson;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

@WebServlet("/produtos")
public class ProdutoServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        String idStr = req.getParameter("id");

        if (idStr == null) {
            Long codigo = Long.parseLong(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            String marca = req.getParameter("marca");
            String categoria = req.getParameter("categoria");
            String tipoPeso = req.getParameter("tipoPeso");
            BigDecimal peso = new BigDecimal(req.getParameter("peso"));
            BigDecimal precoFornecimento = new BigDecimal(req.getParameter("precoFornecimento"));
            BigDecimal precoRevenda = new BigDecimal(req.getParameter("precoRevenda"));
            int estoque = Integer.parseInt(req.getParameter("estoque"));
            boolean isGranel = req.getParameter("vendaGranel") != null;
            float porcentagem = Float.parseFloat(req.getParameter("porcentagem"));
            String descricao = req.getParameter("descricao");

            Produto produto = new Produto(codigo, nome, marca, categoria, tipoPeso, peso, precoFornecimento, precoRevenda, estoque, isGranel, porcentagem, descricao);
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.save(produto);
            resp.sendRedirect("/SimbaPets/produtos");
        } else {
            int id = Integer.parseInt(idStr);
            Long codigo = Long.parseLong(req.getParameter("codigo"));
            String nome = req.getParameter("nome");
            String marca = req.getParameter("marca");
            String categoria = req.getParameter("categoria");
            String tipoPeso = req.getParameter("tipoPeso");
            BigDecimal peso = new BigDecimal(req.getParameter("peso"));
            BigDecimal precoFornecimento = new BigDecimal(req.getParameter("precoFornecimento"));
            BigDecimal precoRevenda = new BigDecimal(req.getParameter("precoRevenda"));
            boolean isGranel = req.getParameter("vendaGranel") != null;
            float porcentagem = Float.parseFloat(req.getParameter("porcentagem"));
            String descricao = req.getParameter("descricao");

            Produto produto = new Produto(id, codigo, nome, marca, categoria, tipoPeso, peso, precoFornecimento, precoRevenda, isGranel, porcentagem, descricao);
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.update(produto);
            resp.sendRedirect("/SimbaPets/produtos");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        ProdutoDAO produtoDAO = new ProdutoDAO(connection);

        String action = req.getParameter("action");
        String codigoStr = req.getParameter("codigo");

        if ("delete".equals(action)) {
            Long codigo = Long.parseLong(codigoStr);
            produtoDAO.delete(codigo);
            resp.sendRedirect("/SimbaPets/produtos");
            return;
        }
        if ("edit".equals(action)) {
            Long codigo = Long.parseLong(codigoStr);
            Produto produtoEditar = produtoDAO.findById(codigo);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String json = gson.toJson(produtoEditar);

            resp.getWriter().write(json);
            return;
        }else {
            List<Produto> produtos = produtoDAO.findAll();
            req.setAttribute("produtos", produtos);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/Produto.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
