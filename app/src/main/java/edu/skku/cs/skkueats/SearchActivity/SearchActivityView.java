package edu.skku.cs.skkueats.SearchActivity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.skku.cs.skkueats.R;


public class SearchActivityView extends AppCompatActivity implements SearchActivityContract.contactView {
    private ArrayList<SearchResult> searchResultArray = new ArrayList<>();
    private ListView searchResultList;
    private SearchActivityAdapter searchActivityAdapter;
    private Bundle savedInstanceState;
    private SearchActivityModel model;

    /*
    검색 버튼을 누르면 버튼 아래 ListView에 검색결과를 search_result_item로 나열해서 보여줌
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.savedInstanceState = savedInstanceState;

        /*
        View 생성시:
            0. Activity에 있는 View를 초기화해줌
            1. 버튼을 누르면 model이 검색어, 검색조건(가게이름, 메뉴이름)을 바탕으로 쿼리를 보내서 결과를 받아옴
            2. 모델은 결과를 받으면 View에게 showRecommend로 결과를 표시하게 함
         */

        initView();
        model = new SearchActivityModel(this);
    }

    @Override
    public void initView() {
        searchResultList = findViewById(R.id.listViewSearchResults);

    }


    @Override
    public void showRecommend(SearchResult searchResult) {
        /*
        받은 SearchResult를 바탕으로 ListView에 Search Result를 추가하여 화면에 표시한다
         */
        searchResultArray.add(new SearchResult(searchResult));
//        restaurantReviewAdapter = new MenuRecommendsAdapter(getApplicationContext(), recommendsArray);
        searchActivityAdapter = new SearchActivityAdapter(this, searchResultArray);
        searchResultList.setAdapter(searchActivityAdapter);
    }
}