package br.com.amsj.kotlin.theater.controller.repository

import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SeatRepository : JpaRepository<Seat, Long> {

    fun findByRowAndNum( row: Char,num: Int ) : Optional<Seat>
}