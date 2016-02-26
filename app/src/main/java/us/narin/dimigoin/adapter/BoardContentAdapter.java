package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import us.narin.dimigoin.R;
import us.narin.dimigoin.model.Content;
import us.narin.dimigoin.model.viewholder.ContentViewHolder;
import us.narin.dimigoin.util.BoardIds;

import java.util.ArrayList;
import java.util.List;

public class BoardContentAdapter extends RecyclerView.Adapter<ContentViewHolder> {

    List<Content> contentList = new ArrayList<>();
    BoardIds  boardIds;
    Context mContext;



    public BoardContentAdapter(Context mContext, BoardIds boardIds, List<Content> contentList){
        this.contentList = contentList;
        this.boardIds = boardIds;
        this.mContext = mContext;

    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bbs_content, parent, false);
        return new ContentViewHolder(mView, mContext, boardIds);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        final Content content = contentList.get(position);
        holder.bindValue(content);

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}
