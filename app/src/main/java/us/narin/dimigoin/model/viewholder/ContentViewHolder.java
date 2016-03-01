package us.narin.dimigoin.model.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import us.narin.dimigoin.R;
import us.narin.dimigoin.activities.ContentActivity;
import us.narin.dimigoin.model.pojo.Content;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.TimeStamp;

public class ContentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.bbs_content_subject)
    TextView bbsSubject;
    @Bind(R.id.bbs_content_author)
    TextView bbsAuthor;
    @Bind(R.id.bbs_content_date)
    TextView bbsTime;
    @Bind(R.id.bbs_content_view_count)
    TextView bbsViewCount;
    @Bind(R.id.bbs_content_good_count)
    TextView bbsGoodCount;
    @Bind(R.id.bbs_content_comment_count)
    TextView bbsCommentCount;
    @Bind(R.id.bbs_content_profile)
    Button bbsProfile;
    @Bind(R.id.bbs_content_content)
    TextView bbsContent;
    Context mContext;
    Schema.BoardIds boardIds;

    @BindString(R.string.bbs_comment)
    String bbsComment;
    @BindString(R.string.bbs_like)
    String bbsLike;
    @BindString(R.string.bbs_view)
    String bbsView;
    @BindString(R.string.bbs_unit)
    String bbsUnit;


    public ContentViewHolder(View itemView, Context mContext, Schema.BoardIds boardIds) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = mContext;
        this.boardIds = boardIds;
    }

    public void bindValue(Content content){
        bbsSubject.setText(content.getContentSubject());
        bbsAuthor.setText(content.getContentAuthor());
        bbsViewCount.setText(bbsView+String.valueOf(content.getContentViewCount())+bbsUnit);
        bbsTime.setText(new TimeStamp(mContext).getTimes(content.getUnixDate()));
        bbsGoodCount.setText(bbsLike+String.valueOf(content.getContentGoodCount())+bbsUnit);
        bbsProfile.setText(String.valueOf(content.getContentAuthor().charAt(0)));
        bbsCommentCount.setText(bbsComment + String.valueOf(content.getContentCommentCount()) + bbsUnit);

        bbsContent.setText(br2nl(content.getContentBody()).trim().replaceAll("&nbsp;",""));

        itemView.setOnClickListener(v -> {
            Intent contentDetail = new Intent(mContext, ContentActivity.class);
            contentDetail.putExtra("content_id", content.getContentId());
            contentDetail.putExtra("content_subject" , content.getContentSubject());
            contentDetail.putExtra("content_board" , boardIds.toString());
            mContext.startActivity(contentDetail);
        });
    }

    public static String br2nl(String html) {
        if(html==null)
            return html;
        Document document = Jsoup.parse(html);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
        document.select("br").append("\\n");
        document.select("p").prepend("\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }
}
