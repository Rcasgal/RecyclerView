package iestrassierra.pmdm.recyclerview;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ListadoTareasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTareas;
    private TextView textViewNoTareas;
    private TareaAdapter tareaAdapter;
    private List<Tarea> listaTareas;

    private boolean filtrarFav = false;

    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_tareas);

        recyclerViewTareas = findViewById(R.id.recyclerViewTareas);
        textViewNoTareas = findViewById(R.id.textViewNoTareas);
        Calendar fechaTarea1 = new GregorianCalendar(2024, Calendar.MAY, 3);

        Calendar fechaTarea2 = new GregorianCalendar(2024, Calendar.AUGUST, 25);

        Date fechaTarea1Date = fechaTarea1.getTime();
        Date fechaTarea2Date = fechaTarea2.getTime();
        listaTareas = new ArrayList<>();
        listaTareas.add(new Tarea("Tarea 1", "Descripción de la Tarea 1", 50, new Date(), fechaTarea1Date, true));
        listaTareas.add(new Tarea("Tarea 2", "Descripción de la Tarea 2", 1, new Date(), fechaTarea2Date, false));

        recyclerViewTareas.setLayoutManager(new LinearLayoutManager(this));
        tareaAdapter = new TareaAdapter(listaTareas);
        recyclerViewTareas.setAdapter(tareaAdapter);

        if (tareaAdapter.getItemCount() == 0) {
            recyclerViewTareas.setVisibility(View.GONE);
            textViewNoTareas.setVisibility(View.VISIBLE);
        } else {
            recyclerViewTareas.setVisibility(View.VISIBLE);
            textViewNoTareas.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAcercaDe:

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("TrassTarea");
                dialogo1.setMessage("IES Trassierra \n Autor: Ruben Castro Galindo \n Año: 2023");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        ;
                    }
                });
               ;
                dialogo1.show();

                return true;

            case R.id.menuItemSalir:
                finish();
                return true;

            case R.id.menuItemFavoritas:

            filtrarFavorita();


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filtrarFavorita(){

        filtrarFav = !filtrarFav;

        if(filtrarFav) {

            tareaAdapter = new TareaAdapter(favoritas(listaTareas));


        }else {

            tareaAdapter = new TareaAdapter(listaTareas);

        }

        recyclerViewTareas.setAdapter(tareaAdapter);
    }

    private List<Tarea> favoritas(List<Tarea> tareas){

        List<Tarea> favoritas = new ArrayList<>();

        for (Tarea tarea : tareas){

            if (tarea.esPrioritaria()){

                favoritas.add(tarea);

            }
        }

        return favoritas;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado_tareas, menu);
        return true;
    }


}