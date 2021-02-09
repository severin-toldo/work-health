package com.stoldo.work_health.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequest {
	
	@NonNull
	private String email;
	
	@NonNull
	private String password;

}
