package com.beadhouse.domen;

import com.beadhouse.in.BasicIn;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 设备信息
 * @author lyq
 *
 */
public class DeviceInfo extends BasicIn {	
	//经度
	private String longitude;
	//纬度
	@JsonProperty("latitude")
	private String Dimension;
	//ip地址
	@JsonProperty("IPAddress")
	private String ipAddress;
	//wifi地址
	private String wifiAddress;
	//simi串号
	@JsonProperty("SIMImsi") 
	private String simImsi;
	//手机串号
	private String phoneImsi;
	//内核版本
	@JsonProperty("coreversion")
	private String codeVersion;
	//蓝牙地址
	private String blueAddress;
	
	
	@JsonProperty("phoneID")
	private String deviceId;
	//@NotNull
	@JsonProperty("netType")
	private String networkType;
	//ioskey
	private String iosKey;
	//设备类型
	@JsonProperty("model")
	private String deviceType;
	//分变率
	private String screenSize;
	
	//操作系统
	@JsonProperty("system")
	private String TelephoneOs;
	//陀螺仪
	private String gyroscope;
	//区域
	private String areaInfo;
	//渠道
	private String channel;
	
	
	
	
	public String getDimension() {
		return Dimension;
	}
	public void setDimension(String dimension) {
		Dimension = dimension;
	}
	public String getWifiAddress() {
		return wifiAddress;
	}
	public void setWifiAddress(String wifiAddress) {
		this.wifiAddress = wifiAddress;
	}
	public String getSimImsi() {
		return simImsi;
	}
	public void setSimImsi(String simImsi) {
		this.simImsi = simImsi;
	}
	public String getCodeVersion() {
		return codeVersion;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public void setCodeVersion(String codeVersion) {
		this.codeVersion = codeVersion;
	}
	
	public String getTelephoneOs() {
		return TelephoneOs;
	}
	public void setTelephoneOs(String telephoneOs) {
		TelephoneOs = telephoneOs;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String iPAddress) {
		ipAddress = iPAddress;
	}
	
	
	public String getPhoneImsi() {
		return phoneImsi;
	}
	public void setPhoneImsi(String phoneImsi) {
		this.phoneImsi = phoneImsi;
	}
	
	public String getBlueAddress() {
		return blueAddress;
	}
	public void setBlueAddress(String blueAddress) {
		this.blueAddress = blueAddress;
	}
	
	
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public String getIosKey() {
		return iosKey;
	}
	public void setIosKey(String iosKey) {
		this.iosKey = iosKey;
	}
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screeSize) {
		this.screenSize = screeSize;
	}

	public String getGyroscope() {
		return gyroscope;
	}
	public void setGyroscope(String gyroscope) {
		this.gyroscope = gyroscope;
	}
	public String getAreaInfo() {
		return areaInfo;
	}
	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceID) {
		this.deviceId = deviceID;
	}
	
	
}
