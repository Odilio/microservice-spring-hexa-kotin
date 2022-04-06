package com.arquitetura.hexagonal.service

import com.arquitetura.hexagonal.adapters.dto.BoilerplateDTO
import com.arquitetura.hexagonal.application.model.BoilerplateModel
import com.arquitetura.hexagonal.application.service.BoilerplateService
import com.arquitetura.hexagonal.adapters.outbound.repository.BoilerplateRepository
import com.arquitetura.hexagonal.application.extensions.convertStringToDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource


@ExtendWith(MockitoExtension::class)
@TestPropertySource(properties = ["spring.cloud.config.fail-fast=false"], locations = ["classpath:boilerplate.properties"])
@SpringBootTest(
    properties = ["spring.cloud.config.enabled=false",
        "spring.cloud.kubernetes.enabled=false",
        "spring.flyway.enabled=false",
        "spring.cloud.kubernetes.discovery.enabled=false"],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class BoilerplateServiceTest {
    
    @Mock
    private lateinit var repository: BoilerplateRepository

    @InjectMocks
    private lateinit var service: BoilerplateService

    companion object {
        var results: ArrayList<BoilerplateDTO> = arrayListOf()
        var resultEntitys: ArrayList<BoilerplateModel> = arrayListOf()
        lateinit var pagedResponse: Page<BoilerplateDTO>

        @BeforeAll
        @JvmStatic
        fun setMockOutput() {
            results.add(BoilerplateDTO(2, "example", "2022-07-15"))
            results.add(BoilerplateDTO(4, "example 2", "2022-07-15"))
            
            resultEntitys.add(BoilerplateModel(2, "example", "2022-07-15".convertStringToDate()))
            pagedResponse = PageImpl(results)
        }
    }


    @Test
    fun deveSalvarBoilerPlate() {
        val boilerplateEntity = resultEntitys[0]
        val boilerplateDTO = results[0]

        `when`(repository.save(any(BoilerplateModel::class.java))).thenReturn(boilerplateEntity)

        assertEquals("example",
            service.salvarBoilerplate(boilerplateDTO).nome)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}
