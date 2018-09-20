package retrofitdemo.jlapps.com.retrofitdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //chamada sincrona
        RetrofitAsyncTasks retrofitAsyncTasks = new RetrofitAsyncTasks();
        retrofitAsyncTasks.execute("jjaviles2010");

        //Chamada assincrona a api

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubServices services = retrofit.create(GitHubServices.class);

        Call<List<Repo>> repos = services.listRepos("jjaviles2010");

        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                //pega a lista de repositorios no corpo da requisicao
                List<Repo> repos = response.body();

                for (Repo rep : repos) {
                    Log.i("GitHubServices", rep.getmName());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });*/


    }
}

class RetrofitAsyncTasks extends AsyncTask<String, String, List<Repo>> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Repo> repos) {
        super.onPostExecute(repos);
    }

    @Override
    protected List<Repo> doInBackground(String... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubServices services = retrofit.create(GitHubServices.class);
        Call<List<Repo>> getRepositories = services.listRepos(params[0]);

        try {
            List<Repo> repos = getRepositories.execute().body();
            for (Repo rep: repos){
                Log.i("Repositories", rep.getmName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}