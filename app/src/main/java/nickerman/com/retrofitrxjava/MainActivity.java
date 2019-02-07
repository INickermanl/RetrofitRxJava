package nickerman.com.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nickerman.com.retrofitrxjava.adaptor.UserAdaptor;
import nickerman.com.retrofitrxjava.model.User;
import nickerman.com.retrofitrxjava.retrofit.IMyAPI;
import nickerman.com.retrofitrxjava.retrofit.RetrofitClient;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable subscriptions;
    private RecyclerView recyclerView;
    private IMyAPI myAPI;
    private List<User> userList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        subscriptions = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(IMyAPI.class);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    private void fetchData() {
        subscriptions.add(myAPI.getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        setData(users);
                    }
                }));
    }

    private void setData(List<User> users) {
        adapter = new UserAdaptor(users);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        subscriptions.clear();
    }
}
