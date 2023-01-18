package br.com.acalv3.application.toEnter.gateway.v3

import br.com.acalv3.application.toEnter.gateway.AppGateway
import br.com.acalv3.domain.model.v3.CustomerModel
import br.com.acalv3.domain.service.v3.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("customer")
class CustomerGateway(
   customerService: CustomerService
): AppGateway<CustomerModel>(customerService)