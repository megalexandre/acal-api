package br.com.acalv3.domain.service

import br.com.acalv3.domain.model.Dashboard

interface DashboardService {
    fun get(): Dashboard
}