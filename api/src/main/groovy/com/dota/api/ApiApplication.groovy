package com.dota.api

import com.dota.api.Handler.RestExceptionHandler
import com.dota.api.Heroes.HeroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
@Import(RestExceptionHandler)
class ApiApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate

	static void main(String[] args) {
		SpringApplication.run(ApiApplication, args)
	}

}
