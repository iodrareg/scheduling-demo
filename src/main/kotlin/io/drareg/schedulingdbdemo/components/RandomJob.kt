package io.drareg.schedulingdbdemo.components

import com.github.kagkarlsson.scheduler.task.TaskInstance
import io.drareg.schedulingdbdemo.model.TaskData
import mu.KotlinLogging.logger
import org.springframework.stereotype.Component

@Component
class RandomJob {

    private val logger = logger {}

    fun randomJobWithSleeps(taskInstance: TaskInstance<TaskData>) {
        logger.info("Starting random job with instance $taskInstance with random sleeps (${taskInstance.data.sleeps} sleeps)...")
        Thread.sleep(5000L)
        for(i in taskInstance.data.sleeps downTo 0) {
            logger.info("Finishing task in $i...")
            Thread.sleep(1000)
        }
        logger.info("Task finished")
    }

}
