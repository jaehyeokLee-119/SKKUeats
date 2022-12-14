package edu.skku.cs.skkueats.WriteReview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import edu.skku.cs.skkueats.R;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityModel;
import edu.skku.cs.skkueats.SearchActivity.SearchActivityView;

public class WriteReviewView extends AppCompatActivity implements WriteReviewContract.contactView {
    private Bundle savedInstanceState;
    private TextView textReviewRestaurantName;
    private String id;
    private String restaurantName;
    private Spinner spinner;
    private WriteReviewModel model;
    private RatingBar ratingBar;
    private Button reviewUploadButton;
    private EditText editTextReviewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);
        initView();


        restaurantName = getIntent().getStringExtra("RestaurantName");
        id = getIntent().getStringExtra("id");
        textReviewRestaurantName.setText(restaurantName);

        model = new WriteReviewModel(this, restaurantName);

        reviewUploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String menuName;
                menuName = spinner.getSelectedItem().toString();
                Integer grade;
                grade = (int) ratingBar.getRating();
                String reviewContent;
                reviewContent = editTextReviewContent.getText().toString();

                MenuReview menuReview = new MenuReview(restaurantName, menuName, grade, reviewContent);
                try {
                    model.reviewUpload(menuReview, id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void reviewComplete(String strToShow) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), strToShow, Toast.LENGTH_SHORT).show();
                Intent resultIntent = new Intent();
                setResult(100, resultIntent);
                finish();
            }
        });

    }

    @Override
    public void initView(){
        textReviewRestaurantName = findViewById(R.id.textReviewRestaurantName);
        spinner = findViewById(R.id.spinner);
        reviewUploadButton = findViewById(R.id.reviewUploadButton);
        ratingBar = findViewById(R.id.ratingBar);
        editTextReviewContent = findViewById(R.id.editTextReviewContent);

    }

    @Override
    public void setSpinnerMenus(String[] menuList) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.spinner_menu_item,
                menuList);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                spinner.setAdapter(adapter);
                spinner.setSelection(0);
            }
        });

    }
}
