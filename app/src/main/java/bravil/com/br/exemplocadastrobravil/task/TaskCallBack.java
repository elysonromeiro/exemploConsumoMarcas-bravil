package bravil.com.br.exemplocadastrobravil.task;

public abstract class TaskCallBack implements TaskCallBackI {

    @Override
    public void taskSucsess(String carga) {

    }

    @Override
    public void taskSucsess(String carga, Object object) {

    }

    @Override
    public void taskSucsess(String carga, Object retorno, String... param) {

    }

    @Override
    public void taskError(String carga, String erro) {

    }

    @Override
    public void taskError(String carga, String erro, Object retorno, String... param) {

    }

    @Override
    public void taskCancel(String carga) {

    }
}

interface TaskCallBackI {
    //usado para informar a activity que a task terminou
    void taskSucsess(String carga);

    void taskSucsess(String carga, Object object);

    void taskSucsess(String carga, Object retorno, String... param);

    void taskError(String carga, String erro);

    void taskError(String carga, String erro, Object retorno, String... param);

    void taskCancel(String carga);
}
