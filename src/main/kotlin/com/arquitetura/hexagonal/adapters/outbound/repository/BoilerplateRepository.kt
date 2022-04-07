package com.arquitetura.hexagonal.adapters.outbound.repository

import com.arquitetura.hexagonal.adapters.model.BoilerplateModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BoilerplateRepository : JpaRepository<BoilerplateModel, Long> {

    fun findAll(spec: Specification<BoilerplateModel>?, page: Pageable?): Page<BoilerplateModel>

    @Query(
        """
        SELECT u FROM BoilerplateEntity u WHERE 
            CAST(u.id as text) like upper(CONCAT('%',:search,'%')) 
            or upper(u.nome) like upper(CONCAT('%',:search,'%'))
            or to_char(u.dataUltimaAtualizacao, 'dd/MM/yyyy') like CONCAT('%',:search,'%')
            or to_char(u.dataUltimaAtualizacao, 'yyyy-MM-dd') like CONCAT('%',:search,'%')
            or to_char(u.dataUltimaAtualizacao, 'dd/MM/yyyy HH:mm:ss') like CONCAT('%',:search,'%')
            or to_char(u.dataUltimaAtualizacao, 'yyyy-MM-dd HH:mm:ss') like CONCAT('%',:search,'%')
    """
    )
    fun findAllFields(@Param("search") search: String?, page: Pageable?): Page<BoilerplateModel>

}
