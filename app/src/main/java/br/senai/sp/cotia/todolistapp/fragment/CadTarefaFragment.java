package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;

import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;

public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;

    private DatePickerDialog datePicker;
    // variaveis para ano mes e dia
    int year, month, day;
    // variavel para obter a data atual
    Calendar dataAtual;
    // variavel para a data formata
    String dataFormatada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instanciando o binding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(), container, false);

        // instanciar a data atual
        dataAtual = Calendar.getInstance();

        // obter ano mes e dia da data atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);

        // instanciar o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia) -> {
            
        }, year, month, day);

        // ação do clique do botão
        binding.btData.setOnClickListener(v -> {
            datePicker.show();
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }
}