package us.narin.dimigoin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import us.narin.dimigoin.R;
import us.narin.dimigoin.adapter.CommunityContentAdapter;
import us.narin.dimigoin.api.ApiObject;
import us.narin.dimigoin.api.ApiRequests;
import us.narin.dimigoin.model.BoardListModel;
import us.narin.dimigoin.util.BoardIds;
import us.narin.dimigoin.util.EndlessRecyclerOnScrollListener;
import us.narin.dimigoin.util.SessionManager;


public class CommunityFragment extends Fragment {

    private BoardIds boardIds;
    private Integer currentPage = 1;
    private CommunityContentAdapter itemAdapter;
    private SwipeRefreshLayout refreshBoard;

    public CommunityFragment(BoardIds boardIds) {
        this.boardIds = boardIds;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_community, container, false);

        final RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.bbs_contents_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        getBoardList(mRecyclerView, currentPage);

        refreshBoard = (SwipeRefreshLayout)mView.findViewById(R.id.board_refresh);
        refreshBoard.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBoardList(mRecyclerView, currentPage);
            }
        });


        mRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Toast.makeText(getActivity(), String.valueOf(current_page), Toast.LENGTH_LONG).show();
            }
        });

        return mView;
    }

    public void getBoardList(final RecyclerView boardList, int boardPage){
        ApiRequests apiRequests = ApiObject.initClient();
        Call<BoardListModel> callBBSList = apiRequests.getBoardList(boardIds.toString(), boardPage, SessionManager.getUserToken(getActivity()));
        callBBSList.enqueue(new Callback<BoardListModel>() {
            @Override
            public void onResponse(Response<BoardListModel> response) {
                if (response.code() == 200) {
                    itemAdapter = new CommunityContentAdapter(getActivity(), boardIds, response.body().getData().getContentList());

                    boardList.setAdapter(itemAdapter);
                    refreshBoard.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
