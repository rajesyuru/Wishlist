package com.example.wishlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddWishlist extends AppCompatActivity {

    EditText ProductName;
    EditText Price;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wishlist);

        ProductName = findViewById(R.id.EnterName);
        Price = findViewById(R.id.EnterPrice);

        Intent intent = getIntent();
        String prodname = intent.getStringExtra("prod");
        String price = intent.getStringExtra("price");
        position = intent.getIntExtra("position", 0);

        ProductName.setText(prodname);
        Price.setText(price);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_wishlist_save, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.Save) {
            if (ProductName.getText().length() == 0 || Price.getText().length() == 0){
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("prod_name", ProductName.getText().toString());
                intent.putExtra("price", Price.getText().toString());
                intent.putExtra("pos", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
