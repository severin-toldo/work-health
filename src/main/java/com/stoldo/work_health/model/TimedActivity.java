package com.stoldo.work_health.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TimedActivity extends Actitivty {
	private String time; // format HH:MM
}
