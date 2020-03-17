package com.carson.travelwishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WishListClickListener{

    private static final String TAG = "WISH_LIST_DATABASE";

    private RecyclerView mWishListRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button mAddButton;
    private EditText mNewPlaceNameEditText;
    private EditText mWhyVisit;

    private WishListRecord mWishListRecord;
    private WishListViewModel mWishListViewModel;

    private List<Place> mPlaces;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWishListViewModel = ViewModelProviders.of(getActivity()).get(WishListViewModel.class);

        mPlaces = new ArrayList<>();

        mWishListRecyclerView =findViewById(R.id.wish_list);
        mAddButton = findViewById(R.id.add_place_button);
        mNewPlaceNameEditText = findViewById(R.id.new_place_name);
        mWhyVisit = findViewById(R.id.whyVisitTextView);

        mWishListRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mWishListRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new WishListAdapter(mPlaces,this);
        mWishListRecyclerView.setAdapter(mAdapter);

        //WishListModel
        mWishListViewModel = ViewModelProvider.get(WishListViewModel.class);

        mWishListViewModel.getRecordForDate(date).observe(this, new Observer<WishListRecord>() {
            @Override
            public void onChanged(WishListRecord record) {
                Log.d(TAG, "onCreate , found record: " + record);

                if (record != null) {
                    mWishListRecord = record;
                    //need to display saved data
                }
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String newPlace = mNewPlaceNameEditText.getText().toString();
                String newReason = mWhyVisit.getText().toString();
                if (newPlace.isEmpty()) {

                    return;
                }

                mPlaces.add(new Place(newPlace, newReason));
                mAdapter.notifyItemInserted(mPlaces.size() - 1);
                mWishListViewModel.update(mWishListRecord); //updates database
                mNewPlaceNameEditText.getText().clear();
                mWhyVisit.getText().clear();
            }
        });
    }


    @Override
    public void onListClick(int position) {
        Place place = mPlaces.get(position);
        Uri locationUri = Uri.parse("geo:0, 0?q=" + Uri.encode(place.getName()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, locationUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onListLongClick(int position) {
        final int itemPosition = position;

        AlertDialog confirmationDeleteDialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.delete_place_message, mPlaces.get(position) ))
                .setTitle(getString(R.string.delete_dialog_title))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPlaces.remove(itemPosition);
                        mAdapter.notifyItemRemoved(itemPosition);
                        mWishListViewModel.delete(mWishListRecord);//delete entry
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        confirmationDeleteDialog.show();
    }
}
