package iestrassierra.pmdm.recyclerview;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Tarea {
    private String titulo;
    private String descripcion;
    private int progreso;
    private Date fechaCreacion;
    private Date fechaObjetivo;
    private boolean prioritaria;

    public Tarea(String titulo, String descripcion, int progreso, Date fechaCreacion, Date fechaObjetivo, boolean prioritaria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.progreso = progreso;
        this.fechaCreacion = fechaCreacion;
        this.fechaObjetivo = fechaObjetivo;
        this.prioritaria = prioritaria;
    }

    public boolean esPrioritaria() {
        return prioritaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaObjetivo() {
        return fechaObjetivo;
    }

    public String getFechaObjetivoString(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(fechaObjetivo);
    }

    public void setFechaObjetivo(Date fechaObjetivo) {
        this.fechaObjetivo = fechaObjetivo;
    }

    public boolean isPrioritaria() {
        return prioritaria;
    }

    public void setPrioritaria(boolean prioritaria) {
        this.prioritaria = prioritaria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}