package bravil.com.br.exemplocadastrobravil.util;

import android.content.Context;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.regex.Pattern;

public class NetworkUtil {

    static final String URL_ANALITICS = "http://gsweb01.ddns.net:9090/webrun/webservices/S38Services.jws?wsdl";
    static final String NAME_SPACE_ANALYTICS = "http://gsweb01.ddns.net:9090/webrun/webservices/S38Services.jws";

    //Web Service carga
    static final String URL = "http://131.72.54.46:8080/webrunstudio/webservices/WBRServices.jws?wsdl";
    static final String NAME_SPACE = "http://131.72.54.46:8080/webrunstudio/webservices/WBRServices.jws";

    public static String WsexecargamobileTask(Context context, String parametros, String database) throws IOException, XmlPullParserException {
        Preferencias preferencias = new Preferencias(context);
        String method_name = "Wsexecargamobile";

        String url = URL;
        String name_space = url.split(Pattern.quote("?"))[0];
        String db = String.format("131.72.54.43:%s", database);
        System.out.println("URL_SERVICE " + url);

        SoapObject retorno = null;
        //"tabela|'data hora'|loja|idInicial"
        System.out.println(String.format("PARAMETROS: %s",parametros));
        System.out.println("DATABASE: " + db);
        Object p = (Object) parametros;

        SoapObject request = new SoapObject(NAME_SPACE, method_name);
        request.addProperty("Tipo", "1");
        request.addProperty("Parametros", p);
        request.addProperty("Database", db);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE = new HttpTransportSE(url,(2*60*1000));
        String strRetorno;
        transportSE.call("", envelope);
        if (envelope.bodyIn instanceof SoapFault) {
            strRetorno = "ERROR|" + ((SoapFault) envelope.bodyIn).faultstring;
        } else {
            retorno = (SoapObject) envelope.bodyIn;
            strRetorno = retorno.getPropertySafelyAsString("WsexecargamobileReturn");
        }
//        System.out.println("RETORNO: " + strRetorno);
        return strRetorno;
    }

    public static String sendScript(String script, String codigoExecucao, Context context) throws IOException, XmlPullParserException {
        //tabela secao,grupo,subgrupo,produto

        Preferencias preferencias = new Preferencias(context);
        String url = "http://131.72.54.43:8080/webrunstudio/webservices/WBRServices.jws?wsdl";
        String name_space = "http://131.72.54.43:8080/webrunstudio/webservices/WBRServices.jws";
        String database = "131.72.54.43:demo";
        //String loja = preferencias.getID_LOJA();
        String method_name = "Wsexescript";

        SoapObject retorno = null;

        String parametros = String.format("0001;%s;%s", codigoExecucao, script);

        System.out.println("PARAMETROS: " + parametros);

        SoapObject request = new SoapObject(name_space, method_name);
        request.addProperty("Tipo", "1");
        request.addProperty("Parametros", parametros);
        request.addProperty("Database", database);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        HttpTransportSE transportSE = new HttpTransportSE(url, 10000);
        String strRetorno;
        transportSE.call("", envelope);
        if (envelope.bodyIn instanceof SoapFault) {
            strRetorno = "ERROR|" + ((SoapFault) envelope.bodyIn).faultstring;
        } else {
            retorno = (SoapObject) envelope.bodyIn;
            strRetorno = retorno.getPropertySafelyAsString("WsexescriptReturn");
        }


        System.out.println("PARAMETROS: " + parametros);
        SoapObject request2 = new SoapObject(name_space, method_name);
        request.addProperty("Tipo", "1");
        request.addProperty("Parametros", parametros);
        request.addProperty("Database", database);
        transportSE.call("", envelope);


        return strRetorno;
    }
}
