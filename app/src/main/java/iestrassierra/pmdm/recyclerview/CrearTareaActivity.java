package iestrassierra.pmdm.recyclerview;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class CrearTareaActivity extends AppCompatActivity implements PrimerFragment.OnSiguienteClickListener,SegundoFragment.OnSiguienteClickListener{

    private FragmentManager fragmentManager;
    private TareaViewModel tareaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        fragmentManager = getSupportFragmentManager();
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        cargarPrimerFragment();

    }

    private void cargarPrimerFragment() {

        tareaViewModel.setTitulo("");
        tareaViewModel.setFechaCreacion("");
        tareaViewModel.setFechaObjetivo("");
        tareaViewModel.setPrioritaria(false);
        tareaViewModel.setProgreso(0);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PrimerFragment primerFragment = new PrimerFragment();
        fragmentTransaction.replace(R.id.frameLayoutContainer, primerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSiguienteClick(String titulo, String fechaCreacion, String fechaObjetivo, boolean prioritaria,int progreso) {

        tareaViewModel.setTitulo(titulo);
        tareaViewModel.setFechaCreacion(fechaCreacion);
        tareaViewModel.setFechaObjetivo(fechaObjetivo);
        tareaViewModel.setPrioritaria(prioritaria);
        tareaViewModel.setProgreso(progreso);

        SegundoFragment segundoFragment = new SegundoFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer, segundoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSiguienteClick(String titulo, String fechaCreacion, String fechaObjetivo, boolean prioritaria,int progreso,String descripcion) {

        tareaViewModel.setTitulo(titulo);
        tareaViewModel.setFechaCreacion(fechaCreacion);
        tareaViewModel.setFechaObjetivo(fechaObjetivo);
        tareaViewModel.setPrioritaria(prioritaria);
        tareaViewModel.setProgreso(progreso);
        tareaViewModel.setDescripcion(descripcion);

        PrimerFragment primerFragment = new PrimerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer, primerFragment);
        fragmentTransaction.commit();
    }




}
