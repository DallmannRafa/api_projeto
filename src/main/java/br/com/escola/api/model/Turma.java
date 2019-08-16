package br.com.escola.api.model;

import br.com.escola.api.model.util.Status;

public class Turma {

	private long id_turma;

	private String data_inicio_turma, data_final_turma;

	private Status status;

	public long getId_turma() {
		return id_turma;
	}

	public void setId_turma(long id_turma) {
		this.id_turma = id_turma;
	}


	public String getData_inicio_turma() {
		return data_inicio_turma;
	}

	public void setData_inicio_turma(String data_inicio_turma) {
		this.data_inicio_turma = data_inicio_turma;
	}

	public String getData_final_turma() {
		return data_final_turma;
	}

	public void setData_final_turma(String data_final_turma) {
		this.data_final_turma = data_final_turma;
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
		result = prime * result + ((data_final_turma == null) ? 0 : data_final_turma.hashCode());
		result = prime * result + ((data_inicio_turma == null) ? 0 : data_inicio_turma.hashCode());
		result = prime * result + (int) (id_turma ^ (id_turma >>> 32));
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
		Turma other = (Turma) obj;
		if (data_final_turma == null) {
			if (other.data_final_turma != null)
				return false;
		} else if (!data_final_turma.equals(other.data_final_turma))
			return false;
		if (data_inicio_turma == null) {
			if (other.data_inicio_turma != null)
				return false;
		} else if (!data_inicio_turma.equals(other.data_inicio_turma))
			return false;
		if (id_turma != other.id_turma)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Turma [id_turma=" + id_turma + ", data_inicio_turma=" + data_inicio_turma
				+ ", data_final_turma=" + data_final_turma + ", status=" + status + "]";
	}


}
