package us.narin.dimigoin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.ContentActivity;
import us.narin.dimigoin.model.ContentModel;
import us.narin.dimigoin.model.viewholder.ContentViewHolder;
import us.narin.dimigoin.util.BoardIds;
import us.narin.dimigoin.util.TimeStampManager;

public class CommunityContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

    List<ContentModel> contentModelList = new ArrayList<>();
    BoardIds  boardIds;
    Context mContext;

    public CommunityContentAdapter(Context mContext,BoardIds boardIds, List<ContentModel> contentModelList){
        this.contentModelList = contentModelList;
        this.boardIds = boardIds;
        this.mContext = mContext;

    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bbs_content, parent, false);
        return new ContentViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        final ContentModel contentModel = contentModelList.get(position);
        holder.bbsSubject.setText(contentModel.getContentSubject());
        holder.bbsAuthor.setText(contentModel.getContentAuthor());
        holder.bbsViewCount.setText(String.valueOf(contentModel.getContentViewCount()));
        holder.bbsTime.setText(new TimeStampManager(mContext).getTimes(contentModel.getUnixDate()));
        holder.bbsGoodCount.setText(String.valueOf(contentModel.getContentGoodCount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contentDetail = new Intent(mContext, ContentActivity.class);
                contentDetail.putExtra("content_id", contentModel.getContentId());
                contentDetail.putExtra("content_subject" ,contentModel.getContentSubject());
                contentDetail.putExtra("content_body",contentModel.getContentBody());
                contentDetail.putExtra("content_board" , boardIds.toString());
                contentDetail.putExtra("content_author", contentModel.getContentAuthor());
                mContext.startActivity(contentDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentModelList.size();
    }
}
