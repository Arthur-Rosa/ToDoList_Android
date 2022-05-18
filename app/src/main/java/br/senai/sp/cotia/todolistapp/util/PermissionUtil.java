package br.senai.sp.cotia.todolistapp.util;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtil {
    public static boolean checarPermissao(Activity activity, int requestCode, String... permissoes){
        List<String> negadas = new ArrayList<>();

        for (String permissao : permissoes) {
            // verifica se esta negada
            if(ContextCompat.checkSelfPermission(activity, permissao) != PackageManager.PERMISSION_GRANTED){
                negadas.add(permissao);
            }
        }

        if(negadas.isEmpty()){
            return true;
        }
        String[] permissoesNegadas = new String[negadas.size()];
        negadas.toArray(permissoesNegadas);
        ActivityCompat.requestPermissions(activity, permissoesNegadas, requestCode);
        return false;
    }
}
