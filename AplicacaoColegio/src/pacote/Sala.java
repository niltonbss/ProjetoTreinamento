package pacote;

import java.util.ArrayList;

public class Sala {
	private int codPredio;
	private int numSala;
	private ArrayList <Horario> horarios;
	public ArrayList<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(ArrayList<Horario> horarios) {
		this.horarios = horarios;
	}
	public int getCodPredio() {
		return codPredio;
	}
	public void setCodPredio(int codPredio) {
		this.codPredio = codPredio;
	}
	public int getNumSala() {
		return numSala;
	}
	public void setNumSala(int numSala) {
		this.numSala = numSala;
	}
}
