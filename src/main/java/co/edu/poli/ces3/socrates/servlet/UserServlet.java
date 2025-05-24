package co.edu.poli.ces3.socrates.servlet;

import co.edu.poli.ces3.socrates.dao.User;
import co.edu.poli.ces3.socrates.services.UserService;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

@WebServlet(name = "userServlet", value = "/v2/users")
public class UserServlet extends MyServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        List list = userService.getUsers();
        JSONArray json = new JSONArray(list);

        out.print(json);

        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JsonObject jsonUser = getParamsFromBody(req);

        Class<?> classUser = User.class;
        Field[] fields = classUser.getDeclaredFields();
        User userUpdate = new User();

        try {
            for (Field f : fields) {
                if(jsonUser.has(f.getName())) {
                    Class<?> fieldType = f.getType();
                    Object value = convertJsonElementToFieldType(jsonUser.get(f.getName()), fieldType);

                    f.setAccessible(true);
                    f.set(userUpdate, value);
                }
            }

            userUpdate.setId(Integer.parseInt(req.getParameter("id")));

            User user = userService.upgrade(userUpdate);

            // Cambiar JSONArray por JSONObject
            JSONObject json = new JSONObject(user);

            out.print(json.toString());
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores
        }
    }
}
