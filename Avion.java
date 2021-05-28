public class Avion extends Vehiculos implements BaseVehiculos{

    private final int altitudMaxima;

    public Avion(String referencia, int velocidadMaxima, Colores color, int altitudMaxima) {
        super(referencia, velocidadMaxima, color);
        this.altitudMaxima = altitudMaxima;
    }

    @Override
    public String getReferencia() {
        return this.referencia;
    }

    public int getVelocidadMaxima() {
        return this.velocidadMaxima;
    }
    public Colores getColor() {
        return this.color;
    }

   @Override
    public String toString() {
        return super.toString() +
                "\nAltitud maxima: " + this.altitudMaxima + " metros";
    }
}