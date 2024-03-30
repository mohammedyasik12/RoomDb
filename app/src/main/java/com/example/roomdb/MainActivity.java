package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.roomdb.Api.Appconfig;
import com.example.roomdb.Api.ListAdapter;
import com.example.roomdb.Api.ListModel;
import com.example.roomdb.DB.DbListModel;
import com.example.roomdb.DB.DbRoomDatabase;
import com.example.roomdb.DB.DbViewModel;
import com.example.roomdb.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    //API
    RecyclerView recyclerView;
    RequestQueue requestQueue;
    String apilist_contestId,apilist_teamName,apilist_playerId1,apilist_playerName1,apilist_playerPoints1,apilist_playerId2,apilist_playerName2,apilist_playerPoints2,apilist_playerId3,apilist_playerName3,apilist_playerPoints3,apilist_playerId4,apilist_playerName4,apilist_playerPoints4,apilist_playerId5,apilist_playerName5,apilist_playerPoints5,apilist_playerId6,apilist_playerName6,apilist_playerPoints6,apilist_playerId7,apilist_playerName7,apilist_playerPoints7,apilist_playerId8,apilist_playerName8,apilist_playerPoints8,apilist_playerId9,apilist_playerName9,apilist_playerPoints9,apilist_playerId10,apilist_playerName10,apilist_playerPoints10,apilist_playerId11,apilist_playerName11,apilist_playerPoints11,apilist_pointsTotal;
    public List<ListModel> listModels = new ArrayList<>();
    ListAdapter listAdapter;
    int offset = 0;
    public static final int PAGE_SIZE = 20;

    //DATABASE
    public RecyclerView recyclerViewDb;
    public List<DbListModel> dblistModels = new ArrayList<>();
    DbListAdapter dbListAdapter;
    public DbViewModel dbViewModel;
    Button button_clik;
    String apilive_teamPlayerId1,apilive_teamPlayer1,apilive_teamPlayerPoints1,apilive_PlayertotalPoints,apilive_teamPlayer2,apilive_teamPlayerPoints2;

    String totalformattedNumber;
    TextView txt_total;

    double caluclate;

    LinearLayout alert_dialog;
    TextView alert_text;
    private DbRoomDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //API
        recyclerView = findViewById(R.id.recyclerView);
        requestQueue = Volley.newRequestQueue(this);
      API_Get_List();

        txt_total = findViewById(R.id.txt_total);
        //DB
        recyclerViewDb = findViewById(R.id.recyclerView_db);
        button_clik = findViewById(R.id.button_clik);
        alert_dialog = findViewById(R.id.alert_dialog);
        alert_text = findViewById(R.id.alert_text);

        button_clik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              API_LIVEPOINTS();
                alert_dialog.setVisibility(View.VISIBLE);
                alert_text.setText("Points Calculating...");

            }
        });




    }

    //API

    private void API_Get_List() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {

                final StringRequest request = new StringRequest(Request.Method.POST,
                        "https://primex11.live/game/Api/contest_player_details", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listModels.clear();
                                Log.d("TAG", response.toString());
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    int success = obj.getInt("status");

                                    if (success == Appconfig.TAG_SUCCESS) {
                                        JSONArray arr = obj.getJSONArray("data");
                                        List<ListModel> updates = new ArrayList<>();
                                        for (int i = 0; arr.length() > i; i++) {
                                            JSONObject obj1 = arr.getJSONObject(i);
                                            apilist_contestId = obj1.getString("contest_play_id");
                                            apilist_teamName = obj1.getString("team_name");

                                            apilist_playerId1 = obj1.getString("player1_id");
                                            apilist_playerName1 = obj1.getString("player1");
                                            apilist_playerPoints1 = "0";

                                            apilist_playerId2 = obj1.getString("player2_id");
                                            apilist_playerName2 = obj1.getString("player2");
                                            apilist_playerPoints2 = "0";

                                            apilist_playerId3 = obj1.getString("player3_id");
                                            apilist_playerName3 = obj1.getString("player3");
                                            apilist_playerPoints3 = "0";

                                            apilist_playerId4 = obj1.getString("player4_id");
                                            apilist_playerName4 = obj1.getString("player4");
                                            apilist_playerPoints4 = "0";


                                            apilist_playerId5 = obj1.getString("player5_id");
                                            apilist_playerName5 = obj1.getString("player5");
                                            apilist_playerPoints5 = "0";

                                            apilist_playerId6 = obj1.getString("player6_id");
                                            apilist_playerName6 = obj1.getString("player6");
                                            apilist_playerPoints6 = "0";

                                            apilist_playerId7 = obj1.getString("player7_id");
                                            apilist_playerName7 = obj1.getString("player7");
                                            apilist_playerPoints7 = "0";

                                            apilist_playerId8 = obj1.getString("player8_id");
                                            apilist_playerName8 = obj1.getString("player8");
                                            apilist_playerPoints8 = "0";

                                            apilist_playerId9 = obj1.getString("player9_id");
                                            apilist_playerName9 = obj1.getString("player9");
                                            apilist_playerPoints9 = "0";

                                            apilist_playerId10 = obj1.getString("player10_id");
                                            apilist_playerName10 = obj1.getString("player10");
                                            apilist_playerPoints10 = "0";

                                            apilist_playerId11 = obj1.getString("player11_id");
                                            apilist_playerName11 = obj1.getString("player11");
                                            apilist_playerPoints11 = "0";

                                            apilist_pointsTotal = "0";

                                            listModels.add(new ListModel(apilist_teamName, apilist_playerId1, apilist_playerName1, apilist_playerPoints1, apilist_playerId2, apilist_playerName2, apilist_playerPoints2, apilist_playerId3, apilist_playerName3, apilist_playerPoints3, apilist_playerId4, apilist_playerName4, apilist_playerPoints4, apilist_playerId5, apilist_playerName5, apilist_playerPoints5, apilist_playerId6, apilist_playerName6, apilist_playerPoints6, apilist_playerId7, apilist_playerName7, apilist_playerPoints7, apilist_playerId8, apilist_playerName8, apilist_playerPoints8, apilist_playerId9, apilist_playerName9, apilist_playerPoints9, apilist_playerId10, apilist_playerName10, apilist_playerPoints10, apilist_playerId11, apilist_playerName11, apilist_playerPoints11));
                                            //DATABASE
                                            dblistModels.add(new DbListModel(apilist_contestId, apilist_teamName, apilist_playerId1, apilist_playerName1, apilist_playerPoints1, apilist_playerId2, apilist_playerName2, apilist_playerPoints2, apilist_playerId3, apilist_playerName3, apilist_playerPoints3, apilist_playerId4, apilist_playerName4, apilist_playerPoints4, apilist_playerId5, apilist_playerName5, apilist_playerPoints5, apilist_playerId6, apilist_playerName6, apilist_playerPoints6, apilist_playerId7, apilist_playerName7, apilist_playerPoints7, apilist_playerId8, apilist_playerName8, apilist_playerPoints8, apilist_playerId9, apilist_playerName9, apilist_playerPoints9, apilist_playerId10, apilist_playerName10, apilist_playerPoints10, apilist_playerId11, apilist_playerName11, apilist_playerPoints11, apilist_pointsTotal));

                                            // Do something with the parsed data
                                        }

                                        dbViewModel = new ViewModelProvider(MainActivity.this).get(DbViewModel.class);
                                        dbViewModel.insertData(dblistModels);

                                        dbViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(DbViewModel.class);
                                        recyclerViewDb.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                                        dbViewModel.getAllLeaderBoardFromDB().observe(MainActivity.this, dbModels ->
                                        {
                                            if (dbModels != null && !dbModels.isEmpty()) {
                                                dbListAdapter = new DbListAdapter(MainActivity.this, (ArrayList<DbListModel>) dbModels);
                                                recyclerViewDb.setAdapter(dbListAdapter);

                                                int storeList = dbListAdapter.getItemCount();

                                                txt_total.setText(String.valueOf(storeList));
                                                int getcount = dbListAdapter.getItemCount();
                                                if (getcount >= 0) {

                                                    recyclerView.setVisibility(View.GONE);
                                                }

                                            }
                                        });

                                        listAdapter = new ListAdapter(getApplicationContext(), listModels);
                                        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                                        recyclerView.setLayoutManager(layoutManager);
                                        recyclerView.setAdapter(listAdapter);
                                        listAdapter.notifyDataSetChanged();


                                    } else {
                                        // Handle unsuccessful response
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error response
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("contest_id", "34");
                        return params;
                    }

                };

                // Adding request to request queue
                request.setRetryPolicy(new DefaultRetryPolicy(
                        Appconfig.TAG_VOLLERY_TIMEOUT,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                requestQueue.add(request);

            }
        });

    }

  private void API_LIVEPOINTS() {


      final StringRequest request = new StringRequest(Request.Method.POST,
                "https://game11.online/bannerimage/pointsupdate.txt", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                  listModels.clear();

                Log.d("TAG", response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    int success = obj.getInt("status");

                    if (success == Appconfig.TAG_SUCCESS) {

                        JSONArray arr;
                        arr = obj.getJSONArray("data");
                        List<ListModel> updates = new ArrayList<>();
                        for (int i = 0; arr.length() > i; i++) {
                            JSONObject obj1 = arr.getJSONObject(i);
                            apilive_teamPlayerId1 =obj1.getString("player_id");
                            apilive_teamPlayer1 =obj1.getString("player_name");
                            apilive_teamPlayerPoints1 =obj1.getString("points");


                            dbViewModel = new ViewModelProvider(MainActivity.this).get(DbViewModel.class);

                            String matchingText = apilive_teamPlayer1;
                            double boostercalculate = Double.parseDouble(apilive_teamPlayerPoints1) * 2.0;
                            DecimalFormat decimalFormat = new DecimalFormat("#.##");
                            String formattedNumber = decimalFormat.format(boostercalculate);
                            dbViewModel.replaceText(matchingText, formattedNumber);

                            String matchingText2 = apilive_teamPlayer1;
                            double boostercalculate2 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.9;
                            DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
                            String formattedNumber2 = decimalFormat2.format(boostercalculate2);
                            dbViewModel.replaceText2(matchingText2, formattedNumber2);


                            String matchingText3 = apilive_teamPlayer1;
                            double boostercalculate3 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.8;
                            DecimalFormat decimalFormat3 = new DecimalFormat("#.##");
                            String formattedNumber3 = decimalFormat3.format(boostercalculate3);
                            dbViewModel.replaceText3(matchingText3, formattedNumber3);

                            String matchingText4 = apilive_teamPlayer1;
                            double boostercalculate4 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.7;
                            DecimalFormat decimalFormat4 = new DecimalFormat("#.##");
                            String formattedNumber4 = decimalFormat4.format(boostercalculate4);
                            dbViewModel.replaceText4(matchingText4, formattedNumber4);


                            String matchingText5 = apilive_teamPlayer1;
                            double boostercalculate5 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.6;
                            DecimalFormat decimalFormat5 = new DecimalFormat("#.##");
                            String formattedNumber5 = decimalFormat5.format(boostercalculate5);
                            dbViewModel.replaceText5(matchingText5, formattedNumber5);


                            String matchingText6 = apilive_teamPlayer1;
                            double boostercalculate6 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.5;
                            DecimalFormat decimalFormat6 = new DecimalFormat("#.##");
                            String formattedNumber6 = decimalFormat6.format(boostercalculate6);
                            dbViewModel.replaceText6(matchingText6, formattedNumber6);

                            String matchingText7 = apilive_teamPlayer1;
                            double boostercalculate7 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.4;
                            DecimalFormat decimalFormat7 = new DecimalFormat("#.##");
                            String formattedNumber7 = decimalFormat7.format(boostercalculate7);
                            dbViewModel.replaceText7(matchingText7, formattedNumber7);

                            String matchingText8 = apilive_teamPlayer1;
                            double boostercalculate8 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.3;
                            DecimalFormat decimalFormat8 = new DecimalFormat("#.##");
                            String formattedNumber8 = decimalFormat8.format(boostercalculate8);
                            dbViewModel.replaceText8(matchingText8, formattedNumber8);

                            String matchingText9 = apilive_teamPlayer1;
                            double boostercalculate9 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.2;
                            DecimalFormat decimalFormat9 = new DecimalFormat("#.##");
                            String formattedNumber9 = decimalFormat9.format(boostercalculate9);
                            dbViewModel.replaceText9(matchingText9, formattedNumber9);

                            String matchingText10 = apilive_teamPlayer1;
                            double boostercalculate10 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.1;
                            DecimalFormat decimalFormat10 = new DecimalFormat("#.##");
                            String formattedNumber10 = decimalFormat10.format(boostercalculate10);
                            dbViewModel.replaceText10(matchingText10, formattedNumber10);

                            String matchingText11 = apilive_teamPlayer1;
                            double boostercalculate11 = Double.parseDouble(apilive_teamPlayerPoints1) * 1.0;
                            DecimalFormat decimalFormat11 = new DecimalFormat("#.##");
                            String formattedNumber11 = decimalFormat11.format(boostercalculate11);
                            dbViewModel.replaceText11(matchingText11, formattedNumber11);


                          alert_dialog.setVisibility(View.GONE);
                        }


                    } else { }


                } catch (JSONException e) {
                    //
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {

          /*  @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
               params.put("fixture_id", "45");
                return params;
            }*/

        };

        // Adding request to request queue
        request.setRetryPolicy(new DefaultRetryPolicy(
                Appconfig.TAG_VOLLERY_TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(request);


    }


    public class DbListAdapter extends RecyclerView.Adapter<DbListAdapter.CustomViewHolder> {

        private ArrayList<DbListModel> dblistModel = new ArrayList<>();
        private HashMap<String, Integer> pointNumberMap;
        int rank;

        Context context;
        public DbListAdapter(Context context,ArrayList<DbListModel> dbModel){

            this.context = context;
            this.dblistModel = dbModel;
            this.pointNumberMap = new HashMap<>();
        }



      /*  public void sortByEntryHighToLow() {

          /*  Collections.sort(dblistModel, (item1, item2) ->
                    convertStringToInt(item1.getDbpointsTotal()) - convertStringToInt(item2.getDbpointsTotal()));
            notifyDataSetChanged();

            Collections.sort(dblistModel, (item1, item2) -> {
                BigDecimal sum1 = calculateSum(item1);
                BigDecimal sum2 = calculateSum(item2);
                return sum2.compareTo(sum1); // Compare in descending order
            });
            notifyDataSetChanged();

            alert_dialog.setVisibility(View.GONE);
        }

        private int convertStringToInt(String value) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // Handle the case where the string cannot be converted to an integer
                return 0; // Or any other default value
            }
        }*/

        class CustomViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            TextView txtteamName,txtcontestId,txtplayerId1,txtplayerName1,txtplayerPoints1,txtplayerId2,txtplayerName2,txtplayerPoints2,txttotalvalues,txtplayerId3,txtplayerName3,txtplayerPoints3
                    ,txtplayerId4,txtplayerName4,txtplayerPoints4,txtplayerId5,txtplayerName5,txtplayerPoints5,txtplayerId6,txtplayerName6,txtplayerPoints6,txtplayerId7,txtplayerName7,txtplayerPoints7,txtplayerId8,txtplayerName8,txtplayerPoints8
                    ,txtplayerId9,txtplayerName9,txtplayerPoints9,txtplayerId10,txtplayerName10,txtplayerPoints10,txtplayerId11,txtplayerName11,txtplayerPoints11,txtNumbers;

            CustomViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                txtteamName = mView.findViewById(R.id.txtteamName);
                txtcontestId = mView.findViewById(R.id.txtcontestId);

                txtplayerId1 = mView.findViewById(R.id.txtplayerId1);
                txtplayerName1 = mView.findViewById(R.id.txtplayerName1);
                txtplayerPoints1 = mView.findViewById(R.id.txtplayerPoints1);

                txtplayerId2 = mView.findViewById(R.id.txtplayerId2);
                txtplayerName2 = mView.findViewById(R.id.txtplayerName2);
                txtplayerPoints2 = mView.findViewById(R.id.txtplayerPoints2);

                txtplayerId3 = mView.findViewById(R.id.txtplayerId3);
                txtplayerName3 = mView.findViewById(R.id.txtplayerName3);
                txtplayerPoints3 = mView.findViewById(R.id.txtplayerPoints3);

                txtplayerId4 = mView.findViewById(R.id.txtplayerId4);
                txtplayerName4= mView.findViewById(R.id.txtplayerName4);
                txtplayerPoints4 = mView.findViewById(R.id.txtplayerPoints4);

                txtplayerId5 = mView.findViewById(R.id.txtplayerId5);
                txtplayerName5 = mView.findViewById(R.id.txtplayerName5);
                txtplayerPoints5 = mView.findViewById(R.id.txtplayerPoints5);

                txtplayerId6 = mView.findViewById(R.id.txtplayerId6);
                txtplayerName6 = mView.findViewById(R.id.txtplayerName6);
                txtplayerPoints6 = mView.findViewById(R.id.txtplayerPoints6);

                txtplayerId7 = mView.findViewById(R.id.txtplayerId7);
                txtplayerName7 = mView.findViewById(R.id.txtplayerName7);
                txtplayerPoints7 = mView.findViewById(R.id.txtplayerPoints7);

                txtplayerId8 = mView.findViewById(R.id.txtplayerId8);
                txtplayerName8 = mView.findViewById(R.id.txtplayerName8);
                txtplayerPoints8 = mView.findViewById(R.id.txtplayerPoints8);

                txtplayerId9 = mView.findViewById(R.id.txtplayerId9);
                txtplayerName9 = mView.findViewById(R.id.txtplayerName9);
                txtplayerPoints9 = mView.findViewById(R.id.txtplayerPoints9);

                txtplayerId10 = mView.findViewById(R.id.txtplayerId10);
                txtplayerName10 = mView.findViewById(R.id.txtplayerName10);
                txtplayerPoints10 = mView.findViewById(R.id.txtplayerPoints10);

                txtplayerId11 = mView.findViewById(R.id.txtplayerId11);
                txtplayerName11 = mView.findViewById(R.id.txtplayerName11);
                txtplayerPoints11 = mView.findViewById(R.id.txtplayerPoints11);

                txttotalvalues = mView.findViewById(R.id.txttotalvalue);
                txtNumbers = mView.findViewById(R.id.txtNumber);


            }


            void bind(int number,final DbListModel userModal) {

               // txtNumbers.setText(String.valueOf(number));

                txtteamName.setText(userModal.getDbteamName());
                txtcontestId.setText(userModal.getDbcontest_playid());

                txtplayerId1.setText(userModal.getDbplayerId1());
                txtplayerName1.setText(userModal.getDbplayerName1());
                txtplayerPoints1.setText(userModal.getDbplayerPoints1());

                txtplayerId2.setText(userModal.getDbplayerId2());
                txtplayerName2.setText(userModal.getDbplayerName2());
                txtplayerPoints2.setText(userModal.getDbplayerPoints2());

                txtplayerId3.setText(userModal.getDbplayerId3());
                txtplayerName3.setText(userModal.getDbplayerName3());
                txtplayerPoints3.setText(userModal.getDbplayerPoints3());

                txtplayerId4.setText(userModal.getDbplayerId4());
                txtplayerName4.setText(userModal.getDbplayerName4());
                txtplayerPoints4.setText(userModal.getDbplayerPoints4());

                txtplayerId5.setText(userModal.getDbplayerId5());
                txtplayerName5.setText(userModal.getDbplayerName5());
                txtplayerPoints5.setText(userModal.getDbplayerPoints5());

                txtplayerId6.setText(userModal.getDbplayerId6());
                txtplayerName6.setText(userModal.getDbplayerName6());
                txtplayerPoints6.setText(userModal.getDbplayerPoints6());

                txtplayerId7.setText(userModal.getDbplayerId7());
                txtplayerName7.setText(userModal.getDbplayerName7());
                txtplayerPoints7.setText(userModal.getDbplayerPoints7());

                txtplayerId8.setText(userModal.getDbplayerId8());
                txtplayerName8.setText(userModal.getDbplayerName8());
                txtplayerPoints8.setText(userModal.getDbplayerPoints8());

                txtplayerId9.setText(userModal.getDbplayerId9());
                txtplayerName9.setText(userModal.getDbplayerName9());
                txtplayerPoints9.setText(userModal.getDbplayerPoints9());

                txtplayerId10.setText(userModal.getDbplayerId10());
                txtplayerName10.setText(userModal.getDbplayerName10());
                txtplayerPoints10.setText(userModal.getDbplayerPoints10());

                txtplayerId11.setText(userModal.getDbplayerId11());
                txtplayerName11.setText(userModal.getDbplayerName11());
                txtplayerPoints11.setText(userModal.getDbplayerPoints11());


               caluclate = Double.parseDouble(userModal.getDbplayerPoints1())+Double.parseDouble(userModal.getDbplayerPoints2())+Double.parseDouble(userModal.getDbplayerPoints3())+Double.parseDouble(userModal.getDbplayerPoints4())
                        +Double.parseDouble(userModal.getDbplayerPoints5())+Double.parseDouble(userModal.getDbplayerPoints6())+Double.parseDouble(userModal.getDbplayerPoints7())+Double.parseDouble(userModal.getDbplayerPoints8())
                        +Double.parseDouble(userModal.getDbplayerPoints9())+Double.parseDouble(userModal.getDbplayerPoints10())+Double.parseDouble(userModal.getDbplayerPoints11());
                DecimalFormat decimalFormat = new DecimalFormat("#.##");

                totalformattedNumber = decimalFormat.format(caluclate);
                txttotalvalues.setText("Total Points: "+  totalformattedNumber);



            }


        }

        @Override
        public DbListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.db_list_item, parent, false);
            return new DbListAdapter.CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DbListAdapter.CustomViewHolder holder, int position) {

            DbListModel currentItem = dblistModel.get(position);

            // Generate a unique identifier for the current item's points
            String pointsIdentifier = generatePointsIdentifier(currentItem);

            // Get the serial number from the map or assign a new one if it doesn't exist
            Integer serialNumber = pointNumberMap.get(pointsIdentifier);
            if (serialNumber == null) {
                // Assign a new serial number
                serialNumber = pointNumberMap.size() + 1;
                // Store the serial number in the map for future reference
                pointNumberMap.put(pointsIdentifier, serialNumber);
            }

            // Bind the item with the assigned serial number
            holder.bind(serialNumber, currentItem);

            // Display the serial number in the txtNumbers TextView
            String serialText = String.valueOf(serialNumber);
            holder.txtNumbers.setText(serialText);

        }

        @Override
        public int getItemCount() {
          /*  int limit = 10;
            return Math.min(dblistModel.size(), limit);*/
           return dblistModel.size();
        }

    }

    private BigDecimal calculateSum(DbListModel item) {
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(new BigDecimal(item.getDbplayerPoints1()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints2()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints3()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints4()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints5()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints6()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints7()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints8()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints9()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints10()));
        sum = sum.add(new BigDecimal(item.getDbplayerPoints11()));
        return sum;
    }

    // Helper method to generate a unique identifier for the points of an item
    private String generatePointsIdentifier(DbListModel item) {

      Double calcu= Double.valueOf(item.getDbplayerPoints1())+Double.valueOf(item.getDbplayerPoints2())+Double.valueOf(item.getDbplayerPoints3())+Double.valueOf(item.getDbplayerPoints4())+Double.valueOf(item.getDbplayerPoints5())+Double.valueOf(item.getDbplayerPoints6())+Double.valueOf(item.getDbplayerPoints7())+Double.valueOf(item.getDbplayerPoints8())+Double.valueOf(item.getDbplayerPoints9())+Double.valueOf(item.getDbplayerPoints10())+Double.valueOf(item.getDbplayerPoints11());

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String totalNumber = decimalFormat.format(calcu);


        // Concatenate all points into a single string
        return totalNumber;
    }


}