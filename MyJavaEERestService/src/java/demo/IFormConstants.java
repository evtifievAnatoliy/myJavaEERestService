/*
 * Курс "DEV-j210. Java EE. Разработка веб-сервисов".
 * Интерфейс приложения,демонстрирующего разработку простого RESTful веб-сервиса. 
 * (С) Ю.Д.Заковряшин, 2018-2020.
 */
package demo;

/**
 * Интерфейс содержит определения констант, содержащих HTML-код страниц
 * приложения и их элементов.
 *
 * @author (С) Ю.Д.Заковряшин, 2018-2020.
 */
public interface IFormConstants {

    /**
     * Стандартный заголовок страницы
     */
    String HEADER
            = "<!DOCTYPE html>\n"
            + "<!-- \n"
            + " Курс \"DEV-j210. Java EE. Разработка веб-сервисов\".\n"
            + " Приложение,демонстрирующее разработку простого RESTful \n"
            + " веб-сервиса. \n"
            + " (С) Ю.Д.Заковряшин, 2018-2020.\n"
            + "-->\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <title>Простой RESTful сервис</title>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    </head>\n"
            + "    <body>\n";

    /**
     * Краткое описание сервиса и форма регистрации стартовой страницы
     * приложения.
     */
    String START_FORM
            = "        <h1>Пример RESTful сервиса</h1>\n"
            + "        <p><strong><u>Краткое описание сервиса</u>.</strong><br>\n"
            + "            Во время своей работы сервис обеспечивает запись\n"
            + "            сообщений пользователей в строковом формате. Каждое сообщение имеет\n"
            + "            вид \"имя_пользователя:сообщение\".\n"
            + "        </p>\n"
            + "        <p>\n"
            + "            Имя пользователя, от имени которого вводятся сообщения, определяется\n"
            + "            командой \"Регистрация\". После успешной регистрации на следующей \n"
            + "            форме пользователь может определять параметры и выполять запросы \n"
            + "            \"Добавить сообщение\", \"Изменить сообщение\", \"Удалить сообщение\" и \n"
            + "            \"Получить все сообщения\".\n"
            + "        </p>\n"
            + "        <p>\n"
            + "            Для выхода из приложения или смены имени пользователя следует \n"
            + "            выполнить команду \"Выйти\".\n"
            + "        </p>\n"
            + "        <form action=\"Start\" method=\"GET\">\n"
            + "            <table>\n"
            + "                <tr>\n"
            + "                    <td style=\"text-align: right;\">User:</td>\n"
            + "                    <td><input type=\"text\" name=\"user\" size=\"24\"/></td>\n"
            + "                    <td><input type=\"submit\" name=\"register\" value=\"Регистрация\"/></td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </form>\n";

    /**
     * Заголовок и форма определения параметров операций над общим списком
     * сообщений пользователей.
     */
    String MAIN_FORM
            = "        <h1 style=\"text-align:center\">Пример RESTful сервиса</h1>\n"
            + "        <h2 style=\"text-align:center\">Сообщения</h2>\n"
            + "        <form action=\"Start\" method=\"POST\">\n"
            + "            <table> \n"
            + "                <tr>\n"
            + "                    <td style=\"text-align: right; width: 25%\">\n"
            + "                        Индекс сообщения:\n"
            + "                    </td>\n"
            + "                    <td style=\"width: 25%;\">\n"
            + "                        <input type=\"text\" name=\"id\" size=\"8\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                    <td style=\"width: 25%;\">&nbsp;</td>\n"
            + "                    <td style=\"width: 25%;\">&nbsp;</td>\n"
            + "                </tr>\n"
            + "                <tr>\n"
            + "                    <td style=\"text-align: right; width: 25%;\">Message:</td>\n"
            + "                    <td colspan=\"3\" style=\"width: 75%\">\n"
            + "                        <input type=\"text\" name=\"msg\" size=\"48\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                </tr>\n"
            + "                <tr>\n"
            + "                    <td style=\"text-align: right;\">\n"
            + "                        <input type=\"submit\" name=\"add\" value=\"Добавить\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                    <td>\n"
            + "                        <input type=\"submit\" name=\"update\" value=\"Изменить\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                    <td style=\"text-align: left;\">\n"
            + "                        <input type=\"submit\" name=\"delete\" value=\"Удалить\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                    <td style=\"text-align: left;\">\n"
            + "                        <input type=\"submit\" name=\"list\" value=\"Список\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                </tr>\n"
            + "                <tr>\n"
            + "                    <td>&nbsp;</td>\n"
            + "                    <td colspan=\"2\">\n"
            + "                        <input type=\"submit\" name=\"exit\" value=\"Выйти\""
            + "style=\"width:100%\"/>\n"
            + "                    </td>\n"
            + "                    <td>&nbsp;</td>\n"
            + "                </tr>\n"
            + "            </table>\n"
            + "        </form>";
    /**
     * Заголовок общего списка сообщений.
     */
    String LIST_HEAD
            = "        <h2>Список сообщений</h2>\n"
            + "        <hr style=\"width: 90%; align-items: center\"/>";
    /**
     * Сообщение об успешном выполнении операции.
     */
    String OK
            = "Операция выполнена успешно";
    /**
     * Сообщение об ошибке, связанной с нарушением требований к регистрационному
     * имени пользователя.
     */
    String ERROR_LOGIN
            = "Недопустимое имя пользователя";
    /**
     * Сообщение об ошибке, связанной с отсутствием открытой сесии пользователя.
     */
    String ERROR_SESSION
            = "Пользовательская сессия закрыта";
    /**
     * Сообщение об ошибке, связанной с получением неизвестной команды.
     */
    String ERROR_COMMAND
            = "Получена неизвестная команда";
    /**
     * Сообщение об ошибке, связанной с получением идентификатора сообщения,
     * который не является целым числом.
     */
    String ERROR_ID
            = "Идентификатор сообщения должен быть числом";
    /**
     * Стандартный подвал страницы.
     */
    String FOOTER
            = "        <hr style=\"width: 90%; align-items: center\"/>"
            + "        <p style=\"font-size:10pt; color:darkgray; text-align:center;\">\n"
            + "            &copy;Ю.Д.Заковряшин, 2020\n"
            + "        </p>\n"
            + "    </body>\n</html>\n";
}
