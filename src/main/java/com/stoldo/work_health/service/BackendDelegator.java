package com.stoldo.work_health.service;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoldo.work_health.model.Booking;
import com.stoldo.work_health.model.Chart;
import com.stoldo.work_health.model.UserLoginRequest;
import com.stoldo.work_health.shared.constants.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;




public class BackendDelegator {
	
	private static BackendDelegator instance;
	private WebResource webResource = Client.create().resource(Constants.API_BASE_URL);
	private ObjectMapper objectMapper = new ObjectMapper();

	
	public static BackendDelegator getInstance() {
		if (instance == null) {
			instance = new BackendDelegator();
		}

		return instance;
	}

	private BackendDelegator() {}
	
	public void saveChart(Chart chart, String jwtToken) throws Exception {
		post(objectMapper.writeValueAsString(chart), "api/charts/save", jwtToken);
	}
	
	public List<Chart> getCharts(String jwtToken) throws Exception {
		ClientResponse response = get("api/charts", jwtToken);
		return objectMapper.readValue(response.getEntity(String.class), new TypeReference<List<Chart>>(){});
	}
	
	public void saveBooking(Booking booking, String jwtToken) throws Exception {
		post(objectMapper.writeValueAsString(booking), "api/bookings/save", jwtToken);
	}
	
	public List<Booking> getBookings(String jwtToken) throws Exception {
		ClientResponse response = get("api/bookings", jwtToken);
		return objectMapper.readValue(response.getEntity(String.class), new TypeReference<List<Booking>>(){});
	}
	
	public String login(UserLoginRequest ulr) {
		try {
			ClientResponse response = post(objectMapper.writeValueAsString(ulr), "login", null);
			return response.getHeaders().get(Constants.AUTH_HEADER_NAME).get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private ClientResponse get(String path, String jwtToken) {
		ClientResponse response = webResource
				.path(path)
				.header(Constants.AUTH_HEADER_NAME, jwtToken)
				.accept("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new IllegalStateException("Failed: HTTP error code: " + response.getStatus());
		}
		
		return response;
	}
	
	private ClientResponse post(String json, String path, String jwtToken) {
		ClientResponse response = webResource
				.path(path)
				.header(Constants.AUTH_HEADER_NAME, jwtToken)
				.type("application/json")
				.post(ClientResponse.class, json);
		
		if (response.getStatus() != 200) {
			throw new IllegalStateException("Failed: HTTP error code: " + response.getStatus());
		}
			
		return response;
	}
}
