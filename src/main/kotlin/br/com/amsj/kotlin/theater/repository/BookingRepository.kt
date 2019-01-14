package br.com.amsj.kotlin.theater.repository

import br.com.amsj.kotlin.theater.domain.Booking
import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BookingRepository : JpaRepository<Booking, Long> {

    @Query("Select b from Booking b where b.seat.id = :seatId and b.performance.id = :performanceId")
    fun findBookingBySeatAndPerformance(
            @Param("seatId") seatId: Long, @Param("performanceId") performanceId : Long) : Booking
}