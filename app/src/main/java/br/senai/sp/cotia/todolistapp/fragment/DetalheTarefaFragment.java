package br.senai.sp.cotia.todolistapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.databinding.FragmentDetalheTarefaBinding;

public class DetalheTarefaFragment extends Fragment {

    private FragmentDetalheTarefaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instanciando o binding
        binding = FragmentDetalheTarefaBinding.inflate(getLayoutInflater(), container, false);

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}