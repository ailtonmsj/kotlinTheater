package br.com.amsj.kotlin.theater.controller

import br.com.amsj.kotlin.theater.service.ReportingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import javax.websocket.server.PathParam
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions

@Controller
@RequestMapping(value = "/reports")
class ReportController {

    @Autowired
    lateinit var reportingService: ReportingService


    private fun reports() = reportingService::class.declaredMemberFunctions.map { it.name}

    @RequestMapping("")
    fun main() = ModelAndView("reports", mapOf("reports" to reports()))

    @RequestMapping("/getReport", method = arrayOf(RequestMethod.GET))
    fun getReport(@PathParam("report") report : String): ModelAndView {

        val matchedReport = reportingService::class.declaredMemberFunctions.filter { it.name == report }.firstOrNull()

        val result = matchedReport?.call(reportingService) ?: ""

        return ModelAndView("reports", mapOf("reports" to reports(), "result" to result ))
    }

}