package Repository;

import lombok.*;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class SpecificRepository<T> {
    private Connection connection;
    public abstract void insertNewContent (T insert) throws SQLException;
    public abstract void updateByID(T object) throws SQLException;
    public abstract void deleteByID(T object) throws SQLException;
}
