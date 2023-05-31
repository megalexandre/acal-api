package br.com.acalv3.integration

import br.com.acalv3.domain.model.Hydrometer
import br.com.acalv3.domain.model.Link
import br.com.acalv3.domain.service.AddressService
import br.com.acalv3.domain.service.CustomerService
import br.com.acalv3.domain.service.GroupService
import br.com.acalv3.domain.service.HydrometerService
import br.com.acalv3.domain.service.LinkService
import br.com.acalv3.domain.service.PlaceService
import br.com.acalv3.stub.addressStub
import br.com.acalv3.stub.customerStub
import br.com.acalv3.stub.groupStub
import br.com.acalv3.stub.linkStub
import br.com.acalv3.stub.placeStub
import java.math.BigDecimal
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IntegrationTestUtil {

    @Autowired
    private lateinit var hydrometerService: HydrometerService

    @Autowired
    private lateinit var linkService: LinkService

    @Autowired
    private lateinit var placeService: PlaceService

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var customerService: CustomerService

    @Autowired
    private lateinit var addressService: AddressService

    fun saveLink(): Link {
        val address = addressService.save(addressStub())
        val place = placeService.save(
            placeStub(
                address = address,
                addressId = address.id,
            )
        )

        val group = groupService.save(groupStub())
        val customer = customerService.save(customerStub())

        return linkService.save(
            linkStub(
                place = place,
                mailPlace = place,
                group = group,
                customer = customer,
            )
        )
    }

    fun saveHydrometer(): Hydrometer {
        val link = saveLink()

        return hydrometerService.save(
            Hydrometer(
            id = UUID.randomUUID(),
            reference = "052023",
            costValue = BigDecimal.TEN,
            consumption = 1000L,
            link = link,
            linkId = link.id,
            )
        )
    }
}