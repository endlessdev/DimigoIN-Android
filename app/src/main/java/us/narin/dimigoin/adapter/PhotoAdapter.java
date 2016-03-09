package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import us.narin.dimigoin.R;

import java.util.List;

/**
 * Created by Seungwoo on 2016. 3. 5..
 */
public class PhotoAdapter extends PagerAdapter {

    List<String> photoList;
    Context mContext;
    LayoutInflater layoutInflater;

    public PhotoAdapter(List<String> photoList, Context mContext){
        this.photoList = photoList;
        this.mContext = mContext;
        this.layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View mView = layoutInflater.inflate(R.layout.row_bbs_media_photo,container, false);
        ImageView photoView = (ImageView)mView.findViewById(R.id.bbs_content_media_photo);

        Glide.with(mContext).load(photoList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher).centerCrop().into(photoView);

        container.addView(mView);
        return mView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView)object);
    }

}
