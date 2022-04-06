package com.arquitetura.hexagonal.adapters.outbound

import com.arquitetura.hexagonal.adapters.outbound.repository.BoilerplateRepository
import com.arquitetura.hexagonal.application.model.BoilerplateModel
import com.arquitetura.hexagonal.ports.out.BoilerplatePersistencePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BoilerplatePersistenceAdapter: BoilerplatePersistencePort {

    @Autowired
    private lateinit var boilerplateRepository: BoilerplateRepository

    override fun findByIdOrNull(codigo: Long): BoilerplateModel? {
        return boilerplateRepository.findByIdOrNull(codigo)
    }

    override fun findAll(page: Pageable): Page<BoilerplateModel> {
        return boilerplateRepository.findAll( page)
    }

    override fun findAllFields(search: String?, page: Pageable): Page<BoilerplateModel> {
        return boilerplateRepository.findAllFields(search, page)
    }

    override fun save(boilerplate: BoilerplateModel): BoilerplateModel {
        return boilerplateRepository.save(boilerplate)
    }

    override fun deleteById(id: Long) {
        boilerplateRepository.deleteById(id)
    }
}
