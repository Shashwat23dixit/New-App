import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.newsapp.model.Articles
import com.example.newsapp.model.NewsResponse
import com.example.newsapp.repository.service.RetrofitClient
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
class ArticleDataSource(val scope: CoroutineScope) : PageKeyedDataSource<Int, Articles>(){
    val breakingNews : MutableLiveData<MutableList<Articles>> = MutableLiveData()
    var brekingPageNumebr = 1
    var breakingNewsResponse : NewsResponse? = null
    val searchNews : MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNumber = 1
    var searchNewsResponse : NewsResponse? = null
    //var articles : MutableLiveData<List<Article>> = MutableLiveData()
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Articles>
    ) {
        scope.launch {
            try {
                val response = RetrofitClient.api.getBreakingNews("in", 1, Constants.API_KEY)
                when {
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            breakingNews.postValue(it)
                            callback.onResult(it, null, 2)
                        }
                    }
                }
            }catch (exception : Exception) {
                Log.e("DataSource :: ", exception.message.toString())
            }
        }
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        try {
            scope.launch {
                val response = RetrofitClient.api.getBreakingNews("in", params.requestedLoadSize, Constants.API_KEY)
                when{
                    response.isSuccessful -> {
                        response.body()?.articles?.let {
                            callback.onResult(it, params.key+1)
                        }
                    }
                }
            }
        } catch (exception : Exception) {
            Log.e("DataSource :: ", exception.message.toString())
        }
    }
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        TODO("Not yet implemented")
    }
}