package br.com.amsj.kotlin.theater.controller

import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.service.PerformanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/performances")
class PerformanceController() {

    @Autowired
    lateinit var performanceService: PerformanceService

    @RequestMapping("")
    fun performancesHomePage() =
            ModelAndView("performances/home","performances", performanceService.findAll())

    @RequestMapping("/add")
    fun addPerformance() = ModelAndView("performances/add","performance",Performance(0,""))

    @RequestMapping(value ="/save", method = arrayOf(RequestMethod.POST))
    fun savePerformance(performance: Performance) : String {

        performanceService.save(performance)

        return "redirect:/performances/"
    }
}