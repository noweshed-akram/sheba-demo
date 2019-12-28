package com.btlbd.sheba.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.btlbd.sheba.model.Item;
import com.btlbd.sheba.adapters.ItemAdapter;
import com.btlbd.sheba.adapters.OfferAdapter;
import com.btlbd.sheba.model.OfferItem;
import com.btlbd.sheba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends Fragment {

    View mMainView;

    private RecyclerView mRecyclerView, wmRecyclerView;
    private OfferAdapter mOfferAdapter;
    private ItemAdapter itemAdapter;
    private ArrayList<OfferItem> mOfferItems;
    private ArrayList<Item> itemArrayList;

    private TextView seeAll;

    private RequestQueue mQueue;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_home, container, false);

        seeAll = mMainView.findViewById(R.id.allOffersId);

        mRecyclerView = mMainView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mMainView.getContext(), 3);
        mRecyclerView.setLayoutManager(layoutManager);

        wmRecyclerView = mMainView.findViewById(R.id.itemRecycler);
        wmRecyclerView.setHasFixedSize(true);
        LinearLayoutManager wmlayoutManager
                = new LinearLayoutManager(mMainView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        wmRecyclerView.setLayoutManager(wmlayoutManager);

        mQueue = Volley.newRequestQueue(mMainView.getContext());

        mOfferItems = new ArrayList<>();
        itemArrayList = new ArrayList<>();

        categoryParsing();

        return mMainView;
    }

    private void categoryParsing() {

        //String categoryUrl = "http://btlbd.xyz/sheba-api/api/category";
        String categoryUrl = "https://api.myjson.com/bins/14uvst";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, categoryUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject category = jsonArray.getJSONObject(i);

                                String name = category.getString("name");
                                String icon = category.getString("icon");

                                mOfferItems.add(new OfferItem(icon, name));
                                itemArrayList.add(new Item(icon, name));
                            }

                            mOfferAdapter = new OfferAdapter(mMainView.getContext(), mOfferItems);
                            mRecyclerView.setAdapter(mOfferAdapter);

                            itemAdapter = new ItemAdapter(mMainView.getContext(), itemArrayList);
                            wmRecyclerView.setAdapter(itemAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonObjectRequest);
    }

}
