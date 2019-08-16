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
import br.com.escola.api.dao.CursoDAO;
import br.com.escola.api.model.Curso;
import br.com.escola.api.model.util.Status;

@Path("cursos")
public class CursoController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Curso> listChamados() {
        try {
            CursoDAO cursoDAO = new CursoDAO();
            return cursoDAO.listar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Curso getCurso(@PathParam("id") long id) {
        Curso c1 = new Curso();
        c1.setId_curso(id);
        c1.setNome_curso("nome_curso" + id);
        c1.setCarga_horaria("carga_horaria" + id);
        c1.setStatus(Status.NOVO);
        return c1;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response create(Curso curso) {
        try {
            curso.setStatus(Status.NOVO);
            CursoDAO cursoDAO = new CursoDAO();
            cursoDAO.inserir(curso);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response update(Curso curso) {
        System.out.println(curso.toString());
        return Response.status(Response.Status.OK).build();
    }
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        System.out.println("Deletando ID: " + id);
        return Response.status(Response.Status.OK).build();
    }
}