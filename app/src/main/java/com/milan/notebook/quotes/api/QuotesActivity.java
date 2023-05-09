package com.milan.notebook.quotes.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.milan.notebook.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends AppCompatActivity {

    RecyclerView quotesrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quotes);
        quotesrv = findViewById(R.id.quoteList);

        ApiService service = RetrofitClient.getRetrofit().create(ApiService.class);
        service.getQuotes().enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                Log.d("tag", "response size is:" + response.body().size());
                for (Quote quote: response.body()){
                    Log.d("tag", quote.getAuthor());
                    Log.d("tag", quote.getQuote());
                }

                QuoteAdapter adapter = new QuoteAdapter(response.body());
                quotesrv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {

            }
        });
    }
}