package daniel.prueba.Proyecto.java1.service;

import java.util.List;

public interface ICRUD<T,ID> {

    T save (T t) throws Exception;

    T update(T t) throws Exception;

    List<T> readAll() throws Exception;

    T buscarById(ID id) throws Exception;

    void eliminar(ID id)throws Exception;

}
