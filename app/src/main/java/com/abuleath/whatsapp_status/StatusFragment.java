package com.abuleath.whatsapp_status;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abuleath.whatsapp_status.Adapters.StatusAdapter;
import com.abuleath.whatsapp_status.internet.Server;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class StatusFragment extends Fragment {

    ArrayList<Status> statuses; // all statuses from DB
    ArrayList<Status> Adapterstatuses = new ArrayList<>(); // all statuses from DB
    Server server ;
    Context context;
    int cuerrentStatus = 10;

    @SuppressLint("ValidFragment")
    public StatusFragment(ArrayList<Status> statuses) {
        this.statuses = statuses;

        for(int i = 0 ; i < 10 && i < statuses.size() ; i++)
        {
            Adapterstatuses.add(statuses.get(i));
        }

    }

    RecyclerView recyclerview;
    LinearLayoutManager layoutManager;
    StatusAdapter adapter;
    ProgressBar progressBar;
    // variables for pagination
    private boolean isLoding = true;
    private int pastVisibleItem , visibleItemCount , totalItemCount , previous_total = 0;
    private int view_threshold = 10;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_main , container , false);
        context = container.getContext();
        server = new Server(context);

        //set and show recycler view
        recyclerview = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.prograssBar);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        adapter = new StatusAdapter(getActivity() , Adapterstatuses);
        // show ProgressBar until data load
        progressBar.setVisibility(View.VISIBLE);
        recyclerview.setAdapter(adapter);
        // hide ProgressBar data is load
        progressBar.setVisibility(View.GONE);

        // pagination
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisibleItem = layoutManager.findFirstVisibleItemPosition();

                if(dy > 0)
                {
                    if (isLoding)
                    {
                        if(totalItemCount > previous_total)
                        {
                            isLoding = false;
                            previous_total = totalItemCount;
                        }
                    }

                    if(!isLoding && (totalItemCount - visibleItemCount) <= (pastVisibleItem+view_threshold))
                    {
                        performPagination();
                        isLoding = true;
                    }
                }
            }
        });
        /////////////////////////////////
        return view;
    }


    public ArrayList<Status> getNextStatuss()
    {
        ArrayList<Status> newStatuss = new ArrayList<>();
        for(int i = 0 ; i < 10 && cuerrentStatus < statuses.size() ; i++) {
            newStatuss.add(statuses.get(cuerrentStatus++));
        }

        return newStatuss;
    }

    public void performPagination()
    {
        progressBar.setVisibility(view.VISIBLE);
        if (cuerrentStatus < statuses.size())
        {
            adapter.addStatuses(getNextStatuss());
        }
        else
        {
            Toast.makeText(view.getContext().getApplicationContext() , "لا توجد حالات اخرى متاحة" , Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(view.GONE);
    }
}
