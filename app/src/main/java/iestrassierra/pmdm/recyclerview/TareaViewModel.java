package iestrassierra.pmdm.recyclerview;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class TareaViewModel extends ViewModel {
    private MutableLiveData<String> titulo = new MutableLiveData<>();
    private MutableLiveData<String> fechaCreacion = new MutableLiveData<>();
    private MutableLiveData<String> fechaObjetivo = new MutableLiveData<>();
    private MutableLiveData<Boolean> prioritaria = new MutableLiveData<>();

    private MutableLiveData<Integer> progreso = new MutableLiveData<>();
    private MutableLiveData<String> descripcion = new MutableLiveData<>();

    public MutableLiveData<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(String tituloValue) {
        titulo.setValue(tituloValue);
    }

    public MutableLiveData<Integer> getProgreso() {
        return progreso;
    }

    public void setProgreso(Integer progresovalue) {
        progreso.setValue(progresovalue);
    }
    public MutableLiveData<String> getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacionValue) {
        fechaCreacion.setValue(fechaCreacionValue);
    }

    public MutableLiveData<String> getFechaObjetivo() {
        return fechaObjetivo;
    }

    public void setFechaObjetivo(String fechaObjetivoValue) {
        fechaObjetivo.setValue(fechaObjetivoValue);
    }

    public MutableLiveData<Boolean> getPrioritaria() {
        return prioritaria;
    }

    public void setPrioritaria(Boolean prioritariaValue) {
        prioritaria.setValue(prioritariaValue);
    }

    public MutableLiveData<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcionValue) {
        descripcion.setValue(descripcionValue);
    }
}

