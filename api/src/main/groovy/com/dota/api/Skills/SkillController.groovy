package com.dota.api.Skills

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/skills")
class SkillController {

    @GetMapping
    ResponseEntity getSkills() {

    }


    @PostMapping
    ResponseEntity createSkill() {

    }


    @PutMapping("/{id}")
    ResponseEntity editSkill(@PathVariable Integer id) {

    }


    @DeleteMapping("/{id}")
    ResponseEntity deleteSkills(@PathVariable Integer id) {

    }
}
