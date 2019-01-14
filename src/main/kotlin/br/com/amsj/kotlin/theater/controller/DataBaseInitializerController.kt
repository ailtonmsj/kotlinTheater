package br.com.amsj.kotlin.theater.controller

import br.com.amsj.kotlin.theater.service.DataBaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


@Controller
@RequestMapping("/databaseInitializer")
class DataBaseInitializerController {

    @Autowired
    lateinit var dataBaseService: DataBaseService

    @RequestMapping("initialize")
    fun createInitialDataBase() : String {

        dataBaseService.initialize()

        return "/index"
    }

    @RequestMapping("clear")
    fun clearDataBase() : String {

        dataBaseService.clearDataBase()

        return "/index"
    }
}


