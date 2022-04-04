package com.arquitetura.hexagonal.adapters.outbound

import com.arquitetura.hexagonal.adapters.outbound.repository.BoilerplateRepository
import com.arquitetura.hexagonal.application.model.BoilerplateEntity
import com.arquitetura.hexagonal.ports.out.BoilerplateRepositoryPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BoilerplateAdapter: BoilerplateRepositoryPort{

    @Autowired
    private lateinit var boilerplateRepository: BoilerplateRepository

    override fun findByIdOrNull(codigo: Long): BoilerplateEntity? {
        return boilerplateRepository.findByIdOrNull(codigo)
    }

    override fun findAll(spec: Specification<BoilerplateEntity>?, page: Pageable?): Page<BoilerplateEntity> {
        return boilerplateRepository.findAll(spec, page)
    }

    override fun findAllFields(search: String?, page: Pageable?): Page<BoilerplateEntity> {
        return boilerplateRepository.findAllFields(search, page)
    }

    override fun save(boilerplate: BoilerplateEntity): BoilerplateEntity {
        return boilerplateRepository.save(boilerplate)
    }

    override fun deleteById(id: Long) {
        boilerplateRepository.deleteById(id)
    }
}