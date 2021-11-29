package web;

// to process HTTP request parameters and redirect to the appropriate JSP page
// based on the login status.
// If login successfully validated with the database then redirect to "todo/todo-list.jsp"
// page; otherwise redirect to login.jsp page.

import dao.LoginDao;
import dao.TodoDao;
import dao.TodoDaoImpl;
import model.LoginBean;
import model.Todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    private TodoDao todoDao;

    public void init(){
        loginDao = new LoginDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.sendRedirect("login/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {

                todoDao = new TodoDaoImpl();
                List<Todo> listTodo = todoDao.selectAllTodos();
                request.setAttribute("listTodo", listTodo);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("todo/todo-list.jsp");
                dispatcher.forward(request, response);
            } else {
                HttpSession session = request.getSession();

                //todo add notification: wrong id and pass is entered.

                // session.setAttribute("user", username);
                // response.sendRedirect("login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();

    }

}
