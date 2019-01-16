package link.mgiannone.musixmatchapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumResponse {

	@SerializedName("message")
	@Expose
	private Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static class Message {

		@SerializedName("header")
		@Expose
		private Header header;
		@SerializedName("body")
		@Expose
		private Body body;

		public Header getHeader() {
			return header;
		}

		public void setHeader(Header header) {
			this.header = header;
		}

		public Body getBody() {
			return body;
		}

		public void setBody(Body body) {
			this.body = body;
		}

	}

	public static class Header {

		@SerializedName("status_code")
		@Expose
		private int statusCode;
		@SerializedName("execute_time")
		@Expose
		private float executeTime;

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public float getExecuteTime() {
			return executeTime;
		}

		public void setExecuteTime(float executeTime) {
			this.executeTime = executeTime;
		}

	}

	public static class Body {

		@SerializedName("album")
		@Expose
		private Album album;

		public Album getAlbum() {
			return album;
		}

		public void setAlbum(Album album) {
			this.album = album;
		}

	}

	public static class Album {

		@SerializedName("album_id")
		@Expose
		private int albumId;

		@SerializedName("album_name")
		@Expose
		private String albumName;

		@SerializedName("album_rating")
		@Expose
		private int albumRating;

		@SerializedName("album_release_date")
		@Expose
		private String albumReleaseDate;

		@SerializedName("artist_id")
		@Expose
		private int artistId;

		@SerializedName("artist_name")
		@Expose
		private String artistName;

		@SerializedName("restricted")
		@Expose
		private int restricted;

		@SerializedName("updated_time")
		@Expose
		private String updatedTime;

		@SerializedName("album_coverart_100x100")
		@Expose
		private String albumCoverart100x100;

		@SerializedName("album_coverart_350x350")
		@Expose
		private String albumCoverart350x350;

		@SerializedName("album_coverart_500x500")
		@Expose
		private String albumCoverart500x500;

		@SerializedName("album_coverart_800x800")
		@Expose
		private String albumCoverart800x800;

		public int getAlbumId() {
			return albumId;
		}

		public void setAlbumId(int albumId) {
			this.albumId = albumId;
		}

		public String getAlbumName() {
			return albumName;
		}

		public void setAlbumName(String albumName) {
			this.albumName = albumName;
		}

		public int getAlbumRating() {
			return albumRating;
		}

		public void setAlbumRating(int albumRating) {
			this.albumRating = albumRating;
		}

		public String getAlbumReleaseDate() {
			return albumReleaseDate;
		}

		public void setAlbumReleaseDate(String albumReleaseDate) {
			this.albumReleaseDate = albumReleaseDate;
		}

		public int getArtistId() {
			return artistId;
		}

		public void setArtistId(int artistId) {
			this.artistId = artistId;
		}

		public String getArtistName() {
			return artistName;
		}

		public void setArtistName(String artistName) {
			this.artistName = artistName;
		}

		public int getRestricted() {
			return restricted;
		}

		public void setRestricted(int restricted) {
			this.restricted = restricted;
		}

		public String getUpdatedTime() {
			return updatedTime;
		}

		public void setUpdatedTime(String updatedTime) {
			this.updatedTime = updatedTime;
		}

		public String getAlbumCoverart100x100() {
			return albumCoverart100x100;
		}

		public void setAlbumCoverart100x100(String albumCoverart100x100) {
			this.albumCoverart100x100 = albumCoverart100x100;
		}

		public String getAlbumCoverart350x350() {
			return albumCoverart350x350;
		}

		public void setAlbumCoverart350x350(String albumCoverart350x350) {
			this.albumCoverart350x350 = albumCoverart350x350;
		}

		public String getAlbumCoverart500x500() {
			return albumCoverart500x500;
		}

		public void setAlbumCoverart500x500(String albumCoverart500x500) {
			this.albumCoverart500x500 = albumCoverart500x500;
		}

		public String getAlbumCoverart800x800() {
			return albumCoverart800x800;
		}

		public void setAlbumCoverart800x800(String albumCoverart800x800) {
			this.albumCoverart800x800 = albumCoverart800x800;
		}

	}

}





