package br.com.escola.api.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.escola.api.data.ConexaoJDBC;
import br.com.escola.api.data.ConexaoMysqlJDBC;
import br.com.escola.api.model.Professor;
import br.com.escola.api.model.util.Status;
public class ProfessorDAO {
    private final ConexaoJDBC conexao;
    public ProfessorDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoMysqlJDBC();
    }
    public Long inserir(Professor professor) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO professor (NOME_PROFESSOR, STATUS_PROFESSOR, EMAIL_PROFESSOR) VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, professor.getNome_professor());
            stmt.setString(2, professor.getStatus().toString());
            stmt.setString(3, professor.getEmail_professor());
            stmt.execute();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return id;
    }
    public int alterar(Professor professor) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE professor SET NOME_PROFESSOR = ?, STATUS_PROFESSOR = ?, EMAIL_PORFESSOR = ? WHERE ID_PROFESSOR = ?";

        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, professor.getNome_professor());
            stmt.setString(2, professor.getStatus().toString());
            stmt.setString(3, professor.getEmail_professor());
            stmt.setLong(4, professor.getId_professor());
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
        String sqlQuery = "DELETE FROM professor WHERE ID_PROFESSOR = ?";
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
    public Professor selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM professor WHERE ID_PROFESSOR = ?";
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
    public List<Professor> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM professor ORDER BY ID_PROFESSOR";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            List<Professor> Professor = new ArrayList<>();
            while (rs.next()) {
                Professor.add(parser(rs));
            }
            return Professor;
        } catch (SQLException e) {
            throw e;
        }
    }
    private Professor parser(ResultSet resultSet) throws SQLException {
        Professor c = new Professor();
        c.setId_professor(resultSet.getLong("ID_PROFESSOR"));
        c.setNome_professor(resultSet.getString("NOME_PROFESSOR"));
        c.setEmail_professor(resultSet.getString("EMAIL_PROFESSOR"));
        c.setStatus(Status.valueOf(resultSet.getString("STATUS_PROFESSOR")));
        return c;
    }
}





