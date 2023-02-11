public interface Premio {
    boolean distribuirPremio(Apostador apostador);
    boolean distribuirPremio(Bolao bolao);
    boolean distribuirPremio();
    int getResultado();
}
