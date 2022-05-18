package br.senai.sp.cotia.todolistapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.adapter.TarefaAdapter;
import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.databinding.FragmentPrincipalBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class PrincipalFragment extends Fragment {

    private FragmentPrincipalBinding binding;
    // variavel para a lista
    private List<Tarefa> tarefas;
    // variavel para o adapter
    private TarefaAdapter adapter;
    // variavel para database
    private AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instanciando o binding
        binding = FragmentPrincipalBinding.inflate(getLayoutInflater(), container, false);

        binding.btNovaTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_cadTarefaFragment);
        });

        // insanciar a database
        database = AppDatabase.getDatabase(getContext());

        // define o manager do recycler
        binding.recyclerTarefa.setLayoutManager(new LinearLayoutManager(getContext()));

        // executa a asynctask
        new ReadTarefa().execute();

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    class ReadTarefa extends AsyncTask<Void, Void, List<Tarefa>> {

        @Override
        protected List<Tarefa> doInBackground(Void... voids) {
            // buscar as tarefas e guardar na variavel tarefas
            tarefas = database.getTarefaDao().getAll();
            return tarefas;
        }

        @Override
        protected void onPostExecute(List<Tarefa> tarefas) {
            // instancia o adapter
            adapter = new TarefaAdapter(tarefas, getContext(), listenerClick);
            // aplica o adapter no recycler
            binding.recyclerTarefa.setAdapter(adapter);
        }
    }

    // listener para click nas tarefas
    private TarefaAdapter.OnTarefaClickLitener listenerClick = (view, tarefa) -> {
      // variavel para "pendurar" a tarefa
        Bundle bundle = new Bundle();
        // pendura a tarefa no bundle
        bundle.putSerializable("tarefa", tarefa);
        // navega para o fragment de detalhes
        NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_principalFragment_to_detalheTarefaFragment, bundle);
    };
}