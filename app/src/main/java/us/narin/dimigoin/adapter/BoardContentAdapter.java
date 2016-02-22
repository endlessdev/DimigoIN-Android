package us.narin.dimigoin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.ContentActivity;
import us.narin.dimigoin.model.Content;
import us.narin.dimigoin.model.viewholder.ContentViewHolder;
import us.narin.dimigoin.util.BoardIds;
import us.narin.dimigoin.util.TimeStamp;

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
        return new ContentViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        final Content content = contentList.get(position);
        holder.bbsSubject.setText(content.getContentSubject());
        holder.bbsAuthor.setText(content.getContentAuthor());
        holder.bbsViewCount.setText("조회수 "+String.valueOf(content.getContentViewCount())+"건");
        holder.bbsTime.setText(new TimeStamp(mContext).getTimes(content.getUnixDate()));
        holder.bbsGoodCount.setText("좋아요 "+String.valueOf(content.getContentGoodCount())+"개");
        holder.bbsProfile.setText(String.valueOf(content.getContentAuthor().charAt(0)));
        holder.bbsCommentCount.setText("댓글 " + String.valueOf(content.getContentCommentCount()) + "건");

        Document doc = Jsoup.parse(content.getContentBody());
        holder.bbsContent.setText(doc.text());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contentDetail = new Intent(mContext, ContentActivity.class);
                contentDetail.putExtra("content_id", content.getContentId());
                contentDetail.putExtra("content_subject" , content.getContentSubject());
                contentDetail.putExtra("content_board" , boardIds.toString());
                mContext.startActivity(contentDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}
