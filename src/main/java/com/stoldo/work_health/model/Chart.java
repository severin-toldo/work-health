package com.stoldo.work_health.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Chart {
	private Long id;
	private String accno;
	private String shortName;
	private String name;
	private ChartType chartType;
	private BigDecimal balance;
	
	@Override
	public String toString() {
		return accno + " - " + name;
	}
}
