package org.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Build API on client side which matches to API on server side
//and can wire across that boundary

@FeignClient("roomservices")  // should match to Eureka service name
public interface RoomClient {

    @GetMapping("/rooms")
    List<Room> getAllRooms();

    @GetMapping("/rooms/{id}")
    Room getRoom(@PathVariable("id") long id);

}
