package us.narin.dimigoin.fragments.element;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.adapter.BoardContentAdapter;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.pojo.BoardList;
import us.narin.dimigoin.model.pojo.Content;
import us.narin.dimigoin.util.EndlessScrollListener;
import us.narin.dimigoin.util.Schema;
import us.narin.dimigoin.util.Session;

import java.util.ArrayList;
import java.util.List;


public class BoardFragment extends Fragment {

    private Schema.BoardIds boardIds;
    private Integer currentPage = 1;
    private BoardContentAdapter itemAdapter;
    private List<Content> boardList;
    private SwipeRefreshLayout refreshBoard;

    public BoardFragment setBoardIds(Schema.BoardIds boardIds) {
        this.boardIds = boardIds;

        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_community, container, false);

        final RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.bbs_contents_list);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        boardList = getBoardList(currentPage);
        itemAdapter = new BoardContentAdapter(getActivity(), boardIds, boardList);

        mRecyclerView.setAdapter(itemAdapter);
        Log.d("BoardFragment " + boardIds.toString(), "onCreateView()");

        refreshBoard = (SwipeRefreshLayout) mView.findViewById(R.id.board_refresh);
        refreshBoard.setOnRefreshListener(() -> {
            mRecyclerView.setAdapter(itemAdapter);
            getBoardList(currentPage);
            Log.d("BoardFragment " + boardIds.toString(), "onRefresh()");
        });
        refreshBoard.setColorSchemeResources(R.color.colorAccent);


        mRecyclerView.addOnScrollListener(new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {

                if (getBoardList(current_page).size() <= 0) {
                    return;
                }

                boardList.addAll(getBoardList(current_page));
                Log.d("BoardFragment " + boardIds.toString(), "onLoadMore() : " + current_page);
                itemAdapter.notifyDataSetChanged();
            }
        });

        return mView;
    }

    private List<Content> getBoardList(final int boardPage) {

        final List<Content> retModel = new ArrayList<>();

        ApiRequests apiRequests = ApiObject.initClient(Schema.API_ENDPOINT);
        Call<BoardList> callBBSList = apiRequests.getBoardList(boardIds.toString(), boardPage, Session.getUserToken(getActivity()));
        callBBSList.enqueue(new Callback<BoardList>() {
            @Override
            public void onResponse(Response<BoardList> response) {
                if (response.code() == 200) {

                    refreshBoard.setRefreshing(false);
                    List<Content> tmpModel = response.body().getData().getContentList();
                    if (boardPage != 1)
                        boardList.addAll(tmpModel);
                    retModel.addAll(tmpModel);
                    itemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("BoardFragment " + boardIds.toString(), "onFailure() : " + t.getMessage());
                Toast.makeText(getContext(), "onFailure() : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return retModel;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
