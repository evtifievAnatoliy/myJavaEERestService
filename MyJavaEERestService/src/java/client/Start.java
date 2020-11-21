/*
 * Курс "DEV-j210. Java EE. Разработка веб-сервисов".
 * Стартовый сервлет приложения,демонстрирующего разработку простого RESTful 
 * веб-сервиса. 
 * (С) Ю.Д.Заковряшин, 2018-2020.
 */
package client;

import demo.IFormConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Стартовый сервлет приложения.
 *
 * @author (С) Ю.Д.Заковряшин, 2018-2020.
 */
@WebServlet(name = "Start", urlPatterns = {"/Start"})
public class Start extends HttpServlet implements IFormConstants {

    /**
     * Перечисление, определяющее типы команд, которые обрабатываются сервлетом.
     */
    public static enum Command {
        ADD, UPDATE, DELETE, LIST, EXIT, ERROR;
    }
    /**
     *
     */
    private Client client;
    /**
     *
     */
    private WebTarget basic;

    /**
     * Метод инициализации объекта сервлета, в котором создается REST-клиент и
     * определяется базовый адрес запроса.
     */
    @Override
    public void init() {
        client = ClientBuilder.newClient();
        basic = client.target("http://localhost:8080/MyJavaEERestService/rs/demo");
       
    }

    /**
     * Обработка запроса на регистрацию пользователя.
     *
     * @param request servlet request ссылка на объект запроса
     * @param response servlet response ссылка на объект ответа
     * @throws IOException выбрасывается в случае возникновения ошибок
     * ввода/вывода
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Определение кодировок запроса и ответа
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        try (PrintWriter out = response.getWriter()) {
            // Имя пользователя не должно быть равным null или не должно быть 
            // пустым
            if (user == null || user.trim().isEmpty()) {
                // Возврат на стартовую форму с сообщением об ошибке
                out.println(formStart(ERROR_LOGIN));
                return;
            }
            // Открытие сессии пользователя и сохранение его имени в атрибуте
            // сессии
            HttpSession hs = request.getSession();
            hs.setAttribute("user", user);
            // Вывод основной формы приложения
            out.println(formMain(null));
        }
    }

    /**
     * Обработка запросов на добавление, изменение, удаление сообщений, а также
     * запросов на получение всего списка сообщений.
     *
     * @param request servlet request ссылка на объект запроса
     * @param response servlet response ссылка на объект ответа
     * @throws IOException выбрасывается в случае возникновения ошибок
     * ввода/вывода
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Определение кодировок запроса и ответа
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // Поскольку запрос может содержать разные наборы пар 
        // "команда:параметр команды", то удобнее получить коллекцию всех
        // параметров запроса.
        Map<String, String[]> map = request.getParameterMap();
        // Коллекция имён параметров полезна для поиска команды
        Set<String> set = map.keySet();
        // Определение имени команды и параметров команды
        Command cmd = null;
        String user = null;
        // Значения параметров в коллекции map хранятся  в виде массива,
        // но поскольку мы знаем, что из формы передаётся только одно значение,
        // из массива значений мы извлекаем только первый элемент.
        String msg = map.get("msg")[0];
        String id = map.get("id")[0];
        HttpSession hs = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            if (hs == null) {
                out.println(formStart(ERROR_SESSION));
                return;
            } else {
                // Определение пользователя по атрибуту сессии
                user = (String) hs.getAttribute("user");
                // Определение полученной команды
                if (set.contains("add")) {
                    cmd = Command.ADD;
                } else if (set.contains("update")) {
                    cmd = Command.UPDATE;
                } else if (set.contains("delete")) {
                    cmd = Command.DELETE;
                } else if (set.contains("list")) {
                    cmd = Command.LIST;
                } else if (set.contains("exit")) {
                    out.println(formStart(null));
                    return;
                } else {
                    // Обработка ситуации, когда получена неизвестная команда
                    // или параметр с именем команды по какой-то причине
                    // отсутствует.
                    out.println(formStart(ERROR_COMMAND));
                    return;
                }
            }
            String reply = query(cmd, user, msg, id);
            if (cmd == Command.LIST) {
                // Формирование ответа со общим списком сообщений
                out.println(formList(reply));
            } else {
                // Формирование основной формы приложения
                out.println(formMain(reply));
            }
        } catch (Exception e) {
            // Обработка всех возникших исключений, за исключением тех, которые
            // связаны с нарушением ограничений на параметры ввода
            log(">>> ERROR: " + e.getMessage());
//            e.printStackTrace();
            response.sendRedirect("index.html");
        }
    }

    /**
     * Краткое описание сервлета
     *
     * @return строка с описанием сервлета.
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    /**
     * Метод посылает запрос к REST-сервису и получает ответ на запрос.
     *
     * @param cmd идентификатор команды
     * @param user регистрационное имя пользователя
     * @param msg строка сообщения
     * @param id строка, содержащая индекс сообщения для замены или удаления
     * @return строка, содержащая ответ службы на запрос
     * @throws Exception выбрасывается в случае возникновения непредвиденных
     * исключений
     */
    private String query(Command cmd, String user, String msg, String id)
            throws Exception {
        // Объект Builder предназначен для формирования HTTP запроса из 
        // данных пользователя
        Invocation.Builder builder = null;
        // Уточнённый адрес отправки запроса
        WebTarget target = null;
        String reply = null;
        int index = 0;
        switch (cmd) {
            case ADD:
                // Определение адреса запроса. В данном примере к базовому адресу
                // добавляется параметр пути с именем пользователя
                target = basic.path("{user}");
                // Подстановка конкретного имени пользователя в адрес запроса
                target = target.resolveTemplate("user", user);
                // Добавление к запросу параметра запроса msg
                target = target.queryParam("msg", msg);
                // Определение типа ответа на запрос, что нужно для 
                // правильной интерпретации полученного ответа
                builder = target.request(MediaType.TEXT_HTML);
                // Формирование запроса, отправка запроса типа POST и получение
                // ответа на запрос. Второй параметр метода позволяет сразу
                // получить ответ в виде строки
                reply = builder.post(null, String.class);
                break;
            case UPDATE:
                // Аналогично запросу POST формируется и отсылается запрос 
                // типа PUT, только частью адреса запроса теперь является
                // индекс сообщения, который является параметром пути
                target = basic.path("{id}");
                target = target.resolveTemplate("id", id);
                // user  и msg являются параметрами запроса, а не пути
                target = target.queryParam("user", user);
                target = target.queryParam("newMessage", msg);
                builder = target.request(MediaType.TEXT_HTML);
                reply = builder.put(Entity.entity(msg, MediaType.TEXT_PLAIN),
                        String.class);
                break;
            case DELETE:
                // Аналогично запросу PUT формируется и отсылается запрос 
                // типа DELETE
                target = basic.path("{id}");
                target = target.resolveTemplate("id", id);
                builder = target.request(MediaType.TEXT_HTML);
                reply = builder.delete(String.class);
                break;
            case LIST:
                // Формируется запрос типа GET для получения списка всех
                // сообщений
                target = basic.path("list");
                builder = target.request(MediaType.TEXT_HTML);
                reply = builder.get(String.class);
        }
        return reply;
    }

    /**
     * Метод формирует HTML-страницу со стартовой формой приложения.
     *
     * @param message сообщение, которое добавляется на страницу.
     * @return строка, содержащая стартовую HTML-страницу.
     */
    private String formStart(String message) {
        StringBuilder sb = new StringBuilder(HEADER).append(START_FORM);
        // Если сообщение не null, то оно добавляется на страницу
        if (message != null) {
            sb.append("<p style='color:red;'>").append(message)
                    .append("</p>");
        }
        sb.append(FOOTER);
        return sb.toString();
    }

    /**
     * Метод формирует HTML-страницу с главной формой приложения.
     *
     * @param message сообщение, которое добавляется на страницу.
     * @return строка, содержащая HTML-страницу с главной формой приложения.
     */
    private String formMain(String message) {
        StringBuilder sb = new StringBuilder(HEADER).append(MAIN_FORM);
        // Если сообщение не null, то оно добавляется на страницу
        if (message != null) {
            sb.append("<p>Reply: ").append(message).append("</p>\n");
        }
        sb.append(FOOTER);
        return sb.toString();
    }

    /**
     * Метод формирует HTML-страницу, содержащую главную форму приложения и
     * список всех полученных ранее сообщений.
     *
     * @param message сообщение, которое добавляется на страницу.
     * @return строка, содержащая HTML-страницу главной формой приложения и
     * общим списком сообщений.
     */
    private String formList(String message) {
        StringBuilder sb = new StringBuilder(HEADER).append(MAIN_FORM);
        sb.append(LIST_HEAD);
        // Если сообщение не null, то оно добавляется на страницу
        if (message != null) {
            sb.append(message);
        }
        sb.append(FOOTER);
        return sb.toString();
    }

    /**
     * Метод, вызываемый средой выполнения перед удалением объекта из памяти. В
     * данном методе обеспечивается закрытие клиент REST-сервиса.
     */
    @Override
    public void destroy() {
        if (client != null) {
            client.close();
        }
    }
}
