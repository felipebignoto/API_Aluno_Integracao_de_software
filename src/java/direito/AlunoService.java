package direito;

import com.google.gson.Gson;
import dao.AlunoDao;
import dominio.Aluno;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoService {

    private final AlunoDao alunoDao = new AlunoDao();
    private final Gson gson = new Gson();

    @GET
    public String listarAlunos() {
        List<Aluno> alunos = alunoDao.listarAlunos();
        return gson.toJson(alunos);
    }

    @GET
    @Path("/{matricula}")
    public Response buscarAluno(@PathParam("matricula") int matricula) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setMatricula(matricula);
        Aluno resultado = alunoDao.buscar(aluno);
        if (resultado != null) {
            return Response.ok(gson.toJson(resultado)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"erro\":\"Aluno não encontrado\"}")
                .build();
        }
    }

    @POST
    public Response cadastrarAluno(String json) {
        Aluno aluno = gson.fromJson(json, Aluno.class);
        boolean sucesso = alunoDao.cadastrarAluno(aluno);
        if (sucesso) {
            return Response.status(Response.Status.CREATED)
                .entity("{\"mensagem\":\"Aluno cadastrado com sucesso\"}")
                .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao cadastrar aluno\"}")
                .build();
        }
    }

    @PUT
    @Path("/{matricula}")
    public Response atualizarAluno(@PathParam("matricula") int matricula, String json) throws SQLException {
        Aluno aluno = gson.fromJson(json, Aluno.class);
        aluno.setMatricula(matricula);
        boolean atualizado = alunoDao.atualizar(aluno);
        if (atualizado) {
            return Response.ok("{\"mensagem\":\"Aluno atualizado com sucesso\"}").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"erro\":\"Aluno não encontrado para atualizar\"}")
                .build();
        }
    }

    @DELETE
    @Path("/{matricula}")
    public Response deletarAluno(@PathParam("matricula") int matricula) throws SQLException {
        boolean deletado = alunoDao.deletar(matricula);
        if (deletado) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"erro\":\"Aluno não encontrado para deletar\"}")
                .build();
        }
    }
}
