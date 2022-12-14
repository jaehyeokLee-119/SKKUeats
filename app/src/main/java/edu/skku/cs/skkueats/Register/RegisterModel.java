package edu.skku.cs.skkueats.Register;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import edu.skku.cs.skkueats.ApplicationGlobal;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterModel implements RegisterContract.contactModel {
    private RegisterContract.contactView view;

    private Boolean registerSuccess = false;
    private String verifyCode;
    private ApplicationGlobal applicationGlobal;
    private String serverUrl;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RegisterModel(RegisterContract.contactView view, ApplicationGlobal applicationGlobal) {
        this.view = view;
        this.applicationGlobal = applicationGlobal;
        this.serverUrl = applicationGlobal.getServerURL();
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String res = response.body().string();

            JSONObject json = null;
            try {
                json = new JSONObject(res);
                registerSuccess = json.getBoolean("registerSuccess");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(registerSuccess){
                view.signupSuccess();
            }else{
                view.signupFail();
            }

        }
    };

    @Override
    public boolean sendEmail(String email){
        String skkuEmail = email + "@g.skku.edu";
        // add sendEmail logic
        return true;
    }

    @Override
    public boolean verifyCode(String code){
        //add verifyCode logic
        return true;
    }


    @Override
    public void signup(String id, String pw, String pw2, String email) throws JSONException {
        //add signup logic

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("id", id)
            .put("password", pw)
            .put("passwordCheck", pw2)
            .put("email", email)
            .put("emailCheck", "true");

        RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .url(serverUrl + "users/new-user")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);


    }



}
