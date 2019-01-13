package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.controller.repository.SeatRepository
import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class SeatService {

    @Autowired
    lateinit var seatRepository : SeatRepository

    fun initializeDataBase() {

        fun getPrice( row: Int, num: Int ) : BigDecimal {
            return when {
                row >=14 -> BigDecimal(14.50)
                num <=3 || num >= 34 -> BigDecimal(16.50)
                row == 1 -> BigDecimal(21)
                else -> BigDecimal(18)
            }

        }

        fun getDescription( row: Int, num: Int ) : String {
            return when {
                row == 15 -> "Back Row"
                row == 14 -> "Cheaper Seat"
                num <=3 || num >= 34 -> "Restricted View"
                row <=2 -> "Best View"
                else -> "Standard Seat"
            }
        }

        val seats = ArrayList<Seat>()

        for (row in 1..15) {
            for (num in 1..36) {
                seats.add(Seat(0, (row+64).toChar(), num, getPrice(row,num), getDescription(row,num) ))
            }
        }
        seatRepository.saveAll(seats)
    }

    fun clearDataBase() {
        seatRepository.deleteAll()
    }

    fun findByRowAndNum( row: Char, num: Int ) : Optional<Seat> {
        return seatRepository.findByRowAndNum(row, num)
    }




}