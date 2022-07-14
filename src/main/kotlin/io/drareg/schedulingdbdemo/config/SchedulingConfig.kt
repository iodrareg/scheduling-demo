package io.drareg.schedulingdbdemo.config

import com.github.kagkarlsson.scheduler.task.ExecutionContext
import com.github.kagkarlsson.scheduler.task.FailureHandler
import com.github.kagkarlsson.scheduler.task.Task
import com.github.kagkarlsson.scheduler.task.TaskInstance
import com.github.kagkarlsson.scheduler.task.helper.OneTimeTask
import com.github.kagkarlsson.scheduler.task.helper.Tasks
import com.github.kagkarlsson.scheduler.task.schedule.Schedules.*
import io.drareg.schedulingdbdemo.components.RandomJob
import io.drareg.schedulingdbdemo.components.RandomJobFailureHandler
import io.drareg.schedulingdbdemo.model.TaskData
import mu.KotlinLogging.logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


@Configuration
class SchedulingConfig(val randomJob: RandomJob) {

    private val logger = logger {}

    @Bean
    fun recurringSampleTask(): Task<Void?>? {
        return Tasks
            .recurring("recurring-sample-task", fixedDelay(Duration.ofMinutes(1)))
//            .recurring("recurring-sample-task", cron("*/60 * * * * *"))
//            .recurring("recurring-sample-task", daily(ZoneId.of("ECT"), LocalTime.ofInstant(Instant.now().plusMillis(86400000L), ZoneId.of("ECT"))))
            .execute { instance: TaskInstance<Void?>?, ctx: ExecutionContext? ->
                logger.info("Running recurring-simple-task. Instance: {}, ctx: {}", instance, ctx)
            }
    }

    @Bean
    fun randomOneTimeTask(): OneTimeTask<TaskData>? {
        return Tasks.oneTime("random-job-1", TaskData::class.java)
            .onFailure(FailureHandler.MaxRetriesFailureHandler(3, RandomJobFailureHandler()))
            .execute { taskInstance, _ ->
                run {
                    randomJob.randomJobWithSleeps(taskInstance)
                }
            }
    }

}
