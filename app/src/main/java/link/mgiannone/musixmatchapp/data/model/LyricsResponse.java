package link.mgiannone.musixmatchapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LyricsResponse {

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

		@SerializedName("lyrics")
		@Expose
		private Lyrics lyrics;

		public Lyrics getLyrics() {
			return lyrics;
		}

		public void setLyrics(Lyrics lyrics) {
			this.lyrics = lyrics;
		}

	}

	public static class Lyrics implements Serializable{

		@SerializedName("lyrics_id")
		@Expose
		private int lyricsId;
		@SerializedName("restricted")
		@Expose
		private int restricted;
		@SerializedName("instrumental")
		@Expose
		private int instrumental;
		@SerializedName("lyrics_body")
		@Expose
		private String lyricsBody;
		@SerializedName("lyrics_language")
		@Expose
		private String lyricsLanguage;
		@SerializedName("script_tracking_url")
		@Expose
		private String scriptTrackingUrl;
		@SerializedName("pixel_tracking_url")
		@Expose
		private String pixelTrackingUrl;
		@SerializedName("lyrics_copyright")
		@Expose
		private String lyricsCopyright;
		@SerializedName("backlink_url")
		@Expose
		private String backlinkUrl;
		@SerializedName("updated_time")
		@Expose
		private String updatedTime;

		public int getLyricsId() {
			return lyricsId;
		}

		public void setLyricsId(int lyricsId) {
			this.lyricsId = lyricsId;
		}

		public int getRestricted() {
			return restricted;
		}

		public void setRestricted(int restricted) {
			this.restricted = restricted;
		}

		public int getInstrumental() {
			return instrumental;
		}

		public void setInstrumental(int instrumental) {
			this.instrumental = instrumental;
		}

		public String getLyricsBody() {
			return lyricsBody;
		}

		public void setLyricsBody(String lyricsBody) {
			this.lyricsBody = lyricsBody;
		}

		public String getLyricsLanguage() {
			return lyricsLanguage;
		}

		public void setLyricsLanguage(String lyricsLanguage) {
			this.lyricsLanguage = lyricsLanguage;
		}

		public String getScriptTrackingUrl() {
			return scriptTrackingUrl;
		}

		public void setScriptTrackingUrl(String scriptTrackingUrl) {
			this.scriptTrackingUrl = scriptTrackingUrl;
		}

		public String getPixelTrackingUrl() {
			return pixelTrackingUrl;
		}

		public void setPixelTrackingUrl(String pixelTrackingUrl) {
			this.pixelTrackingUrl = pixelTrackingUrl;
		}

		public String getLyricsCopyright() {
			return lyricsCopyright;
		}

		public void setLyricsCopyright(String lyricsCopyright) {
			this.lyricsCopyright = lyricsCopyright;
		}

		public String getBacklinkUrl() {
			return backlinkUrl;
		}

		public void setBacklinkUrl(String backlinkUrl) {
			this.backlinkUrl = backlinkUrl;
		}

		public String getUpdatedTime() {
			return updatedTime;
		}

		public void setUpdatedTime(String updatedTime) {
			this.updatedTime = updatedTime;
		}

	}

}

