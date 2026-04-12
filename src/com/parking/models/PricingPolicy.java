/**
 * 
 */
package com.parking.models;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 
 */
public class PricingPolicy {
	private double hourlyRate;

	public PricingPolicy() {
		this.hourlyRate = 10000.0; // Mặc định 10.000 VND / 1 giờ
	}

	public PricingPolicy(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double calculateFee(LocalDateTime entryTime, LocalDateTime exitTime, boolean hasValidSubscription) {
		if (hasValidSubscription) {
			return 0.0; // Miễn phí nếu có vé tháng
		}

		long hours = Duration.between(entryTime, exitTime).toHours();
		if (hours == 0) {
			hours = 1; // Thu phí ít nhất là 1 giờ cho khách lẻ
		}
		
		// Tại đây có thể mở rộng logic tính phí theo block giờ (VD: 30 phút, 2 giờ, v.v.),
		// hoặc phân loại giá theo khung giờ ban ngày/ban đêm.
		return hours * hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
}