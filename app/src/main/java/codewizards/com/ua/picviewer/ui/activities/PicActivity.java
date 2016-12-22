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
    ImageView ivPic;
    private int posOfGood = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        DataContainer.getInstance().registerObserver(this);
        ivPic = (ImageView) findViewById(R.id.iv_pic_full);
        posOfGood = getIntent().getIntExtra(Const.EXTRA_POS_OF_GOOD, -1);
        if(posOfGood != -1) {
            showPic();
        }
    }

    private void showPic() {
        List<Good> list = DataContainer.getInstance().getListOfGoods();
        if(list != null) {
            Good good = list.get(posOfGood);
            Glide.with(PicActivity.this).load(good.getUrl()).placeholder(R.mipmap.ic_placeholder).
                    error(R.mipmap.ic_error_placeholder).into(ivPic);
        }
    }

    @Override
    public void update() {
        List<Good> list = DataContainer.getInstance().getListOfGoods();
        if(posOfGood != -1) {
            showPic();
        }
    }
}
