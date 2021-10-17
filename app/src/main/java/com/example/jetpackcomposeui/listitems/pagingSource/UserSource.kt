package com.example.jetpackcomposeui.listitems.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackcomposeui.listitems.model.User
import com.example.jetpackcomposeui.listitems.network.RetrofitClient
import retrofit2.HttpException
import java.io.IOException

class UserSource : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val nextPage = params.key ?: 1
            val userList = RetrofitClient.apiService.getUserList(page = nextPage)
            LoadResult.Page(
                data = userList.data,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.data.isEmpty()) null else userList.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}