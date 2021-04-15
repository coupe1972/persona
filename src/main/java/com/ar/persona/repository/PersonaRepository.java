package com.ar.persona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ar.persona.SexoEnum;
import com.ar.persona.TipoDocumentoEnum;
import com.ar.persona.dto.DiferentesTiposPersonaDTO;
import com.ar.persona.entity.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{

	Persona findByTipoDocumentoAndNumeroDocumentoAndPaisAndSexo(TipoDocumentoEnum tipoDocumento, int numeroDocumento,
			String pais, SexoEnum sexo);
	
	@Query(value= " SELECT COUNT(p.sexo) as cantidad, p.sexo " + 
		   " FROM  Persona p " + 
		   " GROUP BY p.sexo " ,nativeQuery = true)
	List<DiferentesTiposPersonaDTO> getCantidadMujeresHombres();
	
	Integer countByPaisIgnoreCase(String pais);
}
