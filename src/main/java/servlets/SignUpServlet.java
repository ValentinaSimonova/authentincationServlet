package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// сервлет обработки запросов на signup - регистрация
public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем параметры из запроса для регистрации
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        if(login != null && pass != null){
            // Регистрация пользователя
            UserProfile userProfile = new UserProfile(login, pass);
            accountService.addNewUser(userProfile);
        }
    }
}
