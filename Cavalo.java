package ClinicaVeterinaria;

public class Cavalo extends Animal {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz barulho cavalo";
	}
	public Cavalo(String nome, int idade, String dono, String alimentacao, String cuidados) {
		super(nome, idade, dono, alimentacao, cuidados);
		this.especie = "Cavalo";
	}
}
