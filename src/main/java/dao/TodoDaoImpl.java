package dao;

import model.Todo;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoDaoImpl implements TodoDao{

	private static final String INSERT_TODOS_SQL =
			"INSERT INTO todos" +
			"(title, username, description, target_date, is_done) " +
			"VALUES (?, ?, ?, ?, ?);" ;
	private static final String SELECT_TODO_BY_ID =
			"SELECT id, title, username, description, target_date, is_done " +
					"FROM todos WHERE id = ?;";
	private static final String SELECT_ALL_TODOS =
			"SELECT * FROM todos;";
	private static final String DELETE_TODO_BY_ID =
			"DELETE FROM todos WHERE id = ?;";
	private static final String UPDATE_TODO =
			"UPDATE todos SET title = ?, username = ?, description = ?, " +
					"target_date = ?, is_done = ? WHERE id = ?;";

	public TodoDaoImpl(){}

	@Override
	public void insertTodo(Todo todo) throws SQLException {
		System.out.println(INSERT_TODOS_SQL);
		try(Connection connection = JDBCUtils.getConnection();
		    PreparedStatement preparedStatement =
				    connection.prepareStatement(INSERT_TODOS_SQL)){
			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4,
					JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.isStatus());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			JDBCUtils.printSQLException(e);
		}
	}

	@Override
	public Todo selectTodo(int todoId) {
		Todo todo = null;
		// 1.Establish a Connection
		try(Connection connection = JDBCUtils.getConnection();
		    // 2. Create a statement using connection object
			PreparedStatement preparedStatement =
					connection.prepareStatement(SELECT_TODO_BY_ID)){
			preparedStatement.setLong(1,todoId);
			System.out.println(preparedStatement);
			// 3. Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();
			// 4. Process the ResultSet object.
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String username = resultSet.getString("username");
				String description = resultSet.getString("description");
				LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
				boolean isDone = resultSet.getBoolean("is_done");
				todo = new Todo(id, title, username, description, targetDate,
						isDone);
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return todo;
	}

	@Override
	public List<Todo> selectAllTodos() {

		List<Todo> todos = new ArrayList<>();

		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(SELECT_ALL_TODOS)){
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String username = resultSet.getString("username");
				String description = resultSet.getString("description");
				LocalDate targetDate = resultSet.getDate("target_date").toLocalDate();
				boolean isDone = resultSet.getBoolean("is_done");
				todos.add(new Todo(id, title, username, description, targetDate,
						isDone));
			}
		} catch (SQLException e) {
			JDBCUtils.printSQLException(e);
		}
		return todos;
	}

	@Override
	public boolean deleteTodo(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(DELETE_TODO_BY_ID)){
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public boolean updateTodo(Todo todo) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(UPDATE_TODO)){

			preparedStatement.setString(1, todo.getTitle());
			preparedStatement.setString(2, todo.getUsername());
			preparedStatement.setString(3, todo.getDescription());
			preparedStatement.setDate(4,
					JDBCUtils.getSQLDate(todo.getTargetDate()));
			preparedStatement.setBoolean(5, todo.isStatus());
			preparedStatement.setLong(6, todo.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
}
