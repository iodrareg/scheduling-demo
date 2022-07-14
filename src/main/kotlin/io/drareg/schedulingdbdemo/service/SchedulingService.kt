package io.drareg.schedulingdbdemo.service

import com.github.kagkarlsson.scheduler.Scheduler
import com.github.kagkarlsson.scheduler.task.helper.OneTimeTask
import io.drareg.schedulingdbdemo.model.TaskData
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class SchedulingService(private val scheduler: Scheduler, private val randomOneTimeTask: OneTimeTask<TaskData>) {

    fun startTask(delayInSeconds: Long) {
        scheduler.schedule(
            randomOneTimeTask.instance(
                UUID.randomUUID().toString(),
                TaskData(10)), Instant.now().plusSeconds(delayInSeconds)
        )
    }

}
