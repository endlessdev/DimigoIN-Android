package us.narin.dimigoin.model.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import us.narin.dimigoin.R;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    public TextView bbsSubject;
    public TextView bbsAuthor;
    public TextView bbsTime;
    public TextView bbsViewCount;
    public TextView bbsGoodCount;

    public ContentViewHolder(View itemView) {
        super(itemView);

        bbsSubject = (TextView)itemView.findViewById(R.id.bbs_content_subject);
        bbsAuthor = (TextView)itemView.findViewById(R.id.bbs_content_author);
        bbsViewCount = (TextView)itemView.findViewById(R.id.bbs_content_view_count);
        bbsTime = (TextView)itemView.findViewById(R.id.bbs_content_date);
        bbsGoodCount = (TextView)itemView.findViewById(R.id.bbs_content_good_count);

    }
}
