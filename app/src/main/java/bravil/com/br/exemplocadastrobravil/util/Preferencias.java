package bravil.com.br.exemplocadastrobravil.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {
    private Context contexto;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NOME_ARQUIVO = "exemploCadastro.preferencias"; //Nome do arquivo onde serão salvas as prefer
    private final int MODE = 0;

    public static final String DATA_BASE = "database";
    public static final String LOJA = "loja_id";

    public static final String LOJA_SELECIONADA = "loja_selecionada";

    public static final String FUNCIONARIO = "FUNCIONARIO_ID";
    public static final String WEB_SERVICE = "webservice_url";
    public static final String FIRSTRUN = "FIRSTRUN";
    public static final String ULTIMA_SINC = "ULTIMA_SINC";
    public static final String ULTIMA_SINC_HORA = "ULTIMA_SINC_HORA";
    public static final String PROXIMA_SINC = "PROXIMA_SINC";

    public static final String ULT_SINC_CLIENTES_DATA = "ULT_CLIENTES_D";
    public static final String ULT_SINC_PRODUTOS_DATA = "ULT_PRODUTOS_D";
    public static final String ULT_SINC_FINALIZADORA_DATA = "ULT_FINALIZADORA_D";
    public static final String ULT_SINC_IMAGENS_DATA = "ULT_IMAGENS_D";

    public static final String ULT_SINC_CLIENTES_HORA = "ULT_CLIENTES_H";
    public static final String ULT_SINC_PRODUTOS_HORA = "ULT_PRODUTOS_H";
    public static final String ULT_SINC_FINALIZADORA_HORA = "ULT_FINALIZADORA_H";
    public static final String ULT_SINC_IMAGENS_HORA = "ULT_IMAGENS_H";

    public Preferencias(Context contexto) {
        this.contexto = contexto;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit();
    }

    public void setDefaultConfg() {

        boolean isFirstRun = preferences.getBoolean(FIRSTRUN, true);
        if (isFirstRun) {
            String WEBSERVICE = NetworkUtil.URL;
            salvarWebServiceUrl(WEBSERVICE);

        }
    }

    public boolean isFirstRun(){
        return preferences.getBoolean(FIRSTRUN,true);
    }

    public void setFirstRunValue(boolean value){
        editor.putBoolean(FIRSTRUN,value);
        editor.commit();
    }

    public void salvarDataBase(String path){
        editor.putString(DATA_BASE,path);
        editor.commit();
    }



    public void salvarWebServiceUrl(String url){
        editor.putString(WEB_SERVICE,url);
        editor.commit();
    }



    public Long getLojaSelecionada(){
        return preferences.getLong(LOJA_SELECIONADA,1L);
    }

    public void salvarClientesDataSinc(String data, String hora){
        editor.putString(ULT_SINC_CLIENTES_DATA,data);
        editor.putString(ULT_SINC_CLIENTES_HORA,hora);
        editor.commit();
    }

    public void salvarProdutosDataSinc(String data, String hora){
        editor.putString(ULT_SINC_PRODUTOS_DATA,data);
        editor.putString(ULT_SINC_PRODUTOS_HORA,hora);
        editor.commit();
    }


    public String getDATA_BASE() {
        return preferences.getString(DATA_BASE,null);
    }

    public String getID_LOJA() {
        return preferences.getString(LOJA,null);
    }

    public String getID_FUNCIONARIO(){
        return preferences.getString(FUNCIONARIO,null);
    }

    public String getWEBSERVIDCE_URL() {
        return preferences.getString(WEB_SERVICE,null);
    }

    public String getUltimaSincData(){
        return preferences.getString(ULTIMA_SINC,null);
    }

    public String getUltimaSincHora(){
        return preferences.getString(ULTIMA_SINC_HORA,null);
    }

    public void clear() {
        editor.clear();
        editor.commit();
    }

    public String getProximaSinc() {
        return preferences.getString(PROXIMA_SINC,null);
    }
}
