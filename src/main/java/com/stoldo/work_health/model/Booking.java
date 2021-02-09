package com.stoldo.work_health.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
	private Long id;
	private Date date;
	private String text;
	private Chart sollChart;
	private Chart habenChart;
	private BigDecimal amount;
	
	public String getTableDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		return sdf.format(date);
	}
}
