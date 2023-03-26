package entidades;

public class Temperatura {
	private String nombre;
    private String sigla;

    public Temperatura(String nombre, String sigla) {
        this.nombre = nombre;
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return this.nombre + " - (" + this.sigla + ")";
    }
}
