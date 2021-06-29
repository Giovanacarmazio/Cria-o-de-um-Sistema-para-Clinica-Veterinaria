package ClinicaVeterinaria;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClinicaVeterinaria {
	private ArrayList<Animal> Animal;

	public ClinicaVeterinaria() {
		this.Animal = new ArrayList<Animal>();
	}

	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Cavalo leCavalo (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Idade", "Dono", "Alimentacao" , "Cuidados proprios"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Cavalo Cavalo = new Cavalo (valores[0],idade,valores[2],valores[3],valores[4]);
		return Cavalo;
	}

	public Passaro lePassaro (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Idade", "Dono", "Alimentacao" , "Cuidados proprios"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Passaro Passaro = new Passaro (valores[0],idade,valores[2],valores[3],valores[4]);
		return Passaro;
	}
		
	public Cachorro leCachorro (){

		String [] valores = new String [5];
		String [] nomeVal = {"Nome", "Idade", "Dono", "Alimentacao" , "Cuidados proprios"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Cachorro Cachorro = new Cachorro (valores[0],idade,valores[2],valores[3],valores[4]);
		return Cachorro;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}

	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um numero valido.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaAnimal (ArrayList<Animal> Animal){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\ClinicaVeterinaria.dados"));
			for (int i=0; i < Animal.size(); i++)
				outputStream.writeObject(Animal.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossivel criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Animal> recuperaAnimal (){
		ArrayList<Animal> AnimalTemp = new ArrayList<Animal>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\ClinicaVeterinaria.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Animal) {
					AnimalTemp.add((Animal) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com animais nao existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return AnimalTemp;
		}
	}

	public void menuClinicaVeterinaria (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Clinica Veterinaria\n" +
					"Opcoes:\n" + 
					"1. Entrar Animal\n" +
					"2. Exibir Animal\n" +
					"3. Limpar Animais\n" +
					"4. Gravar Animal\n" +
					"5. Recuperar Animais\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Animais\n" +
						"Opcoes:\n" + 
						"1. Cachorro\n" +
						"2. Passaro\n" +
						"3. Cavalo\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: Animal.add((Animal)leCachorro());
				break;
				case 2: Animal.add((Animal)lePassaro());
				break;
				case 3: Animal.add((Animal)leCavalo());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Animal para entrada Nao escolhido!");
				}
				break;
			case 2: // Exibir dados
				if (Animal.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com os animais primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < Animal.size(); i++)	{
					dados += Animal.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (Animal.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com os animais primeiramente");
					break;
				}
				Animal.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (Animal.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com os animais primeiramente");
					break;
				}
				salvaAnimal(Animal);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				Animal = recuperaAnimal();
				if (Animal.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo Clinica Veterinaria");
				break;
			}
		} while (opc1 != 9);
	}

	public static void main (String [] args){

		ClinicaVeterinaria pet = new ClinicaVeterinaria ();
		pet.menuClinicaVeterinaria();

	}

}
