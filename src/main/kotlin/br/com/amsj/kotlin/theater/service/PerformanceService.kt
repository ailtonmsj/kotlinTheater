package br.com.amsj.kotlin.theater.service

import br.com.amsj.kotlin.theater.domain.Performance
import br.com.amsj.kotlin.theater.repository.PerformanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PerformanceService {

    @Autowired
    lateinit var performanceRepository: PerformanceRepository

    fun save (performances: List<Performance>){
        performanceRepository.saveAll(performances)
    }

    fun deleteAll(){
        performanceRepository.deleteAll()
    }

    fun findAll() : List<Performance> {
        return performanceRepository.findAll()
    }

    fun save(performance: Performance){
        performanceRepository.save(performance)
    }

    fun findById(id: Long) : Optional<Performance> {
        return performanceRepository.findById(id)
    }

}