package servelet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.TaylorSz.dao.ConnectionFactory;
import org.TaylorSz.dao.FinanceiroDAO;
import org.TaylorSz.model.Financeiro;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/contas")
public class ContaServelet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionFactory.getConnection();

        String idStr = req.getParameter("id");
        String beneficiario = req.getParameter("beneficiario");
        LocalDate data = LocalDate.parse(req.getParameter("dataVencimento"));
        BigDecimal valor = new BigDecimal(req.getParameter("valor"));
        String status = req.getParameter("status");

        if (idStr == null) {
            Financeiro financeiro = new Financeiro(beneficiario, data, valor, status);
            FinanceiroDAO financeiroDAO = new FinanceiroDAO(connection);
            financeiroDAO.save(financeiro);
        }else {
            int id = Integer.parseInt(idStr);
            Financeiro financeiro = new Financeiro(id, beneficiario, data, valor, status);
            FinanceiroDAO financeiroDAO = new FinanceiroDAO(connection);
            financeiroDAO.update(financeiro);
        }

        resp.sendRedirect("/SimbaPets/contas");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();

        FinanceiroDAO financeiroDAO = new FinanceiroDAO(connection);
        List<Financeiro> financeiros = financeiroDAO.findAll();
        List<Financeiro> contasPagas = financeiroDAO.contasPagas();
        List<Financeiro> contasPendentes = financeiroDAO.contasPendentes();
        List<Financeiro> contasVencidas = financeiroDAO.contasVencidas();

        req.setAttribute("contasVencidas", contasVencidas);
        req.setAttribute("contasPendentes", contasPendentes);
        req.setAttribute("contasPagas", contasPagas);
        req.setAttribute("contas", financeiros);

        String action = req.getParameter("action");
        String idStr = req.getParameter("id");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(idStr);
            financeiroDAO.delete(id);
            resp.sendRedirect("/SimbaPets/contas");
            return;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Conta.jsp");
        dispatcher.forward(req, resp);
    }
}
