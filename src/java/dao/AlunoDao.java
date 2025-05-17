package dao;

import dominio.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDao {

    private final Connection conecta;
    PreparedStatement stm;

    public AlunoDao() {
        this.conecta = new ConnectionFactory().conecta();
    }

    public List<Aluno> listarAlunos() {
        try {
            List<Aluno> lista = new ArrayList<>();
            String sql = "SELECT * FROM alunos";
            stm = conecta.prepareStatement(sql);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Aluno a = new Aluno();
                    a.setMatricula(rs.getInt("matricula"));
                    a.setNome(rs.getString("nome"));
                    a.setIdade(rs.getInt("idade"));
                    a.setEmail(rs.getString("email"));
                    lista.add(a);
                }
            }
            stm.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
    }

    public boolean cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO alunos(matricula, nome, idade, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pst = conecta.prepareStatement(sql)) {
            pst.setInt(1, aluno.getMatricula());
            pst.setString(2, aluno.getNome());
            pst.setInt(3, aluno.getIdade());
            pst.setString(4, aluno.getEmail());
            int linhas = pst.executeUpdate();
            return linhas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Aluno buscar(Aluno aluno) {
        String sql = "SELECT * FROM alunos WHERE matricula = ?";
        Aluno retorno = null;
        try (PreparedStatement pst = conecta.prepareStatement(sql)) {
            pst.setInt(1, aluno.getMatricula());
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    retorno = new Aluno();
                    retorno.setMatricula(res.getInt("matricula"));
                    retorno.setNome(res.getString("nome"));
                    retorno.setIdade(res.getInt("idade"));
                    retorno.setEmail(res.getString("email"));
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return retorno;
    }

    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, idade = ?, email = ? WHERE matricula = ?";
        try (PreparedStatement pst = conecta.prepareStatement(sql)) {
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getIdade());
            pst.setString(3, aluno.getEmail());
            pst.setInt(4, aluno.getMatricula());
            int linhas = pst.executeUpdate();
            return linhas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean deletar(int matricula) {
        String sql = "DELETE FROM alunos WHERE matricula = ?";
        try (PreparedStatement pst = conecta.prepareStatement(sql)) {
            pst.setInt(1, matricula);
            int linhasAfetadas = pst.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
