package br.senai.sp.cotia.todolistapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.senai.sp.cotia.todolistapp.model.Tarefa;

@Dao
public interface TarefaDao {
    @Insert
    void insert(Tarefa t);

    @Update
    void update(Tarefa t);

    @Delete
    void delete(Tarefa t);

    @Query("select * from tarefa")
    List<Tarefa> getAll();
}
