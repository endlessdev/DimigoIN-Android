package us.narin.dimigoin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import us.narin.dimigoin.R;
import us.narin.dimigoin.model.ContentModel;

/**
 * Created by Seungwoo on 2016. 1. 5..
 */
public class CommunityItemAdapter extends ArrayAdapter<ContentModel> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<ContentModel> contentList;

    public CommunityItemAdapter(Context mContext, int resourceId,List<ContentModel> contentList) {
        super(mContext, resourceId, contentList);
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.contentList = contentList;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public ContentModel getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View mView = mInflater.inflate(R.layout.row_bbs_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(mView);

        ContentModel tmpModel = getItem(position);

        viewHolder.bbsAuthor.setText(tmpModel.getContentAuthor());
        viewHolder.bbsGoodCount.setText(String.valueOf(tmpModel.getContentGoodCount()));
        viewHolder.bbsTime.setText(tmpModel.getContentDate());
        viewHolder.bbsViewCount.setText(String.valueOf(tmpModel.getContentViewCount()));
        viewHolder.bbsSubject.setText(tmpModel.getContentSubject());

        return mView;
    }

    class ViewHolder {
        public TextView bbsSubject;
        public TextView bbsAuthor;
        public TextView bbsTime;
        public TextView bbsViewCount;
        public TextView bbsGoodCount;

        public ViewHolder(View itemView) {

            bbsSubject = (TextView) itemView.findViewById(R.id.bbs_content_subject);
            bbsAuthor = (TextView) itemView.findViewById(R.id.bbs_content_author);
            bbsViewCount = (TextView) itemView.findViewById(R.id.bbs_content_view_count);
            bbsTime = (TextView) itemView.findViewById(R.id.bbs_content_date);
            bbsGoodCount = (TextView) itemView.findViewById(R.id.bbs_content_good_count);

        }
    }

}
