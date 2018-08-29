package api;

import org.json.JSONObject;

import nvd.hasan.imagecrop.Example;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("api")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image,
                                 @Part("lang") String lang,
                                 @Part("leftX") float leftX,
                                 @Part("leftY") float topY,
                                 @Part("rightX") float rightX,
                                 @Part("rightY") float bottomY);
//    Call<ResponseBody> postImage(@Part MultipartBody.Part image);

    @GET("/")
    Call<ResponseBody> getToken();
}
