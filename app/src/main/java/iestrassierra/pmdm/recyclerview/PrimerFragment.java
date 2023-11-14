package iestrassierra.pmdm.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
public class PrimerFragment extends Fragment {

    private EditText editTextTitulo, editTextFechaCreacion, editTextFechaObjetivo;

    private Spinner spProgreso;
    private CheckBox checkBoxPrioritaria;
    private Button btnSiguiente;

    public interface OnSiguienteClickListener {
        void onSiguienteClick(String titulo, String fechaCreacion, String fechaObjetivo, boolean prioritaria,int progreso);
        void showDatePickerDialog(TextInputEditText editText);
    }

    private OnSiguienteClickListener listener;
    public void setOnSiguienteClickListener(OnSiguienteClickListener listener) {
        this.listener = listener;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_primer, container, false);

        editTextTitulo = view.findViewById(R.id.editTextTitulo);
        editTextFechaCreacion = view.findViewById(R.id.editTextFechaCreacion);
        editTextFechaObjetivo = view.findViewById(R.id.editTextFechaObjetivo);
        checkBoxPrioritaria = view.findViewById(R.id.checkBoxPrioritaria);
        spProgreso = view.findViewById(R.id.spinnerProgreso);
        btnSiguiente = view.findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = editTextTitulo.getText().toString();
                String fechaCreacion = editTextFechaCreacion.getText().toString();
                String fechaObjetivo = editTextFechaObjetivo.getText().toString();
                boolean prioritaria = checkBoxPrioritaria.isChecked();
                int progreso = spProgreso.getSelectedItemPosition();


                if (listener != null) {
                    listener.onSiguienteClick(titulo, fechaCreacion, fechaObjetivo, prioritaria,progreso);
                }
            }
        });


        editTextFechaCreacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.showDatePickerDialog((TextInputEditText) editTextFechaCreacion);
                }
            }
        });

        editTextFechaObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.showDatePickerDialog((TextInputEditText) editTextFechaObjetivo);
                }
            }
        });

        return view;
    }
}

