package com.ar.persona;

public enum SexoEnum {

	MASCULINO("MASCULINO"), 
	FEMENINO("FEMENINO");
	
	private final String name;

    private SexoEnum(String code) {
        this.name = code;
    }

    public String getName() {
        return name;
    }
}
