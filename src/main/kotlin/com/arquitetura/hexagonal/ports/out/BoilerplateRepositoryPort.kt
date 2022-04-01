package com.arquitetura.hexagonal.ports.out

import com.arquitetura.hexagonal.application.model.BoilerplateEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface BoilerplateRepositoryPort {

    fun findByIdOrNull(codigo: Long): BoilerplateEntity?

    fun findAll(spec: Specification<BoilerplateEntity?>?, page: Pageable?): Page<BoilerplateEntity>?

    fun findAllFields(search: String?, page: Pageable?): Page<BoilerplateEntity>

    fun save(boilerplate: BoilerplateEntity): BoilerplateEntity

    fun deleteById(id: Long)
}
