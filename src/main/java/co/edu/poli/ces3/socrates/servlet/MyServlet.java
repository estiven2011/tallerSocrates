package co.edu.poli.ces3.socrates.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class MyServlet extends HttpServlet {

    private static final String METHOD_PATCH = "PATCH";
    private final Gson gson = new Gson();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String protocol = req.getMethod();

        if(protocol.equals(METHOD_PATCH)){
            doPatch(req,resp);
        }else {
            super.service(req, resp);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String protocol = req.getProtocol();
        String msg = "Metodo patch no soportado";
        if (protocol.endsWith("1.1")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
        }
    }

    protected JsonObject getParamsFromBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        return JsonParser.parseString(sb.toString()).getAsJsonObject();
    }

    protected Object convertJsonElementToFieldType(JsonElement jsonElement, Class<?> targetType) {
        return switch (targetType.getName()) {
            case "java.lang.String" -> jsonElement.getAsString();
            case "int", "java.lang.Integer" -> jsonElement.getAsInt();
            case "long", "java.lang.Long" -> jsonElement.getAsLong();
            case "boolean", "java.lang.Boolean" -> jsonElement.getAsBoolean();
            case "double", "java.lang.Double" -> jsonElement.getAsDouble();
            case "float", "java.lang.Float" -> jsonElement.getAsFloat();
            case "java.math.BigDecimal" -> jsonElement.getAsBigDecimal();
            default -> gson.fromJson(jsonElement, targetType);
        };
    }

}
