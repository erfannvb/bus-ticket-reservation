package nvb.dev.busticketreservation.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import nvb.dev.busticketreservation.base.service.impl.BaseServiceImpl;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.exception.ValidationException;
import nvb.dev.busticketreservation.repository.TicketRepository;
import nvb.dev.busticketreservation.service.TicketService;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

public class TicketServiceImpl extends BaseServiceImpl<Long, Ticket, TicketRepository> implements TicketService {

    protected final EntityManager entityManager;

    public TicketServiceImpl(EntityManager entityManager, TicketRepository repository) {
        super(entityManager, repository);
        this.entityManager = entityManager;
    }

    @Override
    public boolean validate(Ticket ticket) {
        try {

            ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();

            Validator validator = validatorFactory.getValidator();

            Set<ConstraintViolation<Ticket>> constraintViolationSet = validator.validate(ticket);
            if (!constraintViolationSet.isEmpty()) {
                for (ConstraintViolation<Ticket> violation : constraintViolationSet) {
                    System.out.println(violation.getMessage());
                }
                validatorFactory.close();
                return false;
            } else {
                return true;
            }

        } catch (ValidationException e) {
            e.getStackTrace();
            return false;
        }
    }
}
