package co.edu.poli.ces3.socrates.servlet;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Subject;
import co.edu.poli.ces3.socrates.services.SubjectService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SubjectServlet", value = "/subjects")
public class SubjectServlet extends HttpServlet {

    private SubjectService service;

    @Override
    public void init() {
        try {
            MysqlConnection connection = new MysqlConnection() {
                @Override
                public void disconnect() {

                }
            };
            service = new SubjectService(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error iniciando SubjectServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            List<Subject> list = service.getAllSubjects();
            out.print(new Gson().toJson(list));
        } catch (SQLException e) {
            response.setStatus(500);
            response.getWriter().print("{\"error\":\"Error consultando subjects\"}");
        }
    }
}
