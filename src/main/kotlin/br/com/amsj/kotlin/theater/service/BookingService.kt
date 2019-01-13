package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.stereotype.Service

@Service
class BookingService() {

    fun isSeatFree(seat : Seat) : Boolean {
        return true
    }

}