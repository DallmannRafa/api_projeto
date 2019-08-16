package br.com.escola.api.model;

import br.com.escola.api.model.util.Status;

public class Aluno {

	private long id_aluno;

	private String nome_aluno;

	private String dt_nasc;

	private Status status;

	public long getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(long id_aluno) {
		this.id_aluno = id_aluno;
	}

	public String getNome_aluno() {
		return nome_aluno;
	}

	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	public String getDt_nasc() {
		return dt_nasc;
	}

	public void setDt_nasc(String dt_nasc) {
		this.dt_nasc = dt_nasc;
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
		result = prime * result + ((dt_nasc == null) ? 0 : dt_nasc.hashCode());
		result = prime * result + (int) (id_aluno ^ (id_aluno >>> 32));
		result = prime * result + ((nome_aluno == null) ? 0 : nome_aluno.hashCode());
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
		Aluno other = (Aluno) obj;
		if (dt_nasc == null) {
			if (other.dt_nasc != null)
				return false;
		} else if (!dt_nasc.equals(other.dt_nasc))
			return false;
		if (id_aluno != other.id_aluno)
			return false;
		if (nome_aluno == null) {
			if (other.nome_aluno != null)
				return false;
		} else if (!nome_aluno.equals(other.nome_aluno))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [id_aluno=" + id_aluno + ", nome_aluno=" + nome_aluno + ", dt_nasc=" + dt_nasc + ", status="
				+ status + "]";
	}

}
