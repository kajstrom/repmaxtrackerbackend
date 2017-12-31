package fi.kajstrom.repmaxtracker.unit.domain;

import fi.kajstrom.repmaxtracker.domain.Set;
import fi.kajstrom.repmaxtracker.domain.SetService;
import fi.kajstrom.repmaxtracker.infrastructure.database.SetGateway;
import fi.kajstrom.repmaxtracker.infrastructure.database.UserGateway;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SetServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<Set> captor;

    @Test
    public void addSetWith100At5RepsShouldCalculateCorrect1Rm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 23);

        SetGateway setGateway = mock(SetGateway.class);
        UserGateway userGateway = mock(UserGateway.class);
        SetService setService = new SetService(setGateway, userGateway);

        setService.addSet(1, 1, cal.getTime(), 100.0, 5);

        verify(setGateway).addSet(captor.capture());

        Set set = captor.getValue();

        assertThat(set.getEstimated1Rm()).isCloseTo(116.65, within(.01));
    }

    @Test
    public void addSetWith5RepsAt107Point5ShouldCalculateCorrect1Rm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 23);

        SetGateway setGateway = mock(SetGateway.class);
        UserGateway userGateway = mock(UserGateway.class);
        SetService setService = new SetService(setGateway, userGateway);

        setService.addSet(1, 1, cal.getTime(), 107.5, 5);

        verify(setGateway).addSet(captor.capture());

        Set set = captor.getValue();

        assertThat(set.getEstimated1Rm()).isCloseTo(125.39, within(.01));
    }

    @Test
    public void addSetWith1RepsAt115ShouldCalculateCorrect1Rm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 23);

        SetGateway setGateway = mock(SetGateway.class);
        UserGateway userGateway = mock(UserGateway.class);
        SetService setService = new SetService(setGateway, userGateway);

        setService.addSet(1, 1, cal.getTime(), 115.0, 1);

        verify(setGateway).addSet(captor.capture());

        Set set = captor.getValue();

        assertThat(set.getEstimated1Rm()).isCloseTo(115.0, within(.01));
    }
}
