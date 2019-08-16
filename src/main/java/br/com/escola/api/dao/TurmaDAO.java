package br.com.escola.api.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.escola.api.data.ConexaoJDBC;
import br.com.escola.api.data.ConexaoMysqlJDBC;
import br.com.escola.api.model.Turma;
import br.com.escola.api.model.util.Status;
public class TurmaDAO {
    private final ConexaoJDBC conexao;
    public TurmaDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoMysqlJDBC();
    }
    public Long inserir(Turma turma) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO turma (data_inicio_turma, status, data_final_turma) VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, turma.getData_inicio_turma());
            stmt.setString(2, turma.getStatus().toString());
            stmt.setString(3, turma.getData_final_turma());
            stmt.execute();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return id;
    }
    public int alterar(Turma turma) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE turma SET data_inicio_turma = ?, status = ?, data_final_turma = ? WHERE id = ?";
        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, turma.getData_inicio_turma());
            stmt.setString(2, turma.getStatus().toString());
            stmt.setString(3, turma.getData_final_turma());
            stmt.setLong(4, turma.getId_turma());
            linhasAfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return linhasAfetadas;
    }
    public int excluir(long id) throws SQLException, ClassNotFoundException {
        int linhasAlfetadas = 0;
        String sqlQuery = "DELETE FROM turma WHERE id = ?";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id);
            linhasAlfetadas = stmt.executeUpdate();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return linhasAlfetadas;
    }
    public Turma selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM turma WHERE id = ?";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parser(rs);
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }
    public List<Turma> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM turma ORDER BY id";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            List<Turma> Turma = new ArrayList<>();
            while (rs.next()) {
                Turma.add(parser(rs));
            }
            return Turma;
        } catch (SQLException e) {
            throw e;
        }
    }
    private Turma parser(ResultSet resultSet) throws SQLException {
        Turma c = new Turma();
        c.setId_turma(resultSet.getLong("id"));
        c.setData_inicio_turma(resultSet.getString("data_inicio_turma"));
        c.setData_final_turma(resultSet.getString("data_final_turma"));
        c.setStatus(Status.valueOf(resultSet.getString("status")));
        return c;
    }
}


