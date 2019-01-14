package br.com.amsj.kotlin.theater.controller

import br.com.amsj.kotlin.theater.domain.Booking
import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.domain.Seat
import br.com.amsj.kotlin.theater.service.BookingService
import br.com.amsj.kotlin.theater.service.PerformanceService
import br.com.amsj.kotlin.theater.service.SeatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/bookings")
class BookíngController {

    @Autowired
    lateinit var seatService: SeatService

    @Autowired
    lateinit var bookingService : BookingService

    @Autowired
    lateinit var performanceService: PerformanceService

    fun defaultValues() : Map<String, Any> {

        val performances = performanceService.findAll()

        val seatNums = 1..36
        val seatRows = 'A'..'O'

        val model = mapOf("bean" to CheckAvailabilityBackingBean(),
                "performances" to performances,
                "seatNums" to seatNums,
                "seatRows" to seatRows)

        return model
    }



    @RequestMapping("")
    fun homePage() : ModelAndView {

        val model = defaultValues()
        return ModelAndView("/bookings/seatBooking", model)
    }


    @RequestMapping(value="/checkAvailability", method= arrayOf(RequestMethod.POST))
    fun checkAvailability(bean : CheckAvailabilityBackingBean) : ModelAndView {

        val selectedSeat : Seat = seatService.findByRowAndNum(bean.selectedSeatRow, bean.selectedSeatNum).get()

        val selectedPerformance = performanceService.findById(bean.selectedPerformance!!).get();

        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val result = bookingService.isSeatFree(selectedSeat, selectedPerformance)
        bean.available = result

        if(!result){
            bean.booking = bookingService.findBooking(selectedSeat, selectedPerformance)
        }

        val performances = performanceService.findAll()
        val seatNums = 1..36
        val seatRows = 'A'..'O'

        val model = mapOf("bean" to bean,
                "performances" to performances,
                "seatNums" to seatNums,
                "seatRows" to seatRows)


        return ModelAndView("bookings/seatBooking", model)
    }

    @RequestMapping(value = "bookingSeat", method = arrayOf(RequestMethod.POST))
    fun bookSeat(bean: CheckAvailabilityBackingBean) : ModelAndView{

        val booking = bookingService.reserve(bean.seat!!, bean.performance!!, bean.customerName)

        return ModelAndView("bookings/bookingConfirmed", "booking", booking)
    }

    @RequestMapping(value = "list", method = arrayOf(RequestMethod.GET))
    fun list() : ModelAndView {

        val bookings = bookingService.listAll()

        return ModelAndView("bookings/list", "bookings", bookings)
    }
}

class CheckAvailabilityBackingBean() {

    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var selectedPerformance: Long? = null
    var customerName: String = ""

    var available: Boolean? = null
    var seat: Seat? = null
    var performance: Performance? = null
    var booking: Booking? = null
}