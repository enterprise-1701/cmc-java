package automationFramework.RestApi;

import com.google.gson.annotations.SerializedName;

public class RestResponse {
	
	@SerializedName("x-cub-hdr")
	private RestResponseHeader hdr;

	public RestResponseHeader getHdr() {
		return hdr;
	}

	public void setResponseHeader(RestResponseHeader hdr) {
		this.hdr = hdr;
	}

}
