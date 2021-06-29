package ClinicaVeterinaria;
import java.io.Serializable;
public abstract class Animal implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String nome;
	private   int idade;
	private   String dono;
	private   String alimentacao;
	private   String cuidados;
	protected String especie;
	
	public Animal(String nome, int idade, String dono, String alimentacao, String cuidados) {
		this.nome = nome;
		this.idade = idade;
		this.dono = dono;
		this.alimentacao = alimentacao;
		this.cuidados = cuidados;
	}
	public String toString() {
		String retorno = "";
		retorno += "Nome: "     + this.nome     + "\n";
		retorno += "Idade: "    + this.idade    + " anos\n";
		retorno += "Dono: "     + this.dono     + "\n";
		retorno += "Alimentacao: "     + this.alimentacao     + "\n";
		retorno += "Cuidados: "     + this.cuidados     + "\n";
		retorno += "Especie: "  + this.especie  + "\n";
		retorno += "Barulho: "  + soar()        + "\n";
		return retorno;
	}
	public abstract String soar();
}
