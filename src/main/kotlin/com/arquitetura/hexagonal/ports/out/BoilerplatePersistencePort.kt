package com.arquitetura.hexagonal.ports.out

import com.arquitetura.hexagonal.adapters.dto.BoilerplateMapper
import com.arquitetura.hexagonal.adapters.model.BoilerplateModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

interface BoilerplatePersistencePort {

    fun findByIdOrNull(codigo: Long): BoilerplateModel?

    fun findAll(page: Pageable): Page<BoilerplateModel>

    fun findAllFields(search: String?, page: Pageable): Page<BoilerplateModel>

    fun save(boilerplate: BoilerplateMapper): BoilerplateModel

    fun deleteById(id: Long)
}
