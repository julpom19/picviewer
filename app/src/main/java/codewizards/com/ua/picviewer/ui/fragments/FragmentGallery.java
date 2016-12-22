package codewizards.com.ua.picviewer.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.data_workers.DataContainer;
import codewizards.com.ua.picviewer.data_workers.DataObserver;
import codewizards.com.ua.picviewer.model.Good;
import codewizards.com.ua.picviewer.ui.recyclerview_goods.GoodAdapter;

/**
 * Created by Интернет on 21.12.2016.
 */

public class FragmentGallery extends Fragment implements DataObserver{
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GoodAdapter goodAdapter;
    private DataContainer dataContainer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_goods);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataContainer = DataContainer.getInstance();
        dataContainer.registerObserver(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataContainer.refreshData();
            }
        });
        Log.d("myTag", "onViewCreated in Gallery");
        showList();
    }

    @Override
    public void update() {
        Log.d("myTag", "update in Gallery");
        showList();

    }

    private void showList() {
        Log.d("myTag", "getListOfGoods in show List");
        List<Good> list = dataContainer.getListOfGoods();
        if(dataContainer.isDataError()) {
            Log.d("myTag", "toast data error");
            Toast.makeText(getActivity(), Const.DATA_ERROR_MSG, Toast.LENGTH_SHORT).show();
        } else {
            if(list != null) {
                if(goodAdapter == null) {
                    goodAdapter = new GoodAdapter(getActivity(), list);
                    recyclerView.setAdapter(goodAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    Log.d("myTag", "list init");
                } else {
                    goodAdapter.setListOfGoods(list);
                    goodAdapter.notifyDataSetChanged();
                    Log.d("myTag", "list update");
                }
            }
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        dataContainer.unregisterObserver(this);
        super.onDestroy();
    }
}
