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
import br.com.escola.api.dao.ProfessorDAO;
import br.com.escola.api.model.Professor;
import br.com.escola.api.model.util.Status;

@Path("professores")
public class ProfessorController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Professor> listProfessores() {
        try {
            ProfessorDAO professorDAO = new ProfessorDAO();
            return professorDAO.listar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Professor getProfessor(@PathParam("id") long id) {
        Professor c1 = new Professor();
        c1.setId_professor(id);
        c1.setNome_professor("Nome_professor" + id);
        c1.setEmail_professor("E-mail_professor" + id);
        c1.setStatus(Status.NOVO);
        return c1;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Professor professor) {
        try {
            professor.setStatus(Status.NOVO);
            ProfessorDAO professorDAO = new ProfessorDAO();
            professorDAO.inserir(professor);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Professor professor) {
        System.out.println(professor.toString());
        return Response.status(Response.Status.OK).build();
    }
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        System.out.println("Deletando ID: " + id);
        return Response.status(Response.Status.GONE).build();
    }
}








