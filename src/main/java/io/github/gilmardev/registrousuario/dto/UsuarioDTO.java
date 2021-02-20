package io.github.gilmardev.registrousuario.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UsuarioDTO {
	
	@Id
	@GeneratedValue
	@Column(name = "usuario_id")
	private Long id;
	

	@NotEmpty
	@Length(max =50)
	@Column(name = "nome")
	private String nome;
	
	
	@NotEmpty
	@Length(max = 150)
	@Column(name = "endereco")
	private String endereco;
	
	
	@Email
	@NotEmpty
	@Length(max = 80)
	@Column(name = "email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

}
