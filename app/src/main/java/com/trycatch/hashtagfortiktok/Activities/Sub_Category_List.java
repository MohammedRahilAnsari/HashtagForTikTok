package com.trycatch.hashtagfortiktok.Activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.trycatch.hashtagfortiktok.Adapter.DetailsAdapter;
import com.trycatch.hashtagfortiktok.ApiClient.ApiClient;
import com.trycatch.hashtagfortiktok.Database.FavDatabase;
import com.trycatch.hashtagfortiktok.Model.DataResponses;
import com.trycatch.hashtagfortiktok.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Category_List extends AppCompatActivity implements DetailsAdapter.onItemClick {

    private RecyclerView recyclerView;
    private List<DataResponses> dataResponsesList;
    public String name;
    public Uri uri;
    public ChipGroup chipGroup;
    ImageView selecttag, tiktoktag, copytag, favtag, sharetag;
    List<String> selected;
    List<Chip> chips;
    boolean isSelected = false;
    public static FavDatabase favDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub__category__list);
        uri = Uri.parse("https://vm.tiktok.com/" + "tiktokExtension");
        chipGroup = findViewById(R.id.chip_group);
        getdata();
        getSupportActionBar().setTitle(name);

        selecttag = findViewById(R.id.select_all_tag);
        tiktoktag = findViewById(R.id.tiktok_tag);
        copytag = findViewById(R.id.copy_tag);
        sharetag = findViewById(R.id.share_tag);

        /* favtag = findViewById(R.id.fav_tag);

        favtag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected.size() > 0) {
                    List<FavDataList> favDataLists = new ArrayList<>();
                    for (Chip chi : chips) {
                        if (chi.isSelected()) {
                            FavDataList favdatalist = new FavDataList();
                            favdatalist.setName(chi.getText().toString());
                            favDataLists.add(favdatalist);
                        }
                    }
                    favDatabase.database().addData(favDataLists);
                    Toast.makeText(     Sub_Category_List.this, "Fav tag", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Sub_Category_List.this, "Select Atleast One Tag", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/

    }

    private void getdata() {

        Intent intent = getIntent();
        final String id = intent.getStringExtra("key");
        name = intent.getStringExtra("catname");

        Call<List<DataResponses>> call = ApiClient.getApiService().getalldata(id);

        call.enqueue(new Callback<List<DataResponses>>() {
            @Override
            public void onResponse(Call<List<DataResponses>> call, final Response<List<DataResponses>> response) {
                if (response.body() != null) {
                    dataResponsesList = response.body();
                    recyclerView = findViewById(R.id.rv2);
                    DetailsAdapter detailsAdapter = new DetailsAdapter(Sub_Category_List.this, dataResponsesList, Sub_Category_List.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Sub_Category_List.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(detailsAdapter);
                    final Handler handler = new Handler();

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            recyclerView.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.SubCategory_textview).performClick();
                            // Use package name which we want to check
                            final boolean isAppInstalled = appInstalledOrNot("com.check.application");

                            if (response.body() == null) {
                                onBackPressed();

                            } else {
                                selecttag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        for (Chip chip : chips) {
                                            if (!isSelected) {
                                                chip.setSelected(false);
                                            } else {
                                                chip.setSelected(true);
                                            }
                                            chip.performClick();
                                        }
                                        if (!isSelected) {
                                            isSelected = true;
                                        } else {
                                            isSelected = false;
                                        }
                                    }
                                });

                                tiktoktag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        if (selected.size()> 0) {
                                            if (uri.equals(true)) {
                                                Intent tiktokIntent = new Intent(Intent.ACTION_VIEW, uri);
                                                tiktokIntent.setPackage("com.zhiliaoapp.musically");
                                                startActivity(tiktokIntent);
                                            } else {

                                                Toast.makeText(Sub_Category_List.this, "Please Install TikTok Application", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {

                                            Toast.makeText(Sub_Category_List.this, "Select Atleast One Tag", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                                favDatabase = Room.databaseBuilder(getApplicationContext(), FavDatabase.class, "myfavdb").allowMainThreadQueries().build();

                                copytag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (selected.size() > 0) {
                                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                            ClipData clip = ClipData.newPlainText("", selected.toString().replace("[", "").replace("]", "").replace(",", ""));
                                            clipboard.setPrimaryClip(clip);
                                            Toast.makeText(Sub_Category_List.this, "Copied", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Sub_Category_List.this, "Select Atleast One Tag", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                sharetag.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (selected.size() > 0) {
                                            Intent i = new Intent(Intent.ACTION_SEND);
                                            i.setType("text/plain");
                                            i.putExtra(Intent.EXTRA_TEXT, selected.toString().replace("[", "").replace("]", "").replace(",", ""));
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(Sub_Category_List.this, "Select Atleast One Tag", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }, 100);
                }
            }

            @Override
            public void onFailure(Call<List<DataResponses>> call, Throwable t) {
                Toast.makeText(Sub_Category_List.this, "Something went Wrong Please Try Again After Sometimes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public void onRowClick(final DataResponses dataResponses) {

        chips = new ArrayList<>();

        selected = new ArrayList<>();
        final String[] items = dataResponses.getDescription().split("#");
        chipGroup.removeAllViews();

        for (final String item : items) {
            if (!item.trim().equals("")) {
                final Chip chip = new Chip(this);
                int paddingDp = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 10,
                        getResources().getDisplayMetrics()
                );

                chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
                chip.setText("#" + item);
                chip.setTextSize(16);
                chip.setSelected(false);

                if (chip.isSelected()) {
                    chip.setChipBackgroundColorResource(R.color.tabtextcolor);

                } else {
                    chip.setChipBackgroundColorResource(R.color.white);
                }

                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chip.isSelected()) {
                            chip.setSelected(false);
                            chip.setChipBackgroundColorResource(R.color.white);
                            selected.remove(chip.getText().toString());
                        } else {
                            chip.setSelected(true);
                            chip.setChipBackgroundColorResource(R.color.tabtextcolor);
                            selected.add(chip.getText().toString());
                        }
                    }
                });
                chips.add(chip);
                chipGroup.addView(chip);
            }
        }
    }

    @Override
    public void onBackPressed() {
       if (recyclerView == null) {

       }else{
           super.onBackPressed();
       }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}