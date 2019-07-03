package br.com.msonex.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import br.com.msonex.model.Filme;
import br.com.msonex.model.FilmesResposta;
import br.com.msonex.repository.FilmeRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FilmesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Filme>> filmesLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> errorsLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FilmeRepository repository = new FilmeRepository();


    public FilmesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Filme>> getNoticiasLiveData() {
        return filmesLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return errorsLiveData;
    }

    public void buscarNoticias() {
        compositeDisposable.add(
                repository.obterListaNoticiasDoArquivo(getApplication().getApplicationContext())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> loadingLiveData.setValue(true))
                        .doAfterTerminate(() -> loadingLiveData.setValue(false))
                        .subscribe(noticiasResposta -> filmesLiveData
                                .setValue(noticiasResposta.getFilmes()), throwable -> errorsLiveData
                                .setValue(throwable))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
