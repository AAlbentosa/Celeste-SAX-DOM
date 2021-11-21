package model;

public class Jugador {
		private String name;
		private int puntuation;
		private int screenNumber;
		private boolean completed;
		
		public String getName() {
				return name;
		}
		
		public void setName(String nombre) {
				this.name = nombre;
		}
		
		public int getPuntuation() {
				return puntuation;
		}
		
		public void setPuntuation(int puntuation) {
				this.puntuation = puntuation;
		}
		
		public int getScreenNumber() {
				return screenNumber;
		}
		
		public void setScreenNumber(int numeroPantalla) {
				this.screenNumber = numeroPantalla;
		}
		
		public boolean isCompleted() {
				return completed;
		}
		
		public void setCompleted(boolean completed) {
				this.completed = completed;
		}

		@Override
		public String toString() {
			return "Jugador [nombre=" + name + ", puntuacion=" + puntuation + ", numeroPantalla=" + screenNumber
					+ ", completa=" + completed + "]";
		}
		
		
		
		
}
