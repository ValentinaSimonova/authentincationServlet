package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// сервлет обработки запросов на signin - авторизация
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");
        UserProfile user = accountService.getUserByLogin(login);

        // Зарегестрирован ли пользователь
        if(user != null && user.getLogin().equals(login) && user.getPass().equals(pass)){
            resp.setStatus(HttpServletResponse.SC_OK);
            accountService.addSession(req.getSession().getId(), user);
            resp.getWriter().println("Authorized: "+login);
        }else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Unauthorized");
        }
    }
}
