package us.narin.dimigoin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import us.narin.dimigoin.R;
import us.narin.dimigoin.model.pojo.Comment;
import us.narin.dimigoin.model.viewholder.CommentViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seungwoo on 2016. 2. 21..
 */
public class BoardCommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {


    List<Comment> commentList = new ArrayList<>();
    Context mContext;

    public BoardCommentAdapter(Context mContext, List<Comment> contentModelList){
        this.mContext = mContext;
        this.commentList = contentModelList;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView  = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bbs_comment, parent, false);
        return new CommentViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        final Comment comment = commentList.get(position);
        holder.bindValue(comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
