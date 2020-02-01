package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Wishlist> mWishlists;
    private WishListAdapter mWishListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvListView);

        mWishlists = new ArrayList<>();

        mWishListAdapter = new WishListAdapter(this, R.layout.wishlist_layout, mWishlists, new WishListAdapter.SetOnClick() {
            @Override
            public void onEdit(int position) {
                Wishlist wishlist = mWishlists.get(position);

                Intent intent = new Intent(getApplicationContext(), AddWishlist.class);
                intent.putExtra("prod", wishlist.getProduct());
                intent.putExtra("price", String.valueOf(wishlist.getPrice()));
                intent.putExtra("position", position);
                startActivityForResult(intent, 2);



            }

            @Override
            public void onDelete(int position) {
                mWishlists.remove(position);
                mWishListAdapter.notifyDataSetChanged();

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mRecyclerView.setAdapter(mWishListAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_wishlist_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.AddWishlist) {
            Intent intent = new Intent(this, AddWishlist.class);
            startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String product = data.getStringExtra("prod_name");
                String price = data.getStringExtra("price");


                mWishlists.add(new Wishlist(product, Integer.valueOf(price)));

                mWishListAdapter.notifyDataSetChanged();

            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                String product = data.getStringExtra("prod_name");
                String price = data.getStringExtra("price");
                int position = data.getIntExtra("pos", 0);

                mWishlists.set(position, new Wishlist(product, Integer.valueOf(price)));

                mWishListAdapter.notifyDataSetChanged();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
