package ClinicaVeterinaria;

public class Cachorro extends Animal {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz latidos";
	}
	public Cachorro(String nome, int idade, String dono, String alimentacao, String cuidados) {
		super(nome, idade, dono, alimentacao, cuidados);
		this.especie = "Cachorro";
	}
}
