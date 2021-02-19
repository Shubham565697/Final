import com.example.medivex.Models.Users
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MedvixApi {


    @POST("/insert")


    suspend fun registerUsers(@Body user: Users):Response<LResponse>

  Response<LResponse>

}