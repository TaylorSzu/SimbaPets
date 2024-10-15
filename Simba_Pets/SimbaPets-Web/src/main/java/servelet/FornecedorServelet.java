package servelet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.TaylorSz.controller.IFornecedorDAO;
import org.TaylorSz.dao.ConnectionFactory;
import org.TaylorSz.dao.FornecedorDAO;
import org.TaylorSz.dao.ProdutoDAO;
import org.TaylorSz.model.Fornecedor;
import org.TaylorSz.model.Produto;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/fornecedores")
public class FornecedorServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        String idStr = req.getParameter("id");
        String nomeFornecedor = req.getParameter("nomeFornecedor");
        String cnpj =   req.getParameter("cnpj");
        String rua = req.getParameter("rua");
        String bairro = req.getParameter("bairro");
        int numero = Integer.parseInt(req.getParameter("numero"));
        String cidade = req.getParameter("cidade");
        String estado = req.getParameter("estado");
        String nomeVendedor = req.getParameter("nomeVendedor");
        String telefone = req.getParameter("telefone");
        String email = req.getParameter("email");

        if (idStr == null) {
            String[] produtos = req.getParameterValues("produtos[]");
            Fornecedor fornecedor = new Fornecedor(nomeFornecedor, cnpj, rua, bairro, numero, cidade, estado, nomeVendedor, telefone, email, Arrays.asList(produtos));
            FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
            fornecedorDAO.save(fornecedor);
            resp.sendRedirect("/SimbaPets/fornecedores");
        }else {
            int id = Integer.parseInt(idStr);
            String[] produtos = req.getParameterValues("produtosEditar[]");
            Fornecedor fornecedor = new Fornecedor(id, nomeFornecedor, cnpj, rua, bairro, numero, cidade, estado, nomeVendedor, telefone, email, Arrays.asList(produtos));
            FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
            fornecedorDAO.update(fornecedor);
            resp.sendRedirect("/SimbaPets/fornecedores");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        FornecedorDAO fornecedorDAO = new FornecedorDAO(connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        List<Fornecedor> fornecedores = fornecedorDAO.findAll();
        List<Produto> produtos = produtoDAO.findAll();

        req.setAttribute("produtosFonecedor", produtos);
        req.setAttribute("fornecedores", fornecedores);

        String action = req.getParameter("action");
        String nomeEmpresa = req.getParameter("nomeEmpresa");

        if ("delete".equals(action)) {
            fornecedorDAO.delete(nomeEmpresa);
            resp.sendRedirect("/SimbaPets/fornecedores");
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

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Fornecedor.jsp");
        dispatcher.forward(req, resp);
    }
}
