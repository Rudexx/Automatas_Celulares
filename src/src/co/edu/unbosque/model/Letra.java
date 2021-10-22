package src.co.edu.unbosque.model;

public class Letra {
	
	private String valor;
	private String simbolo;
	
	public Letra(String valor, String simbolo) {
		this.valor = valor;
		this.simbolo = simbolo;
		if(this.valor.length() < 5) {
			int iteraciones = 5-valor.length();
			for (int i = 0; i < iteraciones; i++) {
				this.valor = "0" + this.valor;
			}
		}
	}



	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}



	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}



	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}



	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}




	

}
