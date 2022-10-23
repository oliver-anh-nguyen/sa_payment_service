package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Bank;
import edu.miu.bankservice.repository.BankRepository;
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
class BankServiceImplTest {
    @InjectMocks
    private BankServiceImpl bankService;

    @Mock
    private BankRepository bankRepository;

    @Test
    void should_save_bank_transaction() {
        UUID bankId = UUID.randomUUID();
        UUID propertyId = UUID.randomUUID();
        Bank paypal = new Bank(bankId, "BANK", "anh@gmail.com", 500.0, 2, propertyId);
        bankService.save(paypal);
        assertThat(paypal.getTotal()).isEqualTo(500);
        assertThat(paypal.getId()).isNotNull();
    }

    @Test
    void should_return_bank_transaction() {
        UUID transactionId = UUID.randomUUID();
        UUID propertyId = UUID.randomUUID();
        Bank expectedReservation = new Bank(transactionId, "BANK", "anh@gmail.com", 500.0, 2, propertyId);
        when(bankRepository.findById(transactionId)).thenReturn(Optional.of(expectedReservation));
        assertThat(bankService.getById(transactionId)).isEqualTo(expectedReservation);

    }

    @Test
    void should_throw_bank_exception() {
        UUID transactionId = UUID.randomUUID();
        when(bankRepository.findById(transactionId)).thenReturn(Optional.ofNullable(null));
        assertThatThrownBy(() -> bankService.getById(transactionId))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cannot find transaction bank with id: " + transactionId);

    }
}
