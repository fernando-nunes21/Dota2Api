package com.dota.api.Skins

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/skins")
class SkinController {

    @GetMapping
    ResponseEntity getSkins() {

    }


    @PostMapping
    ResponseEntity createSkin() {

    }

    @PutMapping("/{id}")
    ResponseEntity editSkin(@PathVariable Integer id) {

    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteSkin(@PathVariable Integer id) {

    }
}
