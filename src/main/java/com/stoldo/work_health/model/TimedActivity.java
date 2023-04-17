package com.stoldo.work_health.model;

import java.sql.Time;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TimedActivity extends Actitivty {
	private Time time;
}
