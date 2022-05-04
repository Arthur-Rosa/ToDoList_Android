package br.senai.sp.cotia.todolistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.senai.sp.cotia.todolistapp.R;
import br.senai.sp.cotia.todolistapp.model.Tarefa;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaViewHolder> {

    // variavel para lista de tarefas
    private List<Tarefa> tarefas;
    // váriavel para o Context
    private Context context;

    // construtor que recebe os parametros para o adapter
    public TarefaAdapter(List<Tarefa> lista, Context contexto) {
        this.tarefas = lista;
        this.context = contexto;
    }

    @NonNull
    @Override
    public TarefaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflar a view do viewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tarefas, parent, false);
        // retorna uma viewHolder
        return new TarefaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaViewHolder holder, int position) {
        // obter a tarefa através da position
        Tarefa t = tarefas.get(position);
        // transportar a info da tarefa para o holder
        holder.tvTitulo.setText(t.getTitulo());
        holder.tvDesc.setText(t.getDescricao());
        // formata a data e exibe no textView
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvData.setText(formatador.format(t.getDataPrevista()));
        // verifica se esta concluida
        if(t.isConcluida()){
            holder.tvStatus.setText(R.string.finalizada);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.orange_conc));
        } else if (t.getDataPrevista() < Calendar.getInstance().getTimeInMillis()) {
            holder.tvStatus.setText(R.string.atrasado);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.pink_red));
        }else {
            holder.tvStatus.setText(R.string.aberta);
            holder.tvStatus.setBackgroundColor(context.getResources().getColor(R.color.bege_not_conc));
        }
    }

    @Override
    public int getItemCount() {
        if(tarefas != null){
            return tarefas.size();
        }
        return 0;
    }

    class TarefaViewHolder extends RecyclerView.ViewHolder {
        // váriaveis para os components
        TextView tvTitulo, tvData, tvStatus, tvDesc;

        public TarefaViewHolder(View view) {
            super(view);
            // passar da view para os componentes
            tvTitulo = view.findViewById(R.id.titulo_tarefa_card);
            tvData = view.findViewById(R.id.data_card);
            tvStatus = view.findViewById(R.id.status_card);
            tvDesc = view.findViewById(R.id.descricao_tarefa_card);
        }
    }
}
