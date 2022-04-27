package br.senai.sp.cotia.todolistapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.senai.sp.cotia.todolistapp.model.Tarefa;

@Database(entities = {Tarefa.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // variavel para acesa a database
    private static AppDatabase database;
    // um metodo para tarfea dao
    public abstract TarefaDao getTarefaDao();

    public static AppDatabase getDatabase(Context context){
        // verifico se o db for nulo
        if(database == null){
            // instancia o database
            database = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "todolist").build();
        }

        // retorna o database
        return database;
    }
}
