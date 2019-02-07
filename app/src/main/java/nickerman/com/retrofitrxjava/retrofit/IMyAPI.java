package nickerman.com.retrofitrxjava.retrofit;

import java.util.List;

import io.reactivex.Observable;
import nickerman.com.retrofitrxjava.model.User;
import retrofit2.http.GET;

public interface IMyAPI {
    @GET("posts")
    Observable<List<User>> getPosts();
}
