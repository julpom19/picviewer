package codewizards.com.ua.picviewer.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import codewizards.com.ua.picviewer.Const;
import codewizards.com.ua.picviewer.R;
import codewizards.com.ua.picviewer.model.DataContainer;
import codewizards.com.ua.picviewer.model.Good;

/**
 * Created by Интернет on 21.12.2016.
 */

public class PicActivity extends AppCompatActivity{
    ImageView ivPic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ivPic = (ImageView) findViewById(R.id.iv_pic_full);
        int posOfGood = getIntent().getIntExtra(Const.EXTRA_POS_OF_GOOD, -1);
        if(posOfGood != -1) {
            showPic(posOfGood);
        }

    }

    private void showPic(int pos) {
        Good good = DataContainer.getInstance().getListOfGoods(PicActivity.this).get(pos);
        Glide.with(PicActivity.this).load(good.getUrl()).placeholder(R.mipmap.ic_placeholder).
                error(R.mipmap.ic_error_placeholder).into(ivPic);
    }
}
