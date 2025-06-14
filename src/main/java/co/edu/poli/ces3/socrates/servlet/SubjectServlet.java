package co.edu.poli.ces3.socrates.servlet;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Subject;
import co.edu.poli.ces3.socrates.services.SubjectService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
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
                public void disconnect() {}
            };
            service = new SubjectService(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error iniciando SubjectServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Subject subject = service.getSubjectById(id);
                if (subject != null) {
                    out.print(new Gson().toJson(subject));
                } else {
                    response.setStatus(404);
                    out.print("{\"error\":\"Subject no encontrado\"}");
                }
            } else {
                List<Subject> list = service.getAllSubjects();
                out.print(new Gson().toJson(list));
            }
        } catch (Exception e) {
            response.setStatus(500);
            out.print("{\"error\":\"Error consultando subjects\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try (BufferedReader reader = request.getReader(); PrintWriter out = response.getWriter()) {
            Subject subject = new Gson().fromJson(reader, Subject.class);
            service.createSubject(subject);
            response.setStatus(201);
            out.print("{\"message\":\"Subject creado exitosamente\"}");
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().print("{\"error\":\"Error creando subject\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(400);
            out.print("{\"error\":\"Falta el parámetro id\"}");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            boolean deleted = service.deleteSubject(id);
            if (deleted) {
                out.print("{\"message\":\"Subject eliminado exitosamente\"}");
            } else {
                response.setStatus(404);
                out.print("{\"error\":\"Subject no encontrado\"}");
            }
        } catch (Exception e) {
            response.setStatus(500);
            out.print("{\"error\":\"Error eliminando subject\"}");
        }
    }

    @Override
    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try (BufferedReader reader = request.getReader(); PrintWriter out = response.getWriter()) {
            Subject subject = new Gson().fromJson(reader, Subject.class);
            boolean updated = service.updateSubject(subject);
            if (updated) {
                out.print("{\"message\":\"Subject actualizado exitosamente\"}");
            } else {
                response.setStatus(404);
                out.print("{\"error\":\"Subject no encontrado\"}");
            }
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().print("{\"error\":\"Error actualizando subject\"}");
        }
    }
}
