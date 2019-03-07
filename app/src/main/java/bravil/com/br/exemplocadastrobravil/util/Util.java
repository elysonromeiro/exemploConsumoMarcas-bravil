package bravil.com.br.exemplocadastrobravil.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class Util {

    private static final String ZERO = "0";

    private static final List<String> MONTH_NAME =  Arrays.asList("janeiro","fevereiro","março","abril","maio","junho","julho","agosto","setembro","outubro","novembro","dezembro");

    public static String formatAddZeros(int qtCaracteres, Long value) {
        //qt: quantidade caracteres
        int numCaracValue = value.toString().length();

        StringBuilder stringBuilder = new StringBuilder();
        int numZeros = qtCaracteres - numCaracValue;
        for (int i = 0; i < numZeros; i++) {
            stringBuilder.append(ZERO);
        }
        stringBuilder.append(value);
        return stringBuilder.toString();
    }

    public static Calendar toCalendar(String data) {
        String[] datas = data.split(Pattern.quote("."));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(datas[2]),
                Integer.parseInt(datas[1]) - 1,
                Integer.parseInt(datas[0]), 23, 59, 59);

        return calendar;
    }

    public static String getMonthName(int monthIndex){
        return MONTH_NAME.get(monthIndex);
    }

    public static int getMonthIndex(String month){
        return MONTH_NAME.indexOf(month);
    }

    public static String toMD5(String str) {
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();
            messageDigest.update(str.getBytes(Charset.forName("UTF8")));
            final byte[] resultByte = messageDigest.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < resultByte.length; ++i) {
                sb.append(Integer.toHexString((resultByte[i] & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }





    public static boolean isConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public static String get64Image() {
        return "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAQAAAD9CzEMAAAAAmJLR0QA/4ePzL8AAAAJcEhZcwAAAEgAAABIAEbJaz" +
                "4AAAAJdnBBZwAAADAAAAAwAM7ujFcAAAM5SURBVFjD7ZdNaF1VEMf//zn3JS95z1vT1/KSlKq1SZuSilLQgLY0itGF" +
                "BIJrhSoUSyldln4gLuyqBcGV2yJu7E4Ekbh4CwNSQXERtWiKKH5RlNrQlzbJOTMuXtPcNPcl974k6CKzuvdwzvxmzp" +
                "wzMwfYlP+XcP21JH4Mt/G2m4lmIS1qVrSj078ZOhJqk1+GfgyhuEb77+AKfgRhjd9o0YFLcmTo4FOjbXVreausxI/nJ" +
                "nDp2hFdGFoEQAXRs9Ep8Ra1ohyAZ8Rb0USkgjRABFjdgBs2SaOY5fCDpqTtt+2sG5YYuNRYAsC3fO533GbBLAeANo+OW" +
                "u/wPS3pAAAwJWYwLjeCQ1YEJYQuvKBcviJlu2ko4Gb4hczqAxkMggJT5qfH0wDAEqFaWUwQFlZlAzREM2/RCoa0emkzyy" +
                "ZgXQBrSuKrAXg3L7YMWTGvCdWAgXLH/NzsDzTz6wtwsjfMtf/8+NUDnC1+7qeMzsJ6Anzoqfx52D1xtvJ1+DSOx6e/" +
                "DxTo4uUjsPpVTAcQAMqP1Q/tfui9rSOYbjvad9mV2uvfKAuct4TyVSEpQTYioDMaqb/06sAXlRH1FocPiid31Z8vPr" +
                "0nOmYiRZjB+h6odsFgwpWOQJoHRMCBeN/Fba/TQmAEpQvvtnc/crb005aPasN33ueDvRiY2gPh5O6vpm7lBQBW2PlJ" +
                "95APRjoAAmPQM23bdhx/5tfo78md+61/V/eJsvJ8z1Rf55WDV8cVTY5yE0ApepQw4cIaQhj0KLdXX3vyn31D1TdKL7" +
                "stCo5Vz8WXq5/190z8oTBkKzgNxP0zCceAMa10X7cxcYoQCOvTD4uvPHy68t0OuaY+LdrNAEzz10FxyACEQKEDoDQb" +
                "xeH4ncGL5Rmf5kHOZCdQBIDu7oYLHALi8Fbbi1tBl9JR5W6BllvkYDATzRNkNKqgZspxDbO1ydSmAAEgOZJolGucGr" +
                "5El/PZPZC/NFdXMf3b6HzOzu4m4oAs94CiKCNDnrzPjRglWfUUGQAMWq1XW6jVooO2PLsmAB5giUAXh5G1p1sqnkCJY" +
                "LLyJQCi8DV/Qdb2AKl5eEmYt+FPqA1/BC6VDXjGbsp/L/8C4o0x3HsFHMQAAAAldEVYdGRhdGU6Y3JlYXRlADIwMTEt" +
                "MDEtMjhUMjI6NDc6NTMtMDg6MDCkTnLJAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDExLTAxLTI4VDIyOjQ3OjUzLTA4OjA" +
                "w1RPKdQAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAAASUVORK5CYII=";

//        return "iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAKnRFWHRDcmVhdGlvbiBUaW1lAE1p\n" +
//                "IDUgTXJ6IDIwMDMgMTc6MDE6MDUgKzAxMDCMlhVqAAAAB3RJTUUH0woGFBkFx+DVZwAAAAlwSFlz\n" +
//                "AAALEQAACxEBf2RfkQAAAARnQU1BAACxjwv8YQUAAApaSURBVHja1VlLcxvHEe7dxYNvUiRiSJRI\n" +
//                "grFFJVJiJUypnJR9s6tSlVx8yCU/w38hOSSV35BrrqlUquwcYpWVkmXLyYFSbEe0LFKkLJLgA29g\n" +
//                "Aexr0t/MDrgAFiIUU49sVWMHu7OzX89099c9S/R/fhgvwRjieb3cuH59b7ZUqr7mON5rRNayEP45\n" +
//                "IYKM79OUEGLK94NxxjMWBCIZBC4FQUCeJ/js+Y7j2o7Ttl3XaXC7yu0j1/UOXLd10Gza3zQala/u\n" +
//                "3ftk+/PP/26HSg2l2BMV2NioZw8PjV+apvl2u23+1LYbiww60TN4wOB5HOEb+BUiEQR+wEMLKKCU\n" +
//                "cC308TzPZoXSrITJ4IN2u2W2Wm2ybZuq1SrV6/WKEPSFbdc/LpUO/vb++7//F4/fxjueSoGNjdar\n" +
//                "pZL5m2Yz+BU/m2IFyGB0EABiMH1niA/chLPP/ylsq2vRvlLrIOg6Y2zXdalUKtHR0RE9fLglCoXi\n" +
//                "v0ul7d/dvPnHD7hLI04Rq/fC2pr9s0ol+Y+zZ41rzaZlmaYlBweIONBx13rvxfXVAmUhvDryPDIy\n" +
//                "QrOzs7S8vGzs7e2e9Tzj3URisnh09PUDBtHqNS2zV4FaTfxhZYWmFxctmpkhHjiQs8h2LaUX5LAS\n" +
//                "p7SWjjkYyiBw7fHjx5RKpSiT+U5idHT213z5RyzjvXgTfTZlGAu1mskzQXTxokmLiyYViz4rFrCt\n" +
//                "8hS0fGqzVbpuEC5/r3kcm030mmpTp38UNGa/0Wh0fKFcLpPjOPL/o0eP0GuUf77P8iVL9YkKYJYd\n" +
//                "J6B83iI2fZ4FQQnulclYfE7KNiaKV5tfDEVU2/dFGHFIAsU4uO44PvfxJaBWS0mj0QrBAnSdnbfB\n" +
//                "k9Lmey0ptVpNKoE+yp98LM1EHN7eCwZmBwBVRCEeRPDgvgTSbrsSjOf5cpYxc/o/R55QMUPe40gT\n" +
//                "9ndC+/bkWT3rSYcFOI5GEjxHJAlYKyLt21QWrla0Y+5G1A/6NGJtXdETgQEMY1kWopAhZxaKwj8A\n" +
//                "AIIZ1yahTUQ9Y/J/JSqa+V32Diy6eXwtigcr6bk04OhzYgbi07dkx9M+GBPwxHJBnwJqEgwyTiPJ\n" +
//                "OD0V8BM7qXEr4ATaHl6CI4xwHg1IL/oUKJeLN5tNx0JUgQ2/qJWI+lK7XS3xSSvRdfQ58djY5MrS\n" +
//                "UipAvsK5EGWzc9IRsSg6nXhWgHXKAuDgg/n5ecnM5fL+AneJ9c3+uJqwMkdHwsvl0qnV1QTdvbtD\n" +
//                "u7u2jDiZzBlKp0dYIUuGWFyzrKBDXNGZi+ZPaEPwXDSd0NchILL9/X1KJpM8iWN09epVyQUPHjzg\n" +
//                "55IgMoh1ogI8sMuEZH7zDfP2uEWvv36erl0jTrIEra/v0Pb2HjNzU8bxet2WsT2dTrPiCSa9pGwD\n" +
//                "KO7jHuI62gDYbDYlSanQ6EsltJLT09O0sLAg+xWLRdra2pLtkMkRRlNDKcC2L4MW7B+pQ7WqEi5O\n" +
//                "SzitOEcrK/PcNjtMjD4HBzU6PKxKZgWrArAmI4CDGWBWR0dH5Qwru25LAXlBoNjOzo5s41mtHA4h\n" +
//                "xMCgEpMLdRMKmrD/Wg1M7HUxMhc2rIhLKAd40hnkqASrzMsLmdqVglQCKwDb1qui++AeRGUB/X4G\n" +
//                "cqUBUShmBTyeOhHrqb2MjLbKh1wG1e0DGrwGqdNliO43bEDAkIOcuDeMCu7c8v1nFmz+p4PzrIEr\n" +
//                "0McDpVLho1rNNZFVvgxKwBccxy7TAB7oU2B8fCrH1ZgwTYeUEi9OC4C/cOECR7g0UmmsQl8+1KcA\n" +
//                "h8Mlx7E8JjO24QJHmCKhrERohDwLhaIkBoGTw48uXrwoQ2kyOXGWuyVj8fZr7XnNpghQCOVyc8yG\n" +
//                "bbpz5xGTWV0WOufOZWWshxNbFoodEbK06ESRKJFFySpKZMcbAaoe3t3dlU6PMItZRz9UY4ha4ZyN\n" +
//                "0ZAFDdtaYMB8OCzzgGkms2V6803kSS4X/VvMjltUKDRJbYk0ZKqRTKbY/MYlAIBV2ylep8pCCNUx\n" +
//                "H0CjymFCLl++LBXSuxLgBd1vkLUMWAFZ8ARqaUE4ARfYKv6jnMzlFunKle/ySy15H0S2v++wqZVZ\n" +
//                "qTKDbcqYDvAABwKDQDGwNcDi0CQGxer1OlUqFXnG/x7gncmlmG2gGCJzuWQ3re5rKv63Wh4vqSIy\n" +
//                "KIT6FoEhmTSkQq+8MsXXxqSpeZ7bRWaqJm5JoJrcVEmp7kX5YcAxFJEJfp/Ng1m6Ju5XEMoYkswS\n" +
//                "CZNf7vJyewxMl5FPJjKdFTxNMPD9VoGGrciq1cKtet0xg+DEcbuU0nLaBxS27eI+N5uk2PjJCkxO\n" +
//                "zv1kYSEZzMyoHQMdTZ7nEU1HVlZWODicxZ6QM5QCjDdRLJre9LTFD7Y4rd2VhPY8eEBPFvwECiAy\n" +
//                "IUM11EuxK5fsfbbPidn5PA6BJnggk5mgt96aoE8/3eKYXJH3Fxfn5QYXAgLKTrXteLxDpxWMnuMK\n" +
//                "m+g2I3wFxQwKGKTcmHX4y+bmpiQyUmmEeukJCkgeYAnw/oMD8ADR6mpO8sDOjkO3b3/NYfWoE9fT\n" +
//                "6VQn3wcPIFQCoHZYzQXoD8HsQtAGePRHiM3lcnIMEFc+n5fK4NlwY9kbtHp9UQidBXah6JgHtrc9\n" +
//                "BhPQxIRJ77xzmQEbcn+UcfDLbFYoLwkIonfXdGjUsw+Q2KxF1XXp0iX5XyuneQAbuphxPB9jqsEw\n" +
//                "CsBxD7jW7XoeHOC6gquuNq+CLXkA9xH/IbncLJ0/P8ugDVkna7PQZ1UvKKV0JaZXAdd0BRetwrqQ\n" +
//                "B97wBU2xmP9wbi77W85vTKNntGhBg4qs0QAwp7O1qO6bHdPRjBzlgSg3RH1lkHPjmUple53Ut4G+\n" +
//                "oqYvCr333s+/LJUO/vTw4ZHgZE08+QWKuLT09o068NNGL0yE2hRo7OztrWFfCPvsdm8/K+ZZf23t\n" +
//                "9trS0tUrQTDy6tzchMRyHHG6zzoKadbWQKNfYU4SnZFC9H84cblc2F1f/8t1zo83eMiPWXaphwvi\n" +
//                "FOAH881bt/78z8nJBQ4oMz9gWMmxsVRn31/Z+ekoEA2nAI4tlb29vDg83Lxx//5f73ieDfA3WO6R\n" +
//                "YuMTV0CuAttv7bPPPvjP/ft375nmONtzeoGdNwmgySRcx4gocGyWek8/uvfTCzTaFwojeiH339jY\n" +
//                "9B4/vv/J5uaHN/L5tR0h/DXu9lEIvk4xTnySYYI8ZlmWM5n5H7/xxi9+uLr69uVsdvEqE840L48B\n" +
//                "hQzjmKA0W0edNZqNImQiVKqwWxAc8+1y+WC9VNp8UCx+VXddG0AfsnzBgg97yIPaNGB3ehjPQp8R\n" +
//                "lhmWLEsukUhx3bw0f+HC985ks0vTMzPZM2Nj02e4dp1kmTLNxDi+2CA6KUd0uMqrVur1Sq1SOWTs\n" +
//                "+VK5vM/tnWajUWDm9+wQKBx1K7T1Eg1I4J5WgWhfrAhKO3j2JMsZlkx4niKVr0DZFHVTv/5AgYQM\n" +
//                "4RD5AT7WYbehEEo1NJNG2O/bf6k/4TkzBJmOAIZzWOG9vj2nUAk/FDeikEsDNq6elQKnNc5L8yHl\n" +
//                "hR3/BZO4yStgQSMpAAAAAElFTkSuQmCC";

//        return "Qk04GwAAAAAAADYAAAAoAAAAMAAAADAAAAABABgAAAAAAAIbAAASCwAAEgsAAAAAAAAAAAAA/wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD/iygDiygDiygDiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDiygDiygDmDAC\n" +
//                "mDACjyoDiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/iygDiygDjyoDqj4BvUwAsUIBtEUAvUwApzwBiygDiygD/wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDlS4Ct0cAnjUBiygD\n" +
//                "iygDiygDjyoDqj4BoDcBiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/iygDiygDsUIBlS4CiygDiygDiygDiygDiygDiygDrUEBiygDiygD/wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDnjUBpDkBiygDkiwC\n" +
//                "/wD//wD//wD/iygDiygDmDACmDACiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/iygDiygDt0cAjyoDiygD/wD//wD//wD//wD//wD/iygDiygDoDcBiygDiygD\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDiygDiygDiygD/wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDt0cAiygDlS4C\n" +
//                "/wD//wD//wD//wD//wD/iygDiygDmDACiygDiygD/wD//wD//wD//wD//wD//wD/iygDiygDiygD\n" +
//                "iygDiygDiygDiygDiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD/iygDkSwCsUIBiygDiygD/wD//wD//wD//wD//wD/iygDiygDmDACiygD\n" +
//                "iygD/wD//wD//wD//wD//wD/iygDiygDjyoDpDkBukoAvUwAt0cAmDACiygDiygDiygD/wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDjyoDsUIBiygD\n" +
//                "iygD/wD//wD//wD//wD//wD/iygDiygDmDACiygDiygD/wD//wD//wD//wD/iygDiygDjyoDpzwB\n" +
//                "mDACiygDiygDkSwCrUEBnjUBiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD/iygDiygDtEUAiygDiygD/wD//wD//wD//wD//wD/iygDiygDmDAC\n" +
//                "iygDiygD/wD//wD//wD/iygDiygDjyoDoDcBiygDiygDiygDiygDiygDiygDpzwBkSwCiygDiygD\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDsUIB\n" +
//                "jyoDiygD/wD//wD//wD//wD//wD/iygDiygDmDACiygDiygD/wD//wD/iygDiygDjyoDoDcBiygD\n" +
//                "iygDiygD/wD//wD//wD/iygDjyoDpDkBiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD/iygDnjUBrUEBiygDiygD/wD//wD//wD//wD/iygDiygD\n" +
//                "mjMCiygDiygD/wD//wD/iygDiygDnjUBiygDiygD/wD//wD//wD//wD//wD/iygDiygDnjUBiygD\n" +
//                "iygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygD\n" +
//                "iygDtEUAqj4BiygDiygDiygD/wD/iygDiygDmjMClS4CiygD/wD//wD/iygDiygDmjMCkSwCiygD\n" +
//                "iygD/wD//wD//wD//wD//wD/iygDiygDoDcBiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDjyoDsUIBqj4BjyoDiygDiygDiygDiygD\n" +
//                "rUEBiygDiygD/wD/iygDiygDjyoDnjUBiygDiygD/wD//wD//wD//wD//wD//wD/lS0CiygDmjMC\n" +
//                "iygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD/iygDiygDiygDnjUBpzwBnjUBiygDiygDoDcBqj4BiygDiygD/wD/iygDiygDpDkBkSwCiygD\n" +
//                "/wD//wD//wD//wD//wD//wD//wD/jyoDiygDnjUBiygDiygD/wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygDiygDiygDiygDkSwCt0cA\n" +
//                "vUwAmDACiygD/wD/iygDiygDjyoDrUEBiygDiygD/wD//wD//wD//wD//wD//wD/kCoCiygDmDAC\n" +
//                "kSwCiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD/iygDiygDiygDqj4Bqj4BiygDiygD/wD/iygDiygDnjUBsUIBiygD\n" +
//                "iygD/wD//wD//wD//wD//wD/iygDiygDjyoDnjUBiygDiygD/wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/iygD\n" +
//                "jyoDiygDiygD/wD//wD/iygDiygDrUEBsUIBiygDiygDiygDiygDiygDiygDiygDiygDiygDmDAC\n" +
//                "iygDiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD/fS0MiygDiygDczIU/wD//wD/iygDiygDt0cAukoA\n" +
//                "jyoDiygDiygDiygDiygDiygDiygDlS4CnjUBiygDiygDiygD/wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "Wj4snE0tkmJQVEE0/wD/WT4tiygDiygDlS4CiygDiygDkSwCoDcBqj4BsUIBsUIBpzwBmDACiygD\n" +
//                "iygDiygD/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0uJaWnn97VEE0VEE0VEE0ii8KiygDiygD\n" +
//                "iygDiygDiygDiygDiygDiygDiygDiygDiygDiygDiygD/wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "VEE0VEE0kXRuim5oVEE0VEE0kXRupGJJiygDfS0MazQY/wD/iygDiygDiygDiygDiygDiygDiygD\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0WkU5a1RKVEE0VEE0X0o/nn97uJaWpISBWj4s\n" +
//                "Uj8y/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0\n" +
//                "WkU5sZCPuJaWq4qInn97uJaWuJaWpISBWkU5UD0x/wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD/VEE0WkU5pISBuJaWq4qIY05CdmBWhGlipISBWkU5Tz0x\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0WkY6\n" +
//                "p4mHupeXuJaWpISBZ1RIk4B4Zk9EWkU5Tz0xNSgg/wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD/VEE0Yk1Br5ORwaOjvJycupeXuJaWl3l1cllPfmRbTz0wblpZ\n" +
//                "QTMo/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0alVKxq+t\n" +
//                "yrCwxqmpwaOjvJycupeXpISBfmRbTz0wblpZkHV1Tz0x/wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD/VEE0a1dMz7u60729z7a2yrCwxqmpwaOjp4mGfmRbTz0wdF5ekHV1\n" +
//                "rY6OVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0bFlO18fG3cvL\n" +
//                "2MTE0729z7a2yrCwvKGggW5lUD0xemholXt7r5CQt5WVVEE0/wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD/VEE0bltQ4NTU5tnZ4dHR3cvL2MTE07y8vKWjgW5lUD0xgXNznYiIuJ2d\n" +
//                "vZ6eupiYVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0b11S6eLh7+fn6uDg\n" +
//                "5tnZ4dHR3cvLwKyqfmtgUD4xOCsjqpmZxKysya2txKamv56eVEE0/wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/VEE0cF5T8u/u+PT08+7u7+fn6uDg5tnZx7e0fmtgUT4y/wD/SDct0b+/1L+/\n" +
//                "z7e3yq+vxKamVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0blxR6+bl/Pr6/vz8+PT0\n" +
//                "8+7u7+fnzsK/fmtgUkAz/wD//wD/U0Az4dDQ2sfH1cDAz7e3yq+vVEE0/wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/VEE0sKGc8uvr9/Pz/Pv7/vz8+PT01czKfWpgU0Az/wD//wD//wD/VEE05tnZ\n" +
//                "4dDQ2sfH1b+/z7e3VEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0wbKv7uPj8uvr9/Pz\n" +
//                "/Pv72tTRfmtgVEE0/wD//wD//wD//wD/VEE06+Li5tnZ4NDQ2sfH1b+/VEE0/wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD/VEE0va2p6d3d7uPj8uvr08rHfmtgVEE0/wD//wD//wD//wD//wD/VEE0\n" +
//                "8urq6+Hh5tnZ4NDQ2sfHVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0uqmk49XV6d3d\n" +
//                "zL+7fmtgVEE0/wD//wD//wD//wD//wD//wD/VEE09/Pz8urq6+Hh5tnZ4NDQVEE0/wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD/VEE0lYB44M/PxbSxfmtgVEE0/wD//wD//wD//wD//wD//wD//wD/\n" +
//                "VEE0/Pv79/Pz8urq6+Hh5tnZVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0wrCs\n" +
//                "fmtgVEE0/wD//wD//wD//wD//wD//wD//wD//wD/VEE0+/r6/Pv79/Pz8urq6+HhVEE0/wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD/VEE0VEE0VEE0/wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD/VEE09vDw+/r6/Pv79/Pz5t7dVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "VEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE07+fn9vDw+/r6/Pv7q56XVEE0/wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD/VEE06t7e7+fn9vDwzsbCppiRVEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE049XV6t7eppiRppiRVEE0/wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD/VEE0y7q3dWJXVEE0VEE0/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/VEE0VEE0VEE0/wD//wD//wD/\n" +
//                "/wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD//wD/\n" +
//                "/wD//wD//wD//wD/AAA=";
    }



    public static Bitmap convertBase64StringToBitmap(String base64str) {
        byte[] imageBytes;
        if(base64str == null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBytes = Base64.decode(get64Image(), Base64.DEFAULT);
        }else if("".equals(base64str)){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBytes = Base64.decode(get64Image(), Base64.DEFAULT);
        }else{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBytes = Base64.decode(base64str, Base64.DEFAULT);

        }
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        return decodedImage;
    }

}
