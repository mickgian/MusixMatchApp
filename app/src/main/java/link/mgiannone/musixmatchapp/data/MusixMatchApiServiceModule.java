package link.mgiannone.musixmatchapp.data;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import link.mgiannone.musixmatchapp.data.api.AlbumService;
import link.mgiannone.musixmatchapp.data.api.ChartService;
import link.mgiannone.musixmatchapp.data.api.HeaderInterceptor;
import link.mgiannone.musixmatchapp.data.api.LyricsService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MusixMatchApiServiceModule {
	private static final String BASE_URL = "base_url";

	@Provides
	@Named(BASE_URL)
	String provideBaseUrl() {
		return Config.MUSIX_MATCH_API_HOST;
	}

	@Provides
	@Singleton
	HeaderInterceptor provideHeaderInterceptor() {
		return new HeaderInterceptor();
	}

	@Provides
	@Singleton
	HttpLoggingInterceptor provideHttpLoggingInterceptor() {
		return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
	}

	@Provides
	@Singleton
	OkHttpClient provideHttpClient(HeaderInterceptor headerInterceptor,
								   HttpLoggingInterceptor httpInterceptor) {
		return new OkHttpClient.Builder().addInterceptor(headerInterceptor)
				.addInterceptor(httpInterceptor)
				.build();
	}

	@Provides
	@Singleton
	Converter.Factory provideGsonConverterFactory() {
		return GsonConverterFactory.create();
	}

	@Provides
	@Singleton
	CallAdapter.Factory provideRxJavaAdapterFactory() {
		return RxJava2CallAdapterFactory.create();
	}

	@Provides
	@Singleton
	Retrofit provideRetrofit(@Named(BASE_URL) String baseUrl, Converter.Factory converterFactory,
							 CallAdapter.Factory callAdapterFactory, OkHttpClient client) {
		return new Retrofit.Builder().baseUrl(baseUrl)
				.addConverterFactory(converterFactory)
				.addCallAdapterFactory(callAdapterFactory)
				.client(client)
				.build();
	}

	@Provides
	@Singleton
	ChartService provideChartService(Retrofit retrofit) {
		return retrofit.create(ChartService.class);
	}

	@Provides
	@Singleton
	AlbumService provideAlbumService(Retrofit retrofit) {
		return retrofit.create(AlbumService.class);
	}

	@Provides
	@Singleton
	LyricsService provideLyricsService(Retrofit retrofit) {
		return retrofit.create(LyricsService.class);
	}
}

