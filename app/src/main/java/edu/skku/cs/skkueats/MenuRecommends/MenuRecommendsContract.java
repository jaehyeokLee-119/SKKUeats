package edu.skku.cs.skkueats.MenuRecommends;

import com.naver.maps.geometry.LatLng;

import java.util.ArrayList;

public interface MenuRecommendsContract {
    interface contactView {
        void showRecommend(MenuRecommends menuRecommends);
        void initView();
    }

    interface contactModel {
        void fetchRecommends();
        void pushRecommendsToViewer(ArrayList<MenuRecommends> reviewArrays);
    }
}
