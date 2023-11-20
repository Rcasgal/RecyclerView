package iestrassierra.pmdm.recyclerview;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {
    private List<Tarea> tareas;

    private IComunicador comunicador;



    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = tareas.get(position);
        holder.fechaTextView.setText(tarea.getFechaObjetivoString());
        holder.progresoProgressBar.setProgress(tarea.getProgreso());
        holder.tituloTextView.setText(tarea.getTitulo());
        long diferenciaDias = calcularDiferenciaDias(tarea.getFechaObjetivoDate());
        String mensajeDias = "Quedan: " + diferenciaDias + " días";
        holder.diastextView.setText(mensajeDias);

        if (tarea.esPrioritaria()) {
            holder.prioritariaImageView.setImageResource(R.drawable.prioritaria_icon);
            holder.prioritariaImageView.setVisibility(View.VISIBLE);
        } else {
            holder.prioritariaImageView.setImageResource(R.drawable.image);
            holder.prioritariaImageView.setVisibility(View.VISIBLE);
        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showContextMenu(v,holder.getAdapterPosition());
                return true;
            }
        });

    }

    private void showContextMenu(View view, int posicion) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.inflate(R.menu.menu_contextual);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_description:
                        break;
                    case R.id.action_edit:



                        comunicador.editarTarea(tareas.get(posicion));

                        break;
                    case R.id.action_delete:
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }



    private long calcularDiferenciaDias(Date fechaObjetivo) {
        Date fechaActual = new Date();
        long diferenciaMilisegundos = fechaObjetivo.getTime() - fechaActual.getTime();
        long diferenciaDias = diferenciaMilisegundos / (1000 * 60 * 60 * 24);
        return diferenciaDias;
    }

    public TareaAdapter(List<Tarea> tareas,IComunicador comunicador) {
        this.tareas = tareas;
        this.comunicador = comunicador;
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
