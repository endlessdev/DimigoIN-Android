package us.narin.dimigoin.model.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import us.narin.dimigoin.R;

/**
 * Created by Seungwoo on 2016. 2. 21..
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {

    public Button commentProfile;
    public TextView commentAuthor;
    public TextView commentTime;
    public TextView commentContent;

    public CommentViewHolder(View itemView) {
        super(itemView);

        commentProfile = (Button)itemView.findViewById(R.id.bbs_comment_profile);
        commentAuthor = (TextView)itemView.findViewById(R.id.bbs_comment_author);
        commentTime = (TextView) itemView.findViewById(R.id.bbs_comment_time);
        commentContent = (TextView)itemView.findViewById(R.id.bbs_comment_content);

    }


}
