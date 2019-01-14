package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.domain.Booking
import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.domain.Seat
import br.com.amsj.kotlin.theater.repository.BookingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookingService() {

    @Autowired
    lateinit var bookingRepository: BookingRepository

    fun isSeatFree(seat : Seat, performance: Performance) : Boolean {

        val bookings : List<Booking> = bookingRepository.findAll()
        val matchedBookings = bookings.filter { it.seat == seat && it.performance == performance }

        // if returns zero, there is a seat available
        return matchedBookings.size == 0
    }

    fun reserve(seat: Seat, performance: Performance, customerName: String) : Booking {

        val booking = Booking(0, customerName)
        booking.performance = performance
        booking.seat = seat

        return bookingRepository.save(booking)
    }

    fun findBooking(seat: Seat, performance: Performance): Booking {

        return bookingRepository.findBookingBySeatAndPerformance(seat.id, performance.id)
    }

    fun listAll() : List<Booking> {

        return bookingRepository.findAll()
    }

}