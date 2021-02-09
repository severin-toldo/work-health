package com.stoldo.work_health.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContinuousActivity extends Actitivty {
	private String duration; // format MM:ss;
}
