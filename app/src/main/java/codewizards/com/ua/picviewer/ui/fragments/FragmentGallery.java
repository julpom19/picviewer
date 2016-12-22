package codewizards.com.ua.picviewer.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_goods);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DataContainer.getInstance().registerObserver(this);
        initList();

    }

    @Override
    public void update() {
        initList();
    }

    private void initList() {
        List<Good> list = DataContainer.getInstance().getListOfGoods();
        if(list != null) {
            GoodAdapter goodAdapter = new GoodAdapter(getActivity(), list);
            recyclerView.setAdapter(goodAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }
}
