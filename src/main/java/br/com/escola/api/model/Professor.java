package br.com.escola.api.model;

import br.com.escola.api.model.util.Status;

public class Professor {

	private long id_professor;

	private String nome_professor, email_professor;

	private Status status;

	public long getId_professor() {
		return id_professor;
	}

	public void setId_professor(long id_professor) {
		this.id_professor = id_professor;
	}

	public String getNome_professor() {
		return nome_professor;
	}

	public void setNome_professor(String nome_professor) {
		this.nome_professor = nome_professor;
	}

	public String getEmail_professor() {
		return email_professor;
	}

	public void setEmail_professor(String email_professor) {
		this.email_professor = email_professor;
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
		result = prime * result + ((email_professor == null) ? 0 : email_professor.hashCode());
		result = prime * result + (int) (id_professor ^ (id_professor >>> 32));
		result = prime * result + ((nome_professor == null) ? 0 : nome_professor.hashCode());
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
		Professor other = (Professor) obj;
		if (email_professor == null) {
			if (other.email_professor != null)
				return false;
		} else if (!email_professor.equals(other.email_professor))
			return false;
		if (id_professor != other.id_professor)
			return false;
		if (nome_professor == null) {
			if (other.nome_professor != null)
				return false;
		} else if (!nome_professor.equals(other.nome_professor))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Professor [id_professor=" + id_professor + ", nome_professor=" + nome_professor + ", email_professor="
				+ email_professor + ", status=" + status + "]";
	}

}
