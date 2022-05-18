package br.senai.sp.cotia.todolistapp.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadSubtarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class CadSubtarefaFragment extends Fragment {

    private FragmentCadSubtarefaBinding binding;

    private Tarefa tarefa;

    private AlertDialog dialogFoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instanciando o binding
        binding = FragmentCadSubtarefaBinding.inflate(getLayoutInflater(), container, false);

        if(getArguments() != null){
            tarefa = (Tarefa) getArguments().getSerializable("tarefa");
            binding.tituloDaTarefaQueTaSub.setText(tarefa.getTitulo());
        }

        setHasOptionsMenu(true);

        // instancia o dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.origem_imagem);
        String[] opcoes = {getString(R.string.camera),getString(R.string.galeria)};
        builder.setItems(opcoes, listenerDialog);
        dialogFoto = builder.create();

        // listener do click da imagem
        binding.imageFoto.setOnClickListener(v -> {
            dialogFoto.show();
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    private DialogInterface.OnClickListener listenerDialog = (dialog, i) -> {

    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_subtarefa, menu);
    }

}