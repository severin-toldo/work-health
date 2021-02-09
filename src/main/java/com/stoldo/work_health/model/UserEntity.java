package com.stoldo.work_health.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserEntity {
	private Long id;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
}
