package br.com.msonex.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.msonex.R;
import br.com.msonex.interfaces.RecyclerViewOnItemClickListener;
import br.com.msonex.model.Filme;

public class RecyclerViewFilmesAdapter extends RecyclerView.Adapter<RecyclerViewFilmesAdapter.ViewHolder> {

    private List<Filme> filmesList;
    private RecyclerViewOnItemClickListener listener;

    public RecyclerViewFilmesAdapter(List<Filme> filmesList, RecyclerViewOnItemClickListener listener) {
        this.filmesList = filmesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_filme_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Filme filme = filmesList.get(position);
        holder.bind(filme);
        holder.itemView.setOnClickListener(view -> listener.onItemClick(filme));
    }

    @Override
    public int getItemCount() {
        return filmesList.size();
    }

    public void update(List<Filme> filmesList) {
        this.filmesList = filmesList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView data;
        private TextView diretor;
        private TextView descricao;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            data = itemView.findViewById(R.id.data);
            descricao = itemView.findViewById(R.id.descricao);
            diretor = itemView.findViewById(R.id.direcao);
        }

        public void bind(Filme filme) {
            titulo.setText(filme.getTitulo());
            data.setText(filme.getData());
            descricao.setText(filme.getDescricao());
            diretor.setText(filme.getDirecao());
        }
    }
}
