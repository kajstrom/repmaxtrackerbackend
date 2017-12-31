package fi.kajstrom.repmaxtracker.resources;

import fi.kajstrom.repmaxtracker.domain.Set;
import fi.kajstrom.repmaxtracker.domain.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{userId}/sets")
public class UserSetsController {
    @Autowired
    private SetService setService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SetResource> getUserSets(@PathVariable(value="userId") String reqUserId) throws Exception {
        Long userId = Long.parseLong(reqUserId);

        return setService.getUserSets(userId)
                .stream()
                .map(SetResource::from)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{setId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsersSet(
            @PathVariable(value="userId") String reqUserId,
            @PathVariable(value="setId") String reqSetId
    ) throws Exception{
        Long userId = Long.parseLong(reqUserId);
        Long setId = Long.parseLong(reqSetId);


        Boolean deleted = setService.deleteUserSet(userId, setId);

        if (deleted == false) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
