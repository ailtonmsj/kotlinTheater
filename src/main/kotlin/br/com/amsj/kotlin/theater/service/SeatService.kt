package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.repository.SeatRepository
import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class SeatService {

    @Autowired
    lateinit var seatRepository : SeatRepository

    fun findByRowAndNum( row: Char, num: Int ) : Optional<Seat> {
        return seatRepository.findByRowAndNum(row, num)
    }

    fun saveAll(seats : List<Seat>){
        seatRepository.saveAll(seats)
    }

    fun deleteAll(){
        seatRepository.deleteAll()
    }




}