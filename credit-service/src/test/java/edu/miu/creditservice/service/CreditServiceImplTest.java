package edu.miu.creditservice.service;

import edu.miu.creditservice.entity.Credit;
import edu.miu.creditservice.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    @InjectMocks
    private CreditServiceImpl creditService;

    @Mock
    private CreditRepository creditRepository;

    @Test
    void should_save_transaction() {
        UUID cerditId = UUID.randomUUID();
        UUID propertyId = UUID.randomUUID();
        Credit paypal = new Credit(cerditId, "CREDIT", "anh@gmail.com", 500.0, 2, propertyId);
        creditService.save(paypal);
        assertThat(paypal.getTotal()).isEqualTo(500);
        assertThat(paypal.getId()).isNotNull();
    }

    @Test
    void should_return_paypal_transaction() {
        UUID transactionId = UUID.randomUUID();
        UUID propertyId = UUID.randomUUID();
        Credit expectedReservation = new Credit(transactionId, "CREDIT", "anh@gmail.com", 500.0, 2, propertyId);
        when(creditRepository.findById(transactionId)).thenReturn(Optional.of(expectedReservation));
        assertThat(creditService.getById(transactionId)).isEqualTo(expectedReservation);

    }

    @Test
    void should_throw_exception() {
        UUID transactionId = UUID.randomUUID();
        when(creditRepository.findById(transactionId)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> creditService.getById(transactionId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cannot find transaction credit with id: " + transactionId);

    }
}
