package io.drareg.schedulingdbdemo.rest

import io.drareg.schedulingdbdemo.service.SchedulingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ScheduleController(val schedulingService: SchedulingService) {

    @GetMapping("/startSchedule/{delay}")
    fun startSchedule(@PathVariable delay: Long) {
        schedulingService.startTask(delay)
    }

}
