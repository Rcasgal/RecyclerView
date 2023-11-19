package iestrassierra.pmdm.recyclerview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class CrearTareaActivity extends AppCompatActivity implements PrimerFragment.OnSiguienteClickListener,SegundoFragment.OnVolverClickListener,SegundoFragment.OnGuardarClickListener{

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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PrimerFragment primerFragment = new PrimerFragment();
        primerFragment.setOnSiguienteClickListener(this);
        fragmentTransaction.replace(R.id.frameLayoutContainer, primerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onSiguienteClick(String titulo, String fechaCreacion, String fechaObjetivo, boolean prioritaria, int progreso) {
        tareaViewModel.setTitulo(titulo);
        tareaViewModel.setFechaCreacion(fechaCreacion);
        tareaViewModel.setFechaObjetivo(fechaObjetivo);
        tareaViewModel.setPrioritaria(prioritaria);
        tareaViewModel.setProgreso(progreso);

        SegundoFragment segundoFragment = new SegundoFragment();
        segundoFragment.setOnVolverClickListener(this);
        segundoFragment.setOnGuardarClickListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer, segundoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onVolverClick(String titulo, String fechaCreacion, String fechaObjetivo, boolean prioritaria, int progreso, String descripcion) {

        tareaViewModel.setTitulo(titulo);
        tareaViewModel.setFechaCreacion(fechaCreacion);
        tareaViewModel.setFechaObjetivo(fechaObjetivo);
        tareaViewModel.setPrioritaria(prioritaria);
        tareaViewModel.setProgreso(progreso);
        tareaViewModel.setDescripcion(descripcion);

        PrimerFragment primerFragment = new PrimerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer, primerFragment);
        primerFragment.setOnSiguienteClickListener(this);
        fragmentTransaction.commit();

    }

    @Override
    public void onGuardarClick() {

        String titulo = tareaViewModel.getTituloValue();
        String fechaCreacion = tareaViewModel.getFechaCreacionValue();
        String fechaObjetivo = tareaViewModel.getFechaObjetivoValue();
        boolean prioritaria = tareaViewModel.getPrioritariaValue();
        int progreso = tareaViewModel.getProgresoValue();
        String descripcion = tareaViewModel.getDescripcionValue();

        Tarea nuevaTarea = new Tarea(titulo,descripcion,progreso,fechaCreacion,fechaObjetivo,prioritaria);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("TareaNueva", nuevaTarea);

        setResult(Activity.RESULT_OK, resultIntent);

        finish();

    }

}
