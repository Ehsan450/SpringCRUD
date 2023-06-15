package io.github.hossensyedriadh.springcrud.restcontroller;

import io.github.hossensyedriadh.springcrud.service.OpenService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/open", produces = {MediaType.APPLICATION_JSON_VALUE})
public class OpenController {
    private final OpenService openService;

    @Autowired
    public OpenController(OpenService openService) {
        this.openService = openService;
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable("username") String username) {
        boolean isUnique = this.openService.isUsernameUnique(username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isUnique", isUnique);

        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
