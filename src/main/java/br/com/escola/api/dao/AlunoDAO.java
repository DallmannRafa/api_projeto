package br.com.escola.api.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.escola.api.data.ConexaoJDBC;
import br.com.escola.api.data.ConexaoMysqlJDBC;
import br.com.escola.api.model.Aluno;
import br.com.escola.api.model.util.Status;
public class AlunoDAO {
    private final ConexaoJDBC conexao;
    public AlunoDAO() throws SQLException, ClassNotFoundException {
        this.conexao = new ConexaoMysqlJDBC();
    }
    public Long inserir(Aluno aluno) throws SQLException, ClassNotFoundException {
        Long id = null;
        String sqlQuery = "INSERT INTO aluno (NOME_ALUNO, status, DATA_NASC_ALUNO) VALUES (?, ?, ?) ";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, aluno.getNome_aluno());
            stmt.setString(2, aluno.getStatus().toString());
            stmt.setString(3, aluno.getDt_nasc());
            stmt.execute();
            this.conexao.commit();
        } catch (SQLException e) {
            this.conexao.rollback();
            throw e;
        }
        return id;
    }
    public int alterar(Aluno aluno) throws SQLException, ClassNotFoundException {
        String sqlQuery = "UPDATE aluno SET NOME_ALUNO = ?, STATUS_ALUNO = ?, DATA_NASC_ALUNO = ? WHERE id = ?";
        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, aluno.getNome_aluno());
            stmt.setString(2, aluno.getStatus().toString());
            stmt.setString(3, aluno.getDt_nasc());
            stmt.setLong(4, aluno.getId_aluno());
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
        String sqlQuery = "DELETE FROM aluno WHERE id = ?";
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
    public Aluno selecionar(long id) throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM aluno WHERE ID_ALUNO = ?";
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
    public List<Aluno> listar() throws SQLException, ClassNotFoundException {
        String sqlQuery = "SELECT * FROM aluno ORDER BY ALUNO";
        try {
            PreparedStatement stmt = this.conexao.getConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while (rs.next()) {
                alunos.add(parser(rs));
            }
            return alunos;
        } catch (SQLException e) {
            throw e;
        }
    }
    private Aluno parser(ResultSet resultSet) throws SQLException {
        Aluno c = new Aluno();
        c.setId_aluno(resultSet.getLong("ID_ALUNO"));
        c.setNome_aluno(resultSet.getString("NOME_ALUNO"));
        c.setDt_nasc(resultSet.getString("DATA_NASC_ALUNO"));
        c.setStatus(Status.valueOf(resultSet.getString("status")));
        return c;
    }
}