package bravil.com.br.exemplocadastrobravil.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Random;

import bravil.com.br.exemplocadastrobravil.R;
import bravil.com.br.exemplocadastrobravil.util.NetworkUtil;
import bravil.com.br.exemplocadastrobravil.util.Util;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link CadastroMarcaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CadastroMarcaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastroMarcaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CadastroMarcaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastroMarcaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastroMarcaFragment newInstance(String param1, String param2) {
        CadastroMarcaFragment fragment = new CadastroMarcaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastro_marca, container, false);
        final TextView descricao = (TextView) view.findViewById(R.id.descricao_cadastro_marca);
        Button botaoCadastro = (Button) view.findViewById(R.id.botao_cadastro_marca);
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String script = String.format("/*iniciomobile*/INSERT INTO MARCA(MAR_DESCRICAO) VALUES('%s')/*fim*/", descricao.getText());
                int codExc = new Random().nextInt(5000);
                String codExec = String.format("%s", codExc);
                if (Util.isConnected(getContext())) {
                    SendScriptAsync sendTask = new SendScriptAsync();
                    sendTask.execute(script, codExec);
                    descricao.setText("");
                    Toast.makeText(getContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class SendScriptAsync extends AsyncTask<String, Void, String> {
        private static final int TIME_INTERVAL = 60000;
        Long firstSync = 0L;
        int time = 10;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = ProgressDialog.show(FecharVendaActivity.this,
//                    "Sincronizando",
//                    "Aguarde um momento");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String retorno = NetworkUtil.sendScript(strings[0], strings[1], getContext());
                return retorno;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("CHAMANDO SERVICE!!");
            System.out.println(s);
            System.out.println("----------------------------------------------------------------");

            if (s == null) {
                String errorMsg = "Erro comunicação.\n Verifique a conexão com a internet!";
//                progressDialog.dismiss();
//                alertaErro(errorMsg);
            }else if(s.length() == 0){
                String errorMsg = "Erro comunicação.\n Verifique a conexão com a internet.";

            } else if ("ERROR|007 - Script ja executado".equals(s)) {

//                progressDialog.dismiss();
//                alertaErro("007 - Pedido sincronizado.");

            } else if ("ERROR|006 - Erro: multiple rows in singleton select".equals(s)) {
                WeakReference<Handler> handle = new WeakReference(new Handler());
                handle.get().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 60000);

            } else if ("EXEC|001 - Executado com sucesso".equals(s)) {
//                showMainScreen();
                System.out.println("deu certo");

            } else if (s.contains("ERROR")) {
//                progressDialog.dismiss();
//                dialogEnvioPedido.dismiss();
//                alertaErro(s);
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
            String errorMsg = "Erro comunicação.\n Verifique a conexão com a internet..";
        }
    }

}


