package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentDetalheTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class DetalheTarefaFragment extends Fragment {

    private FragmentDetalheTarefaBinding binding;

    private Tarefa tarefa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instanciando o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(), container, false);

        //verifica se foi passado algo n bundle
        if(getArguments() != null){
            // recupera a tarefa do bundle
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            // popular as informações da tarefa
            if(tarefa.isConcluida()){
                binding.cardDetalheTitulo.setBackgroundColor(getContext().getResources().getColor(R.color.orange_conc));
            } else if (tarefa.getDataPrevista() < Calendar.getInstance().getTimeInMillis()) {
                binding.cardDetalheTitulo.setBackgroundColor(getContext().getResources().getColor(R.color.pink_red));
            }else {
                binding.cardDetalheTitulo.setBackgroundColor(getContext().getResources().getColor(R.color.bege_not_conc));
            }
            binding.cardDetalheTitulo.setText(tarefa.getTitulo());
            binding.cardDetalheDescricao.setText(tarefa.getDescricao());
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            binding.dataDeRealizacao.setText(formatador.format(tarefa.getDataPrevista()));
        }

        binding.btNovaTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(DetalheTarefaFragment.this).navigate(R.id.action_detalheTarefaFragment_to_cadSubtarefaFragment);
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}