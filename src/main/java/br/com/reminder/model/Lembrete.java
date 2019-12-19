package br.com.reminder.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tab_lembretes")
public class Lembrete {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CODIGO_LEMBRETE")
	@SequenceGenerator(name = "CODIGO_LEMBRETE", sequenceName = "SEQ_COD_LEMBRETE", allocationSize = 1)
	private Long codigo;
	
	@Column
	@NotEmpty
	@Size(min = 5)
	private String conteudo;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Prioridade prioridade;
	
	@Column
	private boolean arquivado;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime data;
	
	public Lembrete() {
		super();
	}

	public Lembrete(Long codigo, @NotEmpty @Size(min = 5) String conteudo, Prioridade prioridade, boolean arquivado,
			LocalDateTime data) {
		super();
		this.codigo = codigo;
		this.conteudo = conteudo;
		this.prioridade = prioridade;
		this.arquivado = arquivado;
		this.data = data;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public boolean isArquivado() {
		return arquivado;
	}

	public void setArquivado(boolean arquivado) {
		this.arquivado = arquivado;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Lembrete other = (Lembrete) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}