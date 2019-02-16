package com.project.githubusers.uicomponent;

import com.project.githubusers.model.UserModel;
import com.project.githubusers.rest.ApiInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import retrofit2.Callback;

public class MainPresenterTest {
    @Mock
    ApiInterface apiInterface = Mockito.mock(ApiInterface.class);
    @Mock
    MainContract.View mockedView = Mockito.mock(MainContract.View.class);

    @Captor
    ArgumentCaptor<Callback<UserModel>> captor;

    private MainPresenter mainPresenter = new MainPresenter(mockedView);

    @Test
    public void shoudlShowLoadingWhenQuery() {
        mainPresenter.searchUserName("",0);
        Mockito.verify(mockedView).showLoading(true);
    }
}
