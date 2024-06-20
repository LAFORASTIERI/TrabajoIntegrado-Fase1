package dao;
import java.util.List;

public interface IAlmacenDAO<T> {
	void insertar(T t);
	void borrar(int id);
	List<T> listarTodos();
	T mostrarPorId(int id);
}