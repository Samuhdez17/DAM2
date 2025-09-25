package ejerciciosProgramacionProcesos.Ej1_Ping;


public class UtilidadesSistema {
    private final String os = System.getProperty("os.name").toLowerCase();

    public boolean isUNIX() {
        return os.contains("linux") || os.contains("mac");
    }
}
