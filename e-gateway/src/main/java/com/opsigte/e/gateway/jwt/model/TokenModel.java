package com.opsigte.e.gateway.jwt.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p> @ClassName: <i>TokenModel</i></p>
 * <p> @Description: <i>请求头中包含的token对象</i></p>
 * <p> @Author: <i>zzh</i></p>
 * <p> @Created date: <i>2019/10/7 12:28</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@Data
public class TokenModel implements Serializable {

    private static final long serialVersionUID = -1152383357458247955L;

    private String uid;
    private String token;
	private String clientType;
	private String timestamp;
	private String deviceId;
	private String appVersion;
	private String ip;


	public TokenModel() {
		super();
	}

	public TokenModel(String uid,String token) {
		super();
		this.uid = uid;
        this.token = token;
	}


	public TokenModel(String uid,String token,String clientType, String timestamp, String deviceId, String appVersion, String ip) {
		super();
		this.uid = uid;
        this.token = token;
		this.clientType = clientType;
		this.timestamp = timestamp;
		this.deviceId = deviceId;
		this.appVersion = appVersion;
		this.ip = ip;
	}

}
