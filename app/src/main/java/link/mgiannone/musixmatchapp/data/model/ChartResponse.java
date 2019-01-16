

package link.mgiannone.musixmatchapp.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import link.mgiannone.musixmatchapp.data.Config;


public class ChartResponse {

	@SerializedName("message")
	@Expose
	private Message message;

	public Message getMessage() {
		return message;
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

		public Body getBody() {
			return body;
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

		public float getExecuteTime() {
			return executeTime;
		}
	}

	public static class Body {

		@SerializedName("track_list")
		@Expose
		private List<TrackList> trackList = null;

		public List<TrackList> getTrackList() {
			return trackList;
		}

	}

	@Entity(tableName = Config.MUSIX_MATCH_TRACKS_TABLE_NAME)
	public static class TrackList {

		@SerializedName("track")
		@Expose
		@Embedded(prefix = "trackList_")
		@PrimaryKey
		@NonNull
		private Track track;

		public Track getTrack() {
			return track;
		}

		public void setTrack(@NonNull Track track) {
			this.track = track;
		}
	}

//	@Entity(tableName = Config.MUSIX_MATCH_TRACKS_TABLE_NAME)
	public static class Track {

		@SerializedName("track_id")
		@Expose
		@NonNull
		private int trackId;

		@SerializedName("track_name")
		@Expose
		@NonNull
		private String trackName;

		@SerializedName("track_rating")
		@Expose
		@NonNull
		private int trackRating;

		@SerializedName("commontrack_id")
		@Expose
		@NonNull
		private int commontrackId;

		@SerializedName("instrumental")
		@Expose
		@NonNull
		private int instrumental;

		@SerializedName("explicit")
		@Expose
		@NonNull
		private int explicit;

		@SerializedName("has_lyrics")
		@Expose
		@NonNull
		private int hasLyrics;

		@SerializedName("has_subtitles")
		@Expose
		@NonNull
		private int hasSubtitles;

		@SerializedName("has_richsync")
		@Expose
		@NonNull
		private int hasRichsync;

		@SerializedName("num_favourite")
		@Expose
		@NonNull
		private int numFavourite;

		@SerializedName("album_id")
		@Expose
		@NonNull
		private int albumId;

		@SerializedName("album_name")
		@Expose
		@NonNull
		private String albumName;

		@SerializedName("artist_id")
		@Expose
		@NonNull
		private int artistId;

		@SerializedName("artist_name")
		@Expose
		@NonNull
		private String artistName;

		@SerializedName("track_share_url")
		@Expose
		@NonNull
		private String trackShareUrl;

		@SerializedName("track_edit_url")
		@Expose
		@NonNull
		private String trackEditUrl;

		@SerializedName("restricted")
		@Expose
		@NonNull
		private int restricted;

		@SerializedName("updated_time")
		@Expose
		@NonNull
		private String updatedTime;

		@NonNull
		private String albumImageUrl;

		public int getTrackId() {
			return trackId;
		}

		public void setTrackId(int trackId) {
			this.trackId = trackId;
		}

		public String getTrackName() {
			return trackName;
		}

		public void setTrackName(String trackName) {
			this.trackName = trackName;
		}

		public int getTrackRating() {
			return trackRating;
		}

		public void setTrackRating(int trackRating) {
			this.trackRating = trackRating;
		}

		public int getCommontrackId() {
			return commontrackId;
		}

		public void setCommontrackId(int commontrackId) {
			this.commontrackId = commontrackId;
		}

		public int getInstrumental() {
			return instrumental;
		}

		public void setInstrumental(int instrumental) {
			this.instrumental = instrumental;
		}

		public int getExplicit() {
			return explicit;
		}

		public void setExplicit(int explicit) {
			this.explicit = explicit;
		}

		public int getHasLyrics() {
			return hasLyrics;
		}

		public void setHasLyrics(int hasLyrics) {
			this.hasLyrics = hasLyrics;
		}

		public int getHasSubtitles() {
			return hasSubtitles;
		}

		public void setHasSubtitles(int hasSubtitles) {
			this.hasSubtitles = hasSubtitles;
		}

		public int getHasRichsync() {
			return hasRichsync;
		}

		public void setHasRichsync(int hasRichsync) {
			this.hasRichsync = hasRichsync;
		}

		public int getNumFavourite() {
			return numFavourite;
		}

		public void setNumFavourite(int numFavourite) {
			this.numFavourite = numFavourite;
		}

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

		public String getTrackShareUrl() {
			return trackShareUrl;
		}

		public void setTrackShareUrl(String trackShareUrl) {
			this.trackShareUrl = trackShareUrl;
		}

		public String getTrackEditUrl() {
			return trackEditUrl;
		}

		public void setTrackEditUrl(String trackEditUrl) {
			this.trackEditUrl = trackEditUrl;
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

		public String getAlbumImageUrl() {
			return albumImageUrl;
		}

		public void setAlbumImageUrl(String albumImageUrl) {
			this.albumImageUrl = albumImageUrl;
		}
}

}
