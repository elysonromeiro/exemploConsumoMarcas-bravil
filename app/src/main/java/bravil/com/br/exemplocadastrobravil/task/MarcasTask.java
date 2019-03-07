package bravil.com.br.exemplocadastrobravil.task;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import bravil.com.br.exemplocadastrobravil.entidades.Marca;

public class MarcasTask {

    private Context context;
    private TaskCallBack taskCallBack;
    private String database;

    public MarcasTask(Context context, String database, TaskCallBack taskCallBack) {
        this.context = context;
        this.database = database;
        this.taskCallBack = taskCallBack;
    }

    public void consumirMarcas() {

        Service service = new Service(context, new ServiceCallBack() {
            @Override
            public void resultService(String carga, String retorno, boolean taskSucsess) {
                if (taskSucsess) {
                    taskCallBack.taskSucsess(carga, toMarcas(retorno, carga));
                } else {
                    taskCallBack.taskError(carga, retorno);
                }
            }

            @Override
            public void canceled(String carga) {
                taskCallBack.taskCancel(carga);
            }
        });

        service.POST(Service.CargasContas.CONS_MARCA, database);

    }

    public List<Marca> toMarcas(String r, String carga) {
        System.out.println("RETORNO CONTA" + r);
        List<Marca> marcas = new ArrayList<>();
        if (!"".equals(r)) {
            String[] strSplit = r.split(Pattern.quote("\\"));
            for (String str : strSplit) {
                String[] marcaStr = str.split(Pattern.quote("|"));
                if (Service.CargasContas.CONS_MARCA.equals(carga)) {
                    Marca marca = new Marca(Integer.parseInt(marcaStr[0]), marcaStr[1]);
                    marcas.add(marca);
                }
            }
        }
        System.out.println("==>"+marcas);
        return marcas;
    }


}
