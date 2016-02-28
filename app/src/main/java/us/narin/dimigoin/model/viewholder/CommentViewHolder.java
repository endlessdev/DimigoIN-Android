package us.narin.dimigoin.model.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import us.narin.dimigoin.R;
import us.narin.dimigoin.model.pojo.Comment;

/**
 * Created by Seungwoo on 2016. 2. 21..
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.bbs_comment_profile)
    Button commentProfile;
    @Bind(R.id.bbs_comment_author)
    TextView commentAuthor;
    @Bind(R.id.bbs_comment_time)
    TextView commentTime;
    @Bind(R.id.bbs_comment_content)
    TextView commentContent;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void bindValue(Comment comment){
        commentProfile.setText(String.valueOf(comment.getCommentAuthor().charAt(0)));
        commentContent.setText(comment.getCommentContent());
        commentTime.setText(comment.getPostTime());
        commentAuthor.setText(comment.getCommentAuthor());
    }


}
