package bravil.com.br.exemplocadastrobravil.task;

public interface ServiceCallBack {
    //resposnsavel por entregar os dados que o service coletou
    void resultService(String carga, String retorno, boolean taskSucsess);
    void canceled(String carga);
}
