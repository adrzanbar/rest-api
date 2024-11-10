package com.uncode.stop.rest_api.dto;

import java.util.UUID;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisDTO {

	private UUID id;
	@NotBlank
	private String nombre;
}
