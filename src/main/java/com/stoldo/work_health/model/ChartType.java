package com.stoldo.work_health.model;

public enum ChartType {
	ACTIVE,
	PASSIVE,
	EXPENSE,
	INCOME;
	
	@Override
	public String toString() {
		switch(this) {
	      case ACTIVE: 
	    	  return "Aktiv";
	      case PASSIVE: 
	    	  return "Passiv";
	      case EXPENSE: 
	    	  return "Aufwand";
	      case INCOME: 
	    	  return "Ertrag";
    	  default:
    		  throw new IllegalArgumentException();
	    }
	}
}
