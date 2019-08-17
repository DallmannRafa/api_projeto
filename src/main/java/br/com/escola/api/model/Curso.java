package br.com.escola.api.model;

import br.com.escola.api.model.util.Status;

public class Curso {

	private long id_curso;

	private String nome_curso, carga_horaria;

	private Status status;

	public long getId_curso() {
		return id_curso;
	}

	public void setId_curso(long id_curso) {
		this.id_curso = id_curso;
	}

	public String getNome_curso() {
		return nome_curso;
	}

	public void setNome_curso(String nome_curso) {
		this.nome_curso = nome_curso;
	}

	public String getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(String carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carga_horaria == null) ? 0 : carga_horaria.hashCode());
		result = prime * result + (int) (id_curso ^ (id_curso >>> 32));
		result = prime * result + ((nome_curso == null) ? 0 : nome_curso.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (carga_horaria == null) {
			if (other.carga_horaria != null)
				return false;
		} else if (!carga_horaria.equals(other.carga_horaria))
			return false;
		if (id_curso != other.id_curso)
			return false;
		if (nome_curso == null) {
			if (other.nome_curso != null)
				return false;
		} else if (!nome_curso.equals(other.nome_curso))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id_curso=" + id_curso + ", nome_curso=" + nome_curso + ", carga_horaria=" + carga_horaria
				+ ", status=" + status + "]";
	}

}
