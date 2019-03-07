package bravil.com.br.exemplocadastrobravil.task;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.reflect.Field;
import java.util.ArrayList;

import bravil.com.br.exemplocadastrobravil.util.NetworkUtil;

public class Service {

    public static class Cargas {
        public static final String CLIENTE = "VENDA_CLIENTE";
        public static final String CONTAS_RECEBER = "contareceber";
        public static final String FORMA_PAGAMENTO = "FORMA_PAG";
        public static final String PRODUTO = "VENDA_PRODUTO";
        public static final String SECAO = "SECAO";
        public static final String GRUPO = "GRUPO";
        public static final String SUBGRUPO = "SUBGRUPO";
        public static final String FINALIZADORA = "FINALIZADORA";
        public static final String PLANOS_PAGAMENTO = "PLANOPAG";
        public static final String PRIMEIRA_CARGA = "MOBILE_VENDAS_INSTALL";

        public static void getFieldsValues() {
            ArrayList<String> values = new ArrayList<>();
            for (Field f : Cargas.class.getDeclaredFields()) {
                if (f.getType() == String.class) {
                    try {
                        String a = (String) f.get(null);
                        values.add(a);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class CargasIndividuais {
        public static final String CLIENTE = "CLIENTE_INDIVIDUAL";
        public static final String FORMA_PAGAMENTO = "CLIENTE_FORMA_PAG";
        public static final String PRODUTO = "PRODUTO_INDIVIDUAL";
        public static final String SECAO = "SECAO_INDIVIDUAL";
        public static final String GRUPO_POR_SECAO = "GRUPO_SECAO";
        public static final String SUBGRUPO_POR_GRUPO = "SUBGRUPO_GRUPO_SECAO";
    }

    public static class CargaImagens {
        public static final String PRODUTO = "PRODUTO_IMAGEM";
        public static final String SECAO = "SECAO_IMAGEM";
        public static final String GRUPO = "GRUPO_SECAO_IMAGEM";
        public static final String SUBGRUPO = "SUBGRUPO_GRUPO_SECAO_IMAGEM";
        public static final String FUNCIONARIO = "FUNCIONARIO_IMAGEM";
        public static final String EMPRESA = "EMPRESA_IMAGEM";
    }

    public static class CargasVenda {
        public static final String VENDA_CLIENTE = "CONS_VEN_CLI_MAI";
        public static final String VENDA_PRODUTO = "CONS_VEN_PRO_MAIS_VEN";
        public static final String VENDA_VENDEDORES = "CONS_VEN_VEN";
        public static final String VENDA_HORA = "CONS_VEN_HORA";
        public static final String VENDA_MARCA = "CONS_VEN_MARCA";
        public static final String VENDA_SECAO = "CONS_VEN_SECAO";
    }

    public static class CargasCaixa {
        public static final String CONTA_CORRENTE_SALDOS = "CONS_CTC_SAL";
        public static final String CAIXA_MOV_CANCELAMENTO = "CONS_CXA_MOV_CAN";
        public static final String CAIXA_MOV_ACRES_DESC = "CONS_CXA_MOV_ACR_DCN";
        public static final String CAIXA_MOV_LIQUIDO = "CONS_CXA_MOV_LIQ";
        public static final String CAIXA_MOV_FINALIZADORAS = "CONS_CXA_MOV_FZD";
        public static final String CAIXA_MOV_PLANOS_PAGAMENTO = "CONS_CXA_MOV_PLA";
        public static final String CAIXA_MOV_RECEBIMENTOS = "CONS_CXA_MOV_REC";
        public static final String CAIXA_MOV_REFORCO = "CONS_CXA_MOV_REF";
        public static final String CAIXA_MOV_ENTRADA = "CONS_CXA_MOV_POS";
        public static final String CAIXA_MOV_PAGAMENTO = "CONS_CXA_MOV_PAG";
        public static final String CAIXA_MOV_SANGRIA = "CONS_CXA_MOV_SAN";
        public static final String CAIXA_MOV_SAIDA = "CONS_CXA_MOV_NEG";
        public static final String CAIXA_MOV_FINANCEIRAS = "CONS_CXA_MOV_FIN";
        public static final String CAIXA_MOV_TOTAL = "CONS_CXA_MOV_BRT";
        public static final String CAIXA_MOV_ITENS_CANCELADOS = "CONS_CXA_MOV_CAN_ITE";
    }

    public static class CargasCompra{
        public static final String COMPRAS_FORNECEDOR = "CONS_COMPRAS_FORNECEDOR";
        public static final String COMPRAS_MARCAS = "CONS_COMPRAS_MARCAS";
        public static final String COMPRAS_PRODUTOS = "CONS_COMPRAS_PRODUTOS";
        public static final String COMPRAS_SECAO = "CONS_COMPRAS_SECAO";
    }

    public static class CargasContas{
        public static final String CONTAS_A_RECEBER = "CONS_CONTARECEBER_PERIODO";
        public static final String CONTAS_A_RECEBER_DETALHES = "CONS_CONTARECEBER_PERIODO_DET";//detalhamento

        public static final String CONTAS_A_PAGAR = "CONS_CONTAPAGAR_PERIODO";
        public static final String CONTAS_A_PAGAR_DETALHES = "CONS_CONTAPAGAR_PERIODO_DET";

        public static final String CONTAS_A_RECEBER_LIQ = "CONS_CONTASRECEBERLIQ";
        public static final String CONTAS_A_RECEBER_LIQ_DETALHES = "CONS_CONTASRECEBERLIQ_DET";//detalhamento

        public static final String CONTAS_A_PAGAR_LIQ = "CONS_CONTASPAGARLIQ";
        public static final String CONTAS_A_PAGAR_LIQ_DETALHES = "CONS_CONTASPAGARLIQ_DET";

        public static final String CONS_MARCA = "CONS_MARCAS";
    }

    private Context context;
    private ServiceCallBack serviceCallBack;
    private WsexecargamobileTask task;
    private String carga;
    private boolean executor = false;

    public Service(Context context) {
        this.context = context;
        serviceCallBack = (ServiceCallBack) context;
    }

    public Service(Context context, ServiceCallBack sv) {
        this.context = context;
        serviceCallBack = sv;
    }

    public Service(Context context, boolean executor, ServiceCallBack sv) {
        this.context = context;
        serviceCallBack = sv;
        this.executor = executor;
    }

    public void setServiceCallBack(ServiceCallBack serviceCallBack) {
        this.serviceCallBack = serviceCallBack;
    }

    public void POST(String carga, String database, String... parametros) {
        this.carga = carga;
        try {
            if (parametros == null) {
                this.task = new WsexecargamobileTask();
                this.task.execute(carga, "", database);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(carga);
                for (String s : parametros) {
                    stringBuilder.append("|");
                    stringBuilder.append(s);
                }
                this.task = new WsexecargamobileTask();
                if(executor){
                    this.task.execute(carga, stringBuilder.toString(), database);
                }else{
                    this.task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,carga, stringBuilder.toString(), database);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CANCEL() {
        if (this.task != null) {
            if (task.isRunning()) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>SERVICE >" + task.getCarga() + "< CANCEL");
                this.task.cancel(true);
            } else {
                serviceCallBack.canceled(carga);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>SERVICE >" + task.getCarga() + "< JA CANCELADO");
            }
        }
    }

    private class WsexecargamobileTask extends AsyncTask<String, Void, String> {

        private String carga;
        private boolean running = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            running = true;
        }

        @Override
        protected String doInBackground(String... strings) {
            if (!isCancelled()) {
                try {
                    carga = strings[0];
                    System.out.println("PARAMETROS:" + strings[1]);
                    System.out.println("aquiiiiii");
                    return NetworkUtil.WsexecargamobileTask(context, strings[1], strings[2]);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                System.out.println("isCancelled");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            running = false;
            if (!isCancelled()) {
                if (s == null) {
                    serviceCallBack.resultService(carga, "Comunicacão", false);
                } else if ("ERROR|002 - Registro nao encontrado".equals(s)) {
                    serviceCallBack.resultService(carga, "", true);
                } else if (s.contains("ERROR")) {
                    serviceCallBack.resultService(carga, s, false);
                } else {
                    serviceCallBack.resultService(carga, s, true);
                }
            }
        }

        @Override
        protected void onCancelled() {
//            super.onCancelled();
            running = false;
            System.out.println("ASYNC TASK ON CANCELLED");
            serviceCallBack.canceled(carga);
        }

        public boolean isRunning() {
            return this.running;
        }

        public String getCarga() {
            return this.carga;
        }

    }
}
