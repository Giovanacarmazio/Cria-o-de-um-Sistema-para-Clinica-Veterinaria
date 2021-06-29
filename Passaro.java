package ClinicaVeterinaria;

public class Passaro extends Animal {

	private static final long serialVersionUID = 1L;

	public String soar() {
		return "Faz Piado";
	}
	public Passaro(String nome, int idade, String dono, String alimentacao, String cuidados) {
		super(nome, idade, dono, alimentacao, cuidados);
		this.especie = "Passaro";
	}
}