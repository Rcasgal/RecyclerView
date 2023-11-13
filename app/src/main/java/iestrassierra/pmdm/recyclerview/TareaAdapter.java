package iestrassierra.pmdm.recyclerview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {
    private List<Tarea> tareas;


    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.fechaTextView.setText(tarea.getFechaObjetivoString());
        holder.progresoProgressBar.setProgress(tarea.getProgreso());
        holder.tituloTextView.setText(tarea.getTitulo());
        long diferenciaDias = calcularDiferenciaDias(tarea.getFechaObjetivo());
        String mensajeDias = "Quedan: " + diferenciaDias + " días";
        holder.diastextView.setText(mensajeDias);

        if (tarea.esPrioritaria()) {
            holder.prioritariaImageView.setImageResource(R.drawable.prioritaria_icon);
            holder.prioritariaImageView.setVisibility(View.VISIBLE);
        } else {

            holder.prioritariaImageView.setImageResource(R.drawable.descarga);
            holder.prioritariaImageView.setVisibility(View.VISIBLE);
        }
    }

    private long calcularDiferenciaDias(Date fechaObjetivo) {
        Date fechaActual = new Date();
        long diferenciaMilisegundos = fechaObjetivo.getTime() - fechaActual.getTime();
        long diferenciaDias = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
        return diferenciaDias;
    }

    public TareaAdapter(List<Tarea> tareas) {
        this.tareas = tareas;
    }


    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_item, parent, false);
        return new TareaViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return tareas.size();
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView;
        ProgressBar progresoProgressBar;
        TextView fechaTextView;
        TextView diastextView;
        ImageView prioritariaImageView;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            progresoProgressBar = itemView.findViewById(R.id.progresoProgressBar);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            diastextView = itemView.findViewById(R.id.diasTextView);
            prioritariaImageView = itemView.findViewById(R.id.prioritariaImageView);
        }
    }
}
