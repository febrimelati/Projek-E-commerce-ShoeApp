package com.example.projek_shoeapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projek_shoeapp.utils.adapter.ShoeItemAdapter;
import com.example.projek_shoeapp.utils.model.ShoeCart;
import com.example.projek_shoeapp.utils.model.ShoeItem;
import com.example.projek_shoeapp.viewmodel.CartViewModel;
import com.example.projek_shoeapp.views.CartActivity;
import com.example.projek_shoeapp.views.DetailedActivity;
import com.example.projek_shoeapp.views.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ShoeItemAdapter.ShoeClickedListeners{
    private RecyclerView recyclerView ;
    private List<ShoeItem> shoeItemList;
    private ShoeItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    private androidx.appcompat.widget.SearchView searchView;
    TextView textViewSeeMore;
    private CardView sepatuFutsal, sepatuRunning, sepatuBasket, sepatuConvers, sepatuPantofel, sepatuWanita, sepatuAnak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeVariables();
        setUpList();

        //search view
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        //intent rekomendasi
        textViewSeeMore=findViewById(R.id.SeeMore_rcm);
        textViewSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        //intent categories
        sepatuFutsal=findViewById(R.id.SepatuFutsal);
        sepatuFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuRunning=findViewById(R.id.SepatuRunning);
        sepatuRunning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuBasket=findViewById(R.id.SepatuBasket);
        sepatuBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuConvers=findViewById(R.id.SepatuConvers);
        sepatuConvers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuPantofel=findViewById(R.id.SepatuPantofel);
        sepatuPantofel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuWanita=findViewById(R.id.SepatuWanita);
        sepatuWanita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        sepatuAnak=findViewById(R.id.SepatuAnak);
        sepatuAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i=new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        adapter.setShoeItemList(shoeItemList);
        recyclerView.setAdapter(adapter);


        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CartActivity.class));
            }
        });
    }

    private void filterList(String text) {
        List<ShoeItem> filteredList = new ArrayList<>();
        for (ShoeItem item : shoeItemList){
            if (item.getShoeName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(filteredList);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });
    }

    private void setUpList() {
        shoeItemList.add(new ShoeItem("Nike Revolution", "4.9", R.drawable.nike_revolution_road, 600000));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "4.7", R.drawable.flex_run_road_running, 750000));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "4.8", R.drawable.nikecourt_zoom_vapor_cage, 650000));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "4.6", R.drawable.adidas_eq_run, 900000));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "4.8", R.drawable.adidas_ozelia_shoes_grey, 800000));
        shoeItemList.add(new ShoeItem("Adidas Questar", "4.7", R.drawable.adidas_questar_shoes, 780000));
    }

    private void initializeVariables() {

        cartImageView = findViewById(R.id.cart);
        coordinatorLayout = findViewById(R.id.coordinatorLayoutHome);
        shoeCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        shoeItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.gridRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter = new ShoeItemAdapter(this);
    }

    @Override
    public void onCardClicked(ShoeItem shoe) {

        Intent intent = new Intent(HomeActivity.this, DetailedActivity.class);
        intent.putExtra("shoeItem", shoe);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ShoeItem shoeItem) {
        ShoeCart shoeCart = new ShoeCart();
        shoeCart.setShoeName(shoeItem.getShoeName());
        shoeCart.setShoeBrandName(shoeItem.getShoeBrandName());
        shoeCart.setShoePrice(shoeItem.getShoePrice());
        shoeCart.setShoeImage(shoeItem.getShoeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!shoeCartList.isEmpty()) {
            for (int i = 0; i < shoeCartList.size(); i++) {
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())) {
                    quantity[0] = shoeCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = shoeCartList.get(i).getId();
                }
            }
        }

        Log.d("TAG", "onAddToCartBtnClicked: " + quantity[0]);

        if (quantity[0] == 1) {
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0] * shoeCart.getShoePrice());
            viewModel.insertCartItem(shoeCart);
        } else {
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * shoeCart.getShoePrice());
        }
        makeSnackBar("Item Added To Cart");
    }

    private void makeSnackBar(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomeActivity.this, CartActivity.class));
                    }
                }).show();
    }

}