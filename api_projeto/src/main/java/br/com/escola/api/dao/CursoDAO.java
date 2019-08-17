
package br.com.escola.api.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.escola.api.data.ConexaoJDBC;
import br.com.escola.api.data.ConexaoMysqlJDBC;
import br.com.escola.api.model.Curso;
import br.com.escola.api.model.util.Status;
public class CursoDAO {
    private final ConexaoJDBC conexao;
    public CursoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoMysqlJDBC();
    }
    public Long inserir(Curso curso) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO curso (NOME_CURSO, STATUS_CURSO, CARGA_HORARIA) VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, curso.getNome_curso());
            stmt.setString(2, curso.getStatus().toString());
            stmt.setString(3, curso.getCarga_horaria());
            stmt.execute();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return id;
    }
    public int alterar(Curso curso) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE curso SET NOME_CURSO = ?, STATUS_CURSO = ?, CARGA_HORARIA = ? WHERE ID_CURSO = ?";
        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, curso.getNome_curso());
            stmt.setString(2, curso.getStatus().toString());
            stmt.setString(3, curso.getCarga_horaria());
            stmt.setLong(4, curso.getId_curso());
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
        String sqlQuery = "DELETE FROM curso WHERE ID_CURSO = ?";
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
    public Curso selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM curso WHERE ID_CURSO = ?";
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
    public List<Curso> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM curso ORDER BY ID_CURSO";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            List<Curso> cursos = new ArrayList<>();
            while (rs.next()) {
                cursos.add(parser(rs));
            }
            return cursos;
        } catch (SQLException e) {
            throw e;
        }
    }
    private Curso parser(ResultSet resultSet) throws SQLException {
        Curso c = new Curso();
        c.setId_curso(resultSet.getLong("ID_CURSO"));
        c.setNome_curso(resultSet.getString("NOME_CURSO"));
        c.setCarga_horaria(resultSet.getString("CARGA_HORARIA"));
        c.setStatus(Status.valueOf(resultSet.getString("STATUS_CURSO")));
        return c;
    }
}