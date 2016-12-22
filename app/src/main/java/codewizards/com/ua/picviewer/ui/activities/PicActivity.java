package codewizards.com.ua.picviewer.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.data_workers.DataContainer;
import codewizards.com.ua.picviewer.data_workers.DataObserver;
import codewizards.com.ua.picviewer.model.Good;

/**
 * Created by Интернет on 21.12.2016.
 */

public class PicActivity extends AppCompatActivity implements DataObserver {
    private ImageView ivPic;
    private int posOfGood = -1;
    private DataContainer dataContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ivPic = (ImageView) findViewById(R.id.iv_pic_full);

        dataContainer = DataContainer.getInstance();
        dataContainer.registerObserver(this);

        posOfGood = getIntent().getIntExtra(Const.EXTRA_POS_OF_GOOD, -1);
        if(posOfGood != -1) {
            showPic();
        }
    }

    private void showPic() {
        List<Good> list = dataContainer.getListOfGoods();
        if(list != null) {
            Good good = list.get(posOfGood);
            Glide.with(PicActivity.this).load(good.getUrl()).placeholder(R.mipmap.ic_placeholder).
                    error(R.mipmap.ic_error_placeholder).into(ivPic);
        }
    }

    @Override
    public void update() {
        List<Good> list = dataContainer.getListOfGoods();
        if(posOfGood != -1) {
            showPic();
        }
    }

    @Override
    protected void onDestroy() {
        DataContainer.getInstance().unregisterObserver(this);
        super.onDestroy();
    }
}
