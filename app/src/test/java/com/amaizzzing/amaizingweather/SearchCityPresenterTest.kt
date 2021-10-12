package com.amaizzzing.amaizingweather

import com.amaizzzing.amaizingweather.mvp.models.entity.retrofit.city.GeonameCity
import com.amaizzzing.amaizingweather.mvp.models.repositories.retrofit.IRetrofitCityRepository
import com.amaizzzing.amaizingweather.mvp.presenter.SearchCityPresenter
import com.amaizzzing.amaizingweather.mvp.view.`SearchCityView$$State`
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class SearchCityPresenterTest {
    private lateinit var presenter: SearchCityPresenter

    @Mock
    private lateinit var retrofitCityRepository: IRetrofitCityRepository

    @Mock
    private lateinit var searchCityViewState: `SearchCityView$$State`

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        presenter = SearchCityPresenter()

        presenter.setViewState(searchCityViewState)

        presenter.repo = retrofitCityRepository
    }

    @Test
    fun searchCity_Test() {
        val queryString = ""

        presenter.makeSearch_tempForTests(queryString)

        verify(presenter.repo, times(1))
            .getCityByName_tempForTests(
                queryString,
                presenter
            )
    }

    @Test
    fun handleSearchCityError_Test() {
        presenter.handleCityError()

        verify(searchCityViewState, times(1)).notResult()
    }

    @Test
    fun handleSearchCityResponse_ResponseUnsuccessful() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(response.isSuccessful).thenReturn(false)

        assertFalse(response.isSuccessful)
    }

    @Test
    fun handleSearchCityResponse_Failure() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(response.isSuccessful).thenReturn(false)

        presenter.handleCityResponse(response)

        verify(searchCityViewState, times(1)).notResult()
    }

    @Test
    fun handleSearchCityResponse_ResponseFailure_MethodOrder() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(response.isSuccessful).thenReturn(false)

        presenter.handleCityError()

        val inOrder = inOrder(searchCityViewState)

        with(inOrder.verify(searchCityViewState)) {
            endLoad()
            notResult()
        }
    }

    @Test
    fun handleSearchCityResponse_ResponseIsEmpty() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(response.body()).thenReturn(null)

        presenter.handleCityResponse(response)

        assertNull(response.body())
    }

    @Test
    fun handleSearchCityResponse_ResponseIsNotEmpty() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        val iterator = mock(Iterator::class.java)
        `when`(iterator.hasNext()).thenReturn(true, false)
        `when`(iterator.next()).thenReturn(mock(GeonameCity::class.java))
        val mockList = mock(List::class.java)
        `when`(mockList.iterator()).thenReturn(iterator)

        `when`(response.body()).thenReturn(mockList as List<GeonameCity>)

        presenter.handleCityResponse(response)

        assertNotNull(response.body())
    }

    @Test
    fun handleSearchCityResponse_EmptyResponse() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>

        `when`(response.isSuccessful).thenReturn(true)
        `when`(response.body()).thenReturn(null)

        presenter.handleCityResponse(response)

        verify(searchCityViewState, times(1)).notResult()
    }

    @Test
    fun handleSearchCityResponse_CorrectlyUpdateList() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>
        val searchResults = listOf(mock(GeonameCity::class.java))

        `when`(response.isSuccessful).thenReturn(true)
        `when`(response.body()).thenReturn(searchResults)

        presenter.handleCityResponse(response)

        assertEquals(searchResults.size, presenter.searchCityListPresenter.cityList.size)
    }

    @Test
    fun handleSearchCityResponse_Success() {
        val response = mock(Response::class.java) as Response<List<GeonameCity>?>
        val searchResults = listOf(mock(GeonameCity::class.java))

        `when`(response.isSuccessful).thenReturn(true)
        `when`(response.body()).thenReturn(searchResults)

        presenter.handleCityResponse(response)

        verify(searchCityViewState, times(1)).updateList()
        verify(searchCityViewState, times(1)).endLoad()
    }
}