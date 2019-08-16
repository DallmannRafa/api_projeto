package br.com.escola.api.controller;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.escola.api.dao.TurmaDAO;
import br.com.escola.api.model.Turma;
import br.com.escola.api.model.util.Status;
@Path("turmas")
public class TurmaController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Turma> listTurmas() {
        try {
            TurmaDAO turmaDAO = new TurmaDAO();
            return turmaDAO.listar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Turma getTurma(@PathParam("id") long id) {
        Turma c1 = new Turma();
        c1.setId_turma(id);
        c1.setData_inicio_turma("data_inicio_turma" + id);
        c1.setData_final_turma("data_final_turma" + id);
        c1.setStatus(Status.NOVO);
        return c1;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Turma turma) {
        try {
            turma.setStatus(Status.NOVO);
            TurmaDAO turmaDAO = new TurmaDAO();
            turmaDAO.inserir(turma);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Turma turma) {
        System.out.println(turma.toString());
        return Response.status(Response.Status.OK).build();
    }
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        System.out.println("Deletando ID: " + id);
        return Response.status(Response.Status.GONE).build();
    }
}



