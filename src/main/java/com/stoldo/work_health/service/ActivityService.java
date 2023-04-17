package com.stoldo.work_health.service;

import java.sql.Time;
import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Singleton;

import com.stoldo.work_health.model.ContinuousActivity;
import com.stoldo.work_health.model.ContinuousChallange;

import javafx.scene.image.Image;
import lombok.Getter;

@Singleton
public class ActivityService {
	
	@Getter
	private Queue<ContinuousActivity> continuousAcivities = new LinkedList<>();;
	
	
	public ActivityService() {
		initializeContinuousActivities();	
	}
	
	@SuppressWarnings("deprecation")
	private void initializeContinuousActivities() {
		for (int i = 0; i < 50; i++) {
			
			if (i % 2 == 0) {
				ContinuousChallange c = new ContinuousChallange();
				c.setTitle("Stehen");
				c.setImage(new Image("images/standing.png"));
				c.setDuration(new Time(0, 30, 0));
				
				continuousAcivities.add(c);		
			} else {
				ContinuousActivity a = new ContinuousActivity();
				a.setTitle("Pause (Sitzen)");
//				a.setImage(new Image("images/break.png"));
				a.setDuration(new Time(0, 30, 0));
				
				continuousAcivities.add(a);		
			}
		}
	}	

}
