package com.cespinosab.loanapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

/**
 * Test suit for {@link LoanApiApp}
 */
@ExtendWith(MockitoExtension.class)
public class LoanApiAppTest {

    @Test
    public void testMain() {
        try (MockedStatic<SpringApplication> springApplicationMock = mockStatic(SpringApplication.class)) {
            ConfigurableApplicationContext mockContext = mock(ConfigurableApplicationContext.class);
            springApplicationMock.when(() -> SpringApplication.run(LoanApiApp.class, new String[] {}))
                    .thenReturn(mockContext);

            LoanApiApp.main(new String[] {});

            springApplicationMock.verify(() -> SpringApplication.run(LoanApiApp.class, new String[] {}));
        }
    }
}
