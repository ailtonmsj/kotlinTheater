package br.com.amsj.kotlin.theater.domain

import javax.persistence.*

@Entity
data class Booking (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                    val id: Long,
                    val customerName: String) {

    @ManyToOne
    lateinit var seat: Seat

    @ManyToOne
    lateinit var performance: Performance

    override fun toString(): String = "Booking $customerName - seat: ${seat.num}-${seat.row} - performance: ${performance.name}"

}