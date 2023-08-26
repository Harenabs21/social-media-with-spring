package Repository;


import lombok.*;
import java.sql.*;
import java.util.List;
import java.util.Optional;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class GenericRepository<T> {
    private Connection connection;

    public abstract void insert(T toInsert) throws SQLException;

    public abstract List<T> findAll() throws SQLException;


    public abstract void updateById(int id,T object) throws SQLException;



    public abstract Optional<T> findById(int id) throws SQLException;
    public abstract void deleteById(int id) throws SQLException;
}
