package co.edu.poli.ces3.socrates.servlet;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Enrollment;
import co.edu.poli.ces3.socrates.services.EnrollmentService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "EnrollmentServlet", value = "/enrollments")
public class EnrollmentServlet extends HttpServlet {

    private EnrollmentService service;

    @Override
    public void init() {
        try {
            MysqlConnection connection = new MysqlConnection() {
                @Override
                public void disconnect() {
                }
            };
            service = new EnrollmentService(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error iniciando EnrollmentServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String idParam = request.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);
                Enrollment enrollment = service.getEnrollmentById(id);
                if (enrollment != null) {
                    out.print(new Gson().toJson(enrollment));
                } else {
                    response.setStatus(404);
                    out.print("{\"error\":\"Enrollment not found\"}");
                }
            } else {
                List<Enrollment> list = service.getAllEnrollments();
                out.print(new Gson().toJson(list));
            }
        } catch (SQLException e) {
            response.setStatus(500);
            response.getWriter().print("{\"error\":\"Error consultando enrollments\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Enrollment enrollment = new Gson().fromJson(request.getReader(), Enrollment.class);
            service.createEnrollment(enrollment);
            response.setStatus(201);
            response.getWriter().print("{\"message\":\"Enrollment created successfully\"}");
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"Error creating enrollment\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Enrollment enrollment = new Gson().fromJson(request.getReader(), Enrollment.class);
            boolean updated = service.updateEnrollment(enrollment);
            if (updated) {
                response.getWriter().print("{\"message\":\"Enrollment updated successfully\"}");
            } else {
                response.setStatus(404);
                response.getWriter().print("{\"error\":\"Enrollment not found\"}");
            }
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"Error updating enrollment\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean deleted = service.deleteEnrollment(id);
            if (deleted) {
                response.getWriter().print("{\"message\":\"Enrollment deleted successfully\"}");
            } else {
                response.setStatus(404);
                response.getWriter().print("{\"error\":\"Enrollment not found\"}");
            }
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"Error deleting enrollment\"}");
        }
    }
}
