package com.swiftylions.SLStore.controller;

import com.swiftylions.SLStore.dto.UserDto;
import jakarta.validation.constraints.Size;
import org.apache.catalina.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/dummy")
@Validated
public class DummyController {

    //RequestBody: means http body without header & etc.
    @PostMapping("/create-user")
    public String createUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return "user created successfully";
    }

    //RequestEntity: **IT IS NOT ANNOTATION**
    @PostMapping("/request-entity")
    public String createUserWithEntity(RequestEntity<UserDto> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        UserDto userDto = requestEntity.getBody();
        String queryString = requestEntity.getUrl().getQuery(); //for reading format of requestParam: smth?smth=smth&smth=smth
        String pathString = requestEntity.getUrl().getPath(); //for reading format of pathVariable: smth/smth/smth
        return "user created successfully";
    }

    //Headers
    @GetMapping("/headers")
    public String readHeader(@RequestHeader(name = "user-agent") String userAgent, @RequestHeader(name = "user-location", required = false) String userLocation) {
        return "Received header with value: " + userAgent + " and location: " + userLocation;
    }

    //Headers using Map
    @GetMapping("/headers/map")
    public String readHeaderUsingMap(@RequestHeader Map<String, String> headersValue) {
        return "Received header with value: " + headersValue.toString();
    }

    //Headers using HttpHeaders inside Spring Framework Http (flexible to add header too)
    @GetMapping("/headers/http")
    public String readHeaderUsingMap(@RequestHeader HttpHeaders headersValue) {
        //headersValue.forEach((key, value) -> System.out.println(key + ": " + value));
        headersValue.add("user-location", "MamadAbad");
        List<String> location = headersValue.get("user-loacation");
        return "Received header with value: " + headersValue.toString();
    }

    //RequestParam format: /smth?smth=smth&smth=smth (as variable)
    @GetMapping("/search")
    public String searchUser(@Size(min = 3,max = 30,message = "Name must be in the range of 3 to 30 characters") @RequestParam(required = false, defaultValue = "Guest", name = "name") String userName) {
        return "Searching for user: " + userName;
    }
//    RequestParam format: /smth?smth=smth&smth=smth(as single values)
//    @GetMapping("/multiple-search")
//    public String multipleSearch(@RequestParam String firstName, @RequestParam String lastName) {
//        return "Searching for user: " + firstName + " " + lastName;
//    }

    //RequestParam format: /smth?smth=smth&smth=smth(as array)
    @GetMapping("/multiple-search")
    public String multipleSearch(@RequestParam Map<String, String> Params) {
        return "Searching for user: " + Params.get("firstName") + " " + Params.get("lastName") + " " + Params.get("koskesh");
    }

    //PathVariable format: /user/id/post/id (cant do a default value like RequestParam)
    @GetMapping({"/user/{userId}/post/{postId}", "/user/{userId}"})
    public String getUser(@PathVariable(name = "userId") String id, @PathVariable(required = false) String postId) {
        return "Searching for user: " + id + " and post: " + postId;
    }

    //PathVariable using map format: /user/id/post/id
    @GetMapping({"/user/map/{userId}/post/{postId}", "/user/map/{userId}"})
    public String getUserUsingMap(@PathVariable Map<String, String> pathVariables) {
        return "Searching for user: " + pathVariables.get("userId") + " and post: " + pathVariables.get("postId");
    }

}
