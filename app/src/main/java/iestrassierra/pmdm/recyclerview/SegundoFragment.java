package iestrassierra.pmdm.recyclerview;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
public class SegundoFragment extends Fragment {

    private TextView textViewTitulo, textViewFechaCreacion, textViewFechaObjetivo;

    private Spinner spProgreso;
    private CheckBox checkBoxPrioritaria;
    private TareaViewModel tareaViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tareaViewModel = new ViewModelProvider(requireActivity()).get(TareaViewModel.class);

        tareaViewModel.getTitulo().observe(this, titulo -> {

            textViewTitulo.setText(titulo);

        });

        tareaViewModel.getFechaCreacion().observe(this, fechaCreacion -> {

            textViewFechaCreacion.setText(fechaCreacion);

        });

        tareaViewModel.getFechaObjetivo().observe(this, fechaObjetivo -> {

            textViewFechaObjetivo.setText(fechaObjetivo);

        });

        tareaViewModel.getPrioritaria().observe(this, prioritaria -> {
            checkBoxPrioritaria.setChecked(prioritaria);
        });

        tareaViewModel.getProgreso().observe(this, progreso -> {

            spProgreso.setSelection(progreso);

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_segundo, container, false);

        textViewTitulo = view.findViewById(R.id.textViewTitulo);
        textViewFechaCreacion = view.findViewById(R.id.textViewFechaCreacion);
        textViewFechaObjetivo = view.findViewById(R.id.textViewFechaObjetivo);
        checkBoxPrioritaria = view.findViewById(R.id.checkBoxPrioritaria);
        spProgreso = view.findViewById(R.id.spinnerProgreso);

        return view;
    }
}
