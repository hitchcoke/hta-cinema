package com.example.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowScheduleDto {

	private int movieNo;
	private String movieName;	
	private int ratingNo;
	private String ratingName;
	private List<ScreenDto> screenList;
}
