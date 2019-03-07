package bravil.com.br.exemplocadastrobravil.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import bravil.com.br.exemplocadastrobravil.adapter.MyMarcaRecyclerViewAdapter;
import bravil.com.br.exemplocadastrobravil.R;
import bravil.com.br.exemplocadastrobravil.dummy.DummyContent;
import bravil.com.br.exemplocadastrobravil.entidades.Marca;
import bravil.com.br.exemplocadastrobravil.task.MarcasTask;
import bravil.com.br.exemplocadastrobravil.task.TaskCallBack;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MarcaFragment extends Fragment {

    private ArrayList<Marca> dadosConsulta = new ArrayList<>();
    private MyMarcaRecyclerViewAdapter adapter;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarcaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MarcaFragment newInstance(int columnCount) {
        MarcaFragment fragment = new MarcaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_marca_list, container, false);

        MarcasTask marcaTask = new MarcasTask(this.getContext(), "demo", new TaskCallBack() {
            @Override
            public void taskSucsess(String carga, Object object) {

                super.taskSucsess(carga, object);
                dadosConsulta.clear();
                dadosConsulta.addAll((List<Marca>) object);
                atualizarListView(view);
                

                System.out.println("---------->" + dadosConsulta);
            }
            @Override
            public void taskError(String carga, String erro) {
                super.taskError(carga, erro);
                System.out.println(carga + "----->" + ((String) erro));
            }
        });
        marcaTask.consumirMarcas();
        System.out.println("----------------->2 " + dadosConsulta);
        // Set the adapter

        return view;
    }

    public void atualizarListView(View view){
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyMarcaRecyclerViewAdapter(dadosConsulta, mListener));
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Marca item);
    }
}
