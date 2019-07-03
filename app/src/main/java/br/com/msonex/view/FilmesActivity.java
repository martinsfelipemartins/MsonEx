package br.com.msonex.view;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.com.msonex.R;
import br.com.msonex.adapters.RecyclerViewFilmesAdapter;
import br.com.msonex.interfaces.RecyclerViewOnItemClickListener;
import br.com.msonex.model.Filme;
import br.com.msonex.viewmodel.FilmesViewModel;

public class FilmesActivity extends AppCompatActivity implements RecyclerViewOnItemClickListener {

    private RecyclerView recyclerViewFilmes;
    private ProgressBar progressBar;
    private RecyclerViewFilmesAdapter recyclerViewFilmesAdapter;
    private List<Filme> filmes = new ArrayList<>();
    private FilmesViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = findViewById(R.id.progressBar);
        recyclerViewFilmes = findViewById(R.id.recyclerview);
        recyclerViewFilmes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilmesAdapter = new RecyclerViewFilmesAdapter(filmes, this);
        recyclerViewFilmes.setHasFixedSize(true);
        recyclerViewFilmes.setAdapter(recyclerViewFilmesAdapter);

        // Fazer a inicialização do view model
        viewModel = ViewModelProviders.of(this).get(FilmesViewModel.class);

        // Buscar os dados no repository
        viewModel.buscarNoticias();

        // Adicionar os observables
        viewModel.getNoticiasLiveData().observe(this, noticias -> {
            recyclerViewFilmesAdapter.update(noticias);
        });

        viewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });


        viewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewFilmes, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });
    }


    @Override
    public void onItemClick(Filme filme) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
