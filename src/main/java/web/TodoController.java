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
import javax.servlet.http.HttpSession;
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
		request.setCharacterEncoding("utf-8");

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
					// this page is displayed at the very first.
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

		HttpSession session = request.getSession();
		int user_id =
				Integer.parseInt( String.valueOf(session.getAttribute(
						"user_id")) );
		List<Todo> listTodo = todoDao.selectAllTodos(user_id);
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
			// todo: check why these 3 exception can be handled here?
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		int user_id =
				Integer.parseInt( String.valueOf(session.getAttribute(
						"user_id")) );

		int task_id = Integer.parseInt(request.getParameter("task_id"));
				System.out.println("task_id in showEditForm: " + task_id);
		Todo existingTodo = todoDao.selectTodo(task_id, user_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("todo" +
				"/todo-form.jsp");
		request.setAttribute("todo", existingTodo);
		dispatcher.forward(request, response);
	}

	private void insertTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{

		HttpSession session = request.getSession();
		String user_id = String.valueOf( session.getAttribute("user_id") );

		String task_name = request.getParameter("task_name");
		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//		LocalDate targetDate = LocalDate.parse(request.getParameter(
//				"targetDate"), df);
		Todo newTodo = new Todo(task_name, user_id, LocalDate.now(), isDone);
		todoDao.insertTodo(newTodo);
		//todo what is sendRedirect()?
		response.sendRedirect("list");
	}

	private void updateTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{

		int task_id = Integer.parseInt(request.getParameter("task_id"));
		String task_name = request.getParameter("task_name");

		HttpSession session = request.getSession();
		String user_id = String.valueOf( session.getAttribute("user_id") );

		LocalDate targetDate = LocalDate.parse(request.getParameter(
				"targetDate"));
		boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
		Todo updateTodo = new Todo(task_id, task_name, user_id, targetDate, isDone);
		todoDao.updateTodo(updateTodo);
		response.sendRedirect("list");
	}

	private void deleteTodo(HttpServletRequest request,
	                        HttpServletResponse response)
		throws SQLException, IOException{
		int task_id = Integer.parseInt(request.getParameter("task_id"));
		todoDao.deleteTodo(task_id);
		response.sendRedirect("list");
	}


}
