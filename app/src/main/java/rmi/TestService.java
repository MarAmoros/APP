package rmi;


import java.util.ArrayList;

public interface TestService {
    public String getResponse(String data);
    public boolean getName(String nickname);
    public boolean isStarted();
    public ArrayList<String> GetAnswers(int n);
    public int getTime();
    public boolean getSeguir();
    public boolean setSeguir (boolean s);
    public void RespuestaJugador(String s, String respuesta, String resp);

}