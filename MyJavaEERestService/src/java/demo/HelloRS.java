/*
 * Курс "DEV-j210. Java EE. Разработка веб-сервисов".
 * Класс HelloRS представляет собой простую реализацию REST-сервиса. 
 * (С) Ю.Д.Заковряшин, 2018-2020.
 */
package demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Класс представляет реализацию простого REST-сервиса, который обеспечивает
 * создание, модификацию и удаление пользовательских сообщений.
 *
 * @author (С) Ю.Д.Заковряшин, 2018-2020.
 */
@Path("demo")
public class HelloRS {

    // Ссылка на объект, в котором храняться сообщения пользователей.
    private MessageList list;

    /**
     * Конструктор по умолчанию получает ссылку на объект типа
     * {@link MessageList}.
     */
    public HelloRS() {
        list = MessageList.getInstance();
    }

    /**
     * Метод, обрабатывающий REST-запросы типа GET, позволяет получить общий
     * список сообщений.
     *
     * @return список сообщений
     */
    @GET
    @Path("list")
    @Produces("text/html")
    public String getList() {
        return list.getList();
    }

    /**
     * Метод, обрабатывающий REST-запросы типа POST, позволяет добавить в общий
     * список сообщений новое сообщение от пользователя.
     *
     * @param user - регистрационное имя пользователя.
     * @param message - сообщение пользователя.
     * @return строка с описанием результата выполнения операции.
     */
    @POST
    @Path("{user}")
    @Produces("text/html")
    @Consumes("text/plain")
    public String add(@PathParam("user") String user,
            @QueryParam("msg") String message) {
        if (user == null || user.trim().isEmpty()
                || message == null || message.trim().isEmpty()) {
            return "Error: Invalid message";
        }
        String s = user + ": " + message;
        list.add(s);
        return "New item \"" + s + "\" added!";
    }

    /**
     * Метод, обрабатывающий REST-запросы типа PUT, позволяет заменить в общем
     * списке сообщение с заданным индексом id на новое сообщение от
     * пользователя.
     *
     * @param id - индекс сообщения пользователя, подлежащее замене. Задаётся
     * как параметр пути.
     * @param message - новое сообщение пользователя. Задаётся как параметр
     * запроса.
     * @return строка с описанием результата выполнения операции.
     */
    @PUT
    @Path("{id}")
    @Produces("text/html")
    @Consumes("text/plain")
    public String change(@PathParam("id") String id,
            @QueryParam("user") String user,
            @QueryParam("newMessage") String message) {
        if (id == null || id.trim().isEmpty()) {
            return "Error: Invalid index item";
        }
        try {
            int index = Integer.parseInt(id);
            String s = list.change(index - 1, user, message);
            if (s == null) {
                return "New item \"" + index + ": " + message + "\" added";
            }
            return "Old item \"" + s + "\" changed by \""
                    + message + "\"";
        } catch (NumberFormatException e) {
            return "Error: Invalid index item --> " + e.getMessage();
        }
    }

    /**
     * Метод, обрабатывающий REST-запросы типа DELETE, позволяет удалить
     * сообщение заданным индексом id из общего списка сообщений.
     *
     * @param id - индекс сообщения пользователя, подлежащее замене. Задаётся
     * как параметр пути.
     * @return строка с описанием результата выполнения операции.
     */
    @DELETE
    @Path("{id}")
    @Produces("text/html")
    @Consumes("text/plain")
    public String delete(@PathParam("id") String id) {
        try {
            int index = Integer.parseInt(id);
            String s = list.delete(index);
            if (s != null) {
                return "Item \"" + s + "\" deleted";
            }
            return "Item not found";
        } catch (NumberFormatException e) {
            return "Invalid id parameter";
        }
    }
}

