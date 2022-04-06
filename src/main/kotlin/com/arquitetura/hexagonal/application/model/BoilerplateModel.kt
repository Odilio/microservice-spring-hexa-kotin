package com.arquitetura.hexagonal.application.model

import org.springframework.data.jpa.repository.Temporal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "boilerplates")
class BoilerplateModel(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "nome")
    var nome: String? = null,

    @Column(name = "data_ultima_atualizacao")
    @Temporal(TemporalType.TIMESTAMP)
    var dataUltimaAtualizacao: Date? = null
)
