package com.arquitetura.hexagonal.adapters.outbound

import com.arquitetura.hexagonal.adapters.outbound.integration.BaseServiceIntegration
import com.arquitetura.hexagonal.application.model.BoilerplateModel
import com.arquitetura.hexagonal.ports.out.BoilerplateIntegrationPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class BoilerplateIntegrationAdapter: BoilerplateIntegrationPort {

    @Autowired
    private lateinit var baseServiceIntegration: BaseServiceIntegration

    override fun findByIdOrNull(codigo: Long): BoilerplateModel? {
        return baseServiceIntegration.findByIdOrNull(codigo)
    }

    override fun findAll(page: Pageable): Page<BoilerplateModel> {
        return PageImpl(baseServiceIntegration.findAll(page)?.body?.toMutableList() ?: mutableListOf())
    }

    override fun findAllFields(search: String?, page: Pageable): Page<BoilerplateModel> {
        return PageImpl(baseServiceIntegration.findAllFields(search, page)?.body?.toMutableList() ?: mutableListOf())
    }

    override fun save(boilerplate: BoilerplateModel): BoilerplateModel {
        return baseServiceIntegration.save(boilerplate).body!!
    }

    override fun deleteById(id: Long) {
        baseServiceIntegration.deleteById(id)
    }
}
