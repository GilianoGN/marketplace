package dio.marketplace.ticketing.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dio.marketplace.common.infrastruture.event.dto.CommonEventUpdated;
import dio.marketplace.ticketing.domain.TicketingEvent;
import dio.marketplace.ticketing.domain.TicketingEventRepository;
import dio.marketplace.ticketing.domain.TicketingSeat;
import dio.marketplace.ticketing.domain.TicketingSector;
@Service
public class TicketingCreateEventUseCase {
    private final TicketingEventRepository eventRepository;

    public TicketingCreateEventUseCase(TicketingEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional("ticketingTransactionManager")
    public void execute(CommonEventUpdated event) {
        Map<TicketingSector, List<TicketingSeat>> seats = event.sectors().stream()
            .collect(Collectors.toMap(
                sector -> new TicketingSector(sector.id(), sector.price()),
                sector -> sector.seats().stream()
                    .map(seatDto -> new TicketingSeat(seatDto.number()))
                    .toList()
            ));
        eventRepository.save(new TicketingEvent(event.id(), seats));
    }
}