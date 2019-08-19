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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import br.com.escola.api.dao.AlunoDAO;
import br.com.escola.api.model.Aluno;
import br.com.escola.api.model.util.Status;

@Path("alunos")
public class AlunoController {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Aluno> listAlunos() {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			return alunoDAO.listar();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/")
	public Aluno getAluno(@PathParam("id") long id) {
		
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			return alunoDAO.selecionar(id);
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Aluno aluno) {
		try {
			aluno.setStatus(Status.NOVO);
			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.inserir(aluno);
			return Response.status(Response.Status.CREATED).build();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Aluno aluno) {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.alterar(aluno);
			return Response.status(Response.Status.ACCEPTED).build();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

	@DELETE
	@Path("{id}/")
	public Response delete(@PathParam("id") long id) {
		try {
			AlunoDAO alunoDAO = new AlunoDAO();
			alunoDAO.excluir(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (SQLException | ClassNotFoundException ex) {
			Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
}
