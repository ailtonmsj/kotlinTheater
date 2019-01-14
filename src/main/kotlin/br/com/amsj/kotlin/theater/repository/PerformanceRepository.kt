package br.com.amsj.kotlin.theater.repository

import br.com.amsj.kotlin.theater.domain.Performance
import org.springframework.data.jpa.repository.JpaRepository

interface PerformanceRepository : JpaRepository<Performance, Long> {
}