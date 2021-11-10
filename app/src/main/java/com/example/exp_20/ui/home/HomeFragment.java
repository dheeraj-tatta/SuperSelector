package com.example.exp_20.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exp_20.R;
import com.example.exp_20.databinding.FragmentHomeBinding;
import com.example.exp_20.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RecyclerView recyclerView;
    HomeAdapter.RecyclerViewClickListener listener;
    Context context;
    List<String> teamList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        List<HomeModel> modelMatchList = new ArrayList<>();


        listener = new HomeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {

                Intent i = new Intent(context, GalleryFragment.class);
                i.putExtra("ss", modelMatchList.get(position).isSs());
                i.putExtra("toss", modelMatchList.get(position).getToss());
                i.putExtra("win_team", modelMatchList.get(position).getWt());
                i.putExtra("uid", modelMatchList.get(position).getUid());
                i.putExtra("squad", modelMatchList.get(position).getSquad());
                startActivity(i);

            }
        };
        String url = "https://cricapi.com/api/matches?apikey=DUmLFVC6gPZaDhs8U4vTTK1UNA82";
//my key = Oi11keKppSQHtbRaSOW3MX9mcv62
        //mounika's key = LDkgcBaZQGhywGDQMK3grJuBs652
//        Toast.makeText(getApplicationContext(), "Toast 1", Toast.LENGTH_SHORT).show();
        /*todo api call*/

//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
////                RC.adapter adapter = new RC.adapter(modelMatchList, context, listener);
//
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), "Toast 4", Toast.LENGTH_SHORT).show();
//                    }
//                }`
//        );

//        Toast.makeText(getApplicationContext(), "Toast 7", Toast.LENGTH_SHORT).show();
//        RequestQueue rq = Volley.newRequestQueue(this);
//        rq.add(request);
//
//    }
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        HomeAdapter adapter = new HomeAdapter(modelMatchList, context, listener);
//        try {
//            int i = 0;
//            String ct1, ct2;
////                    Toast.makeText(getApplicationContext(), "Toast 2", Toast.LENGTH_SHORT).show();
//
//            //todo
//            //making dummy data here
//            String dummy_data = "{\n" +
//                    "  \"cache\": true,\n" +
//                    "  \"matches\": [\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059382,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Boost Region\",\n" +
//                    "      \"team-1\": \"Amo Region\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059381,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Mis Ainak Region\",\n" +
//                    "      \"team-1\": \"Band-e-Amir Region\",\n" +
//                    "      \"matchStarted\": true,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1019993,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Pakistan\",\n" +
//                    "      \"team-1\": \"New Zealand\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1034811,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"England\",\n" +
//                    "      \"team-1\": \"India\",\n" +
//                    "      \"matchStarted\": true,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1036383,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Victoria\",\n" +
//                    "      \"team-1\": \"New South Wales\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1036385,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"South Australia\",\n" +
//                    "      \"team-1\": \"Queensland\",\n" +
//                    "      \"matchStarted\": true,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1036387,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Tasmania\",\n" +
//                    "      \"team-1\": \"Western Australia\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1024761,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"KwaZulu-Natal\",\n" +
//                    "      \"team-1\": \"South Western Districts\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1024759,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"North West\",\n" +
//                    "      \"team-1\": \"Namibia\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063304,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Guyana\",\n" +
//                    "      \"team-1\": \"Leeward Islands\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063303,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Barbados\",\n" +
//                    "      \"team-1\": \"Trinidad & Tobago\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063302,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Windward Islands\",\n" +
//                    "      \"team-1\": \"Jamaica\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059712,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"West Indies\",\n" +
//                    "      \"team-1\": \"Zimbabwe\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1050613,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Pakistan Women\",\n" +
//                    "      \"team-1\": \"New Zealand Women\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063067,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Khulna Titans\",\n" +
//                    "      \"team-1\": \"Dhaka Dynamites\",\n" +
//                    "      \"matchStarted\": true,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063068,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Rajshahi Kings\",\n" +
//                    "      \"team-1\": \"Comilla Victorians\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1066688,\n" +
//                    "      \"date\": \"2016-11-19T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"South Africans\",\n" +
//                    "      \"team-1\": \"Victoria XI\",\n" +
//                    "      \"matchStarted\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063052,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Khulna Titans\",\n" +
//                    "      \"team-1\": \"Barisal Bulls\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1043955,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"South Africa Women\",\n" +
//                    "      \"team-1\": \"Australia Women\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1024961,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"KwaZulu-Natal\",\n" +
//                    "      \"team-1\": \"South Western Districts\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1024959,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"North West\",\n" +
//                    "      \"team-1\": \"Namibia\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1004269,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Hong Kong\",\n" +
//                    "      \"team-1\": \"Kenya\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1003857,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Dolphins\",\n" +
//                    "      \"team-1\": \"Titans\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1003859,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Warriors\",\n" +
//                    "      \"team-1\": \"Knights\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1003861,\n" +
//                    "      \"date\": \"2016-11-20T00:00:00.000Z\",\n" +
//                    "      \"squad\": false,\n" +
//                    "      \"team-2\": \"Cape Cobras\",\n" +
//                    "      \"team-1\": \"Lions\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063069,\n" +
//                    "      \"team-2\": \"Rajshahi Kings\",\n" +
//                    "      \"team-1\": \"Dhaka Dynamites\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"date\": \"2016-11-21T00:00:00.000Z\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063070,\n" +
//                    "      \"team-2\": \"Chittagong Vikings\",\n" +
//                    "      \"team-1\": \"Comilla Victorians\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"date\": \"2016-11-21T00:00:00.000Z\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059713,\n" +
//                    "      \"date\": \"2016-11-21T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Sri Lanka\",\n" +
//                    "      \"team-1\": \"Zimbabwe\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1050615,\n" +
//                    "      \"date\": \"2016-11-21T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Pakistan Women\",\n" +
//                    "      \"team-1\": \"New Zealand Women\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063071,\n" +
//                    "      \"team-2\": \"Khulna Titans\",\n" +
//                    "      \"team-1\": \"Rangpur Riders\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"date\": \"2016-11-22T00:00:00.000Z\"\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1063072,\n" +
//                    "      \"team-2\": \"Chittagong Vikings\",\n" +
//                    "      \"team-1\": \"Barisal Bulls\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"date\": \"2016-11-22T00:00:00.000Z\",\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059385,\n" +
//                    "      \"date\": \"2016-11-22T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Boost Region\",\n" +
//                    "      \"team-1\": \"Amo Region\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059384,\n" +
//                    "      \"date\": \"2016-11-22T00:00:00.000Z\",\n" +
//                    "      \"team-2\": \"Mis Ainak Region\",\n" +
//                    "      \"team-1\": \"Band-e-Amir Region\",\n" +
//                    "      \"matchStarted\": false,\n" +
//                    "      \"squad\": true\n" +
//                    "    },\n" +
//                    "    {\n" +
//                    "      \"unique_id\": 1059383,\n" +
//                    "      \"date\": \"2016-11-22T00:00:00.000Z\",\n" +
//                    "      \"squad\": true,\n" +
//                    "      \"team-2\": \"Speen Ghar Region\",\n" +
//                    "      \"team-1\": \"Kabul Green\",\n" +
//                    "      \"matchStarted\": false\n" +
//                    "    }\n" +
//                    "  ]\n" +
//                    "}";
//            JSONObject dummy = new JSONObject(dummy_data);
//
////            JSONObject jsonObject = new JSONObject(response);
//
//            JSONArray jsonArray = dummy.getJSONArray("matches");
//            HomeAdapter adapter = new HomeAdapter(modelMatchList,context,listener);
////                    Toast.makeText(getApplicationContext(),"JSON Array fetched",Toast.LENGTH_SHORT).show();
////                    Toast.makeText(getApplicationContext(), ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
//            for (i = 0; i < jsonArray.length(); i++) {
////                        Toast.makeText(getApplicationContext(), ""+jsonArray.length(), Toast.LENGTH_SHORT).show();
//                JSONObject object = jsonArray.getJSONObject(i);
//                int uid = object.getInt("unique_id");
//                String t1 = object.getString("team-1");
//                ct1 = t1.toLowerCase();
//                Boolean squad = object.getBoolean("squad");
//                String t2 = object.getString("team-2");
//                ct2 = t2.toLowerCase();
//                boolean ss = object.getBoolean("matchStarted");
//                String toss = "No toss data";
//                if (object.has("toss_winner_team")) {
//                    toss = object.getString("toss_winner_team");
//                }
//                String wt = "null";
//                if (object.has("winner_team")) {
//                    wt = object.getString("winner_team");
//                }
//
////                        Toast.makeText(getApplicationContext(), "Toast A", Toast.LENGTH_SHORT).show();
//                if (t2.length() > 21) {
//                    t2 = abbr(t2);
//                }
//                if (t1.length() > 21) {
//                    t1 = abbr(t1);
//                }
//
//                // todo datetimegmt
////                             String d = object.getString("dateTimeGMT") + "\n\n";
////                             String T = "";
////                             T += ddmmyyyy(d.substring(0, 10));
////                             T += "   @  ";
////                             T += d.substring(11, 16);
//                String T = "time";
//
////                        Toast.makeText(getApplicationContext(), "Toast B", Toast.LENGTH_SHORT).show();
////                        if(check(ct1,ct2)) {
//                HomeModel mobj = new HomeModel(uid, t1, t2, T, ss, toss, wt, squad);
////                        Toast.makeText(getApplicationContext(), "Toast C", Toast.LENGTH_SHORT).show();
//                modelMatchList.add(mobj);
////                        }
////                        Toast.makeText(getApplicationContext(), "Toast D", Toast.LENGTH_SHORT).show();
//            }
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//        } catch (Exception e) {
//            Toast.makeText(context, "Response success. Exception", Toast.LENGTH_SHORT).show();
////                         Log.d("excep", "onCreate: "+e);
//            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
//        }




        return root;
    }

    private String abbr(String t2) {
        String abbr = "";
        abbr += t2.charAt(0);
        abbr += ".";
        for (int u = 0; u < t2.length(); u++) {
            if (t2.charAt(u) == ' ') {
                abbr += t2.charAt(u + 1);
                abbr += ".";
            }
        }
        return abbr;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}