package br.senai.sp.cotia.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import br.senai.sp.cotia.todolistapp.R;

import br.senai.sp.cotia.todolistapp.database.AppDatabase;
import br.senai.sp.cotia.todolistapp.database.TarefaDao;
import br.senai.sp.cotia.todolistapp.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;

    private DatePickerDialog datePicker;
    // variaveis para ano mes e dia
    int year, month, day;
    // variavel para obter a data atual
    Calendar dataAtual;
    // variavel para a data formata
    String dataFormatada;
    // variável para a database
    AppDatabase database;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // instancia a database
        database = AppDatabase.getDatabase(getContext());


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
            year = ano;
            month = mes;
            day = dia;

            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);

            binding.btData.setText(dataFormatada);
        }, year, month, day);

        // ação do clique do botão
        binding.btData.setOnClickListener(v -> {
            datePicker.show();
        });

        // listener do botão salvar
        binding.btSalvar.setOnClickListener(v -> {
            // validar os campos
            if (binding.tituloTarefa.getText().toString().isEmpty()) {
                Snackbar.make(binding.btSalvar, "Digite o título da Tarefa ", Snackbar.LENGTH_SHORT).show();
            } else if (binding.descricaoTarefa.getText().toString().isEmpty()) {
                Snackbar.make(binding.btSalvar, "Digite a descrição ", Snackbar.LENGTH_SHORT).show();
            } else if (dataFormatada.isEmpty()) {
                Snackbar.make(binding.btSalvar, "Escolha uma data", Snackbar.LENGTH_SHORT).show();
            } else {
                // criar uma tarefa
                Tarefa tarefa = new Tarefa();
                // popular o objeto tarefa
                tarefa.setTitulo(binding.tituloTarefa.getText().toString());
                tarefa.setDescricao(binding.descricaoTarefa.getText().toString());
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                // criar um calendar
                Calendar dataPrevista = Calendar.getInstance();
                // muda a data para a data no datepicker
                dataPrevista.set(year, month, day);
                // passa os milisegundos para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());

                // salvar tarefa
                new InsertTarefa().execute(tarefa);
            }
        });

        // retorna a view raiz (root) do binding
        return binding.getRoot();
    }

    // AsyncTask para inserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.ProgressBar.setVisibility(View.VISIBLE);
                }
            }, 100);
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            // pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                // chamar o metodo para salvar a tarefa
                database.getTarefaDao().insert(t);
                //retornar
                return "ok";
            } catch (Exception e) {
                e.printStackTrace();
                // retorna mensagem de erro
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.ProgressBar.setVisibility(View.VISIBLE);


                    if (result.equals("ok")) {
                        binding.ProgressBar.setVisibility(View.INVISIBLE);
                        Snackbar.make(binding.btSalvar, "Tarefa criada com sucesso !", Snackbar.LENGTH_SHORT).show();
                        binding.tituloTarefa.setText("");
                        binding.descricaoTarefa.setText("");
                        binding.btData.setText("SELECIONE A DATA");
                        binding.tituloTarefa.requestFocus();

                        // voltar o fragment anterior

                        getActivity().onBackPressed();
                    } else {
                        Snackbar.make(binding.btSalvar, "Deu erro", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }, 1000);
        }
    }

}