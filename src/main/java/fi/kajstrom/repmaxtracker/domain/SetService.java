package fi.kajstrom.repmaxtracker.domain;

import fi.kajstrom.repmaxtracker.domain.exception.UserNotFoundException;
import fi.kajstrom.repmaxtracker.infrastructure.database.SetGateway;
import fi.kajstrom.repmaxtracker.infrastructure.database.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("setService")
public class SetService {

    private SetGateway setGateway;
    private UserGateway userGateway;

    @Autowired
    public SetService(SetGateway setGateway, UserGateway userGateway) {
        this.setGateway = setGateway;
        this.userGateway = userGateway;
    }

    public Integer addSet(long exerciseId, long userId, Date performedOn, Double weight, Integer repetitions) {
        Set set = new Set();
        Double estimated1Rm = calculate1Rm(weight, repetitions);

        set.setExerciseId(exerciseId)
                .setUserId(userId)
                .setPerformedOn(performedOn)
                .setWeight(weight)
                .setRepetitions(repetitions)
                .setEstimated1Rm(estimated1Rm);

        return setGateway.addSet(set);
    }

    public List<Set> allSets() {
        return setGateway.allSets();
    }

    public List<Set> getUserSets(long userId) throws UserNotFoundException{
        guardAgainstMissingUsers(userId);

        return setGateway.getUserSets(userId);
    }

    public Set getSet(long setId) {
        return setGateway.getSet(setId);
    }

    public Boolean deleteUserSet(long userId, long setId) throws UserNotFoundException {
        guardAgainstMissingUsers(userId);

        return setGateway.deleteUserSet(userId, setId);
    }

    private Double calculate1Rm(Double weight, Integer repetitions) {
        if (repetitions.equals(1)) {
            return weight;
        }

        return (weight * repetitions * 0.0333) + weight;
    }

    private void guardAgainstMissingUsers(long userId) throws UserNotFoundException {
        if (userGateway.userExists(userId) == false) {
            throw new UserNotFoundException(String.format("No user found with user id: %s.", userId));
        }
    }
}
