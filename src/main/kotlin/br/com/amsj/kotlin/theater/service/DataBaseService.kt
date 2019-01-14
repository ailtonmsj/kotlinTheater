package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.domain.Seat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class DataBaseService {

    @Autowired
    lateinit var seatService: SeatService

    @Autowired
    lateinit var performanceService: PerformanceService


    fun initialize() {

        fun createPerformance() : List<Performance>{
            return listOf(Performance(1, "Phanton of the Opera"), Performance(2, "The Lion King"), Performance(3, "Beauty And The Beast"))
        }

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

        val performances = createPerformance()
        performanceService.save(performances)

        val seats = ArrayList<Seat>()

        for (row in 1..15) {
            for (num in 1..36) {
//                val performance = performances.get((Math.random() * performances.size).toInt())
                seats.add(Seat(0, (row+64).toChar(), num, getPrice(row,num), getDescription(row,num)))
            }
        }
        seatService.saveAll(seats)
    }

    fun clearDataBase() {
        seatService.deleteAll()
        performanceService.deleteAll()

    }

}

