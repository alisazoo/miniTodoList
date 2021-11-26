package web;

import dao.TodoDao;
import dao.TodoDaoImpl;
import model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao;

	public void init(){
		todoDao = new TodoDaoImpl();
	}

	protected  void doPost(HttpServletRequest request,
	                       HttpServletResponse response)
		throws ServletException, IOException{
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
	                     HttpServletResponse response)
		throws ServletException, IOException{
		String action = request.getServletPath();

		try{
			switch(action){
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertTodo(request, response);
					break;
				case "/delete":
					deleteTodo(request, response);
					break;
				case "/edit":
					showEditForm(request,response);
					break;
				case "/update":
					updateTodo(request, response);
					break;
				case "/list":
					listTodo(request, response);
					break;
				default:
					RequestDispatcher dispatcher =
							request.getRequestDispatcher("login/login.jsp");
					dispatcher.forward(request, response);
					break;
			}
		} catch(SQLException e){
			throw new ServletException(e);
		}
	}

	private void listTodo(HttpServletRequest request,
	                      HttpServletResponse response)
		throws SQLException, IOException, ServletException{
		List<Todo> listTodo = todoDao.selectAllTodos();
		request.setAttribute("listTodo", listTodo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo" +
				"/todo-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request,
	                         HttpServletResponse response)
		throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo" +
				"/todo-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request,
	                          HttpServletResponse response)
			// todo why these 3 exception can be handled here?
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Todo existingTodo = todoDao.selectTodo(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo" +
				"/todo-form.jsp");
		// todo the role of this attribute?
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);
	}

	private void insertTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//		LocalDate targetDate = LocalDate.parse(request.getParameter(
//				"targetDate"), df);
		Todo newTodo = new Todo(title, username, description, LocalDate.now()
				, isDone);
		todoDao.insertTodo(newTodo);
		//todo what is sendRedirect()?
		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String username = request.getParameter("username");
		String description = request.getParameter("description");
		LocalDate targetDate = LocalDate.parse(request.getParameter(
				"targetDate"));
		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
		Todo updateTodo = new Todo(id, title, username,description,
				targetDate, isDone);
		todoDao.updateTodo(updateTodo);
		response.sendRedirect("list");
	}



	private void deleteTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		todoDao.deleteTodo(id);
		response.sendRedirect("list");
	}


}
