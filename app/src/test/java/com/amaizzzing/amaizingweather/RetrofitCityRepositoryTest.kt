package com.amaizzzing.amaizingweather

import com.amaizzzing.amaizingweather.mvp.models.api.IGeonamesDataSource
import com.amaizzzing.amaizingweather.mvp.models.entity.list.model_factory.ICityModelFactory
import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import com.amaizzzing.amaizingweather.mvp.models.network.INetworkStatus
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.RetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.models.repositories.room.ICityCache
import okhttp3.Request
import okio.Timeout
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCityRepositoryTest {
    private lateinit var retrofitCityRepository: IRetrofitCityRepository

    @Mock
    private lateinit var api: IGeonamesDataSource

    @Mock
    private lateinit var networkStatus: INetworkStatus

    @Mock
    private lateinit var cache: ICityCache

    @Mock
    private lateinit var cityModelFactory: ICityModelFactory


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        retrofitCityRepository =
            RetrofitCityRepository(
                api,
                networkStatus,
                cache,
                cityModelFactory
            )
    }

    @Test
    fun searchCity_Test() {
        val queryString = ""
        val call = mock(Call::class.java) as Call<List<GeonameCity>?>

        `when`(
            api.getCitiesByName_tempForTests(
                nameCity = queryString,
                format = Constants.CITY_API_FORMAT,
                limit = Constants.CITY_API_LIMIT,
                addressDetails = Constants.CITY_API_DETAILS
            )
        ).thenReturn(call)

        retrofitCityRepository.getCityByName_tempForTests(queryString, mock(RetrofitCityRepository.CityRepositoryCallback::class.java))

        verify(api, times(1)).getCitiesByName_tempForTests(
            nameCity = queryString,
            format = Constants.CITY_API_FORMAT,
            limit = Constants.CITY_API_LIMIT,
            addressDetails = Constants.CITY_API_DETAILS
        )
    }

    @Test
    fun searchCity_TestCallback() {
        val queryString = ""
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>
        val searchCityRepositoryCallback = mock(RetrofitCityRepository.CityRepositoryCallback::class.java)

        val call = object : Call<List<GeonameCity>?> {
            override fun clone(): Call<List<GeonameCity>?> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<List<GeonameCity>?> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<List<GeonameCity>?>) {
                callback.onResponse(this, response)
                callback.onFailure(this, Throwable())
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }
        }

        `when`(
            api.getCitiesByName_tempForTests(
                nameCity = queryString,
                format = Constants.CITY_API_FORMAT,
                limit = Constants.CITY_API_LIMIT,
                addressDetails = Constants.CITY_API_DETAILS
            )
        ).thenReturn(call)
        retrofitCityRepository.getCityByName_tempForTests(queryString, searchCityRepositoryCallback)

        verify(searchCityRepositoryCallback, times(1)).handleCityResponse(response)
        verify(searchCityRepositoryCallback, times(1)).handleCityError()
    }

    @Test
    fun searchCity_TestCallback_withMock() {
        val queryString = "cityname"
        val call = mock(Call::class.java) as Call<List<GeonameCity>?>
        val callBack = mock(Callback::class.java) as Callback<List<GeonameCity>?>
        val searchCityRepositoryCallBack = mock(RetrofitCityRepository.CityRepositoryCallback::class.java)
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(
            api.getCitiesByName_tempForTests(
                nameCity = queryString,
                format = Constants.CITY_API_FORMAT,
                limit = Constants.CITY_API_LIMIT,
                addressDetails = Constants.CITY_API_DETAILS
            )
        ).then {
            call.enqueue(callBack)
            return@then null
        }

        `when`(call.enqueue(callBack)).then {
            callBack.onResponse(any(), any())
        }

        `when`(callBack.onResponse(any(), any())).then {
            searchCityRepositoryCallBack.handleCityResponse(response)
        }

        retrofitCityRepository.getCityByName_tempForTests(queryString, searchCityRepositoryCallBack)

        verify(searchCityRepositoryCallBack, times(1)).handleCityResponse(response)
    }
}