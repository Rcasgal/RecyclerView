package iestrassierra.pmdm.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class EditarTareaActivity extends AppCompatActivity implements PrimerFragment.OnSiguienteClickListener,SegundoFragment.OnVolverClickListener,SegundoFragment.OnGuardarClickListener{

    private FragmentManager fragmentManager;
    private TareaViewModel tareaViewModel;



    public EditarTareaActivity(TareaViewModel tareaViewModel) {

        this.tareaViewModel = tareaViewModel;

    }

    public EditarTareaActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tarea);

        Tarea tareaEditar = (Tarea)getIntent().getParcelableExtra("tareaEditar");

        tareaViewModel.setPrioritaria(tareaEditar.esPrioritaria());
        tareaViewModel.setProgreso(tareaEditar.getProgreso());
        tareaViewModel.setTitulo(tareaEditar.getTitulo());
        tareaViewModel.setFechaCreacion(tareaEditar.getFechaCreacion());
        tareaViewModel.setFechaObjetivo(tareaEditar.getFechaObjetivo());
        tareaViewModel.setId(tareaEditar.getId());
        tareaViewModel.setDescripcion(tareaEditar.getDescripcion());


        fragmentManager = getSupportFragmentManager();
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
