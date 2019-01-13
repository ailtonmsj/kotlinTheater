package br.com.amsj.kotlin.theater.controller

import br.com.amsj.kotlin.theater.service.BookingService
import br.com.amsj.kotlin.theater.service.TheaterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
class MainController {

    @Autowired
    lateinit var theaterService: TheaterService

    @Autowired
    lateinit var bookingService: BookingService

    @RequestMapping("")
    fun homePage() : ModelAndView =
            ModelAndView("seatBooking", "bean", CheckAvailabilityBackingBean())


    @RequestMapping(value="/checkAvailability", method= arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat = theaterService.find(bean.selectedSeatNum, bean.selectedSeatRow)
        val result = bookingService.isSeatFree(selectedSeat)
        bean.result = "Seat $selectedSeat is " + if (result) "available" else "booked"
        return ModelAndView("seatBooking", "bean", bean)
    }

}



class CheckAvailabilityBackingBean() {
    val seatNums = 1..36
    val seatRows = 'A'..'O'
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var result : String = ""
}
