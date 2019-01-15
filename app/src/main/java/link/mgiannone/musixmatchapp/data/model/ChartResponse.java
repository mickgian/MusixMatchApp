

package link.mgiannone.musixmatchapp.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import link.mgiannone.musixmatchapp.data.Config;


public class ChartResponse {

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


	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(body).append(header).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof ChartResponse) == false) {
			return false;
		}
		ChartResponse rhs = ((ChartResponse) other);
		return new EqualsBuilder().append(body, rhs.body).append(header, rhs.header).isEquals();
	}



	public static class Header {

		@SerializedName("status_code")
		@Expose
		private int statusCode;

		public int getStatusCode() {
			return statusCode;
		}

		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

	}

	public static class Body {

		@SerializedName("track_list")
		@Expose
		private List<Track> track = null;

		public List<Track> getTrack() {
			return track;
		}

		public void setTrack(List<Track> track) {
			this.track = track;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(track).toHashCode();
		}

		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if ((other instanceof Body) == false) {
				return false;
			}
			Body rhs = ((Body) other);
			return new EqualsBuilder().append(track, rhs.track).isEquals();
		}

	}


	public static class Chart {

		@SerializedName("message")
		@Expose
		private ChartResponse message;

		public ChartResponse getMessage() {
			return message;
		}

		public void setMessage(ChartResponse message) {
			this.message = message;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(message).toHashCode();
		}

		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if ((other instanceof Chart) == false) {
				return false;
			}
			Chart rhs = ((Chart) other);
			return new EqualsBuilder().append(message, rhs.message).isEquals();
		}

	}

	@Entity(tableName = Config.MUSIX_MATCH_TRACKS_TABLE_NAME)
	public static class Track {

		@SerializedName("track")
		@Expose
		@PrimaryKey
		@NonNull
		private String track;

		public String getTrack() {
			return track;
		}

		public void setTrack(String track) {
			this.track = track;
		}

		@Override
		public int hashCode() {
			return new HashCodeBuilder().append(track).toHashCode();
		}

		@Override
		public boolean equals(Object other) {
			if (other == this) {
				return true;
			}
			if ((other instanceof Track) == false) {
				return false;
			}
			Track rhs = ((Track) other);
			return new EqualsBuilder().append(track, rhs.track).isEquals();
		}

	}
}
